package com.connectto.wallet.merchant.dataaccess.wallet.mapper.namespace;

import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;

import java.util.List;
import java.util.Map;

public interface ExchangeRateMap {

    public ExchangeRate getById(Long id);

    public List<ExchangeRate> getByParams(Map<String, Object> params);

}
