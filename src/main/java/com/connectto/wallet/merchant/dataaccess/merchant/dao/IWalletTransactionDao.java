package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdraw;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

public interface IWalletTransactionDao {

    public void add(WalletWithdraw data) throws DatabaseException;

    public void add(WalletDeposit data) throws DatabaseException;

/*    public void markTransaction(WalletWithdraw data) throws DatabaseException, EntityNotFoundException;

    public void markTransaction(WalletDeposit data) throws DatabaseException, EntityNotFoundException;*/

}
