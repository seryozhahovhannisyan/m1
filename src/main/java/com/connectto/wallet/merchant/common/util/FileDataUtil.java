package com.connectto.wallet.merchant.common.util;

import com.connectto.wallet.merchant.web.util.Constants;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Serozh on 3/16/2017.
 */
public class FileDataUtil {

    public static String LOGO_FORMAT = "%s_%d%s";
    public static String LOGO_PREFIX_COMPANY = "company";
    public static String LOGO_PREFIX_BRANCH = "branch";
    public static String LOGO_PREFIX_CASHIER = "cashier";


    public static void createFileCompany(String fileName,  byte[] data) throws IOException {
        File originalFile = new File(Initializer.getUploadDir() + Initializer.getCompanyDocumentUploadDir() + Constants.FILE_SEPARATOR + fileName);
        FileUtils.writeByteArrayToFile(originalFile, data);
    }

    public static void createFileAgreement(String fileName,  byte[] data) throws IOException {
        File originalFile = new File(Initializer.getUploadDir() + Initializer.getAgreementDocumentUploadDir() + Constants.FILE_SEPARATOR + fileName);
        FileUtils.writeByteArrayToFile(originalFile, data);
    }


}
