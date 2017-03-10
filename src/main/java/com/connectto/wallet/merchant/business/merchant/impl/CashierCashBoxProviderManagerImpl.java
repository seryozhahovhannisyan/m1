package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.ICashierCashBoxProviderManager;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICashierCashBoxProviderDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
public class CashierCashBoxProviderManagerImpl implements ICashierCashBoxProviderManager {

    private ICashierCashBoxProviderDao dao;

    public void setDao(ICashierCashBoxProviderDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(CashierCashBoxProvider data) throws InternalErrorException {
        try {
            dao.add(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CashierCashBoxProvider getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CashierCashBoxProvider> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public int getCountByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getCountByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void update(CashierCashBoxProvider data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    } 


}
