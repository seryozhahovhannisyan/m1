package com.connectto.wallet.merchant.dataaccess.merchant.extension;

/**
 * Created by Serozh on 6/21/2016.
 */

import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusHandler extends BaseTypeHandler<Status> {
    public StatusHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getKey());
    }

    public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return value != 0?Status.valueOf(value):null;
    }

    public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return value != 0?Status.valueOf(value):null;
    }

    public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}