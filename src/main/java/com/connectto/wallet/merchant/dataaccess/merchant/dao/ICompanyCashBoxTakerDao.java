package com.connectto.wallet.merchant.dataaccess.merchant.dao;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxTaker;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICompanyCashBoxTakerDao {

    public void add(CompanyCashBoxTaker data) throws DatabaseException;

    public CompanyCashBoxTaker getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<CompanyCashBoxTaker> getByParams(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(CompanyCashBoxTaker data) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

}
