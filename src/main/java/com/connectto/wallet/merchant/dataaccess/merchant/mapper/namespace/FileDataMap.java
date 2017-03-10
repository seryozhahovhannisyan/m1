package com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace;

import com.connectto.wallet.merchant.common.data.merchant.FileData;

import java.util.List;
import java.util.Map;

public interface FileDataMap {

    public void add(FileData data);

    public FileData getById(Long id);

    public List<FileData> getByParams(Map<String, Object> params);

    public void update(FileData data);

    public void delete(FileData data);

    public void forceDelete(Long id);

}
