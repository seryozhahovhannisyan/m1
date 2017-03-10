package com.connectto.wallet.merchant.web.action.crm;

import com.connectto.wallet.merchant.web.action.BaseAction;
import org.apache.log4j.Logger;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CRMLoginAction extends BaseAction {

    //private IUserManager userManager;

    private String username;

    private String password;

    private static Logger logger = Logger.getLogger(CRMLoginAction.class);


    public String view(){
        return SUCCESS;
    }

    public String execute(){
        return SUCCESS;
    }

    /*public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }*/

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

}
