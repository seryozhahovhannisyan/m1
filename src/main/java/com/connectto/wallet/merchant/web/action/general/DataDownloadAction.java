package com.connectto.wallet.merchant.web.action.general;


import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by htdev001 on 3/5/14.
 */
public class DataDownloadAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(DataDownloadAction.class.getSimpleName());

    private InputStream inputStream;
    private Long dataId;
    private String dataFileName;

    public String execute() {

        if (Utils.isEmpty(dataFileName) || dataId == -1) {
            logger.error("DataDownloadAction, dataFileName or dataId is null");
            session.put(MESSAGE, getText("error.internal"));
            return "start";
        }
        dataFileName = Initializer.getUploadDir() + Constants.FILE_SEPARATOR + dataId + Constants.FILE_SEPARATOR + dataFileName;
        dataFileName = dataFileName.replaceAll("//", "/");
        dataFileName = dataFileName.replaceAll("\\\\", "/");

        try {
            File file = new File(dataFileName);
            if (!file.exists()) {
                logger.error("The attached file not exists; data id=" + dataId + "; file name = " + dataFileName);
                session.put(MESSAGE, getText("error.internal"));
                return "start";
            }

            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error(e);
        }

        return SUCCESS;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataId(String dataId) {
        try {
            this.dataId = Long.parseLong(dataId);
        } catch (Exception e) {
            this.dataId = -1L;
            logger.error("Incorrect transaction id ,  transactionId=" + dataId);
        }
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

}
