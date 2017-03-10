package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICompanyDao {

    public void add(Company data) throws DatabaseException;

    public void addAdminTest(Company data) throws DatabaseException;

    public Company getById(Long id) throws DatabaseException, EntityNotFoundException;

    public Company getByName(String name) throws DatabaseException, EntityNotFoundException;

    public Company signIn(Map<String, Object> params) throws DatabaseException, EntityNotFoundException;

    public List<Company> getByParamsFull(Map<String, Object> params) throws DatabaseException;

    public List<Company> getByParams(Map<String, Object> params) throws DatabaseException;

    public String getAllowedPartitionValues(Map<String, Object> params) throws DatabaseException;

    public void update(Company data) throws DatabaseException, EntityNotFoundException;

    public void activate(Company data) throws DatabaseException, EntityNotFoundException;

    public void delete(Company data) throws DatabaseException, EntityNotFoundException;

    public  void forceDelete(Long id) throws DatabaseException, EntityNotFoundException;
}
