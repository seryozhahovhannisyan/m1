package com.connectto.wallet.merchant.dataaccess.merchant.extension;

/**
 * Created by Serozh on 6/21/2016.
 */

import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogActionHandler extends BaseTypeHandler<LogAction> {
    public LogActionHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, LogAction parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getKey());
    }

    public LogAction getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int key = rs.getInt(columnName);
        return key != 0 ? LogAction.valueOfKey(key) : null;
    }

    public LogAction getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int key = rs.getInt(columnIndex);
        return key != 0 ? LogAction.valueOfKey(key) : null;
    }

    public LogAction getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
