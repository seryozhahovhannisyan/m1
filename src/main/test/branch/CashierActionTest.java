package branch;

import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICashierManager;
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
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
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
public class CashierActionTest {

    private static Logger logger = Logger.getLogger(CashierActionTest.class);
    private static String SUCCESS = "SUCCESS";
    private static String ERROR = "ERROR";

    //for add
    private static Date currentDate;
    private static CurrencyType currencyType;

    private static ICashierManager cashierManager;

    //

    //Cashier
    private static String roleId;
    private static String nameCashier;
    private static String surnameCashier;
    private static String emailCashier;
    private static String phoneCodeCashier;
    private static String phoneCashier;

    private static String passwordCashier;
    private static String passwordCashierRepeat;

    //


    private static Status status;


    private static HashMap<String, List<FileData>> mixed = new HashMap<String, List<FileData>>();
    private static FileData fileData = null;

    public static void main(String[] args) throws IOException {
        fileData = initFileData();
        List<FileData> fileDatas = new ArrayList<FileData>();
        fileDatas.add(initFileData());
        mixed.put("cashier", fileDatas);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        cashierManager = (ICashierManager) context.getBean("CashierManagerImpl");
        for (long i = 1; i < 500; i++) {
            String result = add(i, i);
            System.out.print("cashier : " + i + " " +result);
        }
    }

    private static String add(Long companyId,  Long branchId) {

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

        Cashier cashierSes = initSessionCashier(companyId, branchId);//// TODO: 3/1/2017 iterate
        currentDate = new Date(System.currentTimeMillis());
        currencyType = cashierSes.getCurrentCashBox().getCurrencyType();
        status = Status.ACTIVE;

        try {
            passwordCashier = SHAHashEnrypt.get_MD5_SecurePassword(passwordCashier);
        } catch (EncryptException e) {
            logger.warn(e);
        }

        for (long i = 1; i < 5; i++) {
            System.out.println(Thread.currentThread() + " Cashier : " + i);
            initAdd(companyId, i);



            CashierCashBox defaultCashBox = initCashierCashBox();

            Cashier cashier = new Cashier();
            cashier.setCompanyId(companyId);
            cashier.setBranchId(branchId);
            cashier.setHeadCashierId(cashierSes.getId());

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

            cashier.setRoleId(Long.parseLong(roleId));
            cashier.setFileDatas(mixed.get("cashier"));

            try {
                cashierManager.add(cashier);
            } catch (InternalErrorException e) {
                e.printStackTrace();
                return ERROR;
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        return SUCCESS;
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

    private static Cashier initSessionCashier(Long companyId, Long branchId) {
        CashierCashBox cashierCashBox = new CashierCashBox();
        cashierCashBox.setCurrencyType(CurrencyType.USD);

        Cashier cashier = new Cashier();
        cashier.setId(companyId);
        cashier.setCompanyId(companyId);
        cashier.setBranchId(branchId);
        cashier.setCurrentCashBox(cashierCashBox);

        return cashier;
    }

    private static void initAdd(Long companyId, Long index) {

        //
        nameCashier = "_Cashier" + companyId + " : " + index;
        surnameCashier = "_surname " + companyId + " : " + index;
        emailCashier = "_emailCashier_" + companyId + "_" + index + "@index.ru";
        phoneCodeCashier = "-" ;
        phoneCashier = "" +System.currentTimeMillis() ;

        roleId = "1";

    }
}