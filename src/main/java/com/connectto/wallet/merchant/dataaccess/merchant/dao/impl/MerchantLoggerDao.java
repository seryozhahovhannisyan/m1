package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.merchant.MerchantLogger;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IMerchantLoggerDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.MerchantLoggerMap;

/**
 * Created by Serozh on 6/21/2016.
 */
public class MerchantLoggerDao implements IMerchantLoggerDao {

    private MerchantLoggerMap map;

    public void setMap(MerchantLoggerMap map) {
        this.map = map;
    }

    @Override
    public void add(MerchantLogger data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

}
