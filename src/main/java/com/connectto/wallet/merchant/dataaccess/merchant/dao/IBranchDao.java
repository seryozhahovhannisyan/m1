package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IBranchDao {

    public void add(Branch data) throws DatabaseException;

    public Branch getById(Long id) throws DatabaseException, EntityNotFoundException;

    public Branch getByIdFull(Long id) throws DatabaseException, EntityNotFoundException;

    public List<Branch> getByParams(Map<String, Object> params) throws DatabaseException;

    public List<Branch> getByParamsFull(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(Branch data) throws DatabaseException, EntityNotFoundException;

    public void updateLogo(Branch data) throws DatabaseException, EntityNotFoundException;

    public void activate(Branch data) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

    public void forceDelete(Long id) throws DatabaseException, EntityNotFoundException;

}
