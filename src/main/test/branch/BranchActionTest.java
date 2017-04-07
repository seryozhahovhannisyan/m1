package branch;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CountryRegion;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Serozh on 6/26/2016.
 */
public class BranchActionTest {

    private static Logger logger = Logger.getLogger(BranchActionTest.class);
    private static String SUCCESS = "SUCCESS";
    private static String ERROR = "ERROR";

    //for add
    private static Date currentDate;
    private static CurrencyType currencyType;

    private static IBranchManager branchManager;
    private static ICompanyManager companyManager;

    private static String name;
    private static String address;

    private static String city;
    private static String street;
    private static String zip;
    private static String email;
    private static String phoneCode;
    private static String phone;
    private static String policyPhoneCode;
    private static String policyPhone;
    private static CountryRegion region;

    //

    //Cashier
    private static String nameCashier;
    private static String surnameCashier;
    private static String emailCashier;
    private static String phoneCodeCashier;
    private static String phoneCashier;

    private static String passwordCashier;
    private static String passwordCashierRepeat;

    //

    private static Long roleId;


    private static Status status;
    private static Long id;
    private static String branchIdes;


    private static HashMap<String, List<FileData>> mixed = new HashMap<String, List<FileData>>();
    private static FileData fileData = null;

    public static void main(String[] args) throws IOException {
        fileData = initFileData();

        List<FileData> cashierFileDatas = new ArrayList<FileData>();
        List<FileData> branchFileDatas = new ArrayList<FileData>();
        cashierFileDatas.add(initFileData());
        branchFileDatas.add(initFileData());
        mixed.put("cashier", cashierFileDatas);
        mixed.put("branch", branchFileDatas);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        branchManager = (IBranchManager) context.getBean("BranchManagerImpl");
        for (long i =1; i < 10; i++) {
            String result = add(i);
            System.out.print("companyId : " + i + " " +result);
        }
    }

    private static String add(Long companyId) {

        passwordCashier = "1";
        passwordCashierRepeat = "1";

        if (Utils.isEmpty(passwordCashier)) {
            return ERROR;
        } /*else if (!GeneralUtil.isValidPassword(passwordCashier)) {
            logger.info("Validation not passed empty password");
            return ERROR;
        } */ else if (!passwordCashier.equals(passwordCashierRepeat)) {
            logger.info("Validation not passed empty password");
            return ERROR;
        }

        Cashier cashierSes = initSessionCashier(companyId);//// TODO: 3/1/2017 iterate
        currentDate = new Date(System.currentTimeMillis());
        currencyType = cashierSes.getCurrentCashBox().getCurrencyType();
        status = Status.ACTIVE;

        for (long i = 1; i < 500; i++) {
            System.out.println("Company : "+companyId + " Branch : " + i);
            initAdd(companyId, i);

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
                Cashier cashier = initCashier(cashierSes, companyId,mixed);
                cashier.setCompanyId(companyId);
                branch.addMember(cashier);
                branch.setCurrentCashBox(defaultBranchCashBox);

                branchManager.add(branch);
            } catch (InternalErrorException e) {
                e.printStackTrace();
                return ERROR;
            } catch (DataParseException e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        return SUCCESS;
    }

    private static Cashier initCashier(Cashier sessionCashier, Long companyId, HashMap<String, List<FileData>> cashierFileData) throws DataParseException {

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

       // cashier.setVerificationCode("Verified");

        cashier.setRegisteredAt(currentDate);
        cashier.setActivatedAt(currentDate);

        cashier.setPrivilege(Privilege.CRUD_BRANCH);
        cashier.setStatus(status);

        cashier.setRoleId(roleId);
        cashier.setFileDatas(cashierFileData.get("cashier"));

        return cashier;
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

    private static Cashier initSessionCashier(Long companyId) {
        CashierCashBox cashierCashBox = new CashierCashBox();
        cashierCashBox.setCurrencyType(CurrencyType.USD);

        Cashier cashier = new Cashier();
        cashier.setId(companyId);
        cashier.setCompanyId(companyId);
        cashier.setCurrentCashBox(cashierCashBox);

        return cashier;
    }

    private static void initAdd(Long companyId, Long index) {
        name = "branch : " + companyId + " : " + index;
        address = "branch address : " + companyId + " : " + index;
        city = "branch city : " + companyId + " : " + index;
        street = "branch street: " + companyId + " : " + index;
        zip = "0" ;
        email = "branch_" + companyId + "_" + index + "@mail.fe";
        phoneCode = "9" ;
        phone = "9" + companyId + "" + index;
        policyPhoneCode = "9" ;
        policyPhone = "9" + companyId + "" + index;
        region = CountryRegion.YEREVAN;
        //
        nameCashier = "Cashier " + companyId + " : " + index;
        surnameCashier = "surname " + companyId + " : " + index;
        emailCashier = "emailCashier_" + companyId + "_" + index + "@index.ru";
        phoneCodeCashier = "-" ;
        phoneCashier = "" +System.currentTimeMillis() ;


        //Role
        roleId = companyId;

    }
}