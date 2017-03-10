package com.connectto.wallet.merchant.web.action.cashier;

import com.connectto.wallet.merchant.business.merchant.ICompanyFormRequestManager;
import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.data.merchant.CompanyFormRequest;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by Serozh on 6/26/2016.
 */
public class CompanyAction extends BaseAction implements ModelDriven<Company> {

    private static Logger logger = Logger.getLogger(CompanyAction.class);

    private ICompanyFormRequestManager companyFormRequestManager;

    private ICompanyManager companyManager;

    private List<CompanyFormRequest> formRequests;

    private List<Company> companies;

    private Company company = new Company();

    private Long id;

    public String addView() {
        return SUCCESS;
    }

    public String add() {
        try {
            company.setExpiredDate(new Date(System.currentTimeMillis()));
            company.setCreatedDate(new Date(System.currentTimeMillis()));
            company.setRequestId(1L);
            companyManager.add(company);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String view() {
        id = company.getId();
        try {
            company = companyManager.getById(id);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String list() {
        try {
            companies = companyManager.getByParams(null);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String editView() {
        id = company.getId();
        try {
            company = companyManager.getById(id);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String edit() {
        try {
            companyManager.update(company);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            companyManager.delete(company);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String forceDelete() {
        id = company.getId();
        try {
            companyManager.forceDelete(id);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public List<CompanyFormRequest> getFormRequests() {
        return formRequests;
    }

    public Company getCompany() {
        return company;
    }

    public Long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanyFormRequestManager(ICompanyFormRequestManager companyFormRequestManager) {
        this.companyFormRequestManager = companyFormRequestManager;
    }

    public void setCompanyManager(ICompanyManager companyManager) {
        this.companyManager = companyManager;
    }


    @Override
    public Company getModel() {
        return company;
    }
}