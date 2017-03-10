package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by htdev001 on 3/5/14.
 */
public class DataUploadAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(DataUploadAction.class.getSimpleName());
    private static final String EXISTS = "exists";

    private InputStream result = new ByteArrayInputStream(INPUT.getBytes());
    private ResponseDto responseDto;

    private File datas[];
    private String datasFileName[];
    private String datasContentType[];

    private String removeFileName;
    private String resource;


    public String upload() {

        if (datas == null || datasFileName == null) {
            result = new ByteArrayInputStream(NONE.getBytes());
            logger.error("DataUploadAction, data or dataFileName is null");
            return INPUT;
        }

        List<FileData> sessionDatas = (List<FileData>) session.get(ScopeKeys.DATA_MULTIPLE);
        List<FileData> fileDatas = new ArrayList<FileData>();
        if(sessionDatas == null){
            sessionDatas = new ArrayList<FileData>();
        }

        try {

            for (int i = 0; i < datas.length; i++) {

                File data = datas[i];
                String dataFileName = datasFileName[i];
                String dataContentType = datasContentType[i];
                if(exists(sessionDatas, dataFileName)){
                    result = new ByteArrayInputStream(EXISTS.getBytes());
                    logger.error("DataUploadAction, dataFileName is exists "+dataFileName);
                    return INPUT;
                }

                byte[] fileData = FileUtils.readFileToByteArray(data);
                if (fileData == null) {
                    result = new ByteArrayInputStream(NONE.getBytes());
                    logger.error("DataUploadAction, data or dataFileName is null");
                    return INPUT;
                }

                FileData d = new FileData();
                d.setData(fileData);
                d.setFileName(dataFileName);
                d.setContentType(dataContentType);
                d.setSize(fileData.length);
                d.setCreationDate(new Date(System.currentTimeMillis()));

                fileDatas.add(d);

            }

            sessionDatas.addAll(fileDatas);

            session.put(ScopeKeys.DATA_MULTIPLE, sessionDatas);

        } catch (IOException e) {
            result = new ByteArrayInputStream(ERROR.getBytes());
            logger.error(e);
            session.put(MESSAGE, getText("error.internal"));
            return ERROR;
        }
        result = new ByteArrayInputStream(SUCCESS.getBytes());
        return SUCCESS;
    }

    public String uploadMixed() {

        if (datas == null || datasFileName == null || Utils.isEmpty(resource)) {
            result = new ByteArrayInputStream(NONE.getBytes());
            logger.error("DataUploadAction, data or dataFileName is null");
            return INPUT;
        }

        Map<String,List<FileData>> sessionDatas = (HashMap<String,List<FileData>>) session.get(ScopeKeys.DATA_MIXED);
        List<FileData> fileDatas = new ArrayList<FileData>();
        if(sessionDatas == null){
            sessionDatas = new HashMap<String,List<FileData>>();
        }

        try {

            for (int i = 0; i < datas.length; i++) {

                File data = datas[i];
                String dataFileName = datasFileName[i];
                String dataContentType = datasContentType[i];
                if(exists(sessionDatas.get(resource), dataFileName)){
                    result = new ByteArrayInputStream(EXISTS.getBytes());
                    logger.error("DataUploadAction, dataFileName is exists "+ dataFileName);
                    return INPUT;
                }

                byte[] fileData = FileUtils.readFileToByteArray(data);
                if (fileData == null) {
                    result = new ByteArrayInputStream(NONE.getBytes());
                    logger.error("DataUploadAction, data or dataFileName is null");
                    return INPUT;
                }

                FileData d = new FileData();
                d.setData(fileData);
                d.setFileName(dataFileName);
                d.setContentType(dataContentType);
                d.setSize(fileData.length);
                d.setCreationDate(new Date(System.currentTimeMillis()));
                d.setStatus(Status.ACTIVE);

                fileDatas.add(d);

            }

            sessionDatas.put(resource, fileDatas);

            session.put(ScopeKeys.DATA_MIXED, sessionDatas);

        } catch (IOException e) {
            result = new ByteArrayInputStream(ERROR.getBytes());
            logger.error(e);
            session.put(MESSAGE, getText("error.internal"));
            return ERROR;
        } catch (Exception e) {
            result = new ByteArrayInputStream(ERROR.getBytes());
            logger.error(e);
            session.put(MESSAGE, getText("error.internal"));
            return ERROR;
        }
        result = new ByteArrayInputStream(SUCCESS.getBytes());
        return SUCCESS;
    }

    private synchronized boolean exists(List<FileData> datas , String dataFileName){

        if(Utils.isEmpty(datas)){
            return false;
        }

        for (int i = 0; i < datas.size(); i++) {
            FileData data = datas.get(i);
            if (dataFileName.equalsIgnoreCase(data.getFileName())) {
                return true;
            }
        }
        return false;
    }

    public String removeUploaded() {

        if (Utils.isEmpty(removeFileName)) {
            logger.error("DataUploadAction remove, removeFileName is null");
            responseDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
            return SUCCESS;
        }
        List<FileData> transactionDatas = (List<FileData>) session.remove(ScopeKeys.DATA_MULTIPLE);

        if (!Utils.isEmpty(transactionDatas)) {
            for (int i = 0; i < transactionDatas.size(); i++) {
                FileData data = transactionDatas.get(i);
                if (removeFileName.equalsIgnoreCase(data.getFileName())) {
                    transactionDatas.remove(i);
                    responseDto.setResponseStatus(ResponseStatus.SUCCESS);
                    session.put(ScopeKeys.DATA_MULTIPLE, transactionDatas);
                    return SUCCESS;
                }
            }
        }
        session.put(ScopeKeys.DATA_MULTIPLE, transactionDatas);
        responseDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        return SUCCESS;
    }

    public String removeAllUploaded() {
        session.remove(ScopeKeys.DATA_MULTIPLE);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }

    public String removeMixedUploaded() {

        if (Utils.isEmpty(removeFileName)) {
            logger.error("DataUploadAction remove, removeFileName is null");
            responseDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
            return SUCCESS;
        }
        Map<String,List<FileData>> sessionDatas = (HashMap<String,List<FileData>>) session.get(ScopeKeys.DATA_MIXED);

        if(exists(sessionDatas.get(resource), removeFileName)){
            sessionDatas.remove(resource);
            session.put(ScopeKeys.DATA_MIXED, sessionDatas);
        }

        return SUCCESS;
    }

    public String removeAllMixedUploaded() {
        session.remove(ScopeKeys.DATA_MIXED);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }


    public void setRemoveFileName(String removeFileName) {
        this.removeFileName = removeFileName;
    }

    public void setDatas(File[] datas) {
        this.datas = datas;
    }

    public void setDatasFileName(String[] datasFileName) {
        this.datasFileName = datasFileName;
    }

    public void setDatasContentType(String[] datasContentType) {
        this.datasContentType = datasContentType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public InputStream getResult() {
        return result;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }
}
