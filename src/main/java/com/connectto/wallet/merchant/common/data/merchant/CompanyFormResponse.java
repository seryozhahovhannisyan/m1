package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class CompanyFormResponse implements Serializable {

    private Long id;

    private String title;
    private String message;

    private String allowedRemoteAddressValues;
    private Set<String> allowedRemoteAddresses;

    private String allowedPartitionValues;
    private Set<String> allowedPartitions;

    private Status status;
    private Date responseAt;

    private FileData agreementDocument;

    //todo remove after test
    @Deprecated
    private Integer userId;
    @Deprecated
    private Long agreementDocumentId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAllowedRemoteAddressValues() {
        return allowedRemoteAddressValues;
    }

    public void setAllowedRemoteAddressValues(String allowedRemoteAddressValues) {
        this.allowedRemoteAddressValues = allowedRemoteAddressValues;
    }

    public Set<String> getAllowedRemoteAddresses() {
        return allowedRemoteAddresses;
    }

    public void setAllowedRemoteAddresses(Set<String> allowedRemoteAddresses) {
        this.allowedRemoteAddresses = allowedRemoteAddresses;
    }

    public String getAllowedPartitionValues() {
        return allowedPartitionValues;
    }

    public void setAllowedPartitionValues(String allowedPartitionValues) {
        this.allowedPartitionValues = allowedPartitionValues;
    }

    public Set<String> getAllowedPartitions() {
        return allowedPartitions;
    }

    public void setAllowedPartitions(Set<String> allowedPartitions) {
        this.allowedPartitions = allowedPartitions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(Date responseAt) {
        this.responseAt = responseAt;
    }

    public FileData getAgreementDocument() {
        return agreementDocument;
    }

    public void setAgreementDocument(FileData agreementDocument) {
        this.agreementDocument = agreementDocument;
    }

    @Deprecated
    public Integer getUserId() {
        return userId;
    }

    @Deprecated
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Deprecated
    public Long getAgreementDocumentId() {
        return agreementDocumentId;
    }

    @Deprecated
    public void setAgreementDocumentId(Long agreementDocumentId) {
        this.agreementDocumentId = agreementDocumentId;
    }
}