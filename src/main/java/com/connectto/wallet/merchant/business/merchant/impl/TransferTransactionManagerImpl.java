package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.encryption.WalletEncription;
import com.connectto.wallet.merchant.business.merchant.ITransferTransactionManager;
import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTicket;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransactionWalletResult;
import com.connectto.wallet.merchant.common.exception.*;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.connectto.wallet.merchant.web.action.wallet.TransactionBaseAction.M_TRANSFER_NAME;
import static com.connectto.wallet.merchant.web.action.wallet.TransactionBaseAction.M_TRANSFER_DESC;

/**
 * Created by Serozh on 2/14/2017.
 */
@Transactional(readOnly = true)
public class TransferTransactionManagerImpl implements ITransferTransactionManager {

    private static final Logger logger = Logger.getLogger(TransferTransactionManagerImpl.class.getSimpleName());

    private ICompanyDao companyDao;
    private ICompanyCashBoxDao companyCashBoxDao;

    private ITransferTransactionDao dao;
    private ITransferTransactionWalletResultDao walletResultDao;
    private ITransferTicketDao ticketDao;

    public void setCompanyDao(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setCompanyCashBoxDao(ICompanyCashBoxDao companyCashBoxDao) {
        this.companyCashBoxDao = companyCashBoxDao;
    }

    public void setDao(ITransferTransactionDao dao) {
        this.dao = dao;
    }

    public void setTicketDao(ITransferTicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void setWalletResultDao(ITransferTransactionWalletResultDao walletResultDao) {
        this.walletResultDao = walletResultDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(TransferTransaction data) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException, HttpConnectionDeniedException, WalletApiException {

        TransferTicket ticket = data.getTransferTicket();
        Long walletId = ticket.getWalletId();
        int partitionId = data.getPartitionId();

        Map<String, Object> signInParams = new HashMap<String, Object>();
        signInParams.put("secretKey", ticket.getSecretKey());
        signInParams.put("clientKey", ticket.getClientKey());

        try {

            Company company = companyDao.signIn(signInParams);

            data.setCompanyId(company.getId());
            CompanyCashBox companyCashBox = company.getCurrentCashBox();
            Long companyCashBoxId = companyCashBox.getId();

            Double dataTransferAmount = data.getTransferAmount();

            Double balanceProvidedByMerchant = companyCashBox.getBalanceProvidedByMerchant();
            balanceProvidedByMerchant -= dataTransferAmount;

            if(balanceProvidedByMerchant < 0){
                throw new PermissionDeniedException("The company cannot transfer so many money");
            }

            CompanyCashBox updateWalletSetup = new CompanyCashBox();
            updateWalletSetup.setId(companyCashBoxId);
            updateWalletSetup.setBalanceProvidedByMerchant(balanceProvidedByMerchant);

            companyCashBoxDao.update(updateWalletSetup);

            data.setCompanyCashBoxId(companyCashBoxId);
            data.setWalletId(walletId);

            ticketDao.add(ticket);
            data.setTransferTicketId(ticket.getId());

            dao.add(data);

            JSONObject preparedJSONObject = prepareJSONObject(data, data.getIsEncoded());
            TransferTransactionWalletResult walletResult = HttpURLBaseConnection.transferFromMerchantToUser(PartitionLCP.valueOf(partitionId).getHost(), preparedJSONObject, data.getIsEncoded());

            try {
                walletResultDao.add(walletResult);
                data.setWalletResultId(walletResult.getId());
                dao.markTransaction(data);
            } catch (DataException e) {
                logger.error(e);
            }

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EncryptException e) {
            throw new InternalErrorException(e);
        }
    }

    private JSONObject prepareJSONObject(TransferTransaction data, boolean encrypt) throws EncryptException {

        TransferTicket transferTicket = data.getTransferTicket();
        String amount = data.getTransferAmount().toString();
        String amountCurrencyType = "" + data.getTransferAmountCurrencyType().getId();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("merchantCompanyId", data.getCompanyId());
        jsonObject.put("merchantTransactionId", data.getId());

        jsonObject.put("name", M_TRANSFER_NAME);
        jsonObject.put("description", M_TRANSFER_DESC);

        jsonObject.put("walletId", data.getWalletId());

        jsonObject.put("amount", encrypt ? WalletEncription.encrypt(amount) : amount);
        jsonObject.put("currencyType",encrypt ? WalletEncription.encrypt(amountCurrencyType) : amountCurrencyType);

        jsonObject.put("secretKey", transferTicket.getSecretKey());
        jsonObject.put("clientKey", transferTicket.getClientKey());

        return jsonObject;
    }
}
