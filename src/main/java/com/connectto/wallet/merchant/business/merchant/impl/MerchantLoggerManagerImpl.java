package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.IMerchantLoggerManager;
import com.connectto.wallet.merchant.common.data.merchant.MerchantLogger;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IMerchantLoggerDao;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class MerchantLoggerManagerImpl implements IMerchantLoggerManager {

    private IMerchantLoggerDao dao;

    public void setDao(IMerchantLoggerDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(MerchantLogger data) throws InternalErrorException {
        try {
            dao.add(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
