package com.connectto.wallet.merchant.dataaccess.merchant.extension;

/**
 * Created by Serozh on 6/21/2016.
 */

import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionRationalDuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRationalDurationHandler extends BaseTypeHandler<TransactionRationalDuration> {
    public TransactionRationalDurationHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, TransactionRationalDuration parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    public TransactionRationalDuration getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return value != 0 ? TransactionRationalDuration.valueOf(value) : null;
    }

    public TransactionRationalDuration getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return value != 0 ? TransactionRationalDuration.valueOf(value) : null;
    }

    public TransactionRationalDuration getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
