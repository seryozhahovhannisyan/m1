package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.CompanyFormResponse;


public interface CompanyFormResponseMap {

    @Deprecated
    public void add(CompanyFormResponse companyFormResponse);

    public CompanyFormResponse getById(Long id);

}
