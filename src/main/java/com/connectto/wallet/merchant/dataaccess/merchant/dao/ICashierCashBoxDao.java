package com.connectto.wallet.merchant.dataaccess.merchant.dao;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICashierCashBoxDao {

    public void add(CashierCashBox data) throws DatabaseException;

    public CashierCashBox getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<CashierCashBox> getByParams(Map<String, Object> params) throws DatabaseException;

    public int getCountByParams(Map<String, Object> params) throws DatabaseException;

    public void update(CashierCashBox data) throws DatabaseException, EntityNotFoundException;

    public void delete(Long id) throws DatabaseException, EntityNotFoundException;

}
