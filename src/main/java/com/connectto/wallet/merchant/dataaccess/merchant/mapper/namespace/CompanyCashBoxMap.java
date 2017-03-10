package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;

import java.util.List;
import java.util.Map;

public interface CompanyCashBoxMap {

    public void add(CompanyCashBox data);

    public CompanyCashBox getById(Long id);

    public CompanyCashBox getByCompanyId(Long companyId);

    public CompanyCashBox getLastByCompanyId(Long id);

    public String getAvailableRateValues(Map<String, Object> params);

    public List<CompanyCashBox> getByParams(Map<String, Object> params);

    public void update(CompanyCashBox data);

    public void activate(CompanyCashBox data);

    public void delete(CompanyCashBox data);

    public void forceDelete(Long id);

}
