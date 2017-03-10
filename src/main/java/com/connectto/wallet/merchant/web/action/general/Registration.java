package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyCashBoxManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.business.merchant.IRoleManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.util.ScopeKeys;

import java.util.Date;
import java.util.List;

/**
 * Created by Serozh on 7/4/2016.
 */
public class Registration extends BaseAction {

    private ICashierManager cashierManager;
    private ICompanyManager companyManager;
    private ICompanyCashBoxManager companyCashBoxManager;
    private IRoleManager roleManager;


    //Personal
    private String name;
    private String surname;
    //
    private String email;
    private String phoneCode;
    private String phone;
    private String password;

    private Long companyId;
    private Long branchId;

    private List<Company> companies;

    private int privilege;
    //for init
    private Status status;
    private Date currentDate;

    public String companyView() {
        privilege = Privilege.CRUD_COMPANY.getId();
        session.put(ScopeKeys.SIGN_UP_PRIVILEGE, privilege);
        return SUCCESS;
    }

    public String branchView() {
        privilege = Privilege.CRUD_BRANCH.getId();
        session.put(ScopeKeys.SIGN_UP_PRIVILEGE, privilege);
        try {
            companies = companyManager.getByParams(null);
        } catch (InternalErrorException e) {
            writeLog(Registration.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String cashierView() {
        privilege = Privilege.CRUD_CASHIER.getId();
        session.put(ScopeKeys.SIGN_UP_PRIVILEGE, privilege);
        try {
            companies = companyManager.getByParamsFull(null);
        } catch (InternalErrorException e) {
            writeLog(Registration.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String cashierSimpleView() {
        privilege = Privilege.CASHIER.getId();
        session.put(ScopeKeys.SIGN_UP_PRIVILEGE, privilege);
        try {
            companies = companyManager.getByParamsFull(null);
        } catch (InternalErrorException e) {
            writeLog(Registration.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String doRegistration() {
        if (privilege == 0) {
            return INPUT;
        }
        status = Status.UNVERIFIED;
         currentDate = new Date(System.currentTimeMillis());

        Cashier cashier = new Cashier();
        // TODO: 7/4/2016 validate
        cashier.setName(name);
        cashier.setSurname(surname);
        //
        cashier.setEmail(email);
        cashier.setPhoneCode(phoneCode);
        cashier.setPhone(phone);
        cashier.setPassword(password);

        cashier.setVerificationCode(GeneralUtil.generateVerificationCode());

        cashier.setRegisteredAt(currentDate);

        cashier.setPrivilege(Privilege.valueOf(privilege));
        cashier.setStatus(status);

        cashier.setFileDatas((List<FileData>) session.remove(ScopeKeys.DATA_MULTIPLE));


        cashier.setCompanyId(companyId);
        cashier.setBranchId(branchId);

        try {

            CashierCashBox defaultCashBox = initCashierCashBox(companyId);
            cashier.setCurrentCashBox(defaultCashBox);

            Role role = roleManager.getLastRole(companyId);

            cashier.setCurrentCashBox(defaultCashBox);
            cashier.setRoleId(role.getId());

            cashierManager.add(cashier);
        } catch (InternalErrorException e) {
            writeLog(Registration.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
        } catch (EntityNotFoundException e) {
            writeLog(Registration.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
        }
        return SUCCESS;
    }

    private CashierCashBox initCashierCashBox(Long companyId) throws EntityNotFoundException, InternalErrorException {

        CompanyCashBox companyCashBox = companyCashBoxManager.getByCompanyId(companyId);

        CashierCashBox cashBox = new CashierCashBox();
        cashBox.setBalanceProvidedByBranch(0d);
        cashBox.setBalanceCurrent(0d);
        cashBox.setBalanceGatheredTax(0d);
        cashBox.setCurrencyType(companyCashBox.getCurrencyType());
        cashBox.setPendingBalanceDeposit(0d);
        cashBox.setPendingBalanceWithdraw(0d);
        cashBox.setPendingTaxAmount(0d);

        cashBox.setOpenedAt(currentDate);
        cashBox.setStatus(status);
        return cashBox;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }

    public void setCompanyManager(ICompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        try {
            this.privilege = Integer.parseInt(privilege);
        } catch (Exception e) {
            this.privilege = 0;
        }
    }
}
