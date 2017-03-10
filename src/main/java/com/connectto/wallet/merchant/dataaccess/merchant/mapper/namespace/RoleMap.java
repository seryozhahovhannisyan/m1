package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.Role;

import java.util.List;
import java.util.Map;

public interface RoleMap {

    public void add(Role data);

    public Role getById(Long id);

    public Role getLastRole(Long companyId);

    public List<Role> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(Role data);

    public void delete(Long id);

}
