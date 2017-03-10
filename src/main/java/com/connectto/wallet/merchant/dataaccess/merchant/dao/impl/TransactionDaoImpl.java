package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionDaoImpl implements ITransactionDao {

    private TransactionMap map;

    public void setMap(TransactionMap map) {
        this.map = map;
    }


    @Override
    public void add(TransactionWithdraw data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDeposit data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<TransactionWithdraw> getWithdrawsByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getWithdrawsByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<TransactionDeposit> getDepositsByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getDepositsByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int getWithdrawsCountByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getWithdrawsCountByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int getDepositsCountByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getDepositsCountByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close(TransactionWithdraw data) throws DatabaseException {
        try {
            map.closeWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close(TransactionDeposit data) throws DatabaseException {
        try {
            map.closeDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
