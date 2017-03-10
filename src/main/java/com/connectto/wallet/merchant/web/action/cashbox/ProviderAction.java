package com.connectto.wallet.merchant.web.action.cashbox;

import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxProviderManager;
import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.business.merchant.IRoleManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.*;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.cashier.BranchAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/26/2016.
 */
public class ProviderAction extends BaseAction {

    private IBranchManager branchManager;
    private int dataCount;

    public String toPage() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Privilege privilege = cashier.getPrivilege();

        Long companyId = cashier.getCompanyId();
        Long branchId = cashier.getBranchId();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("status", Status.ACTIVE);

        try {
            if (privilege.getId() == Privilege.ALL.getId() ||
                    privilege.getId() == Privilege.CRUD_COMPANY.getId()) {
                dataCount = branchManager.getCountByParams(params);
                return "company";
            } else if (privilege.getId() == Privilege.CRUD_BRANCH.getId()) {
                params.put("branchId",branchId);
                dataCount = branchManager.getCountByParams(params);
                return "branch";
            } else if (privilege.getId() == Privilege.CRUD_CASHIER.getId() ||
                    privilege.getId() == Privilege.CASHIER.getId()) {
                return "cashier";
            }
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
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

    public void setBranchManager(IBranchManager branchManager) {
        this.branchManager = branchManager;
    }

}