package com.connectto.wallet.merchant.web.action.wallet;


import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.merchant.business.merchant.ITransferTransactionManager;
import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTicket;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.*;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 11/17/2016.
 */
public class TransferTransactionAction extends TransactionBaseAction {

    private static final Logger logger = Logger.getLogger(TransferTransactionAction.class.getSimpleName());

    private ITransferTransactionManager transferTransactionManager;
    private IWalletManager walletManager;

    //TransferTicket
    private String itemId;
    private String name;
    private String description;
    //
    private String walletId;
    //
    private String secretKey;
    private String clientKey;
    private String companyId;

    public String transferFromMerchantToUser() {
        
        boolean decripted = true;

        TransferTransaction transferTransaction = new TransferTransaction();
        transferTransaction.setActionDate(new Date(System.currentTimeMillis()));
        transferTransaction.setIsEncoded(decripted);

        try {

            if(!convertAmountAndCurrency(decripted)){
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            Wallet wallet = walletManager.getById(DataConverter.convertToLong(walletId));
            CurrencyType currencyType = wallet.getCurrencyType();

            if(currencyType.getId() != productCurrencyType.getId()){
                dto.setActionerror(getText("wallet.back.end.message.unsupported.currency"));
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                return SUCCESS;
            }

            transferTransaction.setTransferAmount(productAmount);
            transferTransaction.setTransferAmountCurrencyType(productCurrencyType);

            TransferTicket ticket = initTransferTicket(decripted);
            transferTransaction.setTransferTicket(ticket);
            transferTransaction.setWalletId(ticket.getWalletId());

            transferTransaction.setPartitionId(wallet.getPartitionId());
            transferTransactionManager.add(transferTransaction);

            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidParameterException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        } catch (EncryptException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        } catch (EntityNotFoundException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.DATA_NOT_FOUND);
        } catch (InternalErrorException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (PermissionDeniedException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
        } catch (HttpConnectionDeniedException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (WalletApiException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.INSERT, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (DataParseException e) {
            writeLog(TransferTransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            logger.error(e);
            dto.setActionerror(e.getMessage());
            dto.setResponseStatus(ResponseStatus.DATA_NOT_FOUND);
        }


        return SUCCESS;
    }

    private synchronized TransferTicket initTransferTicket(boolean decripted) throws InvalidParameterException, EncryptException, NumberFormatException {

        if (Utils.isEmpty(itemId)) {
            throw new InvalidParameterException("Purchase itemId is empty");
        }

        if (Utils.isEmpty(name)) {
            throw new InvalidParameterException("Purchase name is empty");
        }

        if (Utils.isEmpty(description)) {
            throw new InvalidParameterException("Purchase description is empty");
        }

        if (Utils.isEmpty(walletId)) {
            throw new InvalidParameterException("Purchase walletId is empty");
        }

        if (Utils.isEmpty(secretKey)) {
            throw new InvalidParameterException("Purchase secretKey is empty");
        }

        if (Utils.isEmpty(clientKey)) {
            throw new InvalidParameterException("Purchase clientKey is empty");
        }  

        if (Utils.isEmpty(companyId)) {
            throw new InvalidParameterException("Purchase companyId is empty");
        }


        TransferTicket ticket = new TransferTicket();
        ticket.setItemId(Long.parseLong(itemId));
        ticket.setName(name);
        ticket.setDescription(description);

        ticket.setSecretKey(secretKey);
        ticket.setClientKey(clientKey);

        ticket.setTsmCompanyId(Long.parseLong(companyId));
        ticket.setWalletId(Long.parseLong(walletId));

        return ticket;
    }

    /*
     * #################################################################################################################
     * ########################################        GETTER & SETTER       ###########################################
     * #################################################################################################################
     */

    public ResponseDto getDto() {
        return dto;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTransferTransactionManager(ITransferTransactionManager transferTransactionManager) {
        this.transferTransactionManager = transferTransactionManager;
    }

    public void setWalletManager(IWalletManager walletManager) {
        this.walletManager = walletManager;
    }
}
