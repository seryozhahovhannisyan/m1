package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

/**
 * Created by Serozh on 2/14/2017.
 */
public interface TransferTransactionMap {

    public void add(TransferTransaction data);
    public void markTransaction(TransferTransaction data);

}
