package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdraw;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IWalletTransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.WalletTransactionMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class WalletTransactionDaoImpl implements IWalletTransactionDao {

    private WalletTransactionMap map;

    public void setMap(WalletTransactionMap map) {
        this.map = map;
    }

    @Override
    public void add(WalletWithdraw data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(WalletDeposit data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    /*@Override
    public void markTransaction(WalletWithdraw data) throws DatabaseException, EntityNotFoundException {
        try {
            map.markTransactionWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void markTransaction(WalletDeposit data) throws DatabaseException, EntityNotFoundException {
        try {
            map.markTransactionDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }*/
}
