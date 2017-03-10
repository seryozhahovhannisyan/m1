package com.connectto.wallet.merchant.business.merchant.impl;

import com.connectto.wallet.merchant.business.merchant.ICompanyFormRequestManager;
import com.connectto.wallet.merchant.common.data.merchant.*;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.*;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.notification.MailContent;
import com.connectto.wallet.merchant.web.action.util.notification.MailException;
import com.connectto.wallet.merchant.web.action.util.notification.MailSender;
import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Transactional(readOnly = true, value = "MerchantTM")
public class CompanyFormRequestManagerImpl implements ICompanyFormRequestManager {

    private ICompanyFormRequestDao dao;
    private ICompanyDao companyDao;
    private IBranchDao branchDao;
    private IBranchCashBoxDao branchCashBoxDao;
    private ICashierDao cashierDao;
    @Deprecated
    private IRoleDao roleDao;
    private ICashierCashBoxDao cashierCashBoxDao;
    private IFileDataDao fileDataDao;

    //todo remove after test
    private ICompanyFormResponseDao responseDao;
    private ICompanyCashBoxDao companyCashBoxDao;


    public void setDao(ICompanyFormRequestDao dao) {
        this.dao = dao;
    }

    public void setCompanyDao(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setBranchDao(IBranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setCashierDao(ICashierDao cashierDao) {
        this.cashierDao = cashierDao;
    }

    public void setFileDataDao(IFileDataDao fileDataDao) {
        this.fileDataDao = fileDataDao;
    }

    public void setBranchCashBoxDao(IBranchCashBoxDao branchCashBoxDao) {
        this.branchCashBoxDao = branchCashBoxDao;
    }

    public void setCashierCashBoxDao(ICashierCashBoxDao cashierCashBoxDao) {
        this.cashierCashBoxDao = cashierCashBoxDao;
    }

    public void setResponseDao(ICompanyFormResponseDao responseDao) {
        this.responseDao = responseDao;
    }

    public void setCompanyCashBoxDao(ICompanyCashBoxDao companyCashBoxDao) {
        this.companyCashBoxDao = companyCashBoxDao;
    }

    @Deprecated
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void add(MailContent mailContent, CompanyFormRequest data) throws InternalErrorException {
        try {

            dao.add(data);

            if(mailContent != null){
                String fromEmail = Initializer.getSetupInfo().notificationEmailUsername;
                String fromEmailPassword = Initializer.getSetupInfo().notificationEmailPassword;

                MailSender mailNotification = new MailSender();
                mailNotification.sendEmailFromConnectTo(mailContent, fromEmail, fromEmailPassword);
            }

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (MailException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public void activate(CompanyFormRequest companyFormRequest) throws InternalErrorException, EntityNotFoundException {
        try {

            Company company = companyFormRequest.getCompany();
            List<FileData> companyFileDatas = company.getFileDatas();
            CompanyCashBox companyCashBox = company.getCurrentCashBox();

            companyCashBoxDao.activate(companyCashBox);
            companyDao.activate(company);

            Branch branch = Utils.getFirst(company.getBranches());
            BranchCashBox defaultBranchCashBox = branch.getCurrentCashBox();
            List<FileData> branchFileDatas = branch.getFileDatas();

            branchDao.activate(branch);
            defaultBranchCashBox.setBranchId(branch.getId());
//            defaultBranchCashBox.setCurrencyType();
            branchCashBoxDao.add(defaultBranchCashBox);

            Cashier cashier = Utils.getFirst(branch.getMemberCashiers());


            CashierCashBox defaultCashBox = cashier.getCurrentCashBox();
            List<FileData> cashierFileDatas = cashier.getFileDatas();

            cashierDao.activate(cashier);
            defaultCashBox.setCashierId(cashier.getId());
            cashierCashBoxDao.add(defaultCashBox);

            dao.activate(companyFormRequest);

            //companyFileDatas
            if (!Utils.isEmpty(companyFileDatas)) {

                for (FileData data : companyFileDatas) {

                    data.setCompanyId(company.getId());

                    String fileName = data.getFileName();
                    String extension = fileName.substring(fileName.indexOf("."));
                    //
                    fileName = System.currentTimeMillis() + extension;
                    //

                    data.setFileName(fileName);

                    fileDataDao.add(data);

                    File originalFile = new File(Initializer.getCompanyDocumentUploadDir() + Constants.FILE_SEPARATOR + data.getId() + Constants.FILE_SEPARATOR + fileName);
                    FileUtils.writeByteArrayToFile(originalFile, data.getData());
                }
            }

            //branchFileDatas
            if (!Utils.isEmpty(branchFileDatas)) {

                for (FileData data : branchFileDatas) {

                    data.setBranchId(branch.getId());

                    String fileName = data.getFileName();
                    String extension = fileName.substring(fileName.indexOf("."));
                    //
                    fileName = System.currentTimeMillis() + extension;
                    //

                    data.setFileName(fileName);


                    fileDataDao.add(data);

                    File originalFile = new File(Initializer.getCompanyDocumentUploadDir() + Constants.FILE_SEPARATOR + data.getId() + Constants.FILE_SEPARATOR + fileName);
                    FileUtils.writeByteArrayToFile(originalFile, data.getData());
                }
            }

            //cashierFileDatas
            if (!Utils.isEmpty(cashierFileDatas)) {

                for (FileData data : cashierFileDatas) {

                    data.setCashierId(cashier.getId());

                    String fileName = data.getFileName();
                    String extension = fileName.substring(fileName.indexOf("."));
                    //
                    fileName = System.currentTimeMillis() + extension;
                    //

                    data.setFileName(fileName);


                    fileDataDao.add(data);

                    File originalFile = new File(Initializer.getCompanyDocumentUploadDir() + Constants.FILE_SEPARATOR + data.getId() + Constants.FILE_SEPARATOR + fileName);
                    FileUtils.writeByteArrayToFile(originalFile, data.getData());
                }
            }

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        } catch (NullPointerException e) {
            throw new InternalErrorException(e);
        }
    }


    @Override
    @Deprecated
    @Transactional(readOnly = false, rollbackFor = Exception.class, value = "MerchantTM")
    public Cashier activateCompany(CompanyFormRequest data, CompanyFormResponse formResponse, CompanyCashBox companyCashBox) throws InternalErrorException {

        Status status = data.getStatus();
        Company company = null;
        Branch generalOfBranch = null;
        Cashier superAdmin = null;
        Role superRole = null;

        try {

            String password = GeneralUtil.generatePassword();

            FileData agreementDocument = formResponse.getAgreementDocument();
            CompanyFormRequest formRequest = dao.getById(data.getId());
            company = DataConverter.convertCompanyFormRequestToCompany(formRequest,  formResponse);
            //tsmCompany = DataConverter.createTsmCompany(company);
            //tsmCompanyJson = DataConverter.convertToJSON(tsmCompany);

            generalOfBranch = DataConverter.convertCompanyFormRequestToBranch(formRequest);
            superAdmin = DataConverter.createSuperAdmin(formRequest, password);
            superRole = DataConverter.createSuperRole(companyCashBox);


            agreementDocument.setStatus(status);
            fileDataDao.add(agreementDocument);
            formResponse.setAgreementDocumentId(agreementDocument.getId());

            formResponse.setStatus(status);
            responseDao.add(formResponse);
            company.setResponseId(formResponse.getId());

            companyDao.addAdminTest(company);
            companyCashBox.setOpenedAt(company.getCreatedDate());
            companyCashBox.setClosedAt(company.getExpiredDate());
            companyCashBox.setCompanyId(company.getId());
            companyCashBox.setStatus(status);

            companyCashBoxDao.add(companyCashBox);

            generalOfBranch.setCompanyId(company.getId());
            branchDao.add(generalOfBranch);

            superRole.setCompanyId(company.getId());
            roleDao.add(superRole);

            superAdmin.setCompanyId(company.getId());
            superAdmin.setBranchId(generalOfBranch.getId());
            superAdmin.setRoleId(superRole.getId());
            cashierDao.add(superAdmin);

            dao.changeStatus(data);

            File originalFile = new File(Initializer.getAgreementDocumentUploadDir() + Constants.FILE_SEPARATOR + company.getId() + Constants.FILE_SEPARATOR + agreementDocument.getId());
            FileUtils.writeByteArrayToFile(originalFile, agreementDocument.getData());

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EncryptException e) {
            throw new InternalErrorException(e);
        }  catch (IOException e) {
            throw new InternalErrorException(e);
        }
        return superAdmin;
    }




}
