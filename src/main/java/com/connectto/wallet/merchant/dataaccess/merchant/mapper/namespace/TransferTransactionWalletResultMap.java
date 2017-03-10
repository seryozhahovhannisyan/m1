package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransactionWalletResult;

/**
 * Created by Serozh on 2/14/2017.
 */
public interface TransferTransactionWalletResultMap {

    public void add(TransferTransactionWalletResult data);

}
