package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransactionWalletResult;
import com.connectto.wallet.merchant.common.exception.DataException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransferTransactionWalletResultDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransferTransactionWalletResultMap;

/**
 * Created by Serozh on 2/14/2017.
 */
public class TransferTransactionWalletResultDaoImpl implements ITransferTransactionWalletResultDao {

    private TransferTransactionWalletResultMap map;

    public void setMap(TransferTransactionWalletResultMap map) {
        this.map = map;
    }

    @Override
    public void add(TransferTransactionWalletResult data) throws DataException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DataException(e);
        }
    }

}
