package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.OnlineStatus;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class Cashier implements Serializable {

    private Long id;

    //Personal
    private String name;
    private String surname;
    private String logo;
    //
    private String email;
    private String phoneCode;
    private String phone;
    private String password;

    private String verificationCode;
    private String resetPasswordToken;

    private Date registeredAt;
    private Date activatedAt;

    private Company company;
    private Branch branch;
    private Role role;
    private Cashier headCashier;
    private FileData logoFileData;
    private CashierCashBox currentCashBox;

    private Privilege privilege;
    private Status status;
    private OnlineStatus onlineStatus;

    private List<FileData> fileDatas;
    private Set<CashierCashBox> allCashBoxes;

    private Long companyId;
    private Long branchId;
    private Long roleId;
    private Long headCashierId;
    private Long logoId;
    private Long currentCashBoxId;

    private int pendingCount;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public CashierCashBox getCurrentCashBox() {
        return currentCashBox;
    }

    public void setCurrentCashBox(CashierCashBox currentCashBox) {
        this.currentCashBox = currentCashBox;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public List<FileData> getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(List<FileData> fileDatas) {
        this.fileDatas = fileDatas;
    }

    public Set<CashierCashBox> getAllCashBoxes() {
        return allCashBoxes;
    }

    public void setAllCashBoxes(Set<CashierCashBox> allCashBoxes) {
        this.allCashBoxes = allCashBoxes;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }
}
