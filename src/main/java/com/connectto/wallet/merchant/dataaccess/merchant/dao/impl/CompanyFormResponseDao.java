package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;

import com.connectto.wallet.merchant.common.data.merchant.CompanyFormResponse;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyFormResponseDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.CompanyFormResponseMap;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CompanyFormResponseDao implements ICompanyFormResponseDao {

    private CompanyFormResponseMap map;

    public void setMap(CompanyFormResponseMap map) {
        this.map = map;
    }

    @Override
    public void add(CompanyFormResponse data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public CompanyFormResponse getById(Long id) throws DatabaseException {
        try {
            return map.getById(id);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

}
