package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchange;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchange;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface ITransactionExchangeDao {

    public void add(TransactionWithdrawExchange data) throws DatabaseException;

    public void add(TransactionDepositExchange data) throws DatabaseException;

}
