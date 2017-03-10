package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.CompanyFormRequest;
import com.connectto.wallet.merchant.common.exception.DatabaseException;

import java.util.List;
import java.util.Map;

public interface ICompanyFormRequestDao {

    public void add(CompanyFormRequest data) throws DatabaseException;

    public CompanyFormRequest getById(Long id) throws DatabaseException;

    public List<CompanyFormRequest> getByParams(Map<String, Object> params) throws DatabaseException;

    public void activate(CompanyFormRequest companyFormRequest) throws DatabaseException;

    public void changeStatus(CompanyFormRequest data) throws DatabaseException;

}
