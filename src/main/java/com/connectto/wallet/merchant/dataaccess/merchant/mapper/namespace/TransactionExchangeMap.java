package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchange;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchange;

public interface TransactionExchangeMap {

    public void addWithdraw(TransactionWithdrawExchange data);

    public void addDeposit(TransactionDepositExchange data);

}
