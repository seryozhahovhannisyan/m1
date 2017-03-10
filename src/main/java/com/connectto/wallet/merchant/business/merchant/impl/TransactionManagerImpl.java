package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.encryption.WalletEncription;
import com.connectto.wallet.merchant.business.merchant.ITransactionManager;
import com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.deposit.*;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.*;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.*;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import com.connectto.wallet.merchant.dataaccess.wallet.dao.IWalletDao;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import com.connectto.wallet.merchant.web.action.wallet.TransferTransactionAction;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.connectto.wallet.merchant.web.action.wallet.TransactionBaseAction.WITHDRAW_DESC;
import static com.connectto.wallet.merchant.web.action.wallet.TransactionBaseAction.WITHDRAW_NAME;


@Transactional(readOnly = true)
public class TransactionManagerImpl implements ITransactionManager {

    private static final Logger logger = Logger.getLogger(TransactionManagerImpl.class.getSimpleName());

    private ITransactionDao dao;
    private ITransactionExchangeDao exchangeDao;
    private ITransactionExchangeTaxDao exchangeTaxDao;
    private ITransactionProcessDao processDao;
    private ITransactionProcessTaxDao processTaxDao;
    private ITransactionTaxDao taxDao;
    private IWalletTransactionDao walletTransactionDao;
    private IWalletTransactionTaxDao walletTransactionTaxDao;

    private ICashierCashBoxDao cashierCashBoxDao;
    private IWalletDao walletDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(TransactionWithdraw data, boolean decript) throws InternalErrorException, PermissionDeniedException {

        Wallet wallet = data.getWallet();
        CashierCashBox cashBox = data.getCashierCashBox();
        int partitionId = wallet.getPartitionId();

        WalletWithdraw walletWithdraw = data.getWalletWithdraw();
        WalletWithdrawTax walletWithdrawTax = walletWithdraw.getWalletWithdrawTax();
        TransactionWithdrawTax withdrawTax = data.getTax();

        TransactionWithdrawProcess startProcess = data.getProcessStart();
        TransactionWithdrawProcessTax startProcessTax = startProcess.getProcessTax();
        //
        TransactionWithdrawExchange startProcessTaxExchange = startProcessTax.getExchange();
        TransactionWithdrawExchange startProcessExchange = startProcess.getExchange();
        TransactionWithdrawExchangeTax startProcessExchangeTax;
        TransactionWithdrawExchangeTax startProcessTaxExchangeTax;

        try {

            walletTransactionTaxDao.add(walletWithdrawTax);
            Long walletWithdrawTaxId = walletWithdrawTax.getId();
            walletWithdraw.setWalletWithdrawTaxId(walletWithdrawTaxId);
            withdrawTax.setWalletWithdrawTaxId(walletWithdrawTaxId);
            walletTransactionDao.add(walletWithdraw);
            data.setWalletWithdrawId(walletWithdraw.getId());


            //Exchange
            if (startProcessExchange != null) {
                startProcessExchangeTax = startProcessExchange.getExchangeTax();

                exchangeTaxDao.add(startProcessExchangeTax);
                startProcessExchange.setExchangeTaxId(startProcessExchangeTax.getId());
                withdrawTax.setExchangeTaxId(startProcessExchangeTax.getId());
                exchangeDao.add(startProcessExchange);
                startProcess.setExchangeId(startProcessExchange.getId());
            }

            if (startProcessTaxExchange != null) {
                startProcessTaxExchangeTax = startProcessTaxExchange.getExchangeTax();
                exchangeTaxDao.add(startProcessTaxExchangeTax);
                startProcessTaxExchange.setExchangeTaxId(startProcessTaxExchangeTax.getId());
                exchangeDao.add(startProcessTaxExchange);
            }

            processTaxDao.add(startProcessTax);
            Long startProcessTaxId = startProcessTax.getId();
            startProcess.setProcessTaxId(startProcessTaxId);
            withdrawTax.setProcessTaxId(startProcessTaxId);
            taxDao.add(withdrawTax);
            data.setTaxId(withdrawTax.getId());

            processDao.add(startProcess);
            data.setProcessStartId(startProcess.getId());

            dao.add(data);

            cashBoxStartWithdraw(cashBox, data);
            cashierCashBoxDao.update(cashBox);

            JSONObject preparedJSONObject = prepareJSONObject(data, data.getIsEncoded());
            WalletWithdrawTax walletWithdrawTaxStarted = HttpURLBaseConnection.walletStartWithdrawAction(PartitionLCP.valueOf(partitionId).getHost(), preparedJSONObject, decript);
            walletWithdrawTaxStarted.setId(walletWithdrawTax.getId());
            try {
                walletTransactionTaxDao.markTransaction(walletWithdrawTaxStarted);
            } catch (DataException e) {
                logger.error(e);
            }
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (HttpConnectionDeniedException e) {
            throw new InternalErrorException(e);
        } catch (WalletApiException e) {
            throw new InternalErrorException(e);
        } catch (EncryptException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(TransactionDeposit data, boolean decript) throws InternalErrorException, PermissionDeniedException {

        Wallet wallet = data.getWallet();
        CashierCashBox cashBox = data.getCashierCashBox();
        int partitionId = wallet.getPartitionId();

        WalletDeposit walletDeposit = data.getWalletDeposit();
        WalletDepositTax walletDepositTax = walletDeposit.getWalletDepositTax();
        TransactionDepositTax depositTax = data.getTax();

        TransactionDepositProcess startProcess = data.getProcessStart();
        TransactionDepositProcessTax startProcessTax = startProcess.getProcessTax();
        //
        TransactionDepositExchange startProcessTaxExchange = startProcessTax.getExchange();
        TransactionDepositExchange startProcessExchange = startProcess.getExchange();
        TransactionDepositExchangeTax startProcessExchangeTax;
        TransactionDepositExchangeTax startProcessTaxExchangeTax;

        try {

            walletTransactionTaxDao.add(walletDepositTax);
            Long walletDepositTaxId = walletDepositTax.getId();
            walletDeposit.setWalletDepositTaxId(walletDepositTaxId);
            depositTax.setWalletDepositTaxId(walletDepositTaxId);
            walletTransactionDao.add(walletDeposit);
            data.setWalletDepositId(walletDeposit.getId());

            //Exchange
            if (startProcessExchange != null) {
                startProcessExchangeTax = startProcessExchange.getExchangeTax();

                exchangeTaxDao.add(startProcessExchangeTax);
                startProcessExchange.setExchangeTaxId(startProcessExchangeTax.getId());
                depositTax.setExchangeTaxId(startProcessExchangeTax.getId());
                exchangeDao.add(startProcessExchange);
                startProcess.setExchangeId(startProcessExchange.getId());
            }

            if (startProcessTaxExchange != null) {
                startProcessTaxExchangeTax = startProcessTaxExchange.getExchangeTax();
                exchangeTaxDao.add(startProcessTaxExchangeTax);
                startProcessTaxExchange.setExchangeTaxId(startProcessTaxExchangeTax.getId());
                exchangeDao.add(startProcessTaxExchange);
            }

            processTaxDao.add(startProcessTax);
            Long startProcessTaxId = startProcessTax.getId();
            startProcess.setProcessTaxId(startProcessTaxId);
            depositTax.setProcessTaxId(startProcessTaxId);
            taxDao.add(depositTax);
            data.setTaxId(depositTax.getId());

            processDao.add(startProcess);
            data.setProcessStartId(startProcess.getId());

            dao.add(data);

            cashBoxStartDeposit(cashBox, data);
            cashierCashBoxDao.update(cashBox);

            JSONObject preparedJSONObject = prepareJSONObject(data, data.getIsEncoded());
            WalletDepositTax walletDepositTaxStarted = HttpURLBaseConnection.walletStartDepositAction(PartitionLCP.valueOf(partitionId).getHost(), preparedJSONObject, decript);
            walletDepositTaxStarted.setId(walletDepositTax.getId());
            try {
                walletTransactionTaxDao.markTransaction(walletDepositTaxStarted);
            } catch (DataException e) {
                logger.error(e);
            }
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (HttpConnectionDeniedException e) {
            throw new InternalErrorException(e);
        } catch (WalletApiException e) {
            throw new InternalErrorException(e);
        } catch (EncryptException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }

    }

    @Override
    public List<TransactionWithdraw> getWithdrawsByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            List<TransactionWithdraw> withdraws = dao.getWithdrawsByParams(params);
            for (TransactionWithdraw withdraw : withdraws) {
                Wallet wallet = walletDao.getById(withdraw.getWalletId());
                withdraw.setWallet(wallet);
            }
            return withdraws;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<TransactionDeposit> getDepositsByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            List<TransactionDeposit> deposits = dao.getDepositsByParams(params);
            for (TransactionDeposit deposit : deposits) {
                Wallet wallet = walletDao.getById(deposit.getWalletId());
                deposit.setWallet(wallet);
            }
            return deposits;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public int getWithdrawsCountByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getWithdrawsCountByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public int getDepositsCountByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getDepositsCountByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionWithdraw timeOutedWithdraw(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, WalletApiException, HttpConnectionDeniedException, PermissionDeniedException {

        Date currentDate = new Date(System.currentTimeMillis());
        TransactionWithdraw data = null;
        try {

            List<TransactionWithdraw> withdraws = dao.getWithdrawsByParams(params);
            if (Utils.isEmpty(withdraws)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = withdraws.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();
            Wallet wallet = data.getWallet();
            int partitionId = wallet.getPartitionId();

            TransactionWithdrawProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.TIME_OUTED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.TIME_OUTED);

            dao.close(data);

            cashBoxWithdrawRejectWithdraw(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            JSONObject preparedJSONObject = prepareJSONObject(data, data.getIsEncoded());
            WalletWithdrawTax walletWithdrawTaxTimeOuted = HttpURLBaseConnection.walletWithdrawTimeOut(PartitionLCP.valueOf(partitionId).getHost(), preparedJSONObject, data.getIsEncoded());
            if(walletWithdrawTaxTimeOuted == null){
                new WalletApiException("Wallet Api return invalid parameter");
            }
            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        } catch (EncryptException e) {
            new InternalErrorException(e);
        }
        return data;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionDeposit timeOutedDeposit(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, WalletApiException, HttpConnectionDeniedException, PermissionDeniedException  {
        Date currentDate = new Date(System.currentTimeMillis());
        TransactionDeposit data = null;
        try {

            List<TransactionDeposit> deposits = dao.getDepositsByParams(params);
            if (Utils.isEmpty(deposits)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = deposits.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();
            Wallet wallet = data.getWallet();
            int partitionId = wallet.getPartitionId();

            TransactionDepositProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.TIME_OUTED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.TIME_OUTED);

            dao.close(data);

            cashBoxDepositRejectDeposit(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            JSONObject preparedJSONObject = prepareJSONObject(data, data.getIsEncoded());
            WalletDepositTax walletDepositTaxTimeOuted = HttpURLBaseConnection.walletDepositTimeOut(PartitionLCP.valueOf(partitionId).getHost(), preparedJSONObject, data.getIsEncoded());
            if(walletDepositTaxTimeOuted == null){
                new WalletApiException("Wallet Api return invalid parameter");
            }
            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        } catch (EncryptException e) {
            new InternalErrorException(e);
        }
        return data;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionWithdraw canceledWithdrawByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException {
        Date currentDate = new Date(System.currentTimeMillis());
        TransactionWithdraw data = null;
        try {

            List<TransactionWithdraw> withdraws = dao.getWithdrawsByParams(params);
            if (Utils.isEmpty(withdraws)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = withdraws.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();

            TransactionWithdrawProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.CANCELED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.CANCELED);

            dao.close(data);

            cashBoxWithdrawRejectWithdraw(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionDeposit canceledDepositByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException {
        Date currentDate = new Date(System.currentTimeMillis());
        TransactionDeposit data = null;
        try {

            List<TransactionDeposit> deposits = dao.getDepositsByParams(params);
            if (Utils.isEmpty(deposits)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = deposits.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();

            TransactionDepositProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.CANCELED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.CANCELED);

            dao.close(data);

            cashBoxDepositRejectDeposit(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionWithdraw approvedWithdrawByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException {
        Date currentDate = new Date(System.currentTimeMillis());
        TransactionWithdraw data = null;
        try {

            List<TransactionWithdraw> withdraws = dao.getWithdrawsByParams(params);
            if (Utils.isEmpty(withdraws)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = withdraws.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();
            Wallet wallet = data.getWallet();

            TransactionWithdrawProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.RELEASED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.RELEASED);

            dao.close(data);

            cashBoxWithdrawApproveWithdraw(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public TransactionDeposit approvedDepositByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException {
        Date currentDate = new Date(System.currentTimeMillis());
        TransactionDeposit data = null;
        try {

            List<TransactionDeposit> deposits = dao.getDepositsByParams(params);
            if (Utils.isEmpty(deposits)) {
                throw new EntityNotFoundException("Could not found data with params");
            }

            data = deposits.get(0);

            CashierCashBox cashierCashBox = data.getCashierCashBox();
            Wallet wallet = data.getWallet();

            TransactionDepositProcess startProcess = data.getProcessStart();
            startProcess.setState(TransactionState.RELEASED);
            startProcess.setActionDate(currentDate);

            processDao.add(startProcess);

            data.setProcessEndId(startProcess.getId());
            data.setClosedAt(currentDate);
            data.setFinalState(TransactionState.RELEASED);

            dao.close(data);

            cashBoxDepositApproveDeposit(cashierCashBox, data);
            cashierCashBoxDao.update(cashierCashBox);

            return data;
        } catch (DatabaseException e) {
            new InternalErrorException(e);
        }

        return data;
    }

    private JSONObject prepareJSONObject(TransactionWithdraw data, boolean encrypt) throws EncryptException {

        Wallet wallet = data.getWallet();
        Long userId = wallet.getUserId();
        TransactionWithdrawTax depositTax = data.getTax();
        TransactionWithdrawProcessTax depositProcessTax = depositTax.getProcessTax();

        String amount = data.getWithdrawAmount().toString();
        String amountCurrencyType = "" + data.getWithdrawAmountCurrencyType().getId();
        String taxTotal = depositProcessTax.getProcessTaxTotal().toString();
        String taxCurrencyType = "" + depositProcessTax.getProcessTaxCurrencyType().getId();
        String taxType = "" + depositProcessTax.getProcessTaxType().getId();
        String rationalDuration = "" + data.getRationalDuration().getId();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", WITHDRAW_NAME);
        jsonObject.put("description", WITHDRAW_DESC);
        jsonObject.put("orderCode", data.getOrderCode());

        jsonObject.put("userId", userId);
        jsonObject.put("itemId", data.getId());
        jsonObject.put("amount", encrypt ? WalletEncription.encrypt(amount) : amount);
        jsonObject.put("currencyType", encrypt ?  WalletEncription.encrypt(amountCurrencyType) : amountCurrencyType);
        jsonObject.put("tax",  encrypt ? WalletEncription.encrypt(taxTotal) : taxTotal);
        jsonObject.put("taxCurrencyType", encrypt ?  WalletEncription.encrypt(taxCurrencyType) : taxCurrencyType);
        jsonObject.put("taxType",  encrypt ? WalletEncription.encrypt(taxType) : taxType);
        jsonObject.put("rationalDuration",  encrypt ? WalletEncription.encrypt(rationalDuration) : rationalDuration);

        return jsonObject;
    }

    private JSONObject prepareJSONObject(TransactionDeposit data, boolean encrypt) throws EncryptException {

        Wallet wallet = data.getWallet();
        Long userId = wallet.getUserId();
        TransactionDepositTax depositTax = data.getTax();
        TransactionDepositProcessTax depositProcessTax = depositTax.getProcessTax();

        String amount = data.getDepositAmount().toString();
        String amountCurrencyType = "" + data.getDepositAmountCurrencyType().getId();
        String taxTotal = depositProcessTax.getProcessTaxTotal().toString();
        String taxCurrencyType = "" + depositProcessTax.getProcessTaxCurrencyType().getId();
        String taxType = "" + depositProcessTax.getProcessTaxType().getId();
        String rationalDuration = "" + data.getRationalDuration().getId();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", WITHDRAW_NAME);
        jsonObject.put("description", WITHDRAW_DESC);
        jsonObject.put("orderCode", data.getOrderCode());

        jsonObject.put("userId", userId);
        jsonObject.put("itemId", data.getId());
        jsonObject.put("amount", encrypt ? WalletEncription.encrypt(amount) : amount);
        jsonObject.put("currencyType", encrypt ?  WalletEncription.encrypt(amountCurrencyType) : amountCurrencyType);
        jsonObject.put("tax",  encrypt ? WalletEncription.encrypt(taxTotal) : taxTotal);
        jsonObject.put("taxCurrencyType", encrypt ?  WalletEncription.encrypt(taxCurrencyType) : taxCurrencyType);
        jsonObject.put("taxType",  encrypt ? WalletEncription.encrypt(taxType) : taxType);
        jsonObject.put("rationalDuration",  encrypt ? WalletEncription.encrypt(rationalDuration) : rationalDuration);

        return jsonObject;
    }

    private void cashBoxStartWithdraw(CashierCashBox cashBox, TransactionWithdraw data) throws PermissionDeniedException {
        Double balanceCurrent = cashBox.getBalanceCurrent();

        Double pendingBalanceWithdraw = cashBox.getPendingBalanceWithdraw();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double withdrawCashierTotalAmount = data.getCashierTotalAmount();
        Double withdrawCashierCashBoxTotalTax = data.getWithdrawCashierCashBoxTotalTax();

        Double availableBalance = balanceCurrent - pendingBalanceWithdraw - withdrawCashierTotalAmount;
        if (availableBalance < 0) {
            throw new PermissionDeniedException("Cashier balance not supported");
        }

        pendingBalanceWithdraw += withdrawCashierTotalAmount;
        pendingTaxAmount += withdrawCashierCashBoxTotalTax;

        cashBox.setPendingBalanceWithdraw(pendingBalanceWithdraw);
        cashBox.setPendingTaxAmount(pendingTaxAmount);
    }

    private void cashBoxStartDeposit(CashierCashBox cashBox, TransactionDeposit data) throws PermissionDeniedException {

        Double pendingBalanceDeposit = cashBox.getPendingBalanceDeposit();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double depositCashierTotalAmount = data.getCashierTotalAmount();
        Double depositCashierCashBoxTotalTax = data.getDepositCashierCashBoxTotalTax();

        pendingBalanceDeposit += depositCashierTotalAmount;
        pendingTaxAmount += depositCashierCashBoxTotalTax;

        cashBox.setPendingBalanceDeposit(pendingBalanceDeposit);
        cashBox.setPendingTaxAmount(pendingTaxAmount);
    }

    private void cashBoxWithdrawApproveWithdraw(CashierCashBox cashBox, TransactionWithdraw data) throws PermissionDeniedException {
        Double balanceCurrent = cashBox.getBalanceCurrent();
        Double gatheredTax = cashBox.getBalanceGatheredTax();

        Double pendingBalanceWithdraw = cashBox.getPendingBalanceWithdraw();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double withdrawCashierTotalAmount = data.getCashierTotalAmount();
        Double withdrawCashierCashBoxTotalTax = data.getWithdrawCashierCashBoxTotalTax();

        Double availableBalance = balanceCurrent - pendingBalanceWithdraw;
        if (availableBalance < 0) {
            throw new PermissionDeniedException("Cashier balance not supported");
        }

        pendingBalanceWithdraw -= withdrawCashierTotalAmount;
        pendingTaxAmount -= withdrawCashierCashBoxTotalTax;
        balanceCurrent -= withdrawCashierTotalAmount;
        gatheredTax += withdrawCashierCashBoxTotalTax;

        cashBox.setPendingBalanceWithdraw(pendingBalanceWithdraw);
        cashBox.setPendingTaxAmount(pendingTaxAmount);

        cashBox.setBalanceCurrent(balanceCurrent);
        cashBox.setBalanceGatheredTax(gatheredTax);
    }

    private void cashBoxDepositApproveDeposit(CashierCashBox cashBox, TransactionDeposit data) throws PermissionDeniedException {
        Double balanceCurrent = cashBox.getBalanceCurrent();
        Double gatheredTax = cashBox.getBalanceGatheredTax();

        Double pendingBalanceDeposit = cashBox.getPendingBalanceDeposit();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double depositCashierTotalAmount = data.getCashierTotalAmount();
        Double depositCashierCashBoxTotalTax = data.getDepositCashierCashBoxTotalTax();

        pendingBalanceDeposit -= depositCashierTotalAmount;
        pendingTaxAmount -= depositCashierCashBoxTotalTax;
        balanceCurrent += depositCashierTotalAmount;
        gatheredTax += depositCashierCashBoxTotalTax;

        cashBox.setPendingBalanceDeposit(pendingBalanceDeposit);
        cashBox.setPendingTaxAmount(pendingTaxAmount);

        cashBox.setBalanceCurrent(balanceCurrent);
        cashBox.setBalanceGatheredTax(gatheredTax);
    }

    private void cashBoxWithdrawRejectWithdraw(CashierCashBox cashBox, TransactionWithdraw data) throws PermissionDeniedException {
        Double balanceCurrent = cashBox.getBalanceCurrent();
        Double gatheredTax = cashBox.getBalanceGatheredTax();

        Double pendingBalanceWithdraw = cashBox.getPendingBalanceWithdraw();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double withdrawCashierTotalAmount = data.getCashierTotalAmount();
        Double withdrawCashierCashBoxTotalTax = data.getWithdrawCashierCashBoxTotalTax();

        Double availableBalance = balanceCurrent - pendingBalanceWithdraw;
        if (availableBalance < 0) {
            throw new PermissionDeniedException("Cashier balance not supported");
        }

        pendingBalanceWithdraw -= withdrawCashierTotalAmount;
        pendingTaxAmount -= withdrawCashierCashBoxTotalTax;

        cashBox.setPendingBalanceWithdraw(pendingBalanceWithdraw);
        cashBox.setPendingTaxAmount(pendingTaxAmount);
    }

    private void cashBoxDepositRejectDeposit(CashierCashBox cashBox, TransactionDeposit data) throws PermissionDeniedException {

        Double pendingBalanceDeposit = cashBox.getPendingBalanceDeposit();
        Double pendingTaxAmount = cashBox.getPendingTaxAmount();

        Double depositCashierTotalAmount = data.getCashierTotalAmount();
        Double depositCashierCashBoxTotalTax = data.getDepositCashierCashBoxTotalTax();

        pendingBalanceDeposit -= depositCashierTotalAmount;
        pendingTaxAmount -= depositCashierCashBoxTotalTax;

        cashBox.setPendingBalanceDeposit(pendingBalanceDeposit);
        cashBox.setPendingTaxAmount(pendingTaxAmount);
    }

    public void setDao(ITransactionDao dao) {
        this.dao = dao;
    }

    public void setExchangeDao(ITransactionExchangeDao exchangeDao) {
        this.exchangeDao = exchangeDao;
    }

    public void setExchangeTaxDao(ITransactionExchangeTaxDao exchangeTaxDao) {
        this.exchangeTaxDao = exchangeTaxDao;
    }

    public void setProcessDao(ITransactionProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setProcessTaxDao(ITransactionProcessTaxDao processTaxDao) {
        this.processTaxDao = processTaxDao;
    }

    public void setWalletTransactionDao(IWalletTransactionDao walletTransactionDao) {
        this.walletTransactionDao = walletTransactionDao;
    }

    public void setWalletTransactionTaxDao(IWalletTransactionTaxDao walletTransactionTaxDao) {
        this.walletTransactionTaxDao = walletTransactionTaxDao;
    }

    public void setTaxDao(ITransactionTaxDao taxDao) {
        this.taxDao = taxDao;
    }

    public void setCashierCashBoxDao(ICashierCashBoxDao cashierCashBoxDao) {
        this.cashierCashBoxDao = cashierCashBoxDao;
    }

    public void setWalletDao(IWalletDao walletDao) {
        this.walletDao = walletDao;
    }
}
