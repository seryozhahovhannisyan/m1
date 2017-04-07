package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.ICashierCashBoxTakerManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxTaker;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional(readOnly = true)
public class CashierCashBoxTakerManagerImpl implements ICashierCashBoxTakerManager {

    private ICashierCashBoxTakerDao dao;
    private ICashierCashBoxProviderDao cashierCashBoxProviderDao;
    private ICashierDao cashierDao;
    private ICashierCashBoxDao cashierCashBoxDao;
    private IBranchCashBoxDao branchCashBoxDao;

    public void setDao(ICashierCashBoxTakerDao dao) {
        this.dao = dao;
    }

    public void setCashierCashBoxProviderDao(ICashierCashBoxProviderDao cashierCashBoxProviderDao) {
        this.cashierCashBoxProviderDao = cashierCashBoxProviderDao;
    }

    public void setCashierDao(ICashierDao cashierDao) {
        this.cashierDao = cashierDao;
    }

    public void setCashierCashBoxDao(ICashierCashBoxDao cashierCashBoxDao) {
        this.cashierCashBoxDao = cashierCashBoxDao;
    }

    public void setBranchCashBoxDao(IBranchCashBoxDao branchCashBoxDao) {
        this.branchCashBoxDao = branchCashBoxDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(Long cashierId, Long branchId, Double takeAmount, CurrencyType takeCurrencyType, Set<Long> takeIdes) throws InternalErrorException, PermissionDeniedException {

        Date currentDate = new Date(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("branchId", branchId);
        params.put("ides", takeIdes);

        try {

            BranchCashBox branchCashBox = branchCashBoxDao.getBranchCurrentCashBox(branchId);
            Long branchCashBoxId = branchCashBox.getId();
            Double totalForBranchCashBox = takeAmount * takeIdes.size();

            Double balanceCurrent = branchCashBox.getBalanceCurrent();
            Double balanceTotalProvidedForCashierCashBox = branchCashBox.getBalanceTotalProvidedForCashierCashBox();
            Double balanceCurrentProvidedForCashierCashBox = branchCashBox.getBalanceCurrentProvidedForCashierCashBox();
            Double balanceReturnedFromCashierCashBox = branchCashBox.getBalanceReturnedFromCashierCashBox();

            balanceReturnedFromCashierCashBox = balanceReturnedFromCashierCashBox == null ? 0 : balanceReturnedFromCashierCashBox;

            balanceCurrent += totalForBranchCashBox;
            balanceReturnedFromCashierCashBox += totalForBranchCashBox;
            balanceTotalProvidedForCashierCashBox -= totalForBranchCashBox;
            balanceCurrentProvidedForCashierCashBox -= totalForBranchCashBox;

            if (balanceTotalProvidedForCashierCashBox < 0 || balanceCurrentProvidedForCashierCashBox < 0) {
                throw new PermissionDeniedException("provider.cashier.not.provided");
            }

            BranchCashBox updateCashBox = new BranchCashBox();
            updateCashBox.setId(branchCashBoxId);

            updateCashBox.setBalanceCurrent(balanceCurrent);
            updateCashBox.setBalanceTotalProvidedForCashierCashBox(balanceTotalProvidedForCashierCashBox);
            updateCashBox.setBalanceCurrentProvidedForCashierCashBox(balanceCurrentProvidedForCashierCashBox);
            updateCashBox.setBalanceReturnedFromCashierCashBox(balanceReturnedFromCashierCashBox);

            branchCashBoxDao.update(updateCashBox);

            List<Cashier> cashiers = cashierDao.getByParamsFull(params);
            List<CashierCashBoxTaker> cashBoxTakers = new ArrayList<CashierCashBoxTaker>(cashiers.size());
            List<Long> cashierCashBoxIdes = new ArrayList<Long>(cashiers.size());
            for (Cashier cashier : cashiers) {

                CashierCashBox currentCashBox = cashier.getCurrentCashBox();
                if (currentCashBox == null || currentCashBox.getCurrentCashierCashBoxProvider() == null) {
                    throw new PermissionDeniedException("provider.cashier.not.provided");
                }

                CashierCashBoxProvider currentCashierCashBoxProvider = currentCashBox.getCurrentCashierCashBoxProvider();
                CashierCashBoxProvider updateCashierCashBoxProvider = new CashierCashBoxProvider();
                Double balanceProvided = currentCashierCashBoxProvider.getBalanceProvided();
                Double balanceTaken = currentCashierCashBoxProvider.getBalanceTaken();
                balanceTaken = balanceTaken == null ? 0 : balanceTaken;

                Long currentCashierCashBoxProviderId = currentCashierCashBoxProvider.getId();
                Long cashierCurrentCashBoxId = currentCashBox.getId();
                cashierCashBoxIdes.add(cashierCurrentCashBoxId);

                Double balanceProvidedByBranch = currentCashBox.getBalanceProvidedByBranch();
                Double cashierBalanceCurrent = currentCashBox.getBalanceCurrent();

                balanceProvidedByBranch -= takeAmount;
                cashierBalanceCurrent -= takeAmount;

                if (balanceProvidedByBranch < 0 || cashierBalanceCurrent < 0) {
                    throw new PermissionDeniedException("take.less.money.cashier");
                }

                balanceTaken += takeAmount;
                updateCashierCashBoxProvider.setId(currentCashierCashBoxProviderId);
                updateCashierCashBoxProvider.setBalanceTaken(balanceTaken);
                if (balanceTaken.equals(balanceProvided)) {
                    updateCashierCashBoxProvider.setIsTaken(true);
                }
                cashierCashBoxProviderDao.update(updateCashierCashBoxProvider);

                CashierCashBox updateCashierCashBox = new CashierCashBox();
                updateCashierCashBox.setId(cashierCurrentCashBoxId);
                updateCashierCashBox.setBalanceProvidedByBranch(balanceProvidedByBranch);
                updateCashierCashBox.setBalanceCurrent(cashierBalanceCurrent);
                cashierCashBoxDao.update(updateCashierCashBox);

                CashierCashBoxTaker cashBoxTaker = new CashierCashBoxTaker();
                cashBoxTaker.setBalanceTaken(takeAmount);
                cashBoxTaker.setCurrencyType(takeCurrencyType);

                cashBoxTaker.setCashierCashBoxId(cashierCurrentCashBoxId);
                cashBoxTaker.setBranchCashBoxId(branchCashBoxId);

                cashBoxTaker.setTookBy(cashierId);
                cashBoxTaker.setTookAt(currentDate);
                cashBoxTaker.setProviderId(currentCashierCashBoxProviderId);
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
    public CashierCashBoxTaker getById(Long id) throws InternalErrorException, EntityNotFoundException {
        try {
            return dao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CashierCashBoxTaker> getByParams(Map<String, Object> params) throws InternalErrorException {
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
    public void update(CashierCashBoxTaker data) throws InternalErrorException, EntityNotFoundException {
        try {
            dao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }


}
