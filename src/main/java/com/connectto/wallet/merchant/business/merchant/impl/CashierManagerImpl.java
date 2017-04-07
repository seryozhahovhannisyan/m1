package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.FileDataUtil;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICashierCashBoxDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICashierDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IFileDataDao;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.IRoleDao;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
public class CashierManagerImpl implements ICashierManager {

    private ICashierDao dao;
    private ICashierCashBoxDao cashierCashBoxDao;
    private IFileDataDao fileDataDao;
    private IRoleDao roleDao;

    public void setDao(ICashierDao dao) {
        this.dao = dao;
    }

    public void setCashierCashBoxDao(ICashierCashBoxDao cashierCashBoxDao) {
        this.cashierCashBoxDao = cashierCashBoxDao;
    }

    public void setFileDataDao(IFileDataDao fileDataDao) {
        this.fileDataDao = fileDataDao;
    }

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(Cashier data) throws InternalErrorException, EntityNotFoundException {
        try {

            Long roleId = data.getRoleId();
            Role role = roleDao.getById(roleId);
            if(role == null){
                throw new EntityNotFoundException("Could not found role by id = " +roleId);
            }

            CashierCashBox defaultCashBox = data.getCurrentCashBox();
            List<FileData> cashierFileDatas = data.getFileDatas();

            dao.add(data);
            defaultCashBox.setCashierId(data.getId());
            cashierCashBoxDao.add(defaultCashBox);

            //cashierFileDatas
            if (!Utils.isEmpty(cashierFileDatas)) {
                FileData d = cashierFileDatas.get(0);
                d.setCashierId(data.getId());

                String fileName = d.getFileName();
                String extension = fileName.substring(fileName.indexOf("."));
                //
                fileName = String.format(FileDataUtil.LOGO_FORMAT, FileDataUtil.LOGO_PREFIX_CASHIER, data.getId(), extension);
                d.setFileName(fileName);

                fileDataDao.add(d);

                FileDataUtil.createFileCompany(fileName,  d.getData());
                data.setLogo(fileName);
                dao.updateLogo(data);
            }
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Cashier getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CashierCashBox getCashierCashBoxByCashierId(Long cashierId) throws InternalErrorException, EntityNotFoundException {
        try {
            return cashierCashBoxDao.getCurrentCashBox(cashierId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Cashier getProfile(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }



    @Override
    public Cashier login(String username, String password) throws InternalErrorException, EntityNotFoundException {
        username = username.trim().toLowerCase();
        password = password.trim().toLowerCase();
        try {
            password = SHAHashEnrypt.get_MD5_SecurePassword(password);
            return dao.login(username, password);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EncryptException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Cashier> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public int getCountByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getCountByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Cashier> getByParamsFull(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParamsFull(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }



    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void update(Cashier data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void delete(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void delete(List<Long> ides) throws InternalErrorException, EntityNotFoundException {
        try {
            for (Long id : ides){
                dao.delete(id);
            }
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
