package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdraw;

public interface WalletTransactionMap {

    public void addWithdraw(WalletWithdraw data);

    public void addDeposit(WalletDeposit data);

//    public void markTransactionWithdraw(WalletWithdraw data);
//
//    public void markTransactionDeposit(WalletDeposit data);

}
