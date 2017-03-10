package com.connectto.wallet.merchant.business.wallet.impl;

import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyCashBoxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyDao;
import com.connectto.wallet.merchant.dataaccess.wallet.dao.IWalletDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by htdev001 on 8/26/14.
 */
@Transactional(readOnly = true)
public class WalletManagerImpl implements IWalletManager {

    private IWalletDao walletDao;
    private ICompanyCashBoxDao companyCashBoxDao;
    private ICompanyDao companyDao;

    public void setWalletDao(IWalletDao walletDao) {
        this.walletDao = walletDao;
    }

    public void setCompanyCashBoxDao(ICompanyCashBoxDao companyCashBoxDao) {
        this.companyCashBoxDao = companyCashBoxDao;
    }

    public void setCompanyDao(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Wallet getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return walletDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Wallet> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            String availableRateValues = companyCashBoxDao.getAvailableRateValues(params);
            if(!Utils.isEmpty(availableRateValues)){
                DataConverter.parseAvailableRates(availableRateValues);
                params.put("availableRateValues", availableRateValues);
            }

            String allowedPartitionValues = companyDao.getAllowedPartitionValues(params);
            if(!Utils.isEmpty(allowedPartitionValues)){
                DataConverter.parseAllowedPartitions(allowedPartitionValues);
                params.put("allowedPartitionValues", allowedPartitionValues);
            }
            return walletDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (DataParseException e) {
            throw new InternalErrorException(e);
        } catch (NullPointerException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Integer getCountByParams(Map<String, Object> params) throws InternalErrorException {
        try {

            String availableRateValues = companyCashBoxDao.getAvailableRateValues(params);
            if(!Utils.isEmpty(availableRateValues)){
                DataConverter.parseAvailableRates(availableRateValues);
                params.put("availableRateValues", availableRateValues);
            }

            String allowedPartitionValues = companyDao.getAllowedPartitionValues(params);
            if(!Utils.isEmpty(allowedPartitionValues)){
                DataConverter.parseAllowedPartitions(allowedPartitionValues);
                params.put("allowedPartitionValues", allowedPartitionValues);
            }

            return walletDao.getCountByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (DataParseException e) {
            throw new InternalErrorException(e);
        } catch (NullPointerException e) {
            throw new InternalErrorException(e);
        }
    }
}
