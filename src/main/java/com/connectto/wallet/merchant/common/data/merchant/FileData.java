package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;

import java.io.File;
import java.util.Date;

/**
 * Created by htdev001 on 3/7/14.
 */
public class FileData {

    private Long id;
    private String fileName;
    private String contentType;
    private Integer size;
    private Date creationDate;
    private Status status;
    //
    private Company company;
    private CompanyFormResponse companyFormResponse;
    private Branch branch;
    private Cashier cashier;

    private Long companyId;
    private Long companyFormResponseId;
    private Long branchId;
    private Long cashierId;
    //
    private File file;
    private byte[] data;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyFormResponse getCompanyFormResponse() {
        return companyFormResponse;
    }

    public void setCompanyFormResponse(CompanyFormResponse companyFormResponse) {
        this.companyFormResponse = companyFormResponse;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyFormResponseId() {
        return companyFormResponseId;
    }

    public void setCompanyFormResponseId(Long companyFormResponseId) {
        this.companyFormResponseId = companyFormResponseId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
