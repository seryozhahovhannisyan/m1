package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

import java.util.List;
import java.util.Map;

public interface ITransactionDao {

    public void add(TransactionWithdraw data) throws DatabaseException;

    public void add(TransactionDeposit data) throws DatabaseException;

    public List<TransactionWithdraw> getWithdrawsByParams(Map<String, Object> params) throws DatabaseException;

    public List<TransactionDeposit> getDepositsByParams(Map<String, Object> params) throws DatabaseException;

    public int getWithdrawsCountByParams(Map<String, Object> params) throws DatabaseException;

    public int getDepositsCountByParams(Map<String, Object> params) throws DatabaseException;

    public void close(TransactionWithdraw data) throws DatabaseException;

    public void close(TransactionDeposit data) throws DatabaseException;

}
