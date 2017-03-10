package com.connectto.wallet.merchant.web.action.cashier;

import com.connectto.wallet.merchant.business.merchant.IRoleManager;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 6/26/2016.
 */
public class RoleAction extends BaseAction {

    private IRoleManager roleManager;

    private ResponseDto dto;

    private Long roleId;
    private String roleIdes;

    private Role role;
    private String requestJson;
    private int dataCount;

    public String add() {

        try {
            roleManager.add(null);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            return ERROR;
        }
        return SUCCESS;
    }

    public String view() {
        try {
            role = roleManager.getById(roleId);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String listView() {
        try {
            dataCount =  roleManager.getCountByParams(null);
        } catch (InternalErrorException e) {
            writeLog(BranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String list() {

        try {

            Map<String,Object> params = DataConverter.convertRequestToParams(requestJson);
            dataCount =  roleManager.getCountByParams(params);

            long page = (Long)params.get("page");
            long count = (Long)params.get("count");

            if (page * count > dataCount) {
                page = (int) (dataCount / count);
                if (dataCount % count > 0) {
                    page += 1;
                }
            }
            params.put("page", page);
            List<Role> roles = roleManager.getByParams(params);
            dto.addResponse("data", roles);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String edit() {


        try {
            roleManager.update(null);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.UPDATE, null);
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            roleManager.delete(roleId);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    public String deleteMultiple() {

        if(Utils.isEmpty(roleIdes)){
            return ERROR;
        }

        try {
            List<Long> ides = DataConverter.convertStringIdesToLong(roleIdes);
            roleManager.delete(ides);
        } catch (InternalErrorException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        } catch (DataParseException e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.DELETE, null);
        }
        return SUCCESS;
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public Role getRole() {
        return role;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setRoleManager(IRoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public void setRoleId(String roleId) {
        try {
            this.roleId = Long.parseLong(roleId);
        } catch (Exception e) {
            writeLog(RoleAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public void setRoleIdes(String roleIdes) {
        this.roleIdes = roleIdes;
    }

    public List<CurrencyType> loadAvailableRates(Role role){
        if(!Utils.isEmpty(role.getAvailableRateValues())){
            role.getAvailableRates();
        }
        return null;
    }
}