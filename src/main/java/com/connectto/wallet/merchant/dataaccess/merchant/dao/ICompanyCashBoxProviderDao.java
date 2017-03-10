package com.connectto.wallet.merchant.dataaccess.merchant.dao;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxProvider;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICompanyCashBoxProviderDao {

    public void add(CompanyCashBoxProvider data) throws DatabaseException;

    public CompanyCashBoxProvider getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<CompanyCashBoxProvider> getByParams(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(CompanyCashBoxProvider data) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

}
