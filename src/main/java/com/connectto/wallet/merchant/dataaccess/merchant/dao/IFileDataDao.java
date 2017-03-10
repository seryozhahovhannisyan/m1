package com.connectto.wallet.merchant.dataaccess.merchant.dao;

import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IFileDataDao {

    public void add(FileData data) throws DatabaseException;

    public FileData getById(Long id) throws DatabaseException, EntityNotFoundException;

    public List<FileData> getByParams(Map<String, Object> params) throws DatabaseException;

    public void update(FileData data) throws DatabaseException, EntityNotFoundException;

    public void delete(FileData data) throws DatabaseException, EntityNotFoundException;

    public void forceDelete(Long id) throws DatabaseException, EntityNotFoundException;

}