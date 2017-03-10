package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxProvider;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyCashBoxProviderDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.CompanyCashBoxProviderMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CompanyCashBoxProviderDao implements ICompanyCashBoxProviderDao {

    private CompanyCashBoxProviderMap map;

    public void setMap(CompanyCashBoxProviderMap map) {
        this.map = map;
    }

    @Override
    public void add(CompanyCashBoxProvider data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public CompanyCashBoxProvider getById(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            CompanyCashBoxProvider data = map.getById(id);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found CompanyCashBoxProvider id=[%d]",id));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<CompanyCashBoxProvider> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int getCountByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getCountByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(CompanyCashBoxProvider data) throws DatabaseException, EntityNotFoundException {
        try {
            map.update(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            map.delete(id);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

}
