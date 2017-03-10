import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.transaction.deposit.*;
import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 11/10/2016.
 */
public class TransactionUtil {

    private static final String TAX_KEY = "tax";
    private static final String TAX_TYPE_KEY = "type";
    private static final String MESSAGE_UNKNOWN_WALLETS = "Please choose receiver wallet or sender ";
    private static final String MESSAGE_LESS_MONEY = "Less money ";
    private static final String MESSAGE_LESS_REQUEST = "Aveli ";
    private static final String MESSAGE_NOT_SUPPORTED_CURRENCY = "The currency not supported ";
    //
    private static Long partitionId = 1L;
    private static String cashierId = "5";
    private static String walletId = "10";
    private static String depositAmount = "1000";
    private static String depositCurrencyType = "" + CurrencyType.USD.getId();
//    private static String depositAmount = "1000";
//    private static String depositCurrencyType = "" + CurrencyType.USD.getId();


    public static void main(String[] args) throws InvalidParameterException, PermissionDeniedException, InternalErrorException {
       // initDemoTransactionDeposit();
    }

    /*public static TransactionDeposit initDemoTransactionDeposit() throws InvalidParameterException, PermissionDeniedException, InternalErrorException {
        Cashier cashier = DemoCreator.initCashier(CurrencyType.USD, cashierId);
        Wallet wallet = DemoCreator.initWallet(CurrencyType.USD, walletId);
        CompanyCashBox walletSetup = DemoCreator.initcompanyCashBox(CurrencyType.USD, partitionId);
        MerchantSetup merchantSetup = DemoCreator.initMerchantSetup(CurrencyType.USD, partitionId);
        Double pAmount = Double.parseDouble(depositAmount);
        CurrencyType pCurrencyType = CurrencyType.valueOf(Integer.parseInt(depositCurrencyType));
        TransactionDeposit transactionDeposit = createTransaction(pAmount, pCurrencyType, cashier, wallet, walletSetup, merchantSetup, TransactionState.PENDING);
        transactionDeposit.setFinalState(TransactionState.PENDING);
        transactionDeposit.setIsEncoded(false);
        transactionDeposit.setMessage("msg");
        transactionDeposit.setOrderCode("orderCode");
        return transactionDeposit;
    }


    public static TransactionDeposit createTransaction(
            Double depositAmount, CurrencyType depositCurrencyType,
            Cashier cashier, Wallet wallet, CompanyCashBox walletSetup, MerchantSetup merchantSetup,
            TransactionState transactionState
    ) throws InternalErrorException, PermissionDeniedException {

        //<editor-fold desc="initBlock">
        Date currentDate = new Date(System.currentTimeMillis());


        if (wallet == null || walletSetup == null) {
            throw new InternalErrorException(MESSAGE_UNKNOWN_WALLETS);
        }

        boolean isDepositCurrencyTypeSupported = walletSetup.isCurrencyTypeSupported(depositCurrencyType.getId());
        if (!isDepositCurrencyTypeSupported) {
            throw new PermissionDeniedException(String.format("The selected currency type %s not supported ", depositCurrencyType.getName()));
        }

        CurrencyType setupCurrencyType = walletSetup.getCurrencyType();
        CurrencyType walletCurrencyType = wallet.getCurrencyType();

        int setupCurrencyTypeId = setupCurrencyType.getId();
        int walletCurrencyTypeId = walletCurrencyType.getId();
        int depositCurrencyTypeId = depositCurrencyType.getId();

        TransactionDeposit transactionDeposit = new TransactionDeposit();
        transactionDeposit.setDepositAmount(depositAmount);
        transactionDeposit.setDepositCurrencyType(depositCurrencyType);
        transactionDeposit.setSetupTotalAmountCurrencyType(setupCurrencyType);
        transactionDeposit.setOpenedAt(currentDate);
        transactionDeposit.setSetupId(walletSetup.getId());
        transactionDeposit.setWalletId(wallet.getId());
        transactionDeposit.setMerchantSetupId(merchantSetup.getId());


        boolean isFromCurrencyTypeSupported = walletSetup.isCurrencyTypeSupported(walletCurrencyTypeId);
        if (!isFromCurrencyTypeSupported) {
            throw new PermissionDeniedException(String.format("Wallet currency type %s not supported ", walletCurrencyType.getName()));
        }

        Double currentBalance = wallet.getMoney();
        Double frozenAmount = wallet.getFrozenAmount();

        Double availableAmount = currentBalance - frozenAmount;
        if (availableAmount <= 0) {
            //todo open credit card balance, count += all and download from credit card
            throw new InternalErrorException(MESSAGE_LESS_MONEY);
        }
        //</editor-fold>

        if (depositCurrencyTypeId == setupCurrencyTypeId) {
            if (depositCurrencyTypeId == walletCurrencyTypeId) {
                simpleTransactionDepositWithSameCurrencies(transactionDeposit, cashier, wallet, walletSetup, merchantSetup, depositAmount, availableAmount, currentDate, transactionState);
            } else {
                otherWalletCurrency(transactionDeposit, cashier, wallet, walletSetup, merchantSetup, depositAmount, availableAmount, currentDate, transactionState);
            }
        } else {
            //<editor-fold desc="elseBlock">

            if (setupCurrencyTypeId == walletCurrencyTypeId) {
                otherDepositCurrency(transactionDeposit, cashier, wallet, walletSetup, merchantSetup, depositAmount, depositCurrencyType, availableAmount, currentDate, transactionState);
            } else if (depositCurrencyTypeId == walletCurrencyTypeId) {
                otherSetupCurrency(transactionDeposit, cashier, wallet, walletSetup, merchantSetup, depositAmount, availableAmount, currentDate, transactionState);
            } else {
                throw new PermissionDeniedException("You could not use currency" + depositCurrencyType);
            }
            //</editor-fold>
        }
        return transactionDeposit;
    }




    public static void simpleTransactionDepositWithSameCurrencies(TransactionDeposit transactionDeposit,
                                                                  Cashier cashier,
                                                                  Wallet wallet,
                                                                  CompanyCashBox walletSetup,
                                                                  MerchantSetup merchantSetup,
                                                                  Double depositAmount,
                                                                  Double availableAmount, Date currentDate, TransactionState transactionState) throws InternalErrorException {

        Long cashierId = cashier.getId();
        Long setupId = walletSetup.getId();
        Long merchantSetupId = merchantSetup.getId();
        Long walletId = wallet.getId();

        CurrencyType currencyType = walletSetup.getCurrencyType();

        Map<String, Object> depositTaxTypeMap = calculateDepositTax(walletSetup, depositAmount);
        TransactionTaxType taxType = (TransactionTaxType) depositTaxTypeMap.get(TAX_TYPE_KEY);
        Double taxAmount = (Double) depositTaxTypeMap.get(TAX_KEY);

        Map<String, Object> depositMerchantTaxTypeMap = calculateMerchantDepositTax(merchantSetup, depositAmount);
        TransactionTaxType taxMerchantType = (TransactionTaxType) depositMerchantTaxTypeMap.get(TAX_TYPE_KEY);
        Double taxMerchantAmount = (Double) depositMerchantTaxTypeMap.get(TAX_KEY);



        Double totalAmount = depositAmount - taxAmount -taxMerchantAmount;
        if (availableAmount < totalAmount) {
            //todo toCreditCard +=
            throw new InternalErrorException(MESSAGE_LESS_MONEY);
        }

        TransactionDepositProcessTax processTax = new TransactionDepositProcessTax(cashierId, setupId, merchantSetupId, walletId, currentDate, taxAmount, currencyType, taxType);
        MerchantSetupTax merchantSetupTax = new MerchantSetupTax(cashierId, setupId, merchantSetupId, walletId, currentDate, taxMerchantAmount, currencyType, taxMerchantType);
        TransactionDepositTax depositTax = new TransactionDepositTax(cashierId, setupId, merchantSetupId, walletId, currentDate, processTax, merchantSetupTax);
        TransactionDepositProcess depositProcess = new TransactionDepositProcess(cashierId, setupId, merchantSetupId, walletId, transactionState, currentDate, depositAmount, currencyType, processTax);

        transactionDeposit.setProcessStart(depositProcess);
        transactionDeposit.setWalletTotalPrice(totalAmount);
        transactionDeposit.setWalletTotalPriceCurrencyType(currencyType);

        transactionDeposit.setSetupTotalAmount(totalAmount);
        transactionDeposit.setSetupTotalAmountCurrencyType(currencyType);
        transactionDeposit.setTax(depositTax);
    }


    public static void otherWalletCurrency(TransactionDeposit transactionDeposit,
                                           Cashier cashier,
                                           Wallet wallet,
                                           CompanyCashBox walletSetup,
                                           MerchantSetup merchantSetup,
                                           Double depositAmount,
                                           Double availableAmount, Date currentDate, TransactionState transactionState) throws InternalErrorException {

        ExchangeRate selectedExchangeRate = DemoCreator.initExchangeRate();//exchangeRates.get(0);
        Double rateAmount = selectedExchangeRate.getBuy();

        Long cashierId = cashier.getId();
        Long setupId = walletSetup.getId();
        Long merchantSetupId = merchantSetup.getId();
        Long walletId = wallet.getId();
        Long rateId = selectedExchangeRate.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType setupCurrencyType = walletSetup.getCurrencyType();

        Double depositPrice = depositAmount * rateAmount;//480.000AMD
        Map<String, Object> depositProcessTaxMap = calculateDepositTax(walletSetup, depositAmount);//1000 USD
        TransactionTaxType depositProcessTaxType = (TransactionTaxType) depositProcessTaxMap.get(TAX_TYPE_KEY);
        Double depositProcessTax = (Double) depositProcessTaxMap.get(TAX_KEY);//100 USD
        Double depositProcessTaxPrice = depositProcessTax * rateAmount;
        TransactionDepositExchangeTax depositProcessExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTaxPrice, walletCurrencyType, depositProcessTaxType);
        TransactionDepositExchange depositProcessExchange = new TransactionDepositExchange(cashierId, setupId, merchantSetupId, walletId, rateId, currentDate, depositProcessTax, setupCurrencyType, rateAmount, walletCurrencyType, depositProcessTaxPrice, walletCurrencyType, depositProcessExchangeTax);

        Map<String, Object> processTaxExchangeTaxMap = calculateExchangeTax(walletSetup, depositProcessTax);//100USD
        TransactionTaxType processTaxExchangeTaxType = (TransactionTaxType) processTaxExchangeTaxMap.get(TAX_TYPE_KEY);
        Double processTaxExchangeTaxTax = (Double) processTaxExchangeTaxMap.get(TAX_KEY);//10 USD
        Double processTaxExchangeTaxPrice = processTaxExchangeTaxTax * rateAmount;//4800AMD
        TransactionDepositExchangeTax processTaxExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, processTaxExchangeTaxTax, setupCurrencyType, processTaxExchangeTaxPrice, walletCurrencyType, processTaxExchangeTaxType);
        TransactionDepositExchange processTaxExchangeTaxExchange = new TransactionDepositExchange(cashierId, setupId, merchantSetupId, walletId, rateId, currentDate, processTaxExchangeTaxTax, setupCurrencyType, rateAmount, walletCurrencyType, processTaxExchangeTaxPrice, walletCurrencyType, processTaxExchangeTax);

        Map<String, Object> exchangeDepositMap = calculateExchangeTax(walletSetup, depositAmount);//1000 USD
        TransactionTaxType exchangeDepositType = (TransactionTaxType) exchangeDepositMap.get(TAX_TYPE_KEY);
        Double exchangeDeposit = (Double) exchangeDepositMap.get(TAX_KEY);//100 USD
        Double exchangeDepositPrice = exchangeDeposit * rateAmount;//48.000 AMD
        TransactionDepositExchangeTax depositExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, exchangeDeposit, setupCurrencyType, exchangeDepositPrice, walletCurrencyType, exchangeDepositType);

        Double totalTaxAmount = depositProcessTax + exchangeDeposit + processTaxExchangeTaxTax;
        Double totalTaxPrice = depositProcessTaxPrice + exchangeDepositPrice + processTaxExchangeTaxPrice;

        Double depositTotalAmount = depositAmount + totalTaxAmount;
        Double depositTotalPrice = depositPrice + totalTaxPrice;

        if (availableAmount < depositTotalPrice) {
            throw new InternalErrorException(MESSAGE_LESS_MONEY);
        }


        TransactionDepositProcessTax processTax = new TransactionDepositProcessTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTaxPrice, walletCurrencyType, depositProcessTaxType, processTaxExchangeTaxExchange);
        TransactionDepositTax depositTax = new TransactionDepositTax(cashierId, setupId, merchantSetupId, walletId, currentDate, processTax, processTaxExchangeTax,null,  depositExchangeTax);

        TransactionDepositProcess depositProcess = new TransactionDepositProcess(cashierId, setupId, merchantSetupId, walletId, transactionState, currentDate,
                depositAmount, setupCurrencyType,
                depositPrice, depositTotalPrice, walletCurrencyType,
                depositAmount, depositTotalAmount, setupCurrencyType,
                processTax,
                depositProcessExchange);

        transactionDeposit.setProcessStart(depositProcess);
        transactionDeposit.setWalletTotalPrice(depositTotalPrice);
        transactionDeposit.setWalletTotalPriceCurrencyType(walletCurrencyType);

        transactionDeposit.setSetupTotalAmount(depositTotalAmount);
        transactionDeposit.setSetupTotalAmountCurrencyType(setupCurrencyType);
        transactionDeposit.setTax(depositTax);
    }

    public static void otherDepositCurrency(TransactionDeposit transactionDeposit,
                                            Cashier cashier,
                                            Wallet wallet,//USD
                                            CompanyCashBox walletSetup,//USD
                                            MerchantSetup merchantSetup,
                                            Double depositAmount, CurrencyType depositCurrencyType,//480.000 AMD
                                            Double availableAmount, Date currentDate, TransactionState transactionState) throws InternalErrorException {

        ExchangeRate selectedExchangeRate = DemoCreator.initExchangeRate();//exchangeRates.get(0);
        Double rateAmount = selectedExchangeRate.getBuy();//480

        Long cashierId = cashier.getId();
        Long setupId = walletSetup.getId();
        Long merchantSetupId = merchantSetup.getId();
        Long walletId = wallet.getId();
        Long rateId = selectedExchangeRate.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType setupCurrencyType = walletSetup.getCurrencyType();

        Double depositPrice = depositAmount / rateAmount;//1.000 USD
        Map<String, Object> depositProcessTaxMap = calculateDepositTax(walletSetup, depositPrice);//1000 USD
        TransactionTaxType depositProcessTaxType = (TransactionTaxType) depositProcessTaxMap.get(TAX_TYPE_KEY);
        Double depositProcessTax = (Double) depositProcessTaxMap.get(TAX_KEY);//100 USD
        TransactionDepositExchangeTax depositProcessExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTax, walletCurrencyType, depositProcessTaxType);
        TransactionDepositExchange depositProcessExchange = new TransactionDepositExchange(cashierId, setupId, merchantSetupId, walletId, rateId, currentDate, depositPrice, setupCurrencyType, rateAmount, walletCurrencyType,
                depositAmount, depositCurrencyType, depositPrice, walletCurrencyType, depositProcessExchangeTax);

        Map<String, Object> exchangeDepositMap = calculateExchangeTax(walletSetup, depositPrice);//1000 USD
        TransactionTaxType exchangeDepositType = (TransactionTaxType) exchangeDepositMap.get(TAX_TYPE_KEY);
        Double exchangeDeposit = (Double) exchangeDepositMap.get(TAX_KEY);//100 USD
        TransactionDepositExchangeTax depositExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, exchangeDeposit, setupCurrencyType, exchangeDeposit, walletCurrencyType, exchangeDepositType);

        Double totalTaxAmount = depositProcessTax + exchangeDeposit;
        Double depositTotalAmount = depositPrice + totalTaxAmount;

        if (availableAmount < depositTotalAmount) {
            throw new InternalErrorException(MESSAGE_LESS_MONEY);
        }

        TransactionDepositProcessTax processTax = new TransactionDepositProcessTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTax, walletCurrencyType, depositProcessTaxType, null);
        TransactionDepositTax depositTax = new TransactionDepositTax(cashierId, setupId, merchantSetupId, walletId, currentDate,
                processTax,null,  depositExchangeTax);

        TransactionDepositProcess depositProcess = new TransactionDepositProcess(cashierId, setupId, merchantSetupId, walletId, transactionState, currentDate,
                depositAmount, depositCurrencyType,
                depositPrice, depositTotalAmount, walletCurrencyType,
                depositPrice, depositTotalAmount, setupCurrencyType,
                processTax,
                depositProcessExchange);

        transactionDeposit.setProcessStart(depositProcess);
        transactionDeposit.setWalletTotalPrice(depositTotalAmount);
        transactionDeposit.setWalletTotalPriceCurrencyType(walletCurrencyType);

        transactionDeposit.setSetupTotalAmount(depositTotalAmount);
        transactionDeposit.setSetupTotalAmountCurrencyType(setupCurrencyType);
        transactionDeposit.setTax(depositTax);
    }

    public static void otherSetupCurrency(TransactionDeposit transactionDeposit,
                                          Cashier cashier,
                                          Wallet wallet,//AMD
                                          CompanyCashBox walletSetup,//USD
                                          MerchantSetup merchantSetup,//
                                          Double depositAmount,//AMD
                                          Double availableAmount, Date currentDate, TransactionState transactionState) throws InternalErrorException {

        ExchangeRate selectedExchangeRate = DemoCreator.initExchangeRate();//exchangeRates.get(0);
        Double rateAmount = selectedExchangeRate.getBuy();

        Long cashierId = cashier.getId();
        Long setupId = walletSetup.getId();
        Long merchantSetupId = merchantSetup.getId();
        Long walletId = wallet.getId();

        Long rateId = selectedExchangeRate.getId();

        CurrencyType walletCurrencyType = wallet.getCurrencyType();
        CurrencyType setupCurrencyType = walletSetup.getCurrencyType();

        Double setupAmount = depositAmount / rateAmount;//1000 USD
        Map<String, Object> depositProcessTaxMap = calculateDepositTax(walletSetup, setupAmount);//1000 USD
        TransactionTaxType depositProcessTaxType = (TransactionTaxType) depositProcessTaxMap.get(TAX_TYPE_KEY);
        Double depositProcessTax = (Double) depositProcessTaxMap.get(TAX_KEY);//100 USD
        Double depositProcessTaxPrice = depositProcessTax * rateAmount;
        TransactionDepositExchangeTax depositProcessExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTaxPrice, walletCurrencyType, depositProcessTaxType);
        TransactionDepositExchange depositProcessExchange = new TransactionDepositExchange(cashierId, setupId, merchantSetupId, walletId, rateId, currentDate, setupAmount, setupCurrencyType, rateAmount, walletCurrencyType, depositAmount, walletCurrencyType, depositProcessExchangeTax);

        Map<String, Object> processTaxExchangeTaxMap = calculateExchangeTax(walletSetup, depositProcessTax);//100USD
        TransactionTaxType processTaxExchangeTaxType = (TransactionTaxType) processTaxExchangeTaxMap.get(TAX_TYPE_KEY);
        Double processTaxExchangeTaxTax = (Double) processTaxExchangeTaxMap.get(TAX_KEY);//10 USD
        Double processTaxExchangeTaxPrice = processTaxExchangeTaxTax * rateAmount;//4800AMD
        TransactionDepositExchangeTax processTaxExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, processTaxExchangeTaxTax, setupCurrencyType, processTaxExchangeTaxPrice, walletCurrencyType, processTaxExchangeTaxType);
        TransactionDepositExchange processTaxExchangeTaxExchange = new TransactionDepositExchange(cashierId, setupId, merchantSetupId, walletId, rateId, currentDate, processTaxExchangeTaxTax, setupCurrencyType, rateAmount, walletCurrencyType, processTaxExchangeTaxPrice, walletCurrencyType, processTaxExchangeTax);

        Map<String, Object> exchangeDepositMap = calculateExchangeTax(walletSetup, setupAmount);//1000 USD
        TransactionTaxType exchangeDepositType = (TransactionTaxType) exchangeDepositMap.get(TAX_TYPE_KEY);
        Double exchangeDeposit = (Double) exchangeDepositMap.get(TAX_KEY);//100 USD
        Double exchangeDepositPrice = exchangeDeposit * rateAmount;//48.000 AMD
        TransactionDepositExchangeTax depositExchangeTax = new TransactionDepositExchangeTax(cashierId, setupId, merchantSetupId, walletId, currentDate, exchangeDeposit, setupCurrencyType, exchangeDepositPrice, walletCurrencyType, exchangeDepositType);

        Double totalTaxAmount = depositProcessTax + exchangeDeposit + processTaxExchangeTaxTax;
        Double totalTaxPrice = depositProcessTaxPrice + exchangeDepositPrice + processTaxExchangeTaxPrice;

        Double depositTotalAmount = setupAmount + totalTaxAmount;
        Double depositTotalPrice = depositAmount + totalTaxPrice;

        if (availableAmount < depositTotalPrice) {
            throw new InternalErrorException(MESSAGE_LESS_MONEY);
        }

        TransactionDepositProcessTax processTax = new TransactionDepositProcessTax(cashierId, setupId, merchantSetupId, walletId, currentDate, depositProcessTax, setupCurrencyType, depositProcessTaxPrice, walletCurrencyType, depositProcessTaxType, processTaxExchangeTaxExchange);
        TransactionDepositTax depositTax = new TransactionDepositTax(cashierId, setupId, merchantSetupId, walletId, currentDate, processTax, processTaxExchangeTax,null,  depositExchangeTax);

        TransactionDepositProcess depositProcess = new TransactionDepositProcess(cashierId, setupId, merchantSetupId, walletId, transactionState, currentDate,
                depositAmount, walletCurrencyType,
                depositAmount, depositTotalPrice, walletCurrencyType,
                setupAmount, depositTotalAmount, setupCurrencyType,
                processTax,
                depositProcessExchange);

        transactionDeposit.setProcessStart(depositProcess);
        transactionDeposit.setWalletTotalPrice(depositTotalPrice);
        transactionDeposit.setWalletTotalPriceCurrencyType(walletCurrencyType);

        transactionDeposit.setSetupTotalAmount(depositTotalAmount);
        transactionDeposit.setSetupTotalAmountCurrencyType(setupCurrencyType);
        transactionDeposit.setTax(depositTax);
    }

    public static Map<String, Object> calculateExchangeTax(CompanyCashBox walletSetup, Double amount) {

        Map<String, Object> map = new HashMap<String, Object>();
        Double purchesTaxExchange = 0d;
        Double exchangePercentAmount = walletSetup.getExchangeDepositFeePercent() * amount / 100;

        if (exchangePercentAmount < walletSetup.getExchangeDepositMinFee()) {
            purchesTaxExchange = walletSetup.getExchangeDepositMinFee();
            map.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (exchangePercentAmount > walletSetup.getExchangeDepositMaxFee()) {
            purchesTaxExchange = walletSetup.getExchangeDepositMaxFee();
            map.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            purchesTaxExchange = exchangePercentAmount;
            map.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        map.put(TAX_KEY, purchesTaxExchange);
        return map;
    }

    public static Map<String, Object> calculateDepositTax(CompanyCashBox walletSetup, Double amount) {

        Map<String, Object> depositTaxTypeMap = new HashMap<String, Object>();
        Double depositTax = 0d;
        Double depositPercentAmount = walletSetup.getDepositFeePercent() * amount / 100;

        if (depositPercentAmount < walletSetup.getDepositMinFee()) {
            depositTax = walletSetup.getDepositMinFee();
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (depositPercentAmount > walletSetup.getDepositMaxFee()) {
            depositTax = walletSetup.getDepositMaxFee();
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            depositTax = depositPercentAmount;
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        depositTaxTypeMap.put(TAX_KEY, depositTax);
        return depositTaxTypeMap;
    }

    public static Map<String, Object> calculateMerchantExchangeTax(MerchantSetup merchantSetup, Double amount) {

        Map<String, Object> map = new HashMap<String, Object>();
        Double purchesTaxExchange = 0d;
        Double exchangePercentAmount = merchantSetup.getExchangeDepositFeePercent() * amount / 100;

        if (exchangePercentAmount < merchantSetup.getExchangeDepositMinFee()) {
            purchesTaxExchange = merchantSetup.getExchangeDepositMinFee();
            map.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (exchangePercentAmount > merchantSetup.getExchangeDepositMaxFee()) {
            purchesTaxExchange = merchantSetup.getExchangeDepositMaxFee();
            map.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            purchesTaxExchange = exchangePercentAmount;
            map.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        map.put(TAX_KEY, purchesTaxExchange);
        return map;
    }

    public static Map<String, Object> calculateMerchantDepositTax(MerchantSetup merchantSetup, Double amount) {

        Map<String, Object> depositTaxTypeMap = new HashMap<String, Object>();
        Double depositTax = 0d;
        Double depositPercentAmount = merchantSetup.getDepositFeePercent() * amount / 100;

        if (depositPercentAmount < merchantSetup.getDepositMinFee()) {
            depositTax = merchantSetup.getDepositMinFee();
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (depositPercentAmount > merchantSetup.getDepositMaxFee()) {
            depositTax = merchantSetup.getDepositMaxFee();
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            depositTax = depositPercentAmount;
            depositTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        depositTaxTypeMap.put(TAX_KEY, depositTax);
        return depositTaxTypeMap;
    }

*/
}
