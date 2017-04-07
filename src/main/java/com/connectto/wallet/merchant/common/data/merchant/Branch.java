package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CountryRegion;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.BranchCashBox;
import com.connectto.wallet.merchant.common.util.Utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class Branch implements Serializable {

    private Long id;

    private String name;
    private String logo;
    private String address;

    private String city;
    private String street;
    private String zip;
    private String email;
    private String phoneCode;
    private String phone;
    private String policyPhoneCode;
    private String policyPhone;

    private Company company;
    private Cashier headCashier;
    private FileData logoFileData;
    private BranchCashBox currentCashBox;

    private Long companyId;
    private Long headCashierId;
    private Long logoId;
    private Long currentCashBoxId;
    //
    private List<FileData> fileDatas;
    private Set<Cashier> memberCashiers;
    private Set<BranchCashBox> allCashBoxes;
    //lcp
    private CountryRegion countryRegion;
    private Status status;

    public void addMember(Cashier cashier) {
        if (Utils.isEmpty(memberCashiers)) {
            memberCashiers = new HashSet<Cashier>();
        }
        memberCashiers.add(cashier);
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Cashier getHeadCashier() {
        return headCashier;
    }

    public void setHeadCashier(Cashier headCashier) {
        this.headCashier = headCashier;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public FileData getLogoFileData() {
        return logoFileData;
    }

    public void setLogoFileData(FileData logoFileData) {
        this.logoFileData = logoFileData;
    }

    public BranchCashBox getCurrentCashBox() {
        return currentCashBox;
    }

    public void setCurrentCashBox(BranchCashBox currentCashBox) {
        this.currentCashBox = currentCashBox;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getHeadCashierId() {
        return headCashierId;
    }

    public void setHeadCashierId(Long headCashierId) {
        this.headCashierId = headCashierId;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    public Long getCurrentCashBoxId() {
        return currentCashBoxId;
    }

    public void setCurrentCashBoxId(Long currentCashBoxId) {
        this.currentCashBoxId = currentCashBoxId;
    }

    public List<FileData> getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(List<FileData> fileDatas) {
        this.fileDatas = fileDatas;
    }

    public Set<Cashier> getMemberCashiers() {
        return memberCashiers;
    }

    public void setMemberCashiers(Set<Cashier> memberCashiers) {
        this.memberCashiers = memberCashiers;
    }

    public Set<BranchCashBox> getAllCashBoxes() {
        return allCashBoxes;
    }

    public void setAllCashBoxes(Set<BranchCashBox> allCashBoxes) {
        this.allCashBoxes = allCashBoxes;
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
}