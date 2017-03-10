package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositExchangeTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawExchangeTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionExchangeTaxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionExchangeMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionExchangeTaxMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionExchangeTaxDaoImpl implements ITransactionExchangeTaxDao {

    private TransactionExchangeTaxMap map;

    public void setMap(TransactionExchangeTaxMap map) {
        this.map = map;
    }

    @Override
    public void add(TransactionWithdrawExchangeTax data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDepositExchangeTax data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
