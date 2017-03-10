package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcessTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcessTax;

public interface TransactionProcessTaxMap {

    public void addWithdraw(TransactionWithdrawProcessTax data);

    public void addDeposit(TransactionDepositProcessTax data);

}
