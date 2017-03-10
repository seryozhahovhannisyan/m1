package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBoxProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BranchCashBoxProviderMap {

    public void add(List<BranchCashBoxProvider> data);

    public BranchCashBoxProvider getById(Long id);

    public BranchCashBoxProvider getCurrentByBranchCashBoxId(Long branchCashBoxId);

    public Set<BranchCashBoxProvider> getAllByBranchCashBoxId(Long branchCashBoxId);

    public List<BranchCashBoxProvider> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(BranchCashBoxProvider data);

    public void delete(Long id);

}
