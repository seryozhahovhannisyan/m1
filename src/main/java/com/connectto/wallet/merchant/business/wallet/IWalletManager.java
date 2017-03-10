package com.connectto.wallet.merchant.business.wallet;

import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

/**
 * Created by htdev001 on 8/26/14.
 */
public interface IWalletManager {

    public Wallet getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<Wallet> getByParams(Map<String, Object> params) throws InternalErrorException;

    public Integer getCountByParams(Map<String, Object> params) throws InternalErrorException;

}
