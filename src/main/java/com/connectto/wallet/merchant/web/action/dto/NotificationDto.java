package com.connectto.wallet.merchant.web.action.dto;

/**
 * Created by Serozh on 1/24/2017.
 */
public class NotificationDto {

    private String withdrawId;
    private String depositId;

    private String finalState;

    private String img;
    private String name;
    private String surname;

    private String duration;

     /*
    * #################################################################################################################
    * ########################################        GETTER & SETTER       ###########################################
    * #################################################################################################################
    */

    public String getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
