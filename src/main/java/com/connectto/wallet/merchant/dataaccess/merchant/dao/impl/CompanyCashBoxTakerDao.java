package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxTaker;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyCashBoxTakerDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.CompanyCashBoxTakerMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CompanyCashBoxTakerDao implements ICompanyCashBoxTakerDao {

    private CompanyCashBoxTakerMap map;

    public void setMap(CompanyCashBoxTakerMap map) {
        this.map = map;
    }

    @Override
    public void add(CompanyCashBoxTaker data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public CompanyCashBoxTaker getById(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            CompanyCashBoxTaker data = map.getById(id);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found CompanyCashBoxTaker id=[%d]",id));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<CompanyCashBoxTaker> getByParams(Map<String, Object> params) throws DatabaseException {
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
    public void update(CompanyCashBoxTaker data) throws DatabaseException, EntityNotFoundException {
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
