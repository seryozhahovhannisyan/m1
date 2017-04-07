package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICashierDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.CashierMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CashierDao implements ICashierDao {

    private CashierMap map;

    public void setMap(CashierMap map) {
        this.map = map;
    }

    @Override
    public void add(Cashier data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Cashier getById(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            Cashier data = map.getById(id);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found Cashier id=[%d]",id));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Cashier login(String username, String password) throws DatabaseException, EntityNotFoundException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username",username);
        params.put("password",password);
        try {
            Cashier data = map.login(params);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found Cashier username=[%s], password=[%s]",username, password));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }



    @Override
    public List<Cashier> getByParams(Map<String, Object> params) throws DatabaseException {
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
    public List<Cashier> getByParamsFull(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getByParamsFull(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Cashier data) throws DatabaseException, EntityNotFoundException {
        try {
            map.update(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateLogo(Cashier data) throws DatabaseException, EntityNotFoundException {
        try {
            map.updateLogo(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void activate(Cashier data) throws DatabaseException, EntityNotFoundException {
        try {
            map.activate(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void forceDelete(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            map.forceDelete(id);
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
