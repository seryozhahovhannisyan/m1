package com.connectto.wallet.merchant.web.action.cashbox;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.web.action.BaseAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 6/26/2016.
 */
public class ProviderAction extends BaseAction {

    private IBranchManager branchManager;
    //CRUD_COMPANY case
    private ICashierManager cashierManager;
    //CRUD_COMPANY case
    private int dataCount;
    //CRUD_BRANCH
    private Branch branch;

    private CashierCashBox currentCashBox;

    public String toPage() {

        Cashier sessionCashier = (Cashier) session.get(SESSION_CASHIER);
        Privilege privilege = sessionCashier.getPrivilege();
        Long companyId = sessionCashier.getCompanyId();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("status", Status.ACTIVE);

        try {
            if (privilege.getId() == Privilege.ALL.getId() || privilege.getId() == Privilege.CRUD_COMPANY.getId()) {
                dataCount = branchManager.getCountByParams(params);
                return "company";
            } else if (privilege.getId() == Privilege.CRUD_BRANCH.getId()) {
                params.put("branchId", sessionCashier.getBranchId());
                params.put("except", sessionCashier.getId());
                params.put("privileges", new Integer[]{Privilege.CRUD_CASHIER.getId(), Privilege.CASHIER.getId()});
                dataCount = cashierManager.getCountByParams(params);
                return "branch";
            } else if (privilege.getId() == Privilege.CRUD_CASHIER.getId() || privilege.getId() == Privilege.CASHIER.getId()) {
                currentCashBox = cashierManager.getCashierCashBoxByCashierId(sessionCashier.getId());
                return "cashier";
            }
        } catch (InternalErrorException e) {
            writeLog(ProviderAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (EntityNotFoundException e) {
            writeLog(ProviderAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String provideCashiers() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Privilege privilege = cashier.getPrivilege();

        if (privilege.getId() != Privilege.CRUD_BRANCH.getId()) {
            session.put(ERROR, getText("error.permission.denied.exception"));
            return SUCCESS;
        }

        Long branchId = cashier.getBranchId();

        try {
            branch = branchManager.getByIdFull(branchId);
        } catch (InternalErrorException e) {
            writeLog(ProviderAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (EntityNotFoundException e) {
            writeLog(ProviderAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */


    public int getDataCount() {
        return dataCount;
    }

    public Branch getBranch() {
        return branch;
    }

    public CashierCashBox getCurrentCashBox() {
        return currentCashBox;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }

    public void setBranchManager(IBranchManager branchManager) {
        this.branchManager = branchManager;
    }

}