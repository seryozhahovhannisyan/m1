package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.transaction.deposit.TransactionDeposit;
import com.connectto.wallet.merchant.common.data.transaction.withdraw.TransactionWithdraw;
import com.connectto.wallet.merchant.common.exception.*;

import java.util.List;
import java.util.Map;

public interface ITransactionManager {

    public void add(TransactionWithdraw data, boolean decript) throws InternalErrorException, PermissionDeniedException;

    public void add(TransactionDeposit data, boolean decript) throws InternalErrorException, PermissionDeniedException;

    public List<TransactionWithdraw> getWithdrawsByParams(Map<String, Object> params) throws InternalErrorException;

    public List<TransactionDeposit> getDepositsByParams(Map<String, Object> params) throws InternalErrorException;

    public int getWithdrawsCountByParams(Map<String, Object> params) throws InternalErrorException;

    public int getDepositsCountByParams(Map<String, Object> params) throws InternalErrorException;

    public TransactionWithdraw timeOutedWithdraw(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, WalletApiException, HttpConnectionDeniedException, PermissionDeniedException;

    public TransactionDeposit timeOutedDeposit(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, WalletApiException, HttpConnectionDeniedException, PermissionDeniedException;

    public TransactionWithdraw canceledWithdrawByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException;

    public TransactionDeposit canceledDepositByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException;

    public TransactionWithdraw approvedWithdrawByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException;

    public TransactionDeposit approvedDepositByWallet(Map<String, Object> params) throws InternalErrorException, EntityNotFoundException, PermissionDeniedException;



}
