package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface IBranchManager {

    public void add(Branch data) throws InternalErrorException;

    public Branch getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public Branch getByIdFull(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<Branch> getByParams(Map<String, Object> params) throws InternalErrorException;

    public List<Branch> getByParamsFull(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(Branch data) throws InternalErrorException, EntityNotFoundException;

    public void delete(Long id) throws InternalErrorException, EntityNotFoundException;

    public void delete(List<Long> ides) throws InternalErrorException, EntityNotFoundException;

    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException;
}
