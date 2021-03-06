package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.Branch;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BranchMap {

    public void add(Branch data);

    public Set<Branch> getByCompanyId(Long companyId);

    public Branch getById(Long id);

    public Branch getByIdFull(Long id);

    public List<Branch> getByParams(Map<String, Object> params);

    public List<Branch> getByParamsFull(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(Branch data);

    public void updateLogo(Branch data);

    public void activate(Branch data);

    public void delete(Long id);

    public void forceDelete(Long id);

}
