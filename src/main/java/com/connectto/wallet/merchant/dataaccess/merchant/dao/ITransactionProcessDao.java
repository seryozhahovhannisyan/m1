package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcess;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcess;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface ITransactionProcessDao {

    public void add(TransactionWithdrawProcess data) throws DatabaseException;

    public void add(TransactionDepositProcess data) throws DatabaseException;

}
