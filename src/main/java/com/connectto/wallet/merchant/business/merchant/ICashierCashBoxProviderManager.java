package com.connectto.wallet.merchant.business.merchant;


import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICashierCashBoxProviderManager {

    public void add(Long cashierId,
                    Long branchId,
                    Double provideAmount,
                    CurrencyType provideCurrencyType,
                    Set<Long> provideIdes) throws InternalErrorException, PermissionDeniedException;

    public CashierCashBoxProvider getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<CashierCashBoxProvider> getByParams(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(CashierCashBoxProvider data) throws InternalErrorException, EntityNotFoundException;

}
