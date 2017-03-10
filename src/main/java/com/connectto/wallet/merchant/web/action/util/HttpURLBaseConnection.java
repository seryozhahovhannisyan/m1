package com.connectto.wallet.merchant.web.action.util;


import com.connectto.wallet.encryption.WalletEncription;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.deposit.WalletDepositTax;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTransactionWalletResult;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.WalletWithdrawTax;
import com.connectto.wallet.merchant.common.exception.HttpConnectionDeniedException;
import com.connectto.wallet.merchant.common.exception.WalletApiException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by htdev001 on 5/20/14.
 */
public class HttpURLBaseConnection {

    private static final Logger logger = Logger.getLogger(HttpURLBaseConnection.class.getSimpleName());

    /*################################################################################################################*/
    /*#############                           Wallet Transfer Transaction                                #############*/
    /*################################################################################################################*/

    public static synchronized TransferTransactionWalletResult transferFromMerchantToUser(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

//        String httpUrl = walletHost + "/wallet/transfer-from-merchant-to-user.htm";
//        String httpUrl = "http://127.0.0.1:8888/transfer-from-merchant-to-user.htm";
        String httpUrl = "http://127.0.0.1:8080/wallet/transfer-from-merchant-to-user.htm";

        return walletTransferTransactionAction(httpUrl, jsonObject, decript);

    }

    private static synchronized TransferTransactionWalletResult walletTransferTransactionAction(String httpUrl, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

        URL url;
        HttpURLConnection urlConn = null;
        StringBuffer responseResult = new StringBuffer();

        DataOutputStream wr = null;
        BufferedWriter writer = null;
        BufferedReader in = null;

        try {

            url = new URL(httpUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestMethod("POST");
            urlConn.connect();

            if (jsonObject != null) {
                wr = new DataOutputStream(urlConn.getOutputStream());
                writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(jsonObject.toString());

                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        logger.info(e);
                    }
                }
            }


            in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            responseResult = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseResult.append(inputLine);
            }

            return transferTransactionResponse(responseResult.toString(), decript);

        } catch (UnsupportedEncodingException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (ProtocolException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (MalformedURLException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (IOException e) {
            throw new HttpConnectionDeniedException(e);
        } finally {

            if (wr != null) {
                try {
                    wr.flush();
                    wr.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            if (urlConn != null) {
                try {
                    urlConn.disconnect();
                } catch (Exception e) {
                    logger.info(e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info(e);
                }
            }

        }

    }

    private static synchronized TransferTransactionWalletResult transferTransactionResponse(String json, boolean decript) throws WalletApiException {

        if (Utils.isEmpty(json)) {
            return null;
        }

        if (json.startsWith("{") && json.endsWith("}")) {
            JSONParser parser = new JSONParser();
            Object unitsObj = null;
            try {
                unitsObj = parser.parse(json);
                JSONObject responseMessage = (JSONObject) unitsObj;
                if (responseMessage == null || responseMessage.get("transactionReviewDto") == null || responseMessage.get("responseDto") == null) {
                    throw new WalletApiException("Incorrect wallet response incoming");
                }
                JSONObject responseDto = (JSONObject) responseMessage.get("responseDto");
                String status = (String) responseDto.get("status");
                if (!"SUCCESS".equals(status)) {
                    String messages = (String) responseDto.get("messages");
                    throw new WalletApiException(messages);
                }

                JSONObject transactionReviewDto = (JSONObject) responseMessage.get("transactionReviewDto");

                String transactionId = (String) transactionReviewDto.get("transactionId");
                String actionDate = (String) transactionReviewDto.get("actionDate");

                String walletId = (String) transactionReviewDto.get("walletId");

                String walletTotalPrice = (String) transactionReviewDto.get("walletTotalPrice");
                String walletCurrencyType = (String) transactionReviewDto.get("walletCurrencyType");

                if (decript) {

                    walletTotalPrice = WalletEncription.decrypt(walletTotalPrice);
                    walletCurrencyType = WalletEncription.decrypt(walletCurrencyType);

                }

                TransferTransactionWalletResult walletResult = new TransferTransactionWalletResult();

                walletResult.setTransactionId(Long.parseLong(transactionId));
                walletResult.setActionDate(DataConverter.toMerchantDate(actionDate));


                walletResult.setWalletId(Long.parseLong(walletId));
                walletResult.setWalletCurrencyType(CurrencyType.valueOf(Integer.parseInt(walletCurrencyType)));

                return walletResult;

            } catch (Exception e) {
                throw new WalletApiException(e);
            }
        }
        return null;
    }


    /*################################################################################################################*/
    /*#############                                 WalletWithdraw                                       #############*/
    /*################################################################################################################*/
    public static synchronized WalletWithdrawTax walletPreviewWithdrawAction(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

//        String httpUrl = walletHost + "/wallet/preview-withdraw.htm";
//        String httpUrl = "http://127.0.0.1:8888/preview-withdraw.htm";
        String httpUrl = "http://127.0.0.1:8080/wallet/preview-withdraw.htm";

        return walletTransactionMerchantWithdrawAction(httpUrl, jsonObject, decript);

    }

    public static synchronized WalletWithdrawTax walletStartWithdrawAction(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

//        String httpUrl = "http://127.0.0.1:8080/wallet/start-withdraw.htm";
        String httpUrl = "http://127.0.0.1:8080/wallet/start-withdraw.htm";
//        String httpUrl = walletHost + "/wallet/start-withdraw.htm";

        return walletTransactionMerchantWithdrawAction(httpUrl, jsonObject, decript);

    }

    public static synchronized WalletWithdrawTax walletWithdrawTimeOut(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

        String httpUrl = "http://127.0.0.1:8080/wallet/merchant-withdraw-time-out.htm";
//        Stri httpUrl = "http://127.0.0.1:8888/merchant-withdraw-time-out.htm";
//        String httpUrl = walletHost + "/wallet/merchant-withdraw-time-out.htm";

        return walletTransactionMerchantWithdrawAction(httpUrl, jsonObject, decript);

    }

    private static synchronized WalletWithdrawTax walletTransactionMerchantWithdrawAction(String httpUrl, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

        URL url;
        HttpURLConnection urlConn = null;
        StringBuffer responseResult = new StringBuffer();

        DataOutputStream wr = null;
        BufferedWriter writer = null;
        BufferedReader in = null;

        try {

            url = new URL(httpUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestMethod("POST");
            urlConn.connect();

            if (jsonObject != null) {
                wr = new DataOutputStream(urlConn.getOutputStream());
                writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(jsonObject.toString());

                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        logger.info(e);
                    }
                }
            }


            in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            responseResult = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseResult.append(inputLine);
            }

            return convertWithdrawTax(responseResult.toString(), decript);

        } catch (UnsupportedEncodingException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (ProtocolException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (MalformedURLException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (IOException e) {
            throw new HttpConnectionDeniedException(e);
        } finally {

            if (wr != null) {
                try {
                    wr.flush();
                    wr.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            if (urlConn != null) {
                try {
                    urlConn.disconnect();
                } catch (Exception e) {
                    logger.info(e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info(e);
                }
            }

        }

    }

    private static synchronized WalletWithdrawTax convertWithdrawTax(String json, boolean decript) throws WalletApiException {

        if (Utils.isEmpty(json)) {
            return null;
        }

        if (json.startsWith("{") && json.endsWith("}")) {
            JSONParser parser = new JSONParser();
            Object unitsObj = null;
            try {
                unitsObj = parser.parse(json);
                JSONObject responseMessage = (JSONObject) unitsObj;
                if (responseMessage != null && responseMessage.get("transactionReviewDto") != null && responseMessage.get("responseDto") != null) {

                    JSONObject responseDto = (JSONObject) responseMessage.get("responseDto");
                    String status = (String) responseDto.get("status");
                    if (!"SUCCESS".equals(status)) {
                        String messages = (String) responseDto.get("messages");
                        throw new WalletApiException(messages);
                    }

                    JSONObject transactionReviewDto = (JSONObject) responseMessage.get("transactionReviewDto");

                    String transactionId = (String) transactionReviewDto.get("transactionId");
                    String actionDate = (String) transactionReviewDto.get("actionDate");

                    String walletId = (String) transactionReviewDto.get("walletId");
                    String setupId = (String) transactionReviewDto.get("setupId");

                    String walletTotalPrice = (String) transactionReviewDto.get("walletTotalPrice");
                    String walletCurrencyType = (String) transactionReviewDto.get("walletCurrencyType");

                    String setupTotalAmount = (String) transactionReviewDto.get("setupTotalAmount");
                    String setupCurrencyType = (String) transactionReviewDto.get("setupCurrencyType");

                    if (decript) {

                        walletTotalPrice = WalletEncription.decrypt(walletTotalPrice);
                        walletCurrencyType = WalletEncription.decrypt(walletCurrencyType);

                        setupTotalAmount = WalletEncription.decrypt(setupTotalAmount);
                        setupCurrencyType = WalletEncription.decrypt(setupCurrencyType);

                    }

                    WalletWithdrawTax tax = new WalletWithdrawTax();

                    tax.setTransactionId(Long.parseLong(transactionId));
                    tax.setActionDate(DataConverter.toMerchantDate(actionDate));


                    tax.setWalletId(Long.parseLong(walletId));
                    tax.setSetupId(Long.parseLong(setupId));

                    tax.setPaidTaxToWalletSetupPrice(Double.parseDouble(walletTotalPrice));
                    tax.setWalletCurrencyType(CurrencyType.valueOf(Integer.parseInt(walletCurrencyType)));

                    tax.setPaidTaxToWalletSetup(Double.parseDouble(setupTotalAmount));
                    tax.setSetupCurrencyType(CurrencyType.valueOf(Integer.parseInt(setupCurrencyType)));

                    return tax;
                }
            } catch (Exception e) {
                throw new WalletApiException(e);
            }
        }
        return null;
    }

    /*################################################################################################################*/
    /*#############                                 WalletDeposit                                        #############*/
    /*################################################################################################################*/
    public static synchronized WalletDepositTax walletPreviewDepositAction(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

//        String httpUrl = walletHost + "/wallet/preview-deposit.htm";
//        String httpUrl = "http://127.0.0.1:8888/wallet/preview-deposit.htm";
        String httpUrl = "http://127.0.0.1:8080/wallet/preview-deposit.htm";

        return walletTransactionMerchantDepositAction(httpUrl, jsonObject, decript);

    }

    public static synchronized WalletDepositTax walletStartDepositAction(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

//        String httpUrl = "http://127.0.0.1:8888/wallet/start-deposit.htm";
        String httpUrl = "http://127.0.0.1:8080/wallet/start-deposit.htm";
//        String httpUrl = walletHost + "/wallet/start-deposit.htm";

        return walletTransactionMerchantDepositAction(httpUrl, jsonObject, decript);

    }

    public static synchronized WalletDepositTax walletDepositTimeOut(String walletHost, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

        String httpUrl = "hhttp://127.0.0.1:8080/wallet/merchant-deposit-time-out.htm";
//        String httpUrl =  walletHost + "/wallet/merchant-deposit-time-out.htm.htm";

        return walletTransactionMerchantDepositAction(httpUrl, jsonObject, decript);

    }

    private static synchronized WalletDepositTax walletTransactionMerchantDepositAction(String httpUrl, JSONObject jsonObject, boolean decript) throws WalletApiException, HttpConnectionDeniedException {

        URL url;
        HttpURLConnection urlConn = null;
        StringBuffer responseResult = new StringBuffer();

        DataOutputStream wr = null;
        BufferedWriter writer = null;
        BufferedReader in = null;

        try {

            url = new URL(httpUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestMethod("POST");
            urlConn.connect();

            if (jsonObject != null) {
                wr = new DataOutputStream(urlConn.getOutputStream());
                writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(jsonObject.toString());

                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        logger.info(e);
                    }
                }
            }


            in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            responseResult = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseResult.append(inputLine);
            }

            return convertDepositTax(responseResult.toString(), decript);

        } catch (UnsupportedEncodingException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (ProtocolException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (MalformedURLException e) {
            throw new HttpConnectionDeniedException(e);
        } catch (IOException e) {
            throw new HttpConnectionDeniedException(e);
        } finally {

            if (wr != null) {
                try {
                    wr.flush();
                    wr.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            if (urlConn != null) {
                try {
                    urlConn.disconnect();
                } catch (Exception e) {
                    logger.info(e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info(e);
                }
            }

        }

    }

    private static synchronized WalletDepositTax convertDepositTax(String json, boolean decript) throws WalletApiException {

        if (Utils.isEmpty(json)) {
            return null;
        }

        if (json.startsWith("{") && json.endsWith("}")) {
            JSONParser parser = new JSONParser();
            Object unitsObj = null;
            try {
                unitsObj = parser.parse(json);
                JSONObject responseMessage = (JSONObject) unitsObj;
                if (responseMessage != null && responseMessage.get("transactionReviewDto") != null && responseMessage.get("responseDto") != null) {

                    JSONObject responseDto = (JSONObject) responseMessage.get("responseDto");
                    String status = (String) responseDto.get("status");
                    if (!"SUCCESS".equals(status)) {
                        String messages = (String) responseDto.get("messages");
                        throw new WalletApiException(messages);
                    }

                    JSONObject transactionReviewDto = (JSONObject) responseMessage.get("transactionReviewDto");

                    String transactionId = (String) transactionReviewDto.get("transactionId");
                    String actionDate = (String) transactionReviewDto.get("actionDate");

                    String walletId = (String) transactionReviewDto.get("walletId");
                    String setupId = (String) transactionReviewDto.get("setupId");

                    String walletTotalPrice = (String) transactionReviewDto.get("walletTotalPrice");
                    String walletCurrencyType = (String) transactionReviewDto.get("walletCurrencyType");

                    String setupTotalAmount = (String) transactionReviewDto.get("setupTotalAmount");
                    String setupCurrencyType = (String) transactionReviewDto.get("setupCurrencyType");

                    if (decript) {

                        walletTotalPrice = WalletEncription.decrypt(walletTotalPrice);
                        walletCurrencyType = WalletEncription.decrypt(walletCurrencyType);

                        setupTotalAmount = WalletEncription.decrypt(setupTotalAmount);
                        setupCurrencyType = WalletEncription.decrypt(setupCurrencyType);

                    }

                    WalletDepositTax tax = new WalletDepositTax();

                    tax.setTransactionId(Long.parseLong(transactionId));
                    tax.setActionDate(DataConverter.toMerchantDate(actionDate));


                    tax.setWalletId(Long.parseLong(walletId));
                    tax.setSetupId(Long.parseLong(setupId));

                    tax.setPaidTaxToWalletSetupPrice(Double.parseDouble(walletTotalPrice));
                    tax.setWalletCurrencyType(CurrencyType.valueOf(Integer.parseInt(walletCurrencyType)));

                    tax.setPaidTaxToWalletSetup(Double.parseDouble(setupTotalAmount));
                    tax.setSetupCurrencyType(CurrencyType.valueOf(Integer.parseInt(setupCurrencyType)));

                    return tax;
                }
            } catch (Exception e) {
                throw new WalletApiException(e);
            }
        }
        return null;
    }


    public static synchronized CaptchaResponse googleReCaptchaSiteVerify(String secretParameter, String recap, String remoteAddress) {

        CaptchaResponse captchaResponse = new CaptchaResponse();
        captchaResponse.setSuccess(false);
        // Send get request to Google reCaptcha server with secret key
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" + secretParameter + "&response=" + recap + "&remoteip=" + remoteAddress);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            convertCaptchaResponse(captchaResponse, outputString);
        } catch (MalformedURLException e) {
            logger.error(e);
        } catch (ProtocolException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }


        return captchaResponse;
    }

    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }
        };
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertCaptchaResponse(CaptchaResponse captchaResponse, String request) {
        if (request.startsWith("{") && request.endsWith("}")) {
            JSONParser parser = new JSONParser();
            Object unitsObj = null;
            try {
                unitsObj = parser.parse(request);
                JSONObject responseMessage = (JSONObject) unitsObj;
                if (responseMessage != null) {
                    Boolean success = (Boolean) responseMessage.get("success");
                    Object errorCodes = responseMessage.get("error-codes");
                    captchaResponse.setSuccess(success);
                    if (errorCodes != null) {
                        logger.error(errorCodes.toString());
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
