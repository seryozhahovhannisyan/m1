package com.connectto.wallet.merchant.common.util;

import com.connectto.wallet.merchant.common.data.merchant.*;
import com.connectto.wallet.merchant.common.data.merchant.lcp.*;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.web.action.dto.NotificationDto;
import com.connectto.wallet.merchant.web.action.dto.TransactionDto;
import com.connectto.wallet.merchant.web.action.dto.WalletDto;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Serozh on 6/30/2016.
 */
public class DataConverter {

    private static DateFormat merchantDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static DateFormat viewDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Company convertCompanyFormRequestToCompany(CompanyFormRequest formRequest) {
        Company company = new Company();
        company.setRequestId(formRequest.getId());
        company.setName(formRequest.getCompanyName());
        company.setAddress(formRequest.getCompanyAddress());
        company.setEmail(formRequest.getCompanyEmail());

        company.setPhoneCode(formRequest.getCompanyPhoneCode());
        company.setPhone(formRequest.getCompanyPhone());
        company.setCreatedDate(new Date(System.currentTimeMillis()));
        company.setExpiredDate(Utils.getAfter(Constants.COMPANY_EXPIRED_DAYS));
        company.setStatus(Status.UNVERIFIED);
        return company;
    }

    @Deprecated
    public static Company convertCompanyFormRequestToCompany(CompanyFormRequest formRequest, CompanyFormResponse formResponse) {
        Company company = new Company();
        company.setRequestId(formRequest.getId());
        company.setAllowedRemoteAddressValues(formResponse.getAllowedRemoteAddressValues());
        company.setAllowedPartitionValues(formResponse.getAllowedPartitionValues());
        company.setTsmCompanyId(formRequest.getTsmCompanyId());
        company.setName(formRequest.getCompanyName());
        company.setAddress(formRequest.getCompanyAddress());
        company.setEmail(formRequest.getCompanyEmail());

        company.setPhoneCode(formRequest.getCompanyPhoneCode());
        company.setPhone(formRequest.getCompanyPhone());
        company.setCreatedDate(new Date(System.currentTimeMillis()));
        company.setExpiredDate(Utils.getAfter(Constants.COMPANY_EXPIRED_DAYS));
        company.setStatus(Status.UNVERIFIED);

        company.setSecretKey(Generator.get(64, Generator.Type.ALPHABETIC_DIGIT));
        company.setClientKey(Generator.get(64, Generator.Type.ALPHABETIC_DIGIT));
        return company;
    }

    public static Branch convertCompanyFormRequestToBranch(CompanyFormRequest formRequest) {
        Branch branch = new Branch();
        branch.setName(formRequest.getCompanyName());
        branch.setAddress(formRequest.getCompanyAddress());
        branch.setEmail(formRequest.getCompanyEmail());

        branch.setPhoneCode(formRequest.getCompanyPhoneCode());
        branch.setPhone(formRequest.getCompanyPhone());

        branch.setPolicyPhoneCode(formRequest.getContactPhoneCode());
        branch.setPolicyPhone(formRequest.getContactPhone());

        branch.setStatus(Status.UNVERIFIED);
        return branch;
    }

    public static String convertCompanyFormRequestToMessage(CompanyFormRequest formRequest) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Company name").append(formRequest.getCompanyName());
        buffer.append("Company Address").append(formRequest.getCompanyAddress());
        buffer.append("Company Email").append(formRequest.getCompanyEmail());

        buffer.append("Company PhoneCode").append(formRequest.getCompanyPhoneCode());
        buffer.append("Company Phone").append(formRequest.getCompanyPhone());
        buffer.append("Date").append(new Date(System.currentTimeMillis()));
        buffer.append("ExpiredDate").append(Utils.getAfter(Constants.COMPANY_EXPIRED_DAYS));
        buffer.append("Verify Please").append(Status.UNVERIFIED);

        buffer.append("Contact Name").append(formRequest.getContactName());
        buffer.append("Contact Surname").append(formRequest.getContactLastName());

        buffer.append("Contact Email").append(formRequest.getContactEmail());

        buffer.append("Contact PhoneCode").append(formRequest.getContactPhoneCode());
        buffer.append("Contact Phone").append(formRequest.getContactPhone());

        buffer.append("Verify Please").append(Status.UNVERIFIED);
        return buffer.toString();
    }

    @Deprecated
    public static Cashier createSuperAdmin(CompanyFormRequest formRequest, String password) throws EncryptException {
        Cashier cashier = new Cashier();
        password = SHAHashEnrypt.get_MD5_SecurePassword(password);

        cashier.setName(formRequest.getContactName());
        cashier.setSurname(formRequest.getContactLastName());

        cashier.setEmail(formRequest.getContactEmail());
        cashier.setPassword(password);
        cashier.setRegisteredAt(new Date(System.currentTimeMillis()));
        cashier.setPrivilege(Privilege.ALL);
        cashier.setResetPasswordToken(GeneralUtil.generatePasswordToken());

        cashier.setPhoneCode(formRequest.getContactPhoneCode());
        cashier.setPhone(formRequest.getContactPhone());
        cashier.setVerificationCode(GeneralUtil.generateVerificationCode());

        cashier.setStatus(Status.UNVERIFIED);
        return cashier;
    }

    @Deprecated
    public static Role createSuperRole(CompanyCashBox companyCashBox) throws EncryptException {
        Role role = new Role();
        role.setName("Super Admin");
        role.setDescription("For Super Admin allowed transactions 0 -" + companyCashBox.getMaximumLimitOfTransaction() + " " + companyCashBox.getCurrencyType());

        role.setTransactionMin(0d);
        role.setTransactionMax(companyCashBox.getMaximumLimitOfTransaction());
        role.setAvailableRateValues(companyCashBox.getAvailableRateValues());
        role.setIsExchangeAllowed(!Utils.isEmpty(companyCashBox.getAvailableRateValues()));

        return role;
    }


    public static List<Long> convertStringIdesToLong(String data) throws DataParseException {
        List<Long> ides = new ArrayList<Long>();
        if (Utils.isEmpty(data)) {
            throw new DataParseException("Empty list");
        }

        try {
            for (String i : data.split(",")) {
                ides.add(Long.parseLong(i));
            }
            return ides;
        } catch (Exception e) {
            throw new DataParseException(e);
        }

    }

    public static String convertIdesToString(List<Integer> ides) throws DataParseException {
        if (Utils.isEmpty(ides)) {
            throw new DataParseException("Empty list");
        }

        int size = ides.size();
        StringBuilder val = new StringBuilder();
        try {
            for (int i = 0; i < size - 1; i++) {
                val.append(ides.get(i)).append(",");
            }
            val.append(ides.get(size - 1));
            return val.toString();
        } catch (Exception e) {
            throw new DataParseException(e);
        }

    }

    public static Map<String, Object> convertRequestToParams(String request) throws DataParseException {
        Map<String, Object> params = new HashMap<String, Object>();

        if (Utils.isEmpty(request)) {
            throw new DataParseException("Empty list");
        }

        JSONParser parser = new JSONParser();
        Object unitsObj = null;
        try {
            unitsObj = parser.parse(request);
            JSONObject responseMessage = (JSONObject) unitsObj;
            if (responseMessage != null) {

                params.put("count", responseMessage.get("count"));
                params.put("page", responseMessage.get("page"));

                if (responseMessage.get("branchIdes") != null) {
                    params.put("branchIdes", responseMessage.get("branchIdes").toString());
                }

                if (responseMessage.get("filter") != null) {
                    params.put("filter", responseMessage.get("filter").toString());
                }

                if (responseMessage.get("orderBy") != null && responseMessage.get("orderType") != null) {
                    params.put("orderBy", responseMessage.get("orderBy"));
                    params.put("orderType", responseMessage.get("orderType"));
                }

            }
            return params;
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static Map<String, Object> convertWalletRequestToParams(String request) throws DataParseException {
        Map<String, Object> params = new HashMap<String, Object>();

        if (Utils.isEmpty(request)) {
            throw new DataParseException("Empty list");
        }

        JSONParser parser = new JSONParser();
        Object unitsObj = null;
        try {
            unitsObj = parser.parse(request);
            JSONObject responseMessage = (JSONObject) unitsObj;

            if (responseMessage != null) {


                params.put("count", responseMessage.get("count"));
                params.put("page", responseMessage.get("page"));

                if (responseMessage.get("filter") != null) {
                    params.put("filter", responseMessage.get("filter").toString().toLowerCase());
                }

                if (responseMessage.get("orderBy") != null && responseMessage.get("orderType") != null) {
                    String orderBy = (String) responseMessage.get("orderBy");
                    if ("name".equals(orderBy) || "surname".equals(orderBy) || "email".equals(orderBy)) {
                        orderBy = String.format("user_%s", orderBy);
                    }
                    params.put("orderBy", orderBy);
                    params.put("orderType", responseMessage.get("orderType"));
                }

                if (responseMessage.get("withdrawId") != null) {
                    params.put("withdrawId", responseMessage.get("withdrawId").toString());
                } else if (responseMessage.get("depositId") != null) {
                    params.put("depositId", responseMessage.get("depositId").toString());
                }


            }
            return params;
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static CurrencyType convertCurrencyType(String type) throws DataParseException {
        if (Utils.isEmpty(type)) {
            return null;
        }
        try {
            return CurrencyType.valueOf(Integer.valueOf(type));
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static Country convertCountry(String type) throws DataParseException {
        if (Utils.isEmpty(type)) {
            return null;
        }
        try {
            return Country.valueOf(Integer.valueOf(type));
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static CountryRegion convertCountryRegion(String type) throws DataParseException {
        if (Utils.isEmpty(type)) {
            return null;
        }
        try {
            return CountryRegion.valueOf(Integer.valueOf(type));
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static Double convertToDouble(String str) throws DataParseException {
        if (Utils.isEmpty(str)) {
            return null;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static Long convertToLong(String str) throws DataParseException {
        if (Utils.isEmpty(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    public static Set<CurrencyType> parseAvailableRates(String availableRateValues) throws DataParseException, NullPointerException {
        if (Utils.isEmpty(availableRateValues)) {
            throw new NullPointerException("availableRateValues is empty");
        }

        Set<CurrencyType> availableRates = new HashSet<CurrencyType>();

        String[] ars = availableRateValues.split(",");

        try {
            for (String s : ars) {
                CurrencyType type = CurrencyType.valueOf(Integer.parseInt(s.trim()));
                if (type == null) {
                    throw new NullPointerException("CurrencyType not allowed " + s);
                }
                availableRates.add(type);
            }
        } catch (Exception e) {
            throw new DataParseException(e);
        }

        return availableRates;
    }

    public static Set<PartitionLCP> parseAllowedPartitions(String allowedPartitionValues) throws DataParseException, NullPointerException {
        if (Utils.isEmpty(allowedPartitionValues)) {
            throw new NullPointerException("allowedPartitionValues is empty");
        }

        Set<PartitionLCP> allowedPartitions = new HashSet<PartitionLCP>();

        String[] ars = allowedPartitionValues.split(",");

        try {
            for (String s : ars) {
                PartitionLCP type = PartitionLCP.valueOf(Integer.parseInt(s.trim()));
                if (type != null) {
                    allowedPartitions.add(type);
                }
            }
        } catch (Exception e) {
            throw new DataParseException(e);
        }

        return allowedPartitions;
    }

    public static TransactionDto convertToTransactionDto(TransactionWithdraw withdraw) {
        return convertToTransactionDto(withdraw, withdraw.getWallet());
    }

    public static TransactionDto convertToTransactionDto(TransactionWithdraw withdraw, Wallet wallet) {
        TransactionDto dto = new TransactionDto();
//        dto.setWithdrawId(""+withdraw.getId());

        dto.setAccount(String.format("#%d", withdraw.getWalletId()));
        dto.setFinalState(withdraw.getFinalState().getState());

        dto.setOpenedAt(toView(withdraw.getOpenedAt()));
        dto.setClosedAt(toView(withdraw.getClosedAt()));
        long duration = withdraw.getRationalDuration().getDuration() - Utils.diffSeconds(new Date(System.currentTimeMillis()), withdraw.getOpenedAt());
        dto.setDuration("" + duration);

        //100 USD
        dto.setWithdrawAmount(toView(withdraw.getWithdrawAmount()));
        dto.setWithdrawAmountCurrencyType(withdraw.getWithdrawAmountCurrencyType().getName());
        //100 USD + 2 USD WalletWithdrawTax.paidTaxToWalletSetup from Wallet Api = 102 USD
        dto.setWalletTotalPrice(toView(withdraw.getWalletTotalPrice()));
        dto.setWalletTotalPriceCurrencyType(withdraw.getWalletTotalPriceCurrencyType().getName());
        //1 USD (1%)
        dto.setWithdrawCashierCashBoxTotalTax(toView(withdraw.getWithdrawCashierCashBoxTotalTax()));
        dto.setWithdrawCashierCashBoxTotalTaxCurrencyType(withdraw.getWithdrawCashierCashBoxTotalTaxCurrencyType().getName());
        //100 USD - 1 USD (1%) = 99 USD
        dto.setCashierTotalAmount(toView(withdraw.getCashierTotalAmount()));
        dto.setCashierTotalAmountCurrencyType(withdraw.getCashierTotalAmountCurrencyType().getName());

        dto.setOrderCode(!Utils.isEmpty(withdraw.getOrderCode()) ? withdraw.getOrderCode() : "-");

        if (wallet != null) {
            dto.setWalletDto(convertToWalletDto(wallet));
        }

        return dto;
    }

    public static NotificationDto convertToNotificationDto(TransactionWithdraw withdraw) {
        return convertToNotificationDto(withdraw, withdraw.getWallet());
    }

    public static NotificationDto convertToNotificationDto(TransactionWithdraw withdraw, Wallet wallet) {
        NotificationDto dto = new NotificationDto();
        dto.setWithdrawId("" + withdraw.getId());
        dto.setFinalState(withdraw.getFinalState().getState());
        long duration = withdraw.getRationalDuration().getDuration() - Utils.diffSeconds(withdraw.getOpenedAt(), new Date(System.currentTimeMillis()));
        dto.setDuration("" + duration);

        if (wallet != null) {
            dto.setImg(wallet.getUserId() + "");
            dto.setName(wallet.getName());
            dto.setSurname(wallet.getSurname());
        }

        return dto;
    }


    public static WalletDto convertToWalletDto(Wallet wallet) {
        WalletDto dto = new WalletDto();

        dto.setId(wallet.getId() + "");
        dto.setPartitionId(wallet.getPartitionId() + "");
        dto.setImg(wallet.getUserId() + "");
        dto.setName(wallet.getName());
        dto.setSurname(wallet.getSurname());
        dto.setEmail(wallet.getEmail());
        dto.setMoney(toView(wallet.getMoney()));
        dto.setFrozenAmount(toView(wallet.getFrozenAmount()));
        dto.setReceivingAmount(toView(wallet.getReceivingAmount()));
        dto.setCurrencyType("" + wallet.getCurrencyType().getId());
        dto.setIsLocked((wallet.getIsLocked() ? 1 : 0) + "");
        return dto;
    }


    public static TransactionDto convertToTransactionDto(TransactionDeposit withdraw) {
        TransactionDto dto = new TransactionDto();

        dto.setAccount(String.format("#%d", withdraw.getWalletId()));
        dto.setFinalState(withdraw.getFinalState().getState());

        dto.setOpenedAt(toView(withdraw.getOpenedAt()));
        dto.setClosedAt(toView(withdraw.getClosedAt()));


        dto.setOrderCode(!Utils.isEmpty(withdraw.getOrderCode()) ? withdraw.getOrderCode() : "-");
        return dto;
    }

    public static synchronized Date toMerchantDate(String strDate) {
        try {
            return Utils.isEmpty(strDate)
                    ? null
                    : merchantDate.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized String toView(Date date) {
        return date != null ? viewDate.format(date) : "-";
    }

    public static String toView(Double value) {
        return value != null ? String.format("%.2f", value) : "0";
    }

    @Deprecated
    public static String convertToValidAllowedRemoteAddress(String in) throws DataParseException {
        String PARSE_SEPARATOR = "$";
        String[] valid = in.split("\\" + PARSE_SEPARATOR);
        return Arrays.toString(valid).replaceAll("\\[|\\]|\\s", "").replace(PARSE_SEPARATOR, "");
    }

    @Deprecated
    public static String convertToValidAllowedPartitions(String in) throws DataParseException, NumberFormatException {
        String PARSE_SEPARATOR = "$";
        String[] valid = in.split("\\" + PARSE_SEPARATOR);
        for (String s : valid) {
            if (PartitionLCP.valueOf(Integer.parseInt(s)) == null) {
                throw new DataParseException("Invalid partition id");
            }
        }
        return Arrays.toString(valid).replaceAll("\\[|\\]|\\s", "");
    }

    @Deprecated
    public static String convertToAvailableRateValues(String in) throws DataParseException, NumberFormatException {
        String PARSE_SEPARATOR = "$";
        String[] valid = in.split("\\" + PARSE_SEPARATOR);
        for (String s : valid) {
            if (CurrencyType.valueOf(Integer.parseInt(s)) == null) {
                throw new DataParseException("Invalid partition id");
            }
        }
        return Arrays.toString(valid).replaceAll("\\[|\\]|\\s", "");
    }

//    public static void main(String[] args) throws DataParseException {
//        System.out.println(convertIdesToString(Arrays.asList(new Integer[]{
//                TransactionState.PENDING.getId(),
//                TransactionState.CANCELED.getId(),
//                TransactionState.PENDING.getId(),
//                TransactionState.CANCELED.getId(),
//                TransactionState.PENDING.getId(),
//                TransactionState.CANCELED.getId(),
//                TransactionState.PREPARE.getId()
//        })));
//    }

}
