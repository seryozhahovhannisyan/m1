package com.connectto.wallet.merchant.business.wallet;

import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

/**
 * Created by htdev001 on 8/26/14.
 */
public interface IExchangeRateManager {

    public ExchangeRate getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<ExchangeRate> getByParams(Map<String, Object> params) throws InternalErrorException;

}
