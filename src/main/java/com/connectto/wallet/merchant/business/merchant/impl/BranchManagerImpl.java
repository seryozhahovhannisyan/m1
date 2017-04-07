package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.FileDataUtil;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
public class BranchManagerImpl implements IBranchManager {

    private IBranchDao dao;
    private IBranchCashBoxDao branchCashBoxDao;
    private ICashierDao cashierDao;

    private ICashierCashBoxDao cashierCashBoxDao;
    private IFileDataDao fileDataDao;

    public void setDao(IBranchDao dao) {
        this.dao = dao;
    }

    public void setBranchCashBoxDao(IBranchCashBoxDao branchCashBoxDao) {
        this.branchCashBoxDao = branchCashBoxDao;
    }

    public void setCashierDao(ICashierDao cashierDao) {
        this.cashierDao = cashierDao;
    }

    public void setCashierCashBoxDao(ICashierCashBoxDao cashierCashBoxDao) {
        this.cashierCashBoxDao = cashierCashBoxDao;
    }

    public void setFileDataDao(IFileDataDao fileDataDao) {
        this.fileDataDao = fileDataDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(Branch data) throws InternalErrorException {
        try {

            dao.add(data);

            BranchCashBox defaultBranchCashBox = data.getCurrentCashBox();
            List<FileData> branchFileDatas = data.getFileDatas();

            defaultBranchCashBox.setBranchId(data.getId());
//            defaultBranchCashBox.setCurrencyType();
            branchCashBoxDao.add(defaultBranchCashBox);

            Cashier cashier = Utils.getFirst(data.getMemberCashiers());


            CashierCashBox defaultCashBox = cashier.getCurrentCashBox();
            List<FileData> cashierFileDatas = cashier.getFileDatas();

            cashier.setBranchId(data.getId());
            cashierDao.add(cashier);
            defaultCashBox.setCashierId(cashier.getId());
            cashierCashBoxDao.add(defaultCashBox);

            //branchFileDatas
            if (!Utils.isEmpty(branchFileDatas)) {
                FileData d = branchFileDatas.get(0);
                d.setBranchId(data.getId());

                String fileName = d.getFileName();
                String extension = fileName.substring(fileName.indexOf("."));
                //
                fileName = String.format(FileDataUtil.LOGO_FORMAT, FileDataUtil.LOGO_PREFIX_BRANCH, data.getId(), extension);
                d.setFileName(fileName);

                fileDataDao.add(d);

                FileDataUtil.createFileCompany(fileName,  d.getData());
                data.setLogo(fileName);
                dao.updateLogo(data);
            }

            //cashierFileDatas
            if (!Utils.isEmpty(cashierFileDatas)) {
                FileData d = cashierFileDatas.get(0);
                d.setCashierId(cashier.getId());

                String fileName = d.getFileName();
                String extension = fileName.substring(fileName.indexOf("."));
                //
                fileName = String.format(FileDataUtil.LOGO_FORMAT, FileDataUtil.LOGO_PREFIX_CASHIER, cashier.getId(), extension);
                d.setFileName(fileName);

                fileDataDao.add(d);

                FileDataUtil.createFileCompany(fileName,  d.getData());
                cashier.setLogo(fileName);
                cashierDao.updateLogo(cashier);
            }
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Branch getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

     @Override
    public Branch getByIdFull(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getByIdFull(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Branch> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

     @Override
    public List<Branch> getByParamsFull(Map<String, Object> params) throws InternalErrorException {
        try {
            return dao.getByParamsFull(params);
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
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void update(Branch data) throws InternalErrorException, EntityNotFoundException {
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
            for (Long id : ides) {
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
