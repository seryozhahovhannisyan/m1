package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

import java.util.List;
import java.util.Map;

public interface IFileDataManager {

    public void add(FileData data) throws InternalErrorException;

    public FileData getById(Long id) throws InternalErrorException, EntityNotFoundException;

    public List<FileData> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(FileData data) throws InternalErrorException, EntityNotFoundException;

    public void delete(FileData data) throws InternalErrorException, EntityNotFoundException;

    public void forceDelete(Long id) throws InternalErrorException, EntityNotFoundException;

}