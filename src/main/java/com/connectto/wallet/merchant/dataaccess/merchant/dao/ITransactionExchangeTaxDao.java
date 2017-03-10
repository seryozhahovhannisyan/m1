package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchangeTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchangeTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface ITransactionExchangeTaxDao {

    public void add(TransactionWithdrawExchangeTax data) throws DatabaseException;

    public void add(TransactionDepositExchangeTax data) throws DatabaseException;

}
