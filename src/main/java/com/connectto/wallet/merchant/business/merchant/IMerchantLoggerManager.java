package com.connectto.wallet.merchant.business.merchant;

import com.connectto.wallet.merchant.common.data.merchant.MerchantLogger;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;

public interface IMerchantLoggerManager {

    public void add(MerchantLogger data) throws InternalErrorException;
}
