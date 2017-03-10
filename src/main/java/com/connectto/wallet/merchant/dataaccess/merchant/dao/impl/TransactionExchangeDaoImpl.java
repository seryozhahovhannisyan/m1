package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchange;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchange;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionExchangeDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionExchangeMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionExchangeDaoImpl implements ITransactionExchangeDao {

    private TransactionExchangeMap map;

    public void setMap(TransactionExchangeMap map) {
        this.map = map;
    }

    @Override
    public void add(TransactionWithdrawExchange data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDepositExchange data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
