package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface ICompanyCashBoxManager {

    public void add(CompanyCashBox data) throws InternalErrorException;

    public CompanyCashBox getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public CompanyCashBox getByCompanyId(Long companyId) throws InternalErrorException, EntityNotFoundException;

    public List<CompanyCashBox> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(CompanyCashBox data) throws InternalErrorException, EntityNotFoundException;

    public void delete(CompanyCashBox data) throws InternalErrorException, EntityNotFoundException;

    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException;
}
