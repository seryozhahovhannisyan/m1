package com.connectto.wallet.merchant.dataaccess.merchant.dao.impl;
import com.connectto.wallet.merchant.common.data.transaction.transfer.TransferTicket;
import com.connectto.wallet.merchant.common.exception.DatabaseException;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ITransferTicketDao;
import com.connectto.wallet.merchant.dataaccess.merchant.mapper.namespace.TransferTicketMap;

/**
 * Created by Serozh on 2/14/2017.
 */
public class TransferTicketDaoImpl implements ITransferTicketDao {

    private TransferTicketMap map;

    public void setMap(TransferTicketMap map) {
        this.map = map;
    }

    @Override
    public void add(TransferTicket data) throws DatabaseException {
        try {
            map.add(data);
        } catch (RuntimeException e) {
            throw new DatabaseException(e);
        }
    }

}
