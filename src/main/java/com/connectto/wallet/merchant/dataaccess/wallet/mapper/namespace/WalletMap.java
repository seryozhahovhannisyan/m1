package com.connectto.wallet.merchant.dataaccess.wallet.mapper.namespace;

import com.connectto.wallet.merchant.common.data.wallet.Wallet;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 7/8/2016.
 */
public interface WalletMap {

    public Wallet getById(Long id);

    public List<Wallet> getByParams(Map<String, Object> params);

    public Integer getCountByParams(Map<String, Object> params) ;

}
