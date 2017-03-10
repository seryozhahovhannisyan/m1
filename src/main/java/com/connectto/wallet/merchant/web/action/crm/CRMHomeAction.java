package com.connectto.wallet.merchant.web.action.crm;

import com.connectto.wallet.merchant.web.action.BaseAction;
import org.apache.log4j.Logger;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CRMHomeAction extends BaseAction {

    private boolean upgradeDB = false;

    private static Logger logger = Logger.getLogger(CRMHomeAction.class);


    public String view() {
        return SUCCESS;
    }

    public void setUpgradeDB(boolean upgradeDB) {
        this.upgradeDB = upgradeDB;
    }

}
