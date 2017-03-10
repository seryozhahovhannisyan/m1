package com.connectto.wallet.merchant.dataaccess.wallet.dao.impl;

import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.dataaccess.wallet.dao.IWalletDao;
import com.connectto.wallet.merchant.dataaccess.wallet.mapper.namespace.WalletMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 7/8/2016.
 */
public class WalletDao implements IWalletDao {

    private WalletMap map;

    public void setMap(WalletMap map) {
        this.map = map;
    }

    @Override
    public Wallet getById(Long id) throws DatabaseException, EntityNotFoundException {
        try {
            Wallet data = map.getById(id);
            if (data == null) {
                throw new EntityNotFoundException(String.format("Could not found Wallet id=[%d]",id));
            }
            return data;
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Wallet> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Integer getCountByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return map.getCountByParams(params);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
