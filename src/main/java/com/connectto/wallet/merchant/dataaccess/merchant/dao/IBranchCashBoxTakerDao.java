package com.connectto.wallet.merchant.dataaccess.merchant.dao;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBoxTaker;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IBranchCashBoxTakerDao {

    public void add(List<BranchCashBoxTaker> data) throws DatabaseException;

    public BranchCashBoxTaker getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<BranchCashBoxTaker> getByParams(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(BranchCashBoxTaker data) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

}
