package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawTax;

public interface TransactionTaxMap {

    public void addWithdraw(TransactionWithdrawTax data);

    public void addDeposit(TransactionDepositTax data);

}
