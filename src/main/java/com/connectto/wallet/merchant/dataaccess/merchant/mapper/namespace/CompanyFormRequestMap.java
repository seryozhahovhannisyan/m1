package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.CompanyFormRequest;

import java.util.List;
import java.util.Map;

public interface CompanyFormRequestMap {

    public void add(CompanyFormRequest data);

    public CompanyFormRequest getById(Long id);

    public List<CompanyFormRequest> getByParams(Map<String, Object> params);

    public void activateCompany(CompanyFormRequest companyFormRequest);

    public void changeStatus(CompanyFormRequest data);

}
