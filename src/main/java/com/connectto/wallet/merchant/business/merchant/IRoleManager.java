package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface IRoleManager {

    public void add(Role data) throws InternalErrorException;

    public Role getById(Long id) throws InternalErrorException;

    public Role getLastRole(Long companyId) throws InternalErrorException;

    public List<Role> getByParams(Map<String, Object> params) throws InternalErrorException;

    public int getCountByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(Role data) throws InternalErrorException;

    public void delete(Long id) throws InternalErrorException;

    public void delete(List<Long> ides) throws InternalErrorException;
}
