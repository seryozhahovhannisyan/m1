package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Country;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CountryRegion;
import com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.util.Utils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Serozh on 6/22/2016.
 */
public class Company implements Serializable {

    private Long id;

    private Long tsmCompanyId;
    private String name;
    private String address;

    private String city;
    private String street;
    private String zip;
    private String email;

    private String phoneCode;
    private String phone;
    private String policyPhoneCode;
    private String policyPhone;
    private Date createdDate;
    private Date expiredDate;
    //
    private String secretKey;
    private String clientKey;

    private String allowedRemoteAddressValues;
    private Set<String> allowedRemoteAddresses;

    private String allowedPartitionValues;
    private Set<PartitionLCP> allowedPartitions;

    //
    private CompanyFormRequest request;
    private CompanyFormResponse response;
    private CompanyCashBox currentCashBox;
    private FileData logo;
    private Branch headBranch;
    private Cashier headCashier;

    private Long requestId;
    private Long responseId;
    private Long companyCashBoxId;
    private Long logoId;
    private Long headBranchId;
    private Long headCashierId;
    //lcp
    private Country country;
    private CountryRegion countryRegion;
    private Status status;

    private Set<Branch> branches;
    private List<FileData> fileDatas;
    private Set<Cashier> cashiers;
    private Set<CompanyCashBox> allCashBoxes;

    public void addBranch(Branch branch) {
        if (Utils.isEmpty(branches)) {
            branches = new HashSet<Branch>();
        }
        branches.add(branch);
    }

    public boolean isPartitionSupported(int partitionId) throws DataParseException {
        if (!Utils.isEmpty(allowedPartitionValues)) {
            parseAvailablePartitions();
            if (Utils.isEmpty(allowedPartitionValues)) {
                return false;
            }
            for (PartitionLCP ct : PartitionLCP.values()) {
                if (ct.getId() == partitionId) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<PartitionLCP> parseAvailablePartitions() throws DataParseException {
        if (!Utils.isEmpty(this.allowedPartitionValues)) {
            this.allowedPartitions = new HashSet<PartitionLCP>();

            String[] ars = this.allowedPartitionValues.split(",");

            try {
                for (String s : ars) {
                    PartitionLCP type = PartitionLCP.valueOf(Integer.parseInt(s.trim()));
                    if (type != null) {
                        this.allowedPartitions.add(type);
                    }
                }
            } catch (Exception e) {
                throw new DataParseException(e);
            }


            return this.allowedPartitions;
        }

        return null;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPolicyPhoneCode() {
        return policyPhoneCode;
    }

    public void setPolicyPhoneCode(String policyPhoneCode) {
        this.policyPhoneCode = policyPhoneCode;
    }

    public String getPolicyPhone() {
        return policyPhone;
    }

    public void setPolicyPhone(String policyPhone) {
        this.policyPhone = policyPhone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
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

    public Set<PartitionLCP> getAllowedPartitions() {
        return allowedPartitions;
    }

    public void setAllowedPartitions(Set<PartitionLCP> allowedPartitions) {
        this.allowedPartitions = allowedPartitions;
    }

    public CompanyFormRequest getRequest() {
        return request;
    }

    public void setRequest(CompanyFormRequest request) {
        this.request = request;
    }

    public CompanyFormResponse getResponse() {
        return response;
    }

    public void setResponse(CompanyFormResponse response) {
        this.response = response;
    }

    public CompanyCashBox getCurrentCashBox() {
        return currentCashBox;
    }

    public void setCurrentCashBox(CompanyCashBox currentCashBox) {
        this.currentCashBox = currentCashBox;
    }

    public FileData getLogo() {
        return logo;
    }

    public void setLogo(FileData logo) {
        this.logo = logo;
    }

    public Branch getHeadBranch() {
        return headBranch;
    }

    public void setHeadBranch(Branch headBranch) {
        this.headBranch = headBranch;
    }

    public Cashier getHeadCashier() {
        return headCashier;
    }

    public void setHeadCashier(Cashier headCashier) {
        this.headCashier = headCashier;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Long getCompanyCashBoxId() {
        return companyCashBoxId;
    }

    public void setCompanyCashBoxId(Long companyCashBoxId) {
        this.companyCashBoxId = companyCashBoxId;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    public Long getHeadBranchId() {
        return headBranchId;
    }

    public void setHeadBranchId(Long headBranchId) {
        this.headBranchId = headBranchId;
    }

    public Long getHeadCashierId() {
        return headCashierId;
    }

    public void setHeadCashierId(Long headCashierId) {
        this.headCashierId = headCashierId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public CountryRegion getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(CountryRegion countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public List<FileData> getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(List<FileData> fileDatas) {
        this.fileDatas = fileDatas;
    }

    public Set<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(Set<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public Set<CompanyCashBox> getAllCashBoxes() {
        return allCashBoxes;
    }

    public void setAllCashBoxes(Set<CompanyCashBox> allCashBoxes) {
        this.allCashBoxes = allCashBoxes;
    }
}