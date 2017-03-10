package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serozh on 6/22/2016.
 */
public class CompanyFormRequest implements Serializable {

    private Long id;
    private Long tsmCompanyId;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private String companyPhoneCode;
    private String companyPhone;
    private String countOfBranches;
    private String countOfWorkers;
    private String contactName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhoneCode;
    private String contactPhone;
    private String message;

    private Status status;
    private Date requestedAt;

    private Company company;

    /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTsmCompanyId() {
        return tsmCompanyId;
    }

    public void setTsmCompanyId(Long tsmCompanyId) {
        this.tsmCompanyId = tsmCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhoneCode() {
        return companyPhoneCode;
    }

    public void setCompanyPhoneCode(String companyPhoneCode) {
        this.companyPhoneCode = companyPhoneCode;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCountOfBranches() {
        return countOfBranches;
    }

    public void setCountOfBranches(String countOfBranches) {
        this.countOfBranches = countOfBranches;
    }

    public String getCountOfWorkers() {
        return countOfWorkers;
    }

    public void setCountOfWorkers(String countOfWorkers) {
        this.countOfWorkers = countOfWorkers;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneCode() {
        return contactPhoneCode;
    }

    public void setContactPhoneCode(String contactPhoneCode) {
        this.contactPhoneCode = contactPhoneCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}