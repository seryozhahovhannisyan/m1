package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchangeTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchangeTax;

public interface TransactionExchangeTaxMap {

    public void addWithdraw(TransactionWithdrawExchangeTax data);

    public void addDeposit(TransactionDepositExchangeTax data);

}
