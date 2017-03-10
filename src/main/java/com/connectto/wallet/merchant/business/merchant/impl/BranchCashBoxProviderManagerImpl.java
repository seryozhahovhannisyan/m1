package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxProviderManager;
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
public class BranchCashBoxProviderManagerImpl implements IBranchCashBoxProviderManager {

    private IBranchCashBoxProviderDao dao;
    private IBranchCashBoxTakerDao takerDao;
    private IBranchDao branchDao;
    private IBranchCashBoxDao branchCashBoxDao;
    private ICompanyCashBoxDao companyCashBoxDao;

    public void setDao(IBranchCashBoxProviderDao dao) {
        this.dao = dao;
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
    public void add(Long cashierId, Long companyId, Double provideAmount, CurrencyType provideCurrencyType, Set<Long> provideIdes) throws InternalErrorException, PermissionDeniedException {

        Date currentDate = new Date(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("ides", provideIdes);

        try {

            CompanyCashBox companyCashBox = companyCashBoxDao.getByCompanyId(companyId);
            Long companyCashBoxId = companyCashBox.getId();
            Double totalForCompanyCashBox = provideAmount * provideIdes.size();

            Double balanceCurrent = companyCashBox.getBalanceCurrent();
            Double balanceTotalProvidedForBranchCashBox = companyCashBox.getBalanceTotalProvidedForBranchCashBox();
            Double balanceCurrentProvidedForBranchCashBox = companyCashBox.getBalanceCurrentProvidedForBranchCashBox();
            //the first time usage
            balanceTotalProvidedForBranchCashBox = balanceTotalProvidedForBranchCashBox == null ? 0 : balanceTotalProvidedForBranchCashBox;
            balanceCurrentProvidedForBranchCashBox = balanceCurrentProvidedForBranchCashBox == null ? 0 : balanceCurrentProvidedForBranchCashBox;

            if(totalForCompanyCashBox > balanceCurrent){
                throw new PermissionDeniedException("provider.less.money.company");
            }

            balanceCurrent -=totalForCompanyCashBox;
            balanceTotalProvidedForBranchCashBox +=totalForCompanyCashBox;
            balanceCurrentProvidedForBranchCashBox +=totalForCompanyCashBox;

            CompanyCashBox updateCashBox = new CompanyCashBox();
            updateCashBox.setId(companyCashBoxId);

            updateCashBox.setBalanceCurrent(balanceCurrent);
            updateCashBox.setBalanceTotalProvidedForBranchCashBox(balanceTotalProvidedForBranchCashBox);
            updateCashBox.setBalanceCurrentProvidedForBranchCashBox(balanceCurrentProvidedForBranchCashBox);

            companyCashBoxDao.update(updateCashBox);


            List<Branch> branches = branchDao.getByParamsFull(params);
            List<BranchCashBoxProvider> cashBoxProviders = new ArrayList<BranchCashBoxProvider>(branches.size());
            List<Long> branchCashBoxIdes = new ArrayList<Long>(branches.size());
            for (Branch branch : branches) {

                BranchCashBox currentCashBox = branch.getCurrentCashBox();
                if (currentCashBox == null || currentCashBox.getBalanceProvidedByCompany() > 0) {
                    throw new PermissionDeniedException("provider.branch.provide.not.taken");
                }

                Long branchCurrentCashBoxId = currentCashBox.getId();
                branchCashBoxIdes.add(branchCurrentCashBoxId);

                BranchCashBoxProvider cashBoxProvider = new BranchCashBoxProvider();
                cashBoxProvider.setBalanceProvided(provideAmount);
                cashBoxProvider.setCurrencyType(provideCurrencyType);

                cashBoxProvider.setBranchCashBoxId(branchCurrentCashBoxId);
                cashBoxProvider.setCompanyCashBoxId(companyCashBoxId);

                cashBoxProvider.setProvidedBy(cashierId);
                cashBoxProvider.setProvidedAt(currentDate);
                cashBoxProvider.setIsTaken(false);
                cashBoxProviders.add(cashBoxProvider);
            }

            dao.add(cashBoxProviders);

            params.clear();
            params.put("branchCashBoxIdes", branchCashBoxIdes);
            params.put("provideAmount", provideAmount);
            branchCashBoxDao.provideAmount(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public BranchCashBoxProvider getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<BranchCashBoxProvider> getByParams(Map<String, Object> params) throws InternalErrorException {
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
    public void update(BranchCashBoxProvider data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }



}
