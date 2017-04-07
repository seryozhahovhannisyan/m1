package branch;

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
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Serozh on 2/24/2017.
 */
public class CompanyMovement {

    private static Logger logger = Logger.getLogger(CompanyMovement.class);
    private static String SUCCESS = "SUCCESS";
    private static String ERROR = "ERROR";
    public static Long Count = 2l;

    private static Date currentDate = new Date(System.currentTimeMillis());
    private static CurrencyType currencyType = CurrencyType.USD;
    private static Status status = Status.ACTIVE;
    private static FileData fileData = null;

    private static ICompanyFormRequestManager companyFormRequestManager;
    private static ICashierManager cashierManager;
    private static Map<Integer, CompanyFormRequest> successResults = new HashMap<Integer, CompanyFormRequest>();


    private static HashMap<String, List<FileData>> mixed = new HashMap<String, List<FileData>>();

    public static void main3(String[] args) {
        try {
            fileData = initFileData();
            File originalFile = new File(Initializer.getCompanyDocumentUploadDir() + Constants.FILE_SEPARATOR + 1 + Constants.FILE_SEPARATOR + fileData.getFileName());
            FileUtils.writeByteArrayToFile(originalFile, fileData.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        fileData = initFileData();
        List<FileData> cashierFileDatas = new ArrayList<FileData>();
        List<FileData> branchFileDatas = new ArrayList<FileData>();
        List<FileData> companyrFileDatas = new ArrayList<FileData>();
        cashierFileDatas.add(initFileData());
        branchFileDatas.add(initFileData());
        companyrFileDatas.add(initFileData());
        mixed.put("cashier", cashierFileDatas);
        mixed.put("branch", branchFileDatas);
        mixed.put("company", companyrFileDatas);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        companyFormRequestManager = (ICompanyFormRequestManager) context.getBean("CompanyFormRequestManagerImpl");
        cashierManager = (ICashierManager) context.getBean("CashierManagerImpl");

        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 1; i < 5; i++) {//// TODO: 2/28/2017 activation last action  update db neccerry columns
            System.out.println("==>"+i);
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
                } catch (DataParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //war.Merchant CompanyFormRequestActionDemo.clientSendRequest
    private static String clientSendRequest(Integer index) {

        String companyName = "Demo Company " + index;
        String companyAddress = "Demo Address " + index;
        String companyEmail = "DemoEmail" + index + "@gmail.com";
        String companyPhoneCode = "0" + index;
        String companyPhone = "00" + index;
        String countOfBranches = "" + index;
        String countOfWorkers = "" + index;
        String contactName = "Demo contactName" + index;
        String contactLastName = "Demo contactLastName" + index;
        String contactEmail = "DemoContactEmail" + index+ "@gmail.do";
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
            logger.error(e);
            return ERROR;
        }

        return SUCCESS;
    }

    //war.MerchantAdmin CompanyActivationAction.activate
    private static Cashier activate(int userId, String requestId, Integer index) {
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

            FileData agreementDocument = initFileData();
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
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cashier;
    }

    //war.merchant CompanyActivationAction.activate
    private static String activate(Cashier cashierSes) {
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
            logger.error(e);
            e.printStackTrace();
            return ERROR;
        } catch (EntityNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
            return ERROR;
        } catch (DataParseException e) {
            logger.error(e);
            e.printStackTrace();
            return ERROR;
        }
    }

    private static Cashier initCashier(Cashier cashierSes, HashMap<String, List<FileData>> cashierFileData) throws DataParseException {

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
        cashier.setStatus(Status.ACTIVE);
        cashier.setFileDatas(cashierFileData.get("cashier"));

        return cashier;
    }

    private static void initViewData(Cashier sessionCashier) throws DataParseException {

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

        nameRole = role.getName ();
        descriptionRole = role.getDescription();
        transactionMinRole = role.getTransactionMin().toString();
        transactionMaxRole = role.getTransactionMax().toString();
        availableRateValuesRole = role.getAvailableRateValues();

    }

    private static CashierCashBox initCashierCashBox() {
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

    private static BranchCashBox initBranchCashBox() {
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

    private static Branch initBranch(Company sesCompany, HashMap<String, List<FileData>> branchFileData) {
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

    private static Company modifyCompany(Company sessionCompany, HashMap<String, List<FileData>> companyFileData) throws DataParseException {

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

    private static CompanyCashBox modifyCompanyCashBox(CompanyCashBox sessionCompanyCashBox) throws DataParseException {

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

    private static boolean isValidCashier(List<Cashier> cashiers) {
        return !Utils.isEmpty(cashiers) && cashiers.get(0) != null && cashiers.get(0) != null
                && cashiers.get(0).getCompany() != null && cashiers.get(0).getCompany().getCurrentCashBox() != null
                && cashiers.get(0).getBranch() != null;
    }

    private static FileData initFileData() throws IOException {
        File data = new File("C:\\Users\\Serozh\\Desktop\\/merchant.png");
        String mimeType = data.toURL().openConnection().getContentType();
        byte[] fileData = FileUtils.readFileToByteArray(data);
        if (fileData == null) {
            logger.error("DataUploadAction, data or dataFileName is null");
            return null;
        }

        FileData d = new FileData();
        d.setData(fileData);
        d.setStatus(Status.ACTIVE);
        d.setFileName(System.currentTimeMillis() + "_" + data.getName());
        d.setContentType(mimeType);
        d.setSize(fileData.length);
        d.setCreationDate(new Date(System.currentTimeMillis()));

        return d;
    }


    private static void setFields(int i) {
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
    private static String nameCompany;
    private static String addressCompany;
    private static String emailCompany;
    private static String phoneCodeCompany;
    private static String phoneCompany;
    private static Set<PartitionLCP> allowedPartitions;


    //editable
    private static String countryCompany;
    private static String countryRegionCompany;
    private static String cityCompany;
    private static String streetCompany;
    private static String zipCompany;
    private static String policyPhoneCodeCompany;
    private static String policyPhoneCompany;
    private static String availableRateValuesCompany;

    //Setup
    //private static String currencyTypeMerchantSetup;
    private static String depositFeePercentMerchantSetup;
    private static String depositMinFeeMerchantSetup;
    private static String depositMaxFeeMerchantSetup;
    private static String withdrawFeePercentMerchantSetup;
    private static String withdrawMinFeeMerchantSetup;
    private static String withdrawMaxFeeMerchantSetup;
    private static String exchangeDepositFeePercentMerchantSetup;
    private static String exchangeDepositMinFeeMerchantSetup;
    private static String exchangeDepositMaxFeeMerchantSetup;
    private static String exchangeWithdrawFeePercentMerchantSetup;
    private static String exchangeWithdrawMinFeeMerchantSetup;
    private static String exchangeWithdrawMaxFeeMerchantSetup;
    //private static String availableRateValuesMerchantSetup;

    //Branch
    private static String nameBranch;
    private static String addressBranch;
    private static String cityBranch;
    private static String streetBranch;
    private static String zipBranch;
    private static String emailBranch;
    private static String phoneCodeBranch;
    private static String phoneBranch;
    private static String policyPhoneCodeBranch;
    private static String policyPhoneBranch;
    private static CountryRegion countryRegionBranch;

    //Cashier
    private static String nameCashier;
    private static String surnameCashier;
    private static String emailCashier;
    private static String phoneCodeCashier;
    private static String phoneCashier;

    private static String passwordCashier;
    private static String passwordCashierRepeat;
    private static String verificationCode;

    //Role
    private static String nameRole;
    private static String descriptionRole;
    private static String transactionMinRole;
    private static String transactionMaxRole;
    private static String availableRateValuesRole;

}
