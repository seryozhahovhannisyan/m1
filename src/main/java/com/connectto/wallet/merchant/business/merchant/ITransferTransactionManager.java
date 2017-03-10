package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransaction;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.common.exception.HttpConnectionDeniedException;
import com.connectto.wallet.merchant.common.exception.WalletApiException;

/**
 * Created by Serozh on 2/14/2017.
 */
public interface ITransferTransactionManager {

    public void add(TransferTransaction data) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException, HttpConnectionDeniedException, WalletApiException;

}
