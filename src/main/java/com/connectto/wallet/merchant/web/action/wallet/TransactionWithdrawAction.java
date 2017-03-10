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
import com.connectto.wallet.merchant.common.data.transaction.withdraw.*;
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
public class TransactionWithdrawAction extends TransactionBaseAction {

    private IWalletManager walletManager;
    private ICompanyManager companyManager;

    private ITransactionManager transactionManager;
    //
    private String walletId;

    private String orderCode;

    private String withdrawId;

    public String withdrawStart() {

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

    public String withdrawCheckTax() {

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

            TransactionWithdraw transaction = createTransaction(productAmount, productCurrencyType, wallet, companyCashBox, cashBox, TransactionState.PREPARE, decript);
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

    public String withdrawMakePayment() {

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

            TransactionWithdraw transaction = createTransaction(productAmount, productCurrencyType, wallet, companyCashBox, cashBox, TransactionState.PENDING, decript);
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

    public String withdrawTimeOut() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Long withdrawID = DataConverter.convertToLong(withdrawId);

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

            TransactionWithdraw transaction = transactionManager.timeOutedWithdraw(params);
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

    public String withdrawCanceledByWallet() {

        try {

            Long withdrawID = DataConverter.convertToLong(withdrawId);

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

            TransactionWithdraw transaction = transactionManager.canceledWithdrawByWallet(params);
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

    public String withdrawApprovedByWallet() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Long withdrawID = DataConverter.convertToLong(withdrawId);

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

            TransactionWithdraw transaction = transactionManager.approvedWithdrawByWallet(params);
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



    private synchronized TransactionWithdraw createTransaction(Double withdrawAmount, CurrencyType withdrawAmountCurrencyType,
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
        int withdrawCurrencyTypeId = withdrawAmountCurrencyType.getId();

        //MerchantWithdraw merchantWithdraw = initMerchantWithdraw(currentDate, walletId, walletSetupId);
        // TODO: 12/23/2016  create on wallet corresponding viewAction for merchant's checkTaxAction
        WalletWithdraw walletWithdraw = new WalletWithdraw();//initWalletWithdraw(currentDate, walletId, cashBoxId);//

        TransactionWithdraw transactionWithdraw = new TransactionWithdraw();
        transactionWithdraw.setWallet(wallet);
        transactionWithdraw.setWithdrawAmount(withdrawAmount);
        transactionWithdraw.setWithdrawAmountCurrencyType(withdrawAmountCurrencyType);
        //transactionWithdraw.setWithdrawMerchantTotalTax(merchantWithdraw.getMerchantWithdrawTax().getWithdrawTaxTotal());
        //transactionWithdraw.setWithdrawWalletSetupTotalTax(walletWithdraw.getWalletWithdrawTax().getPaidTaxToWalletSetupPrice());
        //transactionWithdraw.setWithdrawWalletSetupTotalTaxCurrencyType(withdrawAmountCurrencyType);
        transactionWithdraw.setCashierTotalAmountCurrencyType(cashBoxCurrencyType);
        transactionWithdraw.setOpenedAt(currentDate);
        transactionWithdraw.setCashierCashBoxId(cashBoxId);
        transactionWithdraw.setWalletId(walletId);
        transactionWithdraw.setFinalState(transactionState);
        transactionWithdraw.setWalletWithdraw(walletWithdraw);
        transactionWithdraw.setRationalDuration(productRationalDuration);
        transactionWithdraw.setOrderCode(orderCode);
        transactionWithdraw.setIsEncoded(decript);


        //</editor-fold>

        if (withdrawCurrencyTypeId == cashBoxCurrencyTypeId) {
            if (withdrawCurrencyTypeId == walletCurrencyTypeId) {
                simpleTransactionWithdrawWithSameCurrencies(transactionWithdraw, wallet, companyCashBox, cashBox, withdrawAmount, currentDate, transactionState, decript);
            } else {
                //otherWalletCurrency(transactionWithdraw, wallet, cashBox, withdrawAmount, currentDate, transactionState);
            }
        } else {
            throw new PermissionDeniedException(msgUnsupported + withdrawAmountCurrencyType);
            //<editor-fold desc="elseBlock">


            //</editor-fold>
        }
        return transactionWithdraw;
    }

    private synchronized void simpleTransactionWithdrawWithSameCurrencies(TransactionWithdraw transactionWithdraw,
                                                                          Wallet wallet,
                                                                          CompanyCashBox companyCashBox,
                                                                          CashierCashBox cashBox,
                                                                          Double withdrawAmount,
                                                                          Date currentDate,
                                                                          TransactionState transactionState, boolean decript) throws InternalErrorException, EncryptException, WalletApiException, HttpConnectionDeniedException {

        Long walletUserId = wallet.getUserId();
        Long walletId = wallet.getId();
        Long cashBoxId = cashBox.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType cashBoxCurrencyType = cashBox.getCurrencyType();

        Map<String, Object> withdrawTaxTypeMap = calculateWithdrawTax(companyCashBox, withdrawAmount);
        TransactionTaxType withdrawTaxType = (TransactionTaxType) withdrawTaxTypeMap.get(TAX_TYPE_KEY);
        Double withdrawTaxAmount = (Double) withdrawTaxTypeMap.get(TAX_KEY);

        WalletWithdraw walletWithdraw = initWalletWithdraw(PartitionLCP.valueOf(wallet.getPartitionId()).getHost(), transactionWithdraw.getOrderCode(), currentDate, walletUserId, withdrawTaxAmount.toString(), "" + walletCurrencyType.getId(), "" + withdrawTaxType.getId(), decript);
        WalletWithdrawTax walletWithdrawTax = walletWithdraw.getWalletWithdrawTax();

        Double cashierTotal = withdrawAmount - withdrawTaxAmount;// + receiverTaxAmount;
        Double walletTotalPrice = walletWithdrawTax.getPaidTaxToWalletSetupPrice();

        TransactionWithdrawProcessTax processTax = new TransactionWithdrawProcessTax(currentDate, walletId, cashBoxId, withdrawTaxAmount, walletCurrencyType, withdrawTaxType);
        TransactionWithdrawProcess withdrawProcess = new TransactionWithdrawProcess(transactionState, currentDate, walletId, cashBoxId, withdrawAmount, walletCurrencyType, processTax);

        TransactionWithdrawTax transactionWithdrawTax = new TransactionWithdrawTax(currentDate, walletId, cashBoxId, processTax, walletWithdrawTax);

        transactionWithdraw.setProcessStart(withdrawProcess);
        transactionWithdraw.setTax(transactionWithdrawTax);

        transactionWithdraw.setWalletTotalPrice(walletTotalPrice);
        transactionWithdraw.setWalletTotalPriceCurrencyType(walletCurrencyType);

        transactionWithdraw.setCashierTotalAmount(cashierTotal);
        transactionWithdraw.setCashierTotalAmountCurrencyType(cashBoxCurrencyType);

        transactionWithdraw.setWithdrawCashierCashBoxTotalTax(withdrawTaxAmount);
        transactionWithdraw.setWithdrawCashierCashBoxTotalTaxCurrencyType(cashBoxCurrencyType);

        transactionWithdraw.setWalletWithdraw(walletWithdraw);
        transactionWithdraw.setCashierCashBox(cashBox);

    }

    private WalletWithdraw initWalletWithdraw(String host, String orderCode, Date actionDate, Long userId, String tax, String taxCurrencyType, String taxType, boolean decript) throws EncryptException, WalletApiException, HttpConnectionDeniedException {

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

        WalletWithdrawTax walletWithdrawTax = HttpURLBaseConnection.walletPreviewWithdrawAction(host, jsonObject, decript);

        WalletWithdraw walletWithdraw = new WalletWithdraw();
        walletWithdraw.setItemId(walletWithdrawTax.getId());
        walletWithdraw.setName(WITHDRAW_NAME);
        walletWithdraw.setStartAt(actionDate);
        walletWithdraw.setEndAt(Utils.getAfterSecunds(actionDate, productRationalDuration.getDuration()));

        walletWithdraw.setDescription(WITHDRAW_DESC);
        walletWithdraw.setWalletWithdrawTax(walletWithdrawTax);

        return walletWithdraw;
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

    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
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
