package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface ITransactionTaxDao {

    public void add(TransactionWithdrawTax data) throws DatabaseException;

    public void add(TransactionDepositTax data) throws DatabaseException;

}
