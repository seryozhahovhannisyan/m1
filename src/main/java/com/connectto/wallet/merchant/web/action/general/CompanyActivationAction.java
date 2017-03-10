package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyFormRequestManager;
import com.connectto.wallet.merchant.common.data.merchant.*;
import com.connectto.wallet.merchant.common.data.merchant.lcp.*;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.util.CaptchaResponse;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Initializer;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 11.11.13
 * Time: 0:51
 * To change this template use File | Settings | File Templates.
 */
public class CompanyActivationAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(CompanyActivationAction.class.getSimpleName());

    private ICompanyFormRequestManager companyFormRequestManager;
    private ICashierManager cashierManager;

    private String user;
    private String token;
    //Company
    private String nameCompany;
    private String addressCompany;
    private String emailCompany;
    private String phoneCodeCompany;
    private String phoneCompany;
    private Set<PartitionLCP> allowedPartitions;


    //editable
    private String countryCompany;
    private String countryRegionCompany;
    private String cityCompany;
    private String streetCompany;
    private String zipCompany;
    private String policyPhoneCodeCompany;
    private String policyPhoneCompany;
    private String availableRateValuesCompany;

    //Setup
    //private String currencyTypeMerchantSetup;
    private String depositFeePercentMerchantSetup;
    private String depositMinFeeMerchantSetup;
    private String depositMaxFeeMerchantSetup;
    private String withdrawFeePercentMerchantSetup;
    private String withdrawMinFeeMerchantSetup;
    private String withdrawMaxFeeMerchantSetup;
    private String exchangeDepositFeePercentMerchantSetup;
    private String exchangeDepositMinFeeMerchantSetup;
    private String exchangeDepositMaxFeeMerchantSetup;
    private String exchangeWithdrawFeePercentMerchantSetup;
    private String exchangeWithdrawMinFeeMerchantSetup;
    private String exchangeWithdrawMaxFeeMerchantSetup;
    //private String availableRateValuesMerchantSetup;

    //Branch
    private String nameBranch;
    private String addressBranch;
    private String cityBranch;
    private String streetBranch;
    private String zipBranch;
    private String emailBranch;
    private String phoneCodeBranch;
    private String phoneBranch;
    private String policyPhoneCodeBranch;
    private String policyPhoneBranch;
    private CountryRegion countryRegionBranch;

    //Cashier
    private String nameCashier;
    private String surnameCashier;
    private String emailCashier;
    private String phoneCodeCashier;
    private String phoneCashier;

    private String passwordCashier;
    private String passwordCashierRepeat;
    private String verificationCode;

    //Role
    private String nameRole;
    private String descriptionRole;
    private String transactionMinRole;
    private String transactionMaxRole;
    private String availableRateValuesRole;
    //
    private Date currentDate;
    private CurrencyType currencyType;
    private Status status;


    @SkipValidation
    //http://127.0.0.1:8383/merchant/activation-redirect-account.htm?user=emailContactTest@gmail.com&token=740u93y9fhrjsczwzw0v2xetsky5wgumo71kqmlri1n1034iaumf2o646f3z160rlxklf5zjvey3eggz3pw7x5yyzi9a5k343p5d1e3kpywxyrzhawk8jya8b4r0tan1
    public String redirectActivateAccount() {
        session.put("u", user);
        session.put("t", token);
        return SUCCESS;
    }

    @SkipValidation
    public String activateAccount() {

        String u = (String) session.get("u");
        String t = (String) session.get("t");

        if (Utils.isEmpty(u) || Utils.isEmpty(t)) {
            addActionError(getText("page.activation.expired.url"));
            return ERROR;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("u", u);
        params.put("t", t);
        params.put("s", new Integer[]{Status.UNVERIFIED.getKey(), Status.UNCONVERTED.getKey()});

        try {

            List<Cashier> cashiers = cashierManager.getByParamsFull(params);
            if (!isValidCashier(cashiers)) {
                addActionError(getText("error.internal"));
                return ERROR;
            }
            initViewData(cashiers.get(0));
            session.put(ScopeKeys.ACCOUNT_ACTIVATION, cashiers.get(0));
        } catch (InternalErrorException e1) {
            logger.error(e1);

            addActionError(getText("error.internal"));
            return ERROR;
        } catch (DataParseException e) {
            logger.error(e);

            addActionError(getText("error.internal"));
            return ERROR;
        }

        return SUCCESS;
    }

    public String activate() {

        Cashier cashierSes = (Cashier) session.remove(ScopeKeys.ACCOUNT_ACTIVATION);
        if (cashierSes == null) {
            String message = getText("error.internal");
            addActionError(message);
            return ERROR;
        }

        currentDate = new Date(System.currentTimeMillis());
        currencyType = cashierSes.getCompany().getCurrentCashBox().getCurrencyType();
        status = Status.ACTIVE;

        String recaptchaSecretKey = Initializer.getSetupInfo().recaptchaSecretKey;
        String recap = getHttpServletRequest().getParameter("g-recaptcha-response");
        String remoteAddress = getHttpServletRequest().getRemoteAddr();

        CaptchaResponse capRes = HttpURLBaseConnection.googleReCaptchaSiteVerify(recaptchaSecretKey, recap, remoteAddress);
        if (capRes.isSuccess()) {
            // Input by Human
            getHttpServletRequest().setAttribute("verified", "true");
        } else {
            // Input by Robot
            getHttpServletRequest().setAttribute("verified", "false");
            addActionError(getText("errors.invalid.captcha.or.psw"));
            return ERROR;
        }

        if (Utils.isEmpty(verificationCode) || !cashierSes.getVerificationCode().equals(verificationCode)) {
            addActionError(getText("errors.required", new String[]{getText("label.password")}));
            addActionError(getText("errors.required", new String[]{getText("label.password")}));
            return ERROR;
        }

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
        Company sesCompany = cashierSes.getCompany();
        try {

            Cashier cashier = initCashier(cashierSes, mixed);

            Branch branch = initBranch(sesCompany, mixed);
            branch.setId(cashierSes.getBranchId());
            branch.addMember(cashier);

            Company company = modifyCompany(cashierSes.getCompany(), mixed);
            company.getBranches().clear();
            company.addBranch(branch);

            CompanyFormRequest companyFormRequest = new CompanyFormRequest();
            companyFormRequest.setCompany(company);

            companyFormRequestManager.activate(companyFormRequest);
            session.put(MESSAGE, getText("pages.activation.success"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(e);
            addActionError(getText("error.internal"));
            return ERROR;
        }catch (EntityNotFoundException e) {
            logger.error(e);
            addActionError(getText("page.branches.info.list.data.found"));
            return ERROR;
        } catch (DataParseException e) {
            logger.error(e);
            addActionError(getText("error.internal"));
            return ERROR;
        }
    }

    private void initViewData(Cashier sessionCashier) throws DataParseException {

        Company company = sessionCashier.getCompany();
        CompanyCashBox companyCashBox = company.getCurrentCashBox();
        Role role = sessionCashier.getRole();

        this.allowedPartitions = company.parseAvailablePartitions();

        this.nameCompany = company.getName();
        this.addressCompany = company.getAddress();
        this.emailCompany = company.getEmail();
        this.phoneCodeCompany = company.getPhoneCode();
        this.phoneCompany = company.getPhone();


        //CashBox
        //currencyTypeMerchantSetup,availableRateValuesMerchantSetup
        this.depositFeePercentMerchantSetup = companyCashBox.getDepositFeePercent().toString();
        this.depositMinFeeMerchantSetup = companyCashBox.getDepositMinFee().toString();
        this.depositMaxFeeMerchantSetup = companyCashBox.getDepositMaxFee().toString();
        this.withdrawFeePercentMerchantSetup = companyCashBox.getWithdrawFeePercent().toString();
        this.withdrawMinFeeMerchantSetup = companyCashBox.getWithdrawMinFee().toString();
        this.withdrawMaxFeeMerchantSetup = companyCashBox.getWithdrawMaxFee().toString();
        this.exchangeDepositFeePercentMerchantSetup = companyCashBox.getExchangeDepositFeePercent().toString();
        this.exchangeDepositMinFeeMerchantSetup = companyCashBox.getExchangeDepositMinFee().toString();
        this.exchangeDepositMaxFeeMerchantSetup = companyCashBox.getExchangeDepositMaxFee().toString();
        this.exchangeWithdrawFeePercentMerchantSetup = companyCashBox.getExchangeWithdrawFeePercent().toString();
        this.exchangeWithdrawMinFeeMerchantSetup = companyCashBox.getExchangeWithdrawMinFee().toString();
        this.exchangeWithdrawMaxFeeMerchantSetup = companyCashBox.getExchangeWithdrawMaxFee().toString();

        this.availableRateValuesCompany = companyCashBox.getAvailableRateValues();

        this.nameRole = role.getName ();
        this.descriptionRole = role.getDescription();
        this.transactionMinRole = role.getTransactionMin().toString();
        this.transactionMaxRole = role.getTransactionMax().toString();
        this.availableRateValuesRole = role.getAvailableRateValues();

    }

    private Cashier initCashier(Cashier cashierSes, HashMap<String, List<FileData>> cashierFileData) throws DataParseException {

        try {
            passwordCashier = SHAHashEnrypt.get_MD5_SecurePassword(passwordCashier);
        } catch (EncryptException e) {
            logger.warn(e);
        }

        CashierCashBox defaultCashBox = initCashierCashBox();

        Cashier cashier = new Cashier();
        cashier.setId(cashierSes.getId());
        cashier.setCurrentCashBox(defaultCashBox);
        cashier.setName(nameCashier);
        cashier.setSurname(surnameCashier);
        //
        cashier.setEmail(emailCashier);
        cashier.setPhoneCode(phoneCodeCashier);
        cashier.setPhone(phoneCashier);
        cashier.setPassword(passwordCashier);

        cashier.setVerificationCode("Verified");

        cashier.setRegisteredAt(currentDate);
        cashier.setActivatedAt(currentDate);

        cashier.setPrivilege(Privilege.ALL);
        cashier.setStatus(status);

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

    private Role initRole(Long companyId) throws DataParseException {
        Role role = new Role();
        role.setCompanyId(companyId);
        role.setName(nameRole);
        role.setDescription(descriptionRole);

        role.setTransactionMin(DataConverter.convertToDouble(transactionMinRole));
        role.setTransactionMax(DataConverter.convertToDouble(transactionMaxRole));
        role.setAvailableRateValues(availableRateValuesRole);
        role.setIsExchangeAllowed(!Utils.isEmpty(availableRateValuesRole));
        return role;
    }

    private Branch initBranch(Company sesCompany, HashMap<String, List<FileData>> branchFileData) {

        CompanyCashBox companyCashBox = sesCompany.getCurrentCashBox();
        BranchCashBox defaultBranchCashBox = initBranchCashBox();
        defaultBranchCashBox.setCurrencyType(companyCashBox.getCurrencyType());

        Branch branch = new Branch();
        branch.setCurrentCashBox(defaultBranchCashBox);
        branch.setName(nameBranch);
        branch.setAddress(addressBranch);

        branch.setCity(cityBranch);
        branch.setStreet(streetBranch);
        branch.setZip(zipBranch);
        branch.setEmail(emailBranch);
        branch.setPhoneCode(phoneCodeBranch);
        branch.setPhone(phoneBranch);
        branch.setPolicyPhoneCode(policyPhoneCodeBranch);
        branch.setPolicyPhone(policyPhoneBranch);

        branch.setFileDatas(branchFileData.get("branch"));
        branch.setCountryRegion(countryRegionBranch);

        branch.setStatus(status);
        return branch;
    }

    private Company modifyCompany(Company sessionCompany, HashMap<String, List<FileData>> companyFileData) throws DataParseException {

        sessionCompany.setCountry(DataConverter.convertCountry(countryCompany));
        sessionCompany.setCountryRegion(DataConverter.convertCountryRegion(countryRegionCompany));
        sessionCompany.setCity(cityCompany);
        sessionCompany.setStreet(streetCompany);
        sessionCompany.setZip(zipCompany);
        sessionCompany.setPolicyPhoneCode(policyPhoneCodeCompany);
        sessionCompany.setPolicyPhone(policyPhoneCompany);

        CompanyCashBox modifiedCashBox = modifyCompanyCashBox(sessionCompany.getCurrentCashBox());

        sessionCompany.setFileDatas(companyFileData.get("company"));
        sessionCompany.setCurrentCashBox(modifiedCashBox);
        sessionCompany.setStatus(Status.ACTIVE);
        return sessionCompany;
    }

    private CompanyCashBox modifyCompanyCashBox(CompanyCashBox sessionCompanyCashBox) throws DataParseException {

        if(!Utils.isEmpty(availableRateValuesCompany)){
            sessionCompanyCashBox.setAvailableRates(DataConverter.parseAvailableRates(availableRateValuesCompany));
            sessionCompanyCashBox.setAvailableRateValues(availableRateValuesCompany);
        }

        sessionCompanyCashBox.setDepositFeePercent(DataConverter.convertToDouble(depositFeePercentMerchantSetup));
        sessionCompanyCashBox.setDepositMinFee(DataConverter.convertToDouble(depositMinFeeMerchantSetup));
        sessionCompanyCashBox.setDepositMaxFee(DataConverter.convertToDouble(depositMaxFeeMerchantSetup));

        sessionCompanyCashBox.setWithdrawFeePercent(DataConverter.convertToDouble(withdrawFeePercentMerchantSetup));
        sessionCompanyCashBox.setWithdrawMinFee(DataConverter.convertToDouble(withdrawMinFeeMerchantSetup));
        sessionCompanyCashBox.setWithdrawMaxFee(DataConverter.convertToDouble(withdrawMaxFeeMerchantSetup));

        sessionCompanyCashBox.setExchangeDepositFeePercent(DataConverter.convertToDouble(exchangeDepositFeePercentMerchantSetup));
        sessionCompanyCashBox.setExchangeDepositMinFee(DataConverter.convertToDouble(exchangeDepositMinFeeMerchantSetup));
        sessionCompanyCashBox.setExchangeDepositMaxFee(DataConverter.convertToDouble(exchangeDepositMaxFeeMerchantSetup));

        sessionCompanyCashBox.setExchangeWithdrawFeePercent(DataConverter.convertToDouble(exchangeWithdrawFeePercentMerchantSetup));
        sessionCompanyCashBox.setExchangeWithdrawMinFee(DataConverter.convertToDouble(exchangeWithdrawMinFeeMerchantSetup));
        sessionCompanyCashBox.setExchangeWithdrawMaxFee(DataConverter.convertToDouble(exchangeWithdrawMaxFeeMerchantSetup));

        return sessionCompanyCashBox;
    }

    private boolean isValidCashier(List<Cashier> cashiers) {
        return !Utils.isEmpty(cashiers) && cashiers.get(0) != null && cashiers.get(0) != null
                && cashiers.get(0).getCompany() != null && cashiers.get(0).getCompany().getCurrentCashBox() != null
                && cashiers.get(0).getBranch() != null;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /*Getters*/

    public String getAvailableRateValuesCompany() {
        return availableRateValuesCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public String getPhoneCodeCompany() {
        return phoneCodeCompany;
    }

    public String getPhoneCompany() {
        return phoneCompany;
    }

    public Set<PartitionLCP> getAllowedPartitions() {
        return allowedPartitions;
    }

    public String getNameRole() {
        return nameRole;
    }

    public String getDescriptionRole() {
        return descriptionRole;
    }

    public String getTransactionMinRole() {
        return transactionMinRole;
    }

    public String getTransactionMaxRole() {
        return transactionMaxRole;
    }

    public String getAvailableRateValuesRole() {
        return availableRateValuesRole;
    }

    /*Setters*/



    public void setCountryCompany(String countryCompany) {
        this.countryCompany = countryCompany;
    }

    public void setCountryRegionCompany(String countryRegionCompany) {
        this.countryRegionCompany = countryRegionCompany;
    }

    public void setCityCompany(String cityCompany) {
        this.cityCompany = cityCompany;
    }

    public void setStreetCompany(String streetCompany) {
        this.streetCompany = streetCompany;
    }

    public void setZipCompany(String zipCompany) {
        this.zipCompany = zipCompany;
    }

    public void setPolicyPhoneCodeCompany(String policyPhoneCodeCompany) {
        this.policyPhoneCodeCompany = policyPhoneCodeCompany;
    }

    public void setPolicyPhoneCompany(String policyPhoneCompany) {
        this.policyPhoneCompany = policyPhoneCompany;
    }

    public void setDepositFeePercentMerchantSetup(String depositFeePercentMerchantSetup) {
        this.depositFeePercentMerchantSetup = depositFeePercentMerchantSetup;
    }

    public void setDepositMinFeeMerchantSetup(String depositMinFeeMerchantSetup) {
        this.depositMinFeeMerchantSetup = depositMinFeeMerchantSetup;
    }

    public void setDepositMaxFeeMerchantSetup(String depositMaxFeeMerchantSetup) {
        this.depositMaxFeeMerchantSetup = depositMaxFeeMerchantSetup;
    }

    public void setWithdrawFeePercentMerchantSetup(String withdrawFeePercentMerchantSetup) {
        this.withdrawFeePercentMerchantSetup = withdrawFeePercentMerchantSetup;
    }

    public void setWithdrawMinFeeMerchantSetup(String withdrawMinFeeMerchantSetup) {
        this.withdrawMinFeeMerchantSetup = withdrawMinFeeMerchantSetup;
    }

    public void setWithdrawMaxFeeMerchantSetup(String withdrawMaxFeeMerchantSetup) {
        this.withdrawMaxFeeMerchantSetup = withdrawMaxFeeMerchantSetup;
    }

    public void setExchangeDepositFeePercentMerchantSetup(String exchangeDepositFeePercentMerchantSetup) {
        this.exchangeDepositFeePercentMerchantSetup = exchangeDepositFeePercentMerchantSetup;
    }

    public void setExchangeDepositMinFeeMerchantSetup(String exchangeDepositMinFeeMerchantSetup) {
        this.exchangeDepositMinFeeMerchantSetup = exchangeDepositMinFeeMerchantSetup;
    }

    public void setExchangeDepositMaxFeeMerchantSetup(String exchangeDepositMaxFeeMerchantSetup) {
        this.exchangeDepositMaxFeeMerchantSetup = exchangeDepositMaxFeeMerchantSetup;
    }

    public void setExchangeWithdrawFeePercentMerchantSetup(String exchangeWithdrawFeePercentMerchantSetup) {
        this.exchangeWithdrawFeePercentMerchantSetup = exchangeWithdrawFeePercentMerchantSetup;
    }

    public void setExchangeWithdrawMinFeeMerchantSetup(String exchangeWithdrawMinFeeMerchantSetup) {
        this.exchangeWithdrawMinFeeMerchantSetup = exchangeWithdrawMinFeeMerchantSetup;
    }

    public void setExchangeWithdrawMaxFeeMerchantSetup(String exchangeWithdrawMaxFeeMerchantSetup) {
        this.exchangeWithdrawMaxFeeMerchantSetup = exchangeWithdrawMaxFeeMerchantSetup;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public void setAddressBranch(String addressBranch) {
        this.addressBranch = addressBranch;
    }

    public void setCityBranch(String cityBranch) {
        this.cityBranch = cityBranch;
    }

    public void setStreetBranch(String streetBranch) {
        this.streetBranch = streetBranch;
    }

    public void setZipBranch(String zipBranch) {
        this.zipBranch = zipBranch;
    }

    public void setEmailBranch(String emailBranch) {
        this.emailBranch = emailBranch;
    }

    public void setPhoneCodeBranch(String phoneCodeBranch) {
        this.phoneCodeBranch = phoneCodeBranch;
    }

    public void setPhoneBranch(String phoneBranch) {
        this.phoneBranch = phoneBranch;
    }

    public void setPolicyPhoneCodeBranch(String policyPhoneCodeBranch) {
        this.policyPhoneCodeBranch = policyPhoneCodeBranch;
    }

    public void setPolicyPhoneBranch(String policyPhoneBranch) {
        this.policyPhoneBranch = policyPhoneBranch;
    }

    public void setCountryRegionBranch(CountryRegion countryRegionBranch) {
        this.countryRegionBranch = countryRegionBranch;
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

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setCompanyFormRequestManager(ICompanyFormRequestManager companyFormRequestManager) {
        this.companyFormRequestManager = companyFormRequestManager;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }
}
