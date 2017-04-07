package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface ICashierManager {

    public void add(Cashier data) throws InternalErrorException, EntityNotFoundException;

    public Cashier getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public CashierCashBox getCashierCashBoxByCashierId(Long cashierId) throws InternalErrorException, EntityNotFoundException;

    public Cashier getProfile(Long id) throws InternalErrorException, EntityNotFoundException;

    public Cashier login(String username, String password) throws InternalErrorException, EntityNotFoundException;

    public List<Cashier> getByParams(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public List<Cashier> getByParamsFull(Map<String, Object> params) throws InternalErrorException;

    public void update(Cashier data) throws InternalErrorException, EntityNotFoundException;

    public void delete(Long id) throws InternalErrorException, EntityNotFoundException;

    public void delete(List<Long> id) throws InternalErrorException, EntityNotFoundException;

    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException;
}
