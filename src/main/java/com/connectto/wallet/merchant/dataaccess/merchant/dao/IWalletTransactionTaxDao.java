package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdrawTax;
import com.connectto.wallet.merchant.common.exception.DataException;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

public interface IWalletTransactionTaxDao {

    public void add(WalletWithdrawTax data) throws DatabaseException;

    public void add(WalletDepositTax data) throws DatabaseException;

    public void markTransaction(WalletWithdrawTax data) throws DataException;

    public void markTransaction(WalletDepositTax data) throws DataException;

}
