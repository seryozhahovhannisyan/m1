package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDepositProcess;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdrawProcess;

public interface TransactionProcessMap {

    public void addWithdraw(TransactionWithdrawProcess data);

    public void addDeposit(TransactionDepositProcess data);

}
