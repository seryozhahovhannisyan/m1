package com.connectto.wallet.merchant.business.merchant;


import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxTaker;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICashierCashBoxTakerManager {

    public void add(Long cashierId,
                    Long branchId,
                    Double takeAmount,
                    CurrencyType takeCurrencyType,
                    Set<Long> takeIdes) throws InternalErrorException, PermissionDeniedException;;

    public CashierCashBoxTaker getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<CashierCashBoxTaker> getByParams(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(CashierCashBoxTaker data) throws InternalErrorException, EntityNotFoundException;

}
