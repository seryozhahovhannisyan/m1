package com.connectto.wallet.merchant.dataaccess.wallet.dao;

import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Created by htdev001 on 8/26/14.
 */
public interface IWalletDao {

    public Wallet getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<Wallet> getByParams(Map<String, Object> params) throws DatabaseException;

    public Integer getCountByParams(Map<String, Object> params) throws DatabaseException;

}
