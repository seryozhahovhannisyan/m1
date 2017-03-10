package com.connectto.wallet.merchant.web.action.cashier;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.business.merchant.IRoleManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.*;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/26/2016.
 */
public class CashierAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(CashierAction.class.getSimpleName());

    private ResponseDto dto;

    private IBranchManager branchManager;
    private IRoleManager roleManager;
    private ICashierManager cashierManager;
    // for force view
    private Branch branch;
    private List<Branch> branches;
    private List<Role> roles;
    // for delete
    private Long id;
    private String cashierIdes;
    // for search
    private String requestJson;
    private int dataCount;

    //
    //Cashier for add
    private String nameCashier;
    private String surnameCashier;
    private String emailCashier;
    private String phoneCodeCashier;
    private String phoneCashier;
    private String passwordCashier;
    private String passwordCashierRepeat;

    private Long roleId;
    private Date currentDate;
    private Status status;

    public String add() {

        if (Utils.isEmpty(passwordCashier)) {
            return ERROR;
        } else if (!GeneralUtil.isValidPassword(passwordCashier)) {
            logger.info("Validation not passed empty password");
            return ERROR;
        }  else if (!passwordCashier.equals(passwordCashierRepeat)) {
            logger.info("Validation not passed empty password");
            return ERROR;
        }

        currentDate = new Date(System.currentTimeMillis());
        status = Status.ACTIVE;

        Cashier sessionCashier = (Cashier)session.get(SESSION_CASHIER);

        CashierCashBox defaultCashBox = initCashierCashBox(sessionCashier.getCurrentCashBox());
        HashMap<String, List<FileData>> mixed = (HashMap<String, List<FileData>>) session.get(ScopeKeys.DATA_MIXED);

        Long companyId = sessionCashier.getCompanyId();
        Long branchId = sessionCashier.getBranchId();

        Cashier cashier = new Cashier();
        cashier.setCompanyId(companyId);
        cashier.setBranchId(branchId);
        cashier.setHeadCashierId(sessionCashier.getId());

        cashier.setCurrentCashBox(defaultCashBox);
        cashier.setName(nameCashier);
        cashier.setSurname(surnameCashier);
        //
        cashier.setEmail(emailCashier);
        cashier.setPhoneCode(phoneCodeCashier);
        cashier.setPhone(phoneCashier);
        cashier.setPassword(passwordCashier);

        cashier.setRegisteredAt(currentDate);
        cashier.setActivatedAt(currentDate);

        cashier.setPrivilege(Privilege.CASHIER);
        cashier.setStatus(status);

        cashier.setRoleId(roleId);
        cashier.setFileDatas(mixed.get("cashier"));


        try {
            cashierManager.add(cashier);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            return ERROR;
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            return ERROR;
        }
        return SUCCESS;
    }

    public String view() {
        try {
            Cashier cashier = cashierManager.getById(id);
            dto.addResponse("item", cashier);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String listView() {
        Cashier cashier = (Cashier)session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        try {
            branches =  branchManager.getByParams(params);
            roles = roleManager.getByParams(params);
            dataCount = cashierManager.getCountByParams(params);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String currentBranchListView() {
        Cashier cashier = (Cashier)session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();
        Long branchId = cashier.getBranchId();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("branchId", branchId);
        try {
            branch = branchManager.getById(branchId);
            dataCount = cashierManager.getCountByParams(params);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String list() {

        Cashier cashier = (Cashier)session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        try {

            Map<String,Object> params = DataConverter.convertRequestToParams(requestJson);
            params.put("companyId", companyId);
            dataCount =  cashierManager.getCountByParams(params);

            long page = (Long)params.get("page");
            long count = (Long)params.get("count");
            params.put("page", (page -1) * count);

            List<Cashier> cashiers = cashierManager.getByParams(params);
            dto.addResponse("data", cashiers);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String edit() {

        Cashier cashier = (Cashier)session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        try {
            cashierManager.update(cashier);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.UPDATE, null);
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.UPDATE, null);
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            cashierManager.delete(id);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    public String deleteMultiple() {

        if(Utils.isEmpty(cashierIdes)){
            return ERROR;
        }

        try {
            List<Long> ides = DataConverter.convertStringIdesToLong(cashierIdes);
            cashierManager.delete(ides);
        } catch (InternalErrorException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (EntityNotFoundException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (DataParseException e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    private CashierCashBox initCashierCashBox(CashierCashBox sessionCashBox) {
        CashierCashBox cashBox = new CashierCashBox();
        cashBox.setBalanceProvidedByBranch(0d);
        cashBox.setBalanceCurrent(0d);
        cashBox.setBalanceGatheredTax(0d);
        cashBox.setCurrencyType(sessionCashBox.getCurrencyType());
        cashBox.setPendingBalanceDeposit(0d);
        cashBox.setPendingBalanceWithdraw(0d);
        cashBox.setPendingTaxAmount(0d);

        cashBox.setOpenedAt(currentDate);
        cashBox.setStatus(status);
        return cashBox;
    }


     /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public Branch getBranch() {
        return branch;
    }

    public int getDataCount() {
        return dataCount;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(String cashierId) {
        try {
            this.id = Long.parseLong(cashierId);
        } catch (Exception e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public void setRoleId(String roleId) {
        try {
            this.roleId = Long.parseLong(roleId);
        } catch (Exception e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public void setCashierIdes(String cashierIdes) {
        this.cashierIdes = cashierIdes;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public void setNameCashier(String nameCashier) {
        this.nameCashier = nameCashier;
    }

    public void setSurnameCashier(String surnameCashier) {
        this.surnameCashier = surnameCashier;
    }

    public void setEmailCashier(String emailCashier) {
        this.emailCashier = emailCashier;
    }

    public void setPhoneCodeCashier(String phoneCodeCashier) {
        this.phoneCodeCashier = phoneCodeCashier;
    }

    public void setPhoneCashier(String phoneCashier) {
        this.phoneCashier = phoneCashier;
    }

    public void setPasswordCashier(String passwordCashier) {
        this.passwordCashier = passwordCashier;
    }

    public void setPasswordCashierRepeat(String passwordCashierRepeat) {
        this.passwordCashierRepeat = passwordCashierRepeat;
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setBranchManager(IBranchManager branchManager) {
        this.branchManager = branchManager;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }

    public void setRoleManager(IRoleManager roleManager) {
        this.roleManager = roleManager;
    }
}