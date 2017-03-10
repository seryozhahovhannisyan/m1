package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBoxTaker;

import java.util.List;
import java.util.Map;

public interface CompanyCashBoxTakerMap {

    public void add(CompanyCashBoxTaker data);

    public CompanyCashBoxTaker getById(Long id);

    public List<CompanyCashBoxTaker> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(CompanyCashBoxTaker data);

    public void delete(Long id);

}
