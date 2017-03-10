package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdrawTax;
import com.connectto.wallet.merchant.common.exception.DataException;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransactionDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IWalletTransactionTaxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransactionMap;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.WalletTransactionTaxMap;

/**
 * Created by Serozh on 1/16/2017.
 */
public class WalletTransactionTaxDaoImpl implements IWalletTransactionTaxDao {

    private WalletTransactionTaxMap map;

    public void setMap(WalletTransactionTaxMap map) {
        this.map = map;
    }

    @Override
    public void add(WalletWithdrawTax data) throws DatabaseException {
        try {
            map.addWithdraw(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void add(WalletDepositTax data) throws DatabaseException {
        try {
            map.addDeposit(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void markTransaction(WalletWithdrawTax data) throws DataException {
        try {
            map.markTransactionWithdraw(data);
        } catch (RuntimeException e) {
            throw new DataException(e);
        }
    }

    @Override
    public void markTransaction(WalletDepositTax data) throws DataException {
        try {
            map.markTransactionDeposit(data);
        } catch (RuntimeException e) {
            throw new DataException(e);
        }
    }
}
