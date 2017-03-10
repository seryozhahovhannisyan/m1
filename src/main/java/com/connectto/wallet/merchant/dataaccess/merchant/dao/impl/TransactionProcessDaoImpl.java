package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcess;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcess;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionProcessDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionProcessMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionProcessDaoImpl implements ITransactionProcessDao {

    private TransactionProcessMap map;

    public void setMap(TransactionProcessMap map) {
        this.map = map;
    }

    @Override
    public void add(TransactionWithdrawProcess data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDepositProcess data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
