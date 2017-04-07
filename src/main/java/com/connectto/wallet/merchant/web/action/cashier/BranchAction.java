package com.connectto.wallet.merchant.web.action.cashier;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.business.merchant.IRoleManager;
import com.connectto.wallet.merchant.common.data.merchant.*;
import com.connectto.wallet.merchant.common.data.merchant.lcp.*;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
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
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/26/2016.
 */
public class BranchAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(BranchAction.class.getSimpleName());
    private ResponseDto dto;

    private IBranchManager branchManager;
    private ICompanyManager companyManager;
    private IRoleManager roleManager;

    //for add
    private Date currentDate;
    private CurrencyType currencyType;

    private String companyName;
    private String name;
    private String address;

    private String city;
    private String street;
    private String zip;
    private String email;
    private String phoneCode;
    private String phone;
    private String policyPhoneCode;
    private String policyPhone;
    private CountryRegion region;

    //
    //Cashier
    private String nameCashier;
    private String surnameCashier;
    private String emailCashier;
    private String phoneCodeCashier;
    private String phoneCashier;
    private String passwordCashier;
    private String passwordCashierRepeat;


    //Role
    private Long roleId;

    private Status status;
    private Long id;
    private String branchIdes;

    private String requestJson;
    private int dataCount;

    private Branch branch;

    public String add() {

        if (Utils.isEmpty(passwordCashier)) {
            addActionError(getText("errors.required", new String[]{getText("label.password")}));
            return ERROR;
        } else if (!GeneralUtil.isValidPassword(passwordCashier)) {
            logger.info("Validation not passed empty password");
            addActionError(getText("errors.required", new String[]{getText("pages.registration.password.lengt.6")}));
            return ERROR;
        } else if (!passwordCashier.equals(passwordCashierRepeat)) {
            logger.info("Validation not passed empty password");
            addActionError(getText("pages.registration.password.type.match"));
            return ERROR;
        }

        HashMap<String, List<FileData>> mixed = (HashMap<String, List<FileData>>) session.get(ScopeKeys.DATA_MIXED);
        Cashier cashierSes = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashierSes.getCompanyId();
        currentDate = new Date(System.currentTimeMillis());
        currencyType = cashierSes.getCurrentCashBox().getCurrencyType();
        status = Status.ACTIVE;

        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(address);

        branch.setCity(city);
        branch.setStreet(street);
        branch.setZip(zip);
        branch.setEmail(email);
        branch.setPhoneCode(phoneCode);
        branch.setPhone(phone);
        branch.setPolicyPhoneCode(policyPhoneCode);
        branch.setPolicyPhone(policyPhone);

        branch.setCompanyId(companyId);
        branch.setCountryRegion(region);

        branch.setStatus(status);
        branch.setFileDatas(mixed.get("branch"));

        try {

            BranchCashBox defaultBranchCashBox = initBranchCashBox();
            Cashier cashier = initCashier(cashierSes, companyId, mixed);
            branch.addMember(cashier);
            branch.setCurrentCashBox(defaultBranchCashBox);

            branchManager.add(branch);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            return ERROR;
        } catch (DataParseException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            return ERROR;
        }
        return SUCCESS;
    }

    public String convertLocationToBranch() {

        if (!validateConvertLocationToBranch()) {
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            return SUCCESS;
        }

        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(address);

        branch.setCity(city);
        branch.setStreet(street);
        branch.setZip(zip);
        branch.setEmail(email);
        branch.setPhoneCode(phoneCode);
        branch.setPhone(phone);
        branch.setPolicyPhoneCode(policyPhoneCode);
        branch.setPolicyPhone(policyPhone);

        branch.setStatus(Status.UNCONVERTED);

        try {

            Company company = companyManager.getByName(companyName.trim());
            branch.setCompanyId(company.getId());
            branchManager.add(branch);

            dto.setActionmessage("Data added successfully");
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            dto.setActionmessage(e.getMessage());
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setActionmessage(e.getMessage());
            dto.setResponseStatus(ResponseStatus.DATA_NOT_FOUND);
        }
        return SUCCESS;
    }

    public String view() {
        try {
            Branch branch = branchManager.getById(id);
            dto.addResponse("item", branch);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String listView() {
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        try {

            Map<String, Object> paramsRole = new HashMap<String, Object>();
            paramsRole.put("companyId", companyId);

            List<Role> roles = roleManager.getByParams(paramsRole);
            dto.addResponse("roles", roles);

            dataCount = branchManager.getCountByParams(params);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String branchView() {
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long branchId = cashier.getBranchId();
        try {
            branch = branchManager.getById(branchId);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String list() {
        System.out.println(requestJson);
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        try {

            Map<String, Object> paramsRole = new HashMap<String, Object>();
            paramsRole.put("companyId", companyId);

            List<Role> roles = roleManager.getByParams(paramsRole);
            dto.addResponse("roles", roles);

            Map<String, Object> params = DataConverter.convertRequestToParams(requestJson);
            params.put("companyId", companyId);
            dataCount = branchManager.getCountByParams(params);

            long page = (Long) params.get("page");
            long count = (Long) params.get("count");

            params.put("page", (page - 1) * count);
            List<Branch> branches = branchManager.getByParams(params);

            dto.addResponse("data", branches);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String edit() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        Branch branch = new Branch();
        branch.setId(id);
        branch.setName(name);
        branch.setAddress(address);

        branch.setCity(city);
        branch.setStreet(street);
        branch.setZip(zip);
        branch.setEmail(email);
        branch.setPhoneCode(phoneCode);
        branch.setPhone(phone);
        branch.setPolicyPhoneCode(policyPhoneCode);
        branch.setPolicyPhone(policyPhone);

        branch.setCompanyId(companyId);

        branch.setStatus(status);
        try {
            branchManager.update(branch);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.UPDATE, null);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.UPDATE, null);
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            branchManager.delete(id);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    public String deleteMultiple() {

        if (Utils.isEmpty(branchIdes)) {
            return ERROR;
        }

        try {
            List<Long> ides = DataConverter.convertStringIdesToLong(branchIdes);
            branchManager.delete(ides);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (EntityNotFoundException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (DataParseException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    private boolean validateConvertLocationToBranch() {
        if (Utils.isEmpty(companyName)) {
            addFieldError("companyName", "is empty");
            return false;
        }
        if (Utils.isEmpty(name)) {
            addFieldError("name", "is empty");
            return false;
        }
        if (Utils.isEmpty(address)) {
            addFieldError("address", "is empty");
            return false;
        }
        if (Utils.isEmpty(city)) {
            addFieldError("city", "is empty");
            return false;
        }
        if (Utils.isEmpty(street)) {
            addFieldError("street", "is empty");
            return false;
        }
        if (Utils.isEmpty(zip)) {
            addFieldError("zip", "is empty");
            return false;
        }
        if (Utils.isEmpty(email)) {
            addFieldError("email", "is empty");
            return false;
        }
        if (Utils.isEmpty(phoneCode)) {
            addFieldError("phoneCode", "is empty");
            return false;
        }
        if (Utils.isEmpty(phone)) {
            addFieldError("phone", "is empty");
            return false;
        }
        if (Utils.isEmpty(policyPhoneCode)) {
            addFieldError("policyPhoneCode", "is empty");
            return false;
        }
        if (Utils.isEmpty(policyPhone)) {
            addFieldError("policyPhone", "is empty");
            return false;
        }

        return true;
    }

    private Cashier initCashier(Cashier sessionCashier, Long companyId, HashMap<String, List<FileData>> cashierFileData) throws DataParseException {

        try {
            passwordCashier = SHAHashEnrypt.get_MD5_SecurePassword(passwordCashier);
        } catch (EncryptException e) {
            logger.warn(e);
        }

        CashierCashBox defaultCashBox = initCashierCashBox();

        Cashier cashier = new Cashier();
        cashier.setHeadCashierId(sessionCashier.getId());
        cashier.setCompanyId(companyId);
        cashier.setCurrentCashBox(defaultCashBox);
        cashier.setName(nameCashier);
        cashier.setSurname(surnameCashier);
        //
        cashier.setEmail(emailCashier);
        cashier.setPhoneCode(phoneCodeCashier);
        cashier.setPhone(phoneCashier);
        cashier.setPassword(passwordCashier);

        //cashier.setVerificationCode("Verified");

        cashier.setRegisteredAt(currentDate);
        cashier.setActivatedAt(currentDate);

        cashier.setPrivilege(Privilege.CRUD_BRANCH);
        cashier.setStatus(status);

        cashier.setRoleId(roleId);
        cashier.setFileDatas(cashierFileData.get("cashier"));

        return cashier;
    }

    private CashierCashBox initCashierCashBox() {
        CashierCashBox cashBox = new CashierCashBox();
        cashBox.setBalanceProvidedByBranch(0d);
        cashBox.setBalanceCurrent(0d);
        cashBox.setBalanceGatheredTax(0d);
        cashBox.setCurrencyType(currencyType);
        cashBox.setPendingBalanceDeposit(0d);
        cashBox.setPendingBalanceWithdraw(0d);
        cashBox.setPendingTaxAmount(0d);

        cashBox.setOpenedAt(currentDate);
        cashBox.setStatus(status);
        return cashBox;
    }

    private BranchCashBox initBranchCashBox() {
        BranchCashBox cashBox = new BranchCashBox();
        cashBox.setCurrencyType(currencyType);
        cashBox.setBalanceProvidedByCompany(0d);
        cashBox.setBalanceCurrent(0d);
        cashBox.setBalanceTotalProvidedForCashierCashBox(0d);
        cashBox.setBalanceCurrentProvidedForCashierCashBox(0d);
        cashBox.setBalanceReturnedFromCashierCashBox(0d);
        cashBox.setBalanceGatheredTax(0d);

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public void setPolicyPhoneCode(String policyPhoneCode) {
        this.policyPhoneCode = policyPhoneCode;
    }

    public void setPolicyPhone(String policyPhone) {
        this.policyPhone = policyPhone;
    }

    public void setRegion(String region) {
        try {
            this.region = CountryRegion.valueOf(Integer.parseInt(region));
        } catch (Exception e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public void setStatus(String status) {
        try {
            this.status = Status.valueOf(Integer.parseInt(status));
        } catch (Exception e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public int getDataCount() {
        return dataCount;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
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

    public String getPolicyPhoneCode() {
        return policyPhoneCode;
    }

    public String getPolicyPhone() {
        return policyPhone;
    }

    public CountryRegion getRegion() {
        return region;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String branchId) {
        try {
            this.id = Long.parseLong(branchId);
        } catch (Exception e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public void setBranchIdes(String branchIdes) {
        this.branchIdes = branchIdes;
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRoleId(String roleId) {
        try {
            this.roleId = Long.parseLong(roleId);
        } catch (Exception e) {
            writeLog(CashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public String getNameCashier() {
        return nameCashier;
    }

    public void setNameCashier(String nameCashier) {
        this.nameCashier = nameCashier;
    }

    public String getSurnameCashier() {
        return surnameCashier;
    }

    public void setSurnameCashier(String surnameCashier) {
        this.surnameCashier = surnameCashier;
    }

    public String getEmailCashier() {
        return emailCashier;
    }

    public void setEmailCashier(String emailCashier) {
        this.emailCashier = emailCashier;
    }

    public String getPhoneCodeCashier() {
        return phoneCodeCashier;
    }

    public void setPhoneCodeCashier(String phoneCodeCashier) {
        this.phoneCodeCashier = phoneCodeCashier;
    }

    public String getPhoneCashier() {
        return phoneCashier;
    }

    public void setPhoneCashier(String phoneCashier) {
        this.phoneCashier = phoneCashier;
    }

    public String getPasswordCashier() {
        return passwordCashier;
    }

    public void setPasswordCashier(String passwordCashier) {
        this.passwordCashier = passwordCashier;
    }

    public void setPasswordCashierRepeat(String passwordCashierRepeat) {
        this.passwordCashierRepeat = passwordCashierRepeat;
    }

    public void setCompanyManager(ICompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setBranchManager(IBranchManager branchManager) {
        this.branchManager = branchManager;
    }

    public void setRoleManager(IRoleManager roleManager) {
        this.roleManager = roleManager;
    }
}