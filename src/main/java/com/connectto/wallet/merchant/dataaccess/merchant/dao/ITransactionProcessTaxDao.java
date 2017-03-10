package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcessTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcessTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface ITransactionProcessTaxDao {

    public void add(TransactionWithdrawProcessTax data) throws DatabaseException;

    public void add(TransactionDepositProcessTax data) throws DatabaseException;

}
