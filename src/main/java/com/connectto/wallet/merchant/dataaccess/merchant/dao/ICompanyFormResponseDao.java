package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.CompanyFormResponse;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;


public interface ICompanyFormResponseDao {

    @Deprecated
    public void add(CompanyFormResponse companyFormResponse) throws DatabaseException;

    public CompanyFormResponse getById(Long id) throws DatabaseException, EntityNotFoundException;

}
