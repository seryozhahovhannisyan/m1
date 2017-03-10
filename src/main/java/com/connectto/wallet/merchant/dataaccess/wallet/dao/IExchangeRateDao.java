package com.connectto.wallet.merchant.dataaccess.wallet.dao;

import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IExchangeRateDao {

    public ExchangeRate getById(Long id) throws DatabaseException, EntityNotFoundException;;

    public List<ExchangeRate> getByParams(Map<String, Object> params) throws DatabaseException;;

}
