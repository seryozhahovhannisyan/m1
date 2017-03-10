package com.connectto.wallet.merchant.web.action.wallet;

import com.connectto.wallet.merchant.business.merchant.ITransactionManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.NotificationDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.dto.TransactionDto;
import com.connectto.wallet.merchant.web.util.Initializer;

import java.util.*;

/**
 * Created by Serozh on 9/26/2016.
 */
public class TransactionAction extends BaseAction {

    private ResponseDto dto;
    private ITransactionManager transactionManager;

    private String requestJson;
    private int dataCount;

    public String listView() {
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cashBoxId", cashBox.getId());


        try {
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));
            dataCount = transactionManager.getWithdrawsCountByParams(params);
            cashier.setPendingCount(dataCount);
            session.put(SESSION_CASHIER, cashier);
            //dataCount += transactionManager.getDepositsCountByParams(params);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String list() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();
        try {

            Map<String, Object> params = DataConverter.convertWalletRequestToParams(requestJson);
            params.put("cashBoxId", cashBox.getId());
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));

            dataCount = transactionManager.getWithdrawsCountByParams(params);
            //dataCount += transactionManager.getDepositsCountByParams(params);

            long page = (Long) params.get("page");
            long count = (Long) params.get("count");
            params.put("page", (page - 1) * count);

            List<TransactionWithdraw> withdraws = transactionManager.getWithdrawsByParams(params);
            //List<TransactionDeposit> deposits = transactionManager.getDepositsByParams(params);
            List<TransactionDto> transactionDtos = new ArrayList<TransactionDto>();

            for (TransactionWithdraw withdraw : withdraws) {
                transactionDtos.add(DataConverter.convertToTransactionDto(withdraw));
            }

//            for (TransactionDeposit deposit : deposits) {
//                transactionDtos.add(DataConverter.convertToTransactionDto(deposit));
//            }


            dto.addResponse("data", transactionDtos);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }
        return SUCCESS;
    }

    public String notification() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cashBoxId", cashBox.getId());
            params.put("upToDate", Utils.agoSeconds(new Date(System.currentTimeMillis()), Initializer.NOTIFICATION_DURATION_SECOND));

            List<TransactionWithdraw> withdraws = transactionManager.getWithdrawsByParams(params);
            List<NotificationDto> notificationDtos = new ArrayList<NotificationDto>();

            for(TransactionWithdraw withdraw : withdraws){
                NotificationDto notificationDto = DataConverter.convertToNotificationDto(withdraw);
                notificationDtos.add(notificationDto);
            }

            if(!Utils.isEmpty(withdraws)){

                params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));
                int pendingCount = transactionManager.getWithdrawsCountByParams(params);
                //pendingCount += transactionManager.getDepositsCountByParams(params);
                cashier.setPendingCount(pendingCount);
            }

            dto.addResponse("data", notificationDtos);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }
        return SUCCESS;
    }

    public String checkNotification() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        CashierCashBox cashBox = cashier.getCurrentCashBox();

        try {

            Map<String, Object> params = DataConverter.convertWalletRequestToParams(requestJson);
            params.put("cashBoxId", cashBox.getId());
            List<TransactionWithdraw> withdraws = transactionManager.getWithdrawsByParams(params);
            if(Utils.isEmpty(withdraws)){
                writeLog(TransactionAction.class.getSimpleName(), new EntityNotFoundException("Transaction not found"), LogLevel.ERROR, LogAction.READ, null);
                dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                return SUCCESS;
            }

            TransactionWithdraw withdraw = withdraws.get(0);
            TransactionDto transactionDto = DataConverter.convertToTransactionDto(withdraw);

            dto.addResponse("data", transactionDto);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(TransactionAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }
        return SUCCESS;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setTransactionManager(ITransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
