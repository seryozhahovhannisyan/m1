package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawTax;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionTaxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.RoleMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionTaxMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class TransactionTaxDaoImpl implements ITransactionTaxDao{

    private TransactionTaxMap map;

    public void setMap(TransactionTaxMap map) {
        this.map = map;
    }

    @Override
    public void add(TransactionWithdrawTax data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(TransactionDepositTax data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
