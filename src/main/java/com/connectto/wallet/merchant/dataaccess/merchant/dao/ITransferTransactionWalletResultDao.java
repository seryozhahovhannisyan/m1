package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransactionWalletResult;
import com.connectto.wallet.merchant.common.exception.DataException;

/**
 * Created by Serozh on 2/14/2017.
 */
public interface ITransferTransactionWalletResultDao {

    public void add(TransferTransactionWalletResult data) throws DataException;

}
