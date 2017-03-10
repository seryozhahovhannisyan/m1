
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;

import java.util.Date;

/**
 * Created by Serozh on 11/11/2016.
 */
public class DemoCreator {

    

    public static synchronized Cashier initCashier(CurrencyType currencyType, String userId) {
        Cashier wallet = new Cashier();
        wallet.setId(350l);

        return wallet;
    }

    public static synchronized Wallet initWallet(CurrencyType currencyType, String userId) {
        Wallet wallet = new Wallet();
        wallet.setId(350l);
        wallet.setUserId(Long.parseLong(userId));
        wallet.setMoney(1000000d);
        wallet.setFrozenAmount(0d);
        wallet.setCurrencyType(currencyType);

        return wallet;
    }

    public static synchronized CompanyCashBox initcompanyCashBox (CurrencyType currencyType, long partitionId) {
        CompanyCashBox walletSetup = new CompanyCashBox();
        /*walletSetup.setId(7l);
        walletSetup.setCompanyId(partitionId);
        walletSetup.setCurrencyType(currencyType);
        walletSetup.setBalance(1000d);
        walletSetup.setAvailableRateValues("152, 4, 125");
        walletSetup.parseAvailableRates();

        walletSetup.setDepositMinFee(10d);
        walletSetup.setDepositMaxFee(1000d);
        walletSetup.setDepositFeePercent(1.5d);

        walletSetup.setExchangeDepositMinFee(10d);
        walletSetup.setExchangeDepositMaxFee(1000d);
        walletSetup.setExchangeDepositFeePercent(1.5d);

        walletSetup.setWithdrawMinFee(10d);
        walletSetup.setWithdrawMaxFee(1000d);
        walletSetup.setWithdrawFeePercent(1.5d);

        walletSetup.setExchangeWithdrawMinFee(10d);
        walletSetup.setExchangeWithdrawMaxFee(1000d);
        walletSetup.setExchangeWithdrawFeePercent(1.5d);*/

        return walletSetup;
    }

    /*public static synchronized MerchantSetup initMerchantSetup (CurrencyType currencyType, long partitionId) {
        MerchantSetup walletSetup = new MerchantSetup();
        walletSetup.setId(1L);
        walletSetup.setCompanyId(partitionId);
        walletSetup.setCurrencyType(currencyType);
        walletSetup.setBalance(1000d);
        walletSetup.setAvailableRateValues("152, 4, 125");
        walletSetup.parseAvailableRates();

        walletSetup.setDepositMinFee(20d);
        walletSetup.setDepositMaxFee(2000d);
        walletSetup.setDepositFeePercent(2.5d);

        walletSetup.setExchangeDepositMinFee(20d);
        walletSetup.setExchangeDepositMaxFee(2000d);
        walletSetup.setExchangeDepositFeePercent(2.5d);

        walletSetup.setWithdrawMinFee(20d);
        walletSetup.setWithdrawMaxFee(2000d);
        walletSetup.setWithdrawFeePercent(2.5d);

        walletSetup.setExchangeWithdrawMinFee(20d);
        walletSetup.setExchangeWithdrawMaxFee(2000d);
        walletSetup.setExchangeWithdrawFeePercent(2.5d);

        return walletSetup;
    }*/

    public static synchronized ExchangeRate initExchangeRate() {
        ExchangeRate rate = new ExchangeRate();
        rate.setOneCurrency(CurrencyType.USD);
        rate.setToCurrency(CurrencyType.AMD);
        rate.setBuy(480d);
        rate.setSell(480d);
        rate.setUpdatedDate(new Date(System.currentTimeMillis()));
        rate.setId(452149L);
        return rate;
    }

}
