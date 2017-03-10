package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdrawTax;

public interface WalletTransactionTaxMap {

    public void addWithdraw(WalletWithdrawTax data);

    public void addDeposit(WalletDepositTax data);

    public void markTransactionWithdraw(WalletWithdrawTax data);

    public void markTransactionDeposit(WalletDepositTax data);

}
