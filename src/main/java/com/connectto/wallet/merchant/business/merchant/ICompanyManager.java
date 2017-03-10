package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface ICompanyManager {

    public void add(Company data) throws InternalErrorException;

    public Company getById(Long id) throws InternalErrorException, EntityNotFoundException;

     public Company getByName(String name) throws InternalErrorException, EntityNotFoundException;

    public List<Company> getByParamsFull(Map<String, Object> params) throws InternalErrorException;

    public List<Company> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(Company data) throws InternalErrorException, EntityNotFoundException;

    public void delete(Company data) throws InternalErrorException, EntityNotFoundException;

    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException;
}
