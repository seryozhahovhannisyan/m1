package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;

import java.util.List;
import java.util.Map;

public interface CashierCashBoxProviderMap {

    public void add(List<CashierCashBoxProvider> data);

    public CashierCashBoxProvider getById(Long id);

    public CashierCashBoxProvider getCurrentByCashierCashBoxId(Long cashierCashBoxId);

    public List<CashierCashBoxProvider> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(CashierCashBoxProvider data);

    public void delete(Long id);

}
