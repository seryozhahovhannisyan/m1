package com.connectto.wallet.merchant.web.action.wallet;

import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.encryption.WalletEncription;
import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.business.merchant.ITransactionManager;
import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionTaxType;
import com.connectto.wallet.merchant.common.data.transaction.deposit.*;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.*;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Generator;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.dto.TransactionDto;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import org.json.simple.JSONObject;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 12/23/2016.
 */
public class TransactionDepositAction extends TransactionBaseAction {

    private IWalletManager walletManager;
    private ICompanyManager companyManager;

    private ITransactionManager transactionManager;
    //
    private String walletId;

    private String orderCode;

    private String depositId;

    public String depositStart() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        try {

            Wallet wallet = walletManager.getById(DataConverter.convertToLong(walletId));
            if(wallet.getIsLocked()){
                writeLog(TransactionAction.class.getSimpleName(), new PermissionDeniedException("Wallet already locked id="+walletId), LogLevel.ERROR, LogAction.READ, null);
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                dto.addFieldError("walletId",getText("wallet.back.end.message.error.locked.wallet"));
                return SUCCESS;
            }

            Company company = companyManager.getById(companyId);

            dto.addResponse("wallet", wallet);
            dto.addResponse("cashier", cashier);
            dto.addResponse("company", company);

            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }

        return SUCCESS;
    }

    public String depositCheckTax() {

        boolean decript = true;
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();
        Role role = cashier.getRole();
        Long companyId = cashier.getCompanyId();

        try {

            Wallet wallet = walletManager.getById(DataConverter.convertToLong(walletId));
            if(wallet.getIsLocked()){
                writeLog(TransactionAction.class.getSimpleName(), new PermissionDeniedException("Wallet already locked id="+walletId), LogLevel.ERROR, LogAction.READ, null);
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                dto.addFieldError("walletId",getText("wallet.back.end.message.error.locked.wallet"));
                return SUCCESS;
            }

            CompanyCashBox companyCashBox = companyManager.getById(companyId).getCurrentCashBox();

            if (!convertAmountAndCurrency(false)) {
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            if (!convertRationalDuration()) {
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            if (!isAmountAllowed(role)) {
                //todo notifictionManager put transaction information
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                return SUCCESS;
            }

            if (!isCurrencyTypeAllowed(role, cashBox)) {
                String msg = getText("wallet.back.end.message.empty.currencyType");
                dto.addFieldError("currencyType", msg);
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                return SUCCESS;
            }

            TransactionDeposit transaction = createTransaction(productAmount, productCurrencyType, wallet, companyCashBox, cashBox, TransactionState.PREPARE, decript);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(transaction);
            dto.addResponse("transactionDto", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (PermissionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
        } catch (EncryptException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (HttpConnectionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (WalletApiException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        }

        return SUCCESS;
    }

    public String depositMakePayment() {

        boolean decript = true;
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();
        Role role = cashier.getRole();
        Long companyId = cashier.getCompanyId();

        try {

            Wallet wallet = walletManager.getById(DataConverter.convertToLong(walletId));
            if(wallet.getIsLocked()){
                writeLog(TransactionAction.class.getSimpleName(), new PermissionDeniedException("Wallet already locked id="+walletId), LogLevel.ERROR, LogAction.READ, null);
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                dto.addFieldError("walletId",getText("wallet.back.end.message.error.locked.wallet"));
                return SUCCESS;
            }
            CompanyCashBox companyCashBox = companyManager.getById(companyId).getCurrentCashBox();

            if (!convertAmountAndCurrency(false)) {
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            if (!convertRationalDuration()) {
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            if (!isAmountAllowed(role)) {
                //todo notifictionManager put transaction information
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                return SUCCESS;
            }

            if (!isCurrencyTypeAllowed(role, cashBox)) {
                String msg = getText("wallet.back.end.message.empty.currencyType");
                dto.addFieldError("currencyType", msg);
                dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
                return SUCCESS;
            }

            TransactionDeposit transaction = createTransaction(productAmount, productCurrencyType, wallet, companyCashBox, cashBox, TransactionState.PENDING, decript);
            transactionManager.add(transaction, decript);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(transaction);
            dto.addResponse("transactionDto", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
            int pC = cashier.getPendingCount();
            pC++;
            cashier.setPendingCount(pC);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (PermissionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
        } catch (EncryptException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (HttpConnectionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (WalletApiException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        }

        return SUCCESS;
    }

    public String depositTimeOut() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Long depositID = DataConverter.convertToLong(depositId);

            if (Utils.isEmpty(orderCode)) {
                String msg = getText("wallet.back.end.message.empty.orderCode");
                dto.addFieldError("orderCode", msg);
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cashBoxId", cashBox.getId());
            params.put("walletId", walletId);
            params.put("orderCode", orderCode);
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));

            TransactionDeposit transaction = transactionManager.timeOutedDeposit(params);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(transaction);
            dto.addResponse("transactionDto", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
            int pC = cashier.getPendingCount();
            pC++;
            cashier.setPendingCount(pC);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (HttpConnectionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (WalletApiException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.WALLET_DENIED);
        } catch (PermissionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }

        return SUCCESS;
    }

    public String depositCanceledByWallet() {

        try {

            Long depositID = DataConverter.convertToLong(depositId);

            if (Utils.isEmpty(orderCode)) {
                String msg = getText("wallet.back.end.message.empty.orderCode");
                dto.addFieldError("orderCode", msg);
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("walletId", walletId);
            params.put("orderCode", orderCode);
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));

            TransactionDeposit transaction = transactionManager.canceledDepositByWallet(params);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(transaction);
            dto.addResponse("transactionDto", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (PermissionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }

        return SUCCESS;
    }

    public String depositApprovedByWallet() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Long depositID = DataConverter.convertToLong(depositId);

            if (Utils.isEmpty(orderCode)) {
                String msg = getText("wallet.back.end.message.empty.orderCode");
                dto.addFieldError("orderCode", msg);
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cashBoxId", cashBox.getId());
            params.put("walletId", walletId);
            params.put("orderCode", orderCode);
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));

            TransactionDeposit transaction = transactionManager.approvedDepositByWallet(params);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(transaction);
            dto.addResponse("transactionDto", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
            int pC = cashier.getPendingCount();
            pC++;
            cashier.setPendingCount(pC);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (PermissionDeniedException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }

        return SUCCESS;
    }



    private synchronized TransactionDeposit createTransaction(Double depositAmount, CurrencyType depositAmountCurrencyType,
                                                               Wallet wallet, CompanyCashBox companyCashBox, CashierCashBox cashBox, TransactionState transactionState, boolean decript)
            throws PermissionDeniedException, InvalidParameterException, InternalErrorException, EncryptException, WalletApiException, HttpConnectionDeniedException {


        String msgUnsupported = getText("wallet.back.end.message.unsupported.currency") + "\t";

        //<editor-fold desc="initBlock">
        Date currentDate = new Date(System.currentTimeMillis());
        String orderCode = Generator.get(6, Generator.Type.ALPHABETIC_DIGIT);


        Long walletId = wallet.getId();
        Long cashBoxId = cashBox.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType cashBoxCurrencyType = cashBox.getCurrencyType();


        int walletCurrencyTypeId = walletCurrencyType.getId();
        int cashBoxCurrencyTypeId = cashBoxCurrencyType.getId();
        int depositCurrencyTypeId = depositAmountCurrencyType.getId();

        //MerchantDeposit merchantDeposit = initMerchantDeposit(currentDate, walletId, walletSetupId);
        // TODO: 12/23/2016  create on wallet corresponding viewAction for merchant's checkTaxAction
        WalletDeposit walletDeposit = new WalletDeposit();//initWalletDeposit(currentDate, walletId, cashBoxId);//

        TransactionDeposit transactionDeposit = new TransactionDeposit();
        transactionDeposit.setWallet(wallet);
        transactionDeposit.setDepositAmount(depositAmount);
        transactionDeposit.setDepositAmountCurrencyType(depositAmountCurrencyType);
        //transactionDeposit.setDepositMerchantTotalTax(merchantDeposit.getMerchantDepositTax().getDepositTaxTotal());
        //transactionDeposit.setDepositWalletSetupTotalTax(walletDeposit.getWalletDepositTax().getPaidTaxToWalletSetupPrice());
        //transactionDeposit.setDepositWalletSetupTotalTaxCurrencyType(depositAmountCurrencyType);
        transactionDeposit.setCashierTotalAmountCurrencyType(cashBoxCurrencyType);
        transactionDeposit.setOpenedAt(currentDate);
        transactionDeposit.setCashierCashBoxId(cashBoxId);
        transactionDeposit.setWalletId(walletId);
        transactionDeposit.setFinalState(transactionState);
        transactionDeposit.setWalletDeposit(walletDeposit);
        transactionDeposit.setRationalDuration(productRationalDuration);
        transactionDeposit.setOrderCode(orderCode);
        transactionDeposit.setIsEncoded(decript);


        //</editor-fold>

        if (depositCurrencyTypeId == cashBoxCurrencyTypeId) {
            if (depositCurrencyTypeId == walletCurrencyTypeId) {
                simpleTransactionDepositWithSameCurrencies(transactionDeposit, wallet, companyCashBox, cashBox, depositAmount, currentDate, transactionState, decript);
            } else {
                //otherWalletCurrency(transactionDeposit, wallet, cashBox, depositAmount, currentDate, transactionState);
            }
        } else {
            throw new PermissionDeniedException(msgUnsupported + depositAmountCurrencyType);
            //<editor-fold desc="elseBlock">


            //</editor-fold>
        }
        return transactionDeposit;
    }

    private synchronized void simpleTransactionDepositWithSameCurrencies(TransactionDeposit transactionDeposit,
                                                                          Wallet wallet,
                                                                          CompanyCashBox companyCashBox,
                                                                          CashierCashBox cashBox,
                                                                          Double depositAmount,
                                                                          Date currentDate,
                                                                          TransactionState transactionState, boolean decript) throws InternalErrorException, EncryptException, WalletApiException, HttpConnectionDeniedException {

        Long walletUserId = wallet.getUserId();
        Long walletId = wallet.getId();
        Long cashBoxId = cashBox.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType cashBoxCurrencyType = cashBox.getCurrencyType();

        Map<String, Object> depositTaxTypeMap = calculateDepositTax(companyCashBox, depositAmount);
        TransactionTaxType depositTaxType = (TransactionTaxType) depositTaxTypeMap.get(TAX_TYPE_KEY);
        Double depositTaxAmount = (Double) depositTaxTypeMap.get(TAX_KEY);

        WalletDeposit walletDeposit = initWalletDeposit(PartitionLCP.valueOf(wallet.getPartitionId()).getHost(), transactionDeposit.getOrderCode(), currentDate, walletUserId, depositTaxAmount.toString(), "" + walletCurrencyType.getId(), "" + depositTaxType.getId(), decript);
        WalletDepositTax walletDepositTax = walletDeposit.getWalletDepositTax();

        Double cashierTotal = depositAmount - depositTaxAmount;// + receiverTaxAmount;
        Double walletTotalPrice = walletDepositTax.getPaidTaxToWalletSetupPrice();

        TransactionDepositProcessTax processTax = new TransactionDepositProcessTax(currentDate, walletId, cashBoxId, depositTaxAmount, walletCurrencyType, depositTaxType);
        TransactionDepositProcess depositProcess = new TransactionDepositProcess(transactionState, currentDate, walletId, cashBoxId, depositAmount, walletCurrencyType, processTax);

        TransactionDepositTax transactionDepositTax = new TransactionDepositTax(currentDate, walletId, cashBoxId, processTax, walletDepositTax);

        transactionDeposit.setProcessStart(depositProcess);
        transactionDeposit.setTax(transactionDepositTax);

        transactionDeposit.setWalletTotalPrice(walletTotalPrice);
        transactionDeposit.setWalletTotalPriceCurrencyType(walletCurrencyType);

        transactionDeposit.setCashierTotalAmount(cashierTotal);
        transactionDeposit.setCashierTotalAmountCurrencyType(cashBoxCurrencyType);

        transactionDeposit.setDepositCashierCashBoxTotalTax(depositTaxAmount);
        transactionDeposit.setDepositCashierCashBoxTotalTaxCurrencyType(cashBoxCurrencyType);

        transactionDeposit.setWalletDeposit(walletDeposit);
        transactionDeposit.setCashierCashBox(cashBox);

    }

    private WalletDeposit initWalletDeposit(String host, String orderCode, Date actionDate, Long userId, String tax, String taxCurrencyType, String taxType, boolean decript) throws EncryptException, WalletApiException, HttpConnectionDeniedException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", WITHDRAW_NAME);
        jsonObject.put("description", WITHDRAW_DESC);
        jsonObject.put("orderCode", orderCode);

        jsonObject.put("userId", userId);
        jsonObject.put("itemId", -1);
        jsonObject.put("amount", WalletEncription.encrypt(productAmount.toString()));
        jsonObject.put("currencyType", WalletEncription.encrypt("" + productCurrencyType.getId()));
        jsonObject.put("tax", WalletEncription.encrypt(tax));
        jsonObject.put("taxCurrencyType", WalletEncription.encrypt(taxCurrencyType));
        jsonObject.put("taxType", WalletEncription.encrypt(taxType));
        jsonObject.put("rationalDuration", WalletEncription.encrypt(rationalDuration));

        WalletDepositTax walletDepositTax = HttpURLBaseConnection.walletPreviewDepositAction(host, jsonObject, decript);

        WalletDeposit walletDeposit = new WalletDeposit();
        walletDeposit.setItemId(walletDepositTax.getId());
        walletDeposit.setName(WITHDRAW_NAME);
        walletDeposit.setStartAt(actionDate);
        walletDeposit.setEndAt(Utils.getAfterSecunds(actionDate, productRationalDuration.getDuration()));

        walletDeposit.setDescription(WITHDRAW_DESC);
        walletDeposit.setWalletDepositTax(walletDepositTax);

        return walletDeposit;
    }


    /*
    * #################################################################################################################
    * ########################################        GETTER & SETTER       ###########################################
    * #################################################################################################################
    */

    public ResponseDto getDto() {
        return dto;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public void setWalletManager(IWalletManager walletManager) {
        this.walletManager = walletManager;
    }

    public void setCompanyManager(ICompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    public void setTransactionManager(ITransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
