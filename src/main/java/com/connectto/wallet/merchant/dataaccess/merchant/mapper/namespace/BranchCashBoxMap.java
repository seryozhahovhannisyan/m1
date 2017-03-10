package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;


import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;

import java.util.List;
import java.util.Map;

public interface BranchCashBoxMap {

    public void add(BranchCashBox data);

    public BranchCashBox getById(Long id);

    public BranchCashBox getBranchCurrentCashBox(Long branchId);

    public List<BranchCashBox> getByParams(Map<String, Object> params);

    public int getCountByParams(Map<String, Object> params);

    public void update(BranchCashBox data);

    public void provideAmount(Map<String, Object> params) ;

    public void delete(Long id);

}
