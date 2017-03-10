package com.connectto.wallet.merchant.business.merchant;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICashierCashBoxProviderManager {

    public void add(CashierCashBoxProvider data) throws InternalErrorException;

    public CashierCashBoxProvider getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<CashierCashBoxProvider> getByParams(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(CashierCashBoxProvider data) throws InternalErrorException, EntityNotFoundException;

}
