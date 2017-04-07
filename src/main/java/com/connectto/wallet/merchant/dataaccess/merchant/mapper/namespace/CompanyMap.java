package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.Company;

import java.util.List;
import java.util.Map;

public interface CompanyMap {

    public void add(Company data);

     public void addAdminTest(Company data);

    public Company getById(Long id);

    public Company getByName(String name);

    public Company signIn(Map<String, Object> params);

    public List<Company> getByParamsFull(Map<String, Object> params);

    public List<Company> getByParams(Map<String, Object> params);

    public String getAllowedPartitionValues(Map<String, Object> params);

    public void update(Company data);

    public void updateLogo(Company data);

    public void activate(Company data);

    public void delete(Company data);

    public void forceDelete(Long id);

}
