package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.MerchantLogger;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

public interface IMerchantLoggerDao {

    public void add(MerchantLogger data) throws DatabaseException;
}
