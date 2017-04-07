package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.CompanyMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CompanyDao implements ICompanyDao {

    private CompanyMap map;

    public void setMap(CompanyMap map) {
        this.map = map;
    }

    @Override
    public void add(Company data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addAdminTest(Company data) throws DatabaseException {
        try {
            map.addAdminTest(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Company getById(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            Company data = map.getById(id);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found Company id=[%d]", id));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Company getByName(String name) throws DatabaseException, EntityNotFoundException {
        try {
            Company data = map.getByName(name);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found Company name=[%s]", name));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Company signIn(Map<String, Object> params) throws DatabaseException, EntityNotFoundException {
        try {
            Company data = map.signIn(params);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not sign in Company params=[%s]", params));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Company> getByParamsFull(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getByParamsFull(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Company> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public String getAllowedPartitionValues(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getAllowedPartitionValues(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }


    @Override
    public void update(Company data) throws DatabaseException, EntityNotFoundException {
        try {
            map.update(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateLogo(Company data) throws DatabaseException, EntityNotFoundException {
        try {
            map.updateLogo(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void activate(Company data) throws DatabaseException, EntityNotFoundException {
        try {
            map.activate(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Company data) throws DatabaseException, EntityNotFoundException {
        try {
            map.delete(data);
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

}
