package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICompanyCashBoxDao {

    public void add(CompanyCashBox data) throws DatabaseException;

    public CompanyCashBox getById(Long id) throws DatabaseException, EntityNotFoundException;

    public CompanyCashBox getByCompanyId(Long companyId) throws DatabaseException, EntityNotFoundException;

    public List<CompanyCashBox> getByParams(Map<String, Object> params) throws DatabaseException;

    public String getAvailableRateValues(Map<String, Object> params) throws DatabaseException;

    public void update(CompanyCashBox data) throws DatabaseException, EntityNotFoundException;

    public void activate(CompanyCashBox data) throws DatabaseException, EntityNotFoundException;

    public void delete(CompanyCashBox data) throws DatabaseException, EntityNotFoundException;

    public  void forceDelete(Long id) throws DatabaseException, EntityNotFoundException;
}
