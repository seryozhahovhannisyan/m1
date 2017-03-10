package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcessTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcessTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionProcessTaxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionProcessTaxMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionProcessTaxDaoImpl implements ITransactionProcessTaxDao {

    private TransactionProcessTaxMap map;

    public void setMap(TransactionProcessTaxMap map) {
        this.map = map;
    }


    @Override
    public void add(TransactionWithdrawProcessTax data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDepositProcessTax data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
