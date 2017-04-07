package com.connectto.wallet.merchant.web.action.admin;

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
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Serozh on 3/18/2017.
 */
public class TotalTestAction extends BaseAction {

    private Date currentDate = new Date(System.currentTimeMillis());
    private CurrencyType currencyType = CurrencyType.USD;
    private Status status = Status.ACTIVE;
    private FileData fileData = null;

    private ICompanyFormRequestManager companyFormRequestManager;
    private ICashierManager cashierManager;
    private Map<Integer, CompanyFormRequest> successResults = new HashMap<Integer, CompanyFormRequest>();
    private HashMap<String, List<FileData>> mixed = new HashMap<String, List<FileData>>();

    private List<String> errors = new ArrayList<String>();

    public String main() {
        errors.clear();
        mixed = (HashMap<String, List<FileData>>) session.get(ScopeKeys.DATA_MIXED);


        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        companyFormRequestManager = (ICompanyFormRequestManager) context.getBean("CompanyFormRequestManagerImpl");
        cashierManager = (ICashierManager) context.getBean("CashierManagerImpl");

        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 1; i < 50; i++) {//// TODO: 2/28/2017 activation last action  update db neccerry columns
            System.out.println("==>" + i);
            setFields(i);
            //create company request
            String clientSendRequest = clientSendRequest(i);
            System.out.println("ClientSendRequest : " + clientSendRequest);
            if (clientSendRequest.equals(SUCCESS)) {
                CompanyFormRequest companyFormRequest = successResults.get(i);
                // admin activate companies
                Cashier activatedCashier = activate(1, "" + companyFormRequest.getId(), i);
                System.out.println("Admin activate : " + activatedCashier);
                //
                params.put("t", activatedCashier.getResetPasswordToken());
                params.put("u", activatedCashier.getEmail());
                List<Cashier> cashiers = null;
                try {
                    cashiers = cashierManager.getByParamsFull(params);
                    if (!isValidCashier(cashiers)) {
                        System.out.println(ERROR + "" + params);
                    } else {
                        initViewData(cashiers.get(0));
                        activate(cashiers.get(0));
                    }
                } catch (InternalErrorException e) {
                    e.printStackTrace();
                    errors.add(e.getMessage());
                } catch (DataParseException e) {
                    e.printStackTrace();
                    errors.add(e.getMessage());
                }
            }
        }
        session.put(ERROR, errors);
        return SUCCESS;
    }

    //war.Merchant CompanyFormRequestActionDemo.clientSendRequest
    private String clientSendRequest(Integer index) {

        String companyName = "Demo Company " + index;
        String companyAddress = "Demo Address " + index;
        String companyEmail = "DemoEmail" + index + "@gmail.com";
        String companyPhoneCode = "0" + index;
        String companyPhone = "00" + index;
        String countOfBranches = "" + index;
        String countOfWorkers = "" + index;
        String contactName = "Demo contactName" + index;
        String contactLastName = "Demo contactLastName" + index;
        String contactEmail = "DemoContactEmail" + index + "@gmail.do";
        String contactPhoneCode = "+" + index;
        String contactPhone = "000" + index;
        String message = "Demomessage" + index;


        CompanyFormRequest formRequest = new CompanyFormRequest();
        formRequest.setCompanyName(companyName);
        formRequest.setCompanyAddress(companyAddress);
        formRequest.setCompanyEmail(companyEmail);
        formRequest.setCompanyPhoneCode(companyPhoneCode);
        formRequest.setCompanyPhone(companyPhone);
        formRequest.setCountOfBranches(countOfBranches);
        formRequest.setCountOfWorkers(countOfWorkers);
        formRequest.setContactName(contactName);
        formRequest.setContactLastName(contactLastName);
        formRequest.setContactEmail(contactEmail);
        formRequest.setContactPhoneCode(contactPhoneCode);
        formRequest.setContactPhone(contactPhone);
        formRequest.setMessage(message);
        formRequest.setStatus(Status.UNVERIFIED);
        formRequest.setRequestedAt(new Date(System.currentTimeMillis()));

        try {

            companyFormRequestManager.add(null, formRequest);
            successResults.put(index, formRequest);
        } catch (InternalErrorException e) {
            e.printStackTrace();
            errors.add(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    //war.MerchantAdmin CompanyActivationAction.activate
    private Cashier activate(int userId, String requestId, Integer index) {
        Cashier cashier = null;
        String formRequestId = requestId;
        String balanceProvidedByMerchant = "100000";
        String acceptedOverpayment = "1000";
        String currencyType = "152";
        String maximumLimitOfTransaction = "1000";

        String depositFeePercent = "1";
        String depositMinFee = "1";
        String depositMaxFee = "2";

        String withdrawFeePercent = "1";
        String withdrawMinFee = "1";
        String withdrawMaxFee = "2";

        String exchangeDepositFeePercent = "1";
        String exchangeDepositMinFee = "1";
        String exchangeDepositMaxFee = "2";

        String exchangeWithdrawFeePercent = "1";
        String exchangeWithdrawMinFee = "1";
        String exchangeWithdrawMaxFee = "2";

        String availableRateValues = "";
        //
        String title = "Demo Admin title " + index;
        String message = "Demo Admin message " + index;
        String allowedRemoteAddressValues = "hollor.com$vshoo.com";
        String allowedPartitionValues = (new StringBuilder())
                .append(PartitionLCP.VSHOO_ARMENIA.getId()).append("$")
                .append(PartitionLCP.VSHOO_ARMENIA_REGIONS.getId()).append("$")
                .append(PartitionLCP.VSHOO_LA.getId()).append("$")
                .append(PartitionLCP.VSHOO_USA.getId()).append("$")
                .append(PartitionLCP.VSHOO_YEREVAN.getId()).append("$")
                .append(PartitionLCP.VSHOO_IRAN.getId())
                .toString();


        CompanyFormResponse companyFormResponse = new CompanyFormResponse();
        companyFormResponse.setTitle(title);
        companyFormResponse.setMessage(message);

        try {

            FileData agreementDocument = mixed.get("cashier").get(0);
            companyFormResponse.setAgreementDocument(agreementDocument);

            if (!Utils.isEmpty(allowedRemoteAddressValues)) {
                companyFormResponse.setAllowedRemoteAddressValues(DataConverter.convertToValidAllowedRemoteAddress(allowedRemoteAddressValues));
            }

            if (!Utils.isEmpty(allowedPartitionValues)) {
                companyFormResponse.setAllowedPartitionValues(DataConverter.convertToValidAllowedPartitions(allowedPartitionValues));
            }

            companyFormResponse.setUserId(userId);
            companyFormResponse.setResponseAt(new Date(System.currentTimeMillis()));

            CompanyCashBox cashBox = new CompanyCashBox();
            if (!Utils.isEmpty(availableRateValues)) {
                cashBox.setAvailableRateValues(DataConverter.convertToAvailableRateValues(availableRateValues));
            }

            cashBox.setBalanceProvidedByMerchant(Double.parseDouble(balanceProvidedByMerchant));
            cashBox.setBalanceCurrent(Double.parseDouble(balanceProvidedByMerchant));
            cashBox.setAcceptedOverpayment(Double.parseDouble(acceptedOverpayment));
            cashBox.setOverpayment(0d);
            cashBox.setCurrencyType(DataConverter.convertCurrencyType(currencyType));
            cashBox.setMaximumLimitOfTransaction(Double.parseDouble(maximumLimitOfTransaction));
            cashBox.setDepositFeePercent(Double.parseDouble(depositFeePercent));
            cashBox.setDepositMinFee(Double.parseDouble(depositMinFee));
            cashBox.setDepositMaxFee(Double.parseDouble(depositMaxFee));

            cashBox.setWithdrawFeePercent(Double.parseDouble(withdrawFeePercent));
            cashBox.setWithdrawMinFee(Double.parseDouble(withdrawMinFee));
            cashBox.setWithdrawMaxFee(Double.parseDouble(withdrawMaxFee));

            cashBox.setExchangeDepositFeePercent(Double.parseDouble(exchangeDepositFeePercent));
            cashBox.setExchangeDepositMinFee(Double.parseDouble(exchangeDepositMinFee));
            cashBox.setExchangeDepositMaxFee(Double.parseDouble(exchangeDepositMaxFee));

            cashBox.setExchangeWithdrawFeePercent(Double.parseDouble(exchangeWithdrawFeePercent));
            cashBox.setExchangeWithdrawMinFee(Double.parseDouble(exchangeWithdrawMinFee));
            cashBox.setExchangeWithdrawMaxFee(Double.parseDouble(exchangeWithdrawMaxFee));

            CompanyFormRequest formRequest = new CompanyFormRequest();
            formRequest.setId(Long.parseLong(formRequestId));
            formRequest.setStatus(Status.PENDING);

            cashier = companyFormRequestManager.activateCompany(formRequest, companyFormResponse, cashBox);

        } catch (DataParseException e) {
            e.printStackTrace();
            errors.add(e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.add(e.getMessage());
        } catch (InternalErrorException e) {
            e.printStackTrace();
            errors.add(e.getMessage());
        }
        return cashier;
    }

    //war.merchant CompanyActivationAction.activate
    private String activate(Cashier cashierSes) {
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
            return SUCCESS;
        } catch (InternalErrorException e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return ERROR;
        } catch (EntityNotFoundException e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return ERROR;
        } catch (DataParseException e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    private Cashier initCashier(Cashier cashierSes, HashMap<String, List<FileData>> cashierFileData) throws DataParseException {

        try {
            passwordCashier = SHAHashEnrypt.get_MD5_SecurePassword(passwordCashier);
        } catch (EncryptException e) {
            errors.add(e.getMessage());
            e.printStackTrace();
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
        cashier.setStatus(Status.ACTIVE);
        cashier.setFileDatas(cashierFileData.get("cashier"));

        return cashier;
    }

    private void initViewData(Cashier sessionCashier) throws DataParseException {

        Company company = sessionCashier.getCompany();
        CompanyCashBox companyCashBox = company.getCurrentCashBox();
        Role role = sessionCashier.getRole();

        allowedPartitions = company.parseAvailablePartitions();

        nameCompany = company.getName();
        addressCompany = company.getAddress();
        emailCompany = company.getEmail();
        phoneCodeCompany = company.getPhoneCode();
        phoneCompany = company.getPhone();


        //CashBox
        //currencyTypeMerchantSetup,availableRateValuesMerchantSetup
        depositFeePercentMerchantSetup = companyCashBox.getDepositFeePercent().toString();
        depositMinFeeMerchantSetup = companyCashBox.getDepositMinFee().toString();
        depositMaxFeeMerchantSetup = companyCashBox.getDepositMaxFee().toString();
        withdrawFeePercentMerchantSetup = companyCashBox.getWithdrawFeePercent().toString();
        withdrawMinFeeMerchantSetup = companyCashBox.getWithdrawMinFee().toString();
        withdrawMaxFeeMerchantSetup = companyCashBox.getWithdrawMaxFee().toString();
        exchangeDepositFeePercentMerchantSetup = companyCashBox.getExchangeDepositFeePercent().toString();
        exchangeDepositMinFeeMerchantSetup = companyCashBox.getExchangeDepositMinFee().toString();
        exchangeDepositMaxFeeMerchantSetup = companyCashBox.getExchangeDepositMaxFee().toString();
        exchangeWithdrawFeePercentMerchantSetup = companyCashBox.getExchangeWithdrawFeePercent().toString();
        exchangeWithdrawMinFeeMerchantSetup = companyCashBox.getExchangeWithdrawMinFee().toString();
        exchangeWithdrawMaxFeeMerchantSetup = companyCashBox.getExchangeWithdrawMaxFee().toString();

        availableRateValuesCompany = companyCashBox.getAvailableRateValues();

        nameRole = role.getName();
        descriptionRole = role.getDescription();
        transactionMinRole = role.getTransactionMin().toString();
        transactionMaxRole = role.getTransactionMax().toString();
        availableRateValuesRole = role.getAvailableRateValues();

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

        branch.setStatus(Status.ACTIVE);
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

        if (!Utils.isEmpty(availableRateValuesCompany)) {
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


    private void setFields(int i) {
        //Company
        nameCompany = "Activated Company name " + i;
        addressCompany = "Activated Company address " + i;
        emailCompany = "ActivatedCompanyEmail" + i + "@gmail.com";
        phoneCodeCompany = "0" + i;
        phoneCompany = "00" + i;
        allowedPartitions = new HashSet<PartitionLCP>();


        //editable
        countryCompany = "" + Country.ARMENIA.getId();
        countryRegionCompany = "" + CountryRegion.YEREVAN.getId();
        cityCompany = "Yerevan " + i;
        streetCompany = "Abovyan " + i;
        zipCompany = "0000";
        policyPhoneCodeCompany = "0";
        policyPhoneCompany = "1" + i;
        availableRateValuesCompany = CurrencyType.USD.getId() + "," + CurrencyType.AMD.getId();

        //Setup
        //currencyTypeMerchantSetup;
        depositFeePercentMerchantSetup = "1";
        depositMinFeeMerchantSetup = "1";
        depositMaxFeeMerchantSetup = "2";

        withdrawFeePercentMerchantSetup = "1";
        withdrawMinFeeMerchantSetup = "1";
        withdrawMaxFeeMerchantSetup = "2";

        exchangeDepositFeePercentMerchantSetup = "1";
        exchangeDepositMinFeeMerchantSetup = "1";
        exchangeDepositMaxFeeMerchantSetup = "2";

        exchangeWithdrawFeePercentMerchantSetup = "1";
        exchangeWithdrawMinFeeMerchantSetup = "1";
        exchangeWithdrawMaxFeeMerchantSetup = "2";
        //availableRateValuesMerchantSetup;

        //Branch
        nameBranch = "nameBranch " + i;
        addressBranch = "addressBranch " + i;
        cityBranch = "cityBranch " + i;
        streetBranch = "streetBranch " + i;
        zipBranch = "0" + i;
        emailBranch = "emailBranch" + i + "@mail.fi";
        phoneCodeBranch = "+" + i;
        phoneBranch = "2" + i;
        policyPhoneCodeBranch = "-" + i;
        policyPhoneBranch = "3" + i;
        countryRegionBranch = CountryRegion.SHIRAK;

        //Cashier
        nameCashier = "nameCashier " + i;
        surnameCashier = "surnameCashier " + i;
        emailCashier = "emailCashier_" + i + "mail.gb";
        phoneCodeCashier = "-" + i;
        phoneCashier = "4" + i;

        passwordCashier = "1";
        passwordCashierRepeat = "1" + i;

        //Role
        nameRole = "role " + i;
        descriptionRole = "descriptionRole " + i;
        transactionMinRole = "1";
        transactionMaxRole = "100" + i;
        availableRateValuesRole = CurrencyType.USD.getId() + "," + CurrencyType.AMD.getId();
    }

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
    //private  String currencyTypeMerchantSetup;
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
    //private  String availableRateValuesMerchantSetup;

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
}
