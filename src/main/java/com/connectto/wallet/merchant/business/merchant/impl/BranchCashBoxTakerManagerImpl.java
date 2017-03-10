package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxProviderManager;
import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxTakerManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBoxProvider;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBoxTaker;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional(readOnly = true)
public class BranchCashBoxTakerManagerImpl implements IBranchCashBoxTakerManager {

    private IBranchCashBoxTakerDao dao;
    private IBranchCashBoxProviderDao branchCashBoxProviderDao;
    private IBranchDao branchDao;
    private IBranchCashBoxDao branchCashBoxDao;
    private ICompanyCashBoxDao companyCashBoxDao;

    public void setDao(IBranchCashBoxTakerDao dao) {
        this.dao = dao;
    }

    public void setBranchCashBoxProviderDao(IBranchCashBoxProviderDao branchCashBoxProviderDao) {
        this.branchCashBoxProviderDao = branchCashBoxProviderDao;
    }

    public void setBranchDao(IBranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setBranchCashBoxDao(IBranchCashBoxDao branchCashBoxDao) {
        this.branchCashBoxDao = branchCashBoxDao;
    }

    public void setCompanyCashBoxDao(ICompanyCashBoxDao companyCashBoxDao) {
        this.companyCashBoxDao = companyCashBoxDao;
    }

     @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(Long cashierId, Long companyId, Double takeAmount, CurrencyType takeCurrencyType, Set<Long> takeIdes) throws InternalErrorException, PermissionDeniedException {

        Date currentDate = new Date(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("ides", takeIdes);

        try {

            CompanyCashBox companyCashBox = companyCashBoxDao.getByCompanyId(companyId);
            Long companyCashBoxId = companyCashBox.getId();
            Double totalForCompanyCashBox = takeAmount * takeIdes.size();

            Double balanceCurrent = companyCashBox.getBalanceCurrent();
            Double balanceTotalProvidedForBranchCashBox = companyCashBox.getBalanceTotalProvidedForBranchCashBox();
            Double balanceCurrentProvidedForBranchCashBox = companyCashBox.getBalanceCurrentProvidedForBranchCashBox();
            Double balanceReturnedFromBranchCashBox = companyCashBox.getBalanceReturnedFromBranchCashBox();

            balanceReturnedFromBranchCashBox = balanceReturnedFromBranchCashBox == null ? 0 : balanceReturnedFromBranchCashBox;

            balanceCurrent +=totalForCompanyCashBox;
            balanceReturnedFromBranchCashBox += totalForCompanyCashBox;
            balanceTotalProvidedForBranchCashBox -=totalForCompanyCashBox;
            balanceCurrentProvidedForBranchCashBox -=totalForCompanyCashBox;

            if(balanceTotalProvidedForBranchCashBox < 0 || balanceCurrentProvidedForBranchCashBox< 0){
                throw new PermissionDeniedException("provider.branch.not.provided");
            }

            CompanyCashBox updateCashBox = new CompanyCashBox();
            updateCashBox.setId(companyCashBoxId);

            updateCashBox.setBalanceCurrent(balanceCurrent);
            updateCashBox.setBalanceTotalProvidedForBranchCashBox(balanceTotalProvidedForBranchCashBox);
            updateCashBox.setBalanceCurrentProvidedForBranchCashBox(balanceCurrentProvidedForBranchCashBox);
            updateCashBox.setBalanceReturnedFromBranchCashBox(balanceReturnedFromBranchCashBox);

            companyCashBoxDao.update(updateCashBox);

            List<Branch> branches = branchDao.getByParamsFull(params);
            List<BranchCashBoxTaker> cashBoxTakers = new ArrayList<BranchCashBoxTaker>(branches.size());
            List<Long> branchCashBoxIdes = new ArrayList<Long>(branches.size());
            for (Branch branch : branches) {

                BranchCashBox currentCashBox = branch.getCurrentCashBox();
                if (currentCashBox == null || currentCashBox.getCurrentBranchCashBoxProvider() == null) {
                    throw new PermissionDeniedException("provider.branch.not.provided");
                }

                BranchCashBoxProvider currentBranchCashBoxProvider = currentCashBox.getCurrentBranchCashBoxProvider();
                BranchCashBoxProvider updateBranchCashBoxProvider = new BranchCashBoxProvider();
                Double balanceProvided = currentBranchCashBoxProvider.getBalanceProvided();
                Double balanceTaken = currentBranchCashBoxProvider.getBalanceTaken();
                balanceTaken = balanceTaken == null ? 0 : balanceTaken;

                Long currentBranchCashBoxProviderId = currentBranchCashBoxProvider.getId();
                Long branchCurrentCashBoxId = currentCashBox.getId();
                branchCashBoxIdes.add(branchCurrentCashBoxId);

                Double balanceProvidedByCompany = currentCashBox.getBalanceProvidedByCompany();
                Double branchBalanceCurrent = currentCashBox.getBalanceCurrent();

                balanceProvidedByCompany -= takeAmount;
                branchBalanceCurrent -= takeAmount;

                if(balanceProvidedByCompany < 0 || branchBalanceCurrent < 0){
                    throw new PermissionDeniedException("take.less.money.branch");
                }

                balanceTaken+=takeAmount;
                updateBranchCashBoxProvider.setId(currentBranchCashBoxProviderId);
                updateBranchCashBoxProvider.setBalanceTaken(balanceTaken);
                if(balanceTaken.equals(balanceProvided)){
                    updateBranchCashBoxProvider.setIsTaken(true);
                }
                branchCashBoxProviderDao.update(updateBranchCashBoxProvider);

                BranchCashBox updateBranchCashBox = new BranchCashBox();
                updateBranchCashBox.setId(branchCurrentCashBoxId);
                updateBranchCashBox.setBalanceProvidedByCompany(balanceProvidedByCompany);
                updateBranchCashBox.setBalanceCurrent(branchBalanceCurrent);
                branchCashBoxDao.update(updateBranchCashBox);

                BranchCashBoxTaker cashBoxTaker = new BranchCashBoxTaker();
                cashBoxTaker.setBalanceTaken(takeAmount);
                cashBoxTaker.setCurrencyType(takeCurrencyType);

                cashBoxTaker.setBranchCashBoxId(branchCurrentCashBoxId);
                cashBoxTaker.setCompanyCashBoxId(companyCashBoxId);

                cashBoxTaker.setTookBy(cashierId);
                cashBoxTaker.setTookAt(currentDate);
                cashBoxTaker.setProviderId(currentBranchCashBoxProviderId);
                cashBoxTakers.add(cashBoxTaker);
            }

            dao.add(cashBoxTakers);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public BranchCashBoxTaker getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<BranchCashBoxTaker> getByParams(Map<String, Object> params) throws InternalErrorException {
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
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void update(BranchCashBoxTaker data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }



}
