package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.CompanyFormRequest;
import com.connectto.wallet.merchant.common.data.merchant.CompanyFormResponse;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.web.action.util.notification.MailContent;

import java.util.List;
import java.util.Map;

public interface ICompanyFormRequestManager {

    public void add(MailContent mailContent, CompanyFormRequest data) throws InternalErrorException;

    public void activate(CompanyFormRequest companyFormRequest) throws InternalErrorException, EntityNotFoundException;

    @Deprecated
    //remove after testing
    public Cashier activateCompany(CompanyFormRequest formRequest, CompanyFormResponse formResponse, CompanyCashBox companyCashBox) throws InternalErrorException;

}
