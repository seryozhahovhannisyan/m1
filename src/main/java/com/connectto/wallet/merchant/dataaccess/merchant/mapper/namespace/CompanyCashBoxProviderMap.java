package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CompanyCashBoxProviderMap {

    public void add(CompanyCashBoxProvider data);

    public CompanyCashBoxProvider getById(Long id);

    public List<CompanyCashBoxProvider> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(CompanyCashBoxProvider data);

    public void delete(Long id);

}
