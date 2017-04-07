package com.connectto.wallet.merchant.dataaccess.merchant.dao;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IBranchCashBoxDao {

    public void add(BranchCashBox data) throws DatabaseException;

    public BranchCashBox getById(Long id) throws DatabaseException, EntityNotFoundException;

    public BranchCashBox getBranchCurrentCashBox(Long branchId) throws DatabaseException, EntityNotFoundException;

    public List<BranchCashBox> getByParams(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(BranchCashBox data) throws DatabaseException, EntityNotFoundException;

    public void provideAmount(Map<String, Object> params) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

}
