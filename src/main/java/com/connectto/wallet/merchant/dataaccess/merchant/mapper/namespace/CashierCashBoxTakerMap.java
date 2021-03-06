package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxTaker;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CashierCashBoxTakerMap {

    public void add(List<CashierCashBoxTaker> data);

    public CashierCashBoxTaker getById(Long id);

    public Set<CashierCashBoxTaker> getByProviderId(Long id);

    public List<CashierCashBoxTaker> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(CashierCashBoxTaker data);

    public void delete(Long id);

}
