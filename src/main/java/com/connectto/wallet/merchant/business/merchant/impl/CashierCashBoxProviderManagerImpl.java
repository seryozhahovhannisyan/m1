package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.ICashierCashBoxProviderManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.*;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional(readOnly = true)
public class CashierCashBoxProviderManagerImpl implements ICashierCashBoxProviderManager {

    private ICashierCashBoxProviderDao dao;
    private IBranchCashBoxDao branchCashBoxDao;
    private ICashierDao cashierDao;
    private ICashierCashBoxDao cashierCashBoxDao;

    public void setDao(ICashierCashBoxProviderDao dao) {
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

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(Long cashierId,
                    Long branchId,
                    Double provideAmount,
                    CurrencyType provideCurrencyType,
                    Set<Long> provideIdes) throws InternalErrorException,PermissionDeniedException {

        Date currentDate = new Date(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("branchId", branchId);
        params.put("ides", provideIdes);

        try {

            BranchCashBox branchCashBox = branchCashBoxDao.getBranchCurrentCashBox(branchId);
            Long branchCashBoxId = branchCashBox.getId();
            Double totalForBranchCashBox = provideAmount * provideIdes.size();

            Double balanceCurrent = branchCashBox.getBalanceCurrent();
            Double balanceTotalProvidedForCashierCashBox = branchCashBox.getBalanceTotalProvidedForCashierCashBox();
            Double balanceCurrentProvidedForCashierCashBox = branchCashBox.getBalanceCurrentProvidedForCashierCashBox();
            //the first time usage
            balanceTotalProvidedForCashierCashBox = balanceTotalProvidedForCashierCashBox == null ? 0 : balanceTotalProvidedForCashierCashBox;
            balanceCurrentProvidedForCashierCashBox = balanceCurrentProvidedForCashierCashBox == null ? 0 : balanceCurrentProvidedForCashierCashBox;

            if(totalForBranchCashBox > balanceCurrent){
                throw new PermissionDeniedException("provider.less.money.branch");
            }

            balanceCurrent -=totalForBranchCashBox;
            balanceTotalProvidedForCashierCashBox +=totalForBranchCashBox;
            balanceCurrentProvidedForCashierCashBox +=totalForBranchCashBox;

            BranchCashBox updateCashBox = new BranchCashBox();
            updateCashBox.setId(branchCashBoxId);

            updateCashBox.setBalanceCurrent(balanceCurrent);
            updateCashBox.setBalanceTotalProvidedForCashierCashBox(balanceTotalProvidedForCashierCashBox);
            updateCashBox.setBalanceCurrentProvidedForCashierCashBox(balanceCurrentProvidedForCashierCashBox);

            branchCashBoxDao.update(updateCashBox);


            List<Cashier> cashiers = cashierDao.getByParamsFull(params);
            List<CashierCashBoxProvider> cashBoxProviders = new ArrayList<CashierCashBoxProvider>(cashiers.size());
            List<Long> cashierCashBoxIdes = new ArrayList<Long>(cashiers.size());
            for (Cashier cashier : cashiers) {

                CashierCashBox currentCashBox = cashier.getCurrentCashBox();
                if (currentCashBox == null || currentCashBox.getBalanceProvidedByBranch() > 0) {
                    throw new PermissionDeniedException("provider.cashier.provide.not.taken");
                }

                Long cashierCurrentCashBoxId = currentCashBox.getId();
                cashierCashBoxIdes.add(cashierCurrentCashBoxId);

                CashierCashBoxProvider cashBoxProvider = new CashierCashBoxProvider();
                cashBoxProvider.setBalanceProvided(provideAmount);
                cashBoxProvider.setCurrencyType(provideCurrencyType);

                cashBoxProvider.setCashierCashBoxId(cashierCurrentCashBoxId);
                cashBoxProvider.setBranchCashBoxId(branchCashBoxId);

                cashBoxProvider.setProvidedBy(cashierId);
                cashBoxProvider.setProvidedAt(currentDate);
                cashBoxProvider.setIsTaken(false);
                cashBoxProviders.add(cashBoxProvider);
            }

            dao.add(cashBoxProviders);

            params.clear();
            params.put("cashierCashBoxIdes", cashierCashBoxIdes);
            params.put("provideAmount", provideAmount);
            cashierCashBoxDao.provideAmount(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CashierCashBoxProvider getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CashierCashBoxProvider> getByParams(Map<String, Object> params) throws InternalErrorException {
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
    public void update(CashierCashBoxProvider data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    } 


}
