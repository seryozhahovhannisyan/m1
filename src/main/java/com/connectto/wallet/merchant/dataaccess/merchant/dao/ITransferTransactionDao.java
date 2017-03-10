package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.exception.DataException;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

/**
 * Created by Serozh on 2/14/2017.
 */
public interface ITransferTransactionDao {

    public void add(TransferTransaction data) throws DatabaseException;

    public void markTransaction(TransferTransaction data) throws DataException;

}
