package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.IFileDataManager;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IFileDataDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
public class FileDataManagerImpl implements IFileDataManager {

    private IFileDataDao dao;

    public void setDao(IFileDataDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(FileData data) throws InternalErrorException {
        try {
            dao.add(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public FileData getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<FileData> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void update(FileData data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void delete(FileData data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.delete(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.forceDelete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
