package com.connectto.wallet.merchant.web.servlet;

import com.connectto.wallet.merchant.common.data.merchant.FileData;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewMixedData extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ViewMixedData.class.getSimpleName());
    private String IMAGE_TYPE = "image/jpeg";

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OutputStream out = null;
        try {

            String resource = request.getParameter("resource");
            String datasFileName = request.getParameter("datasFileName");

            Map<String,List<FileData>> sessionDatas = (HashMap<String,List<FileData>>) request.getSession().getAttribute(ScopeKeys.DATA_MIXED);
            FileData fileData = findFileData(sessionDatas.get(resource), datasFileName);

            byte[] srcImage = fileData.getData();

            if (srcImage != null && srcImage.length > 0) {
                response.setContentType(IMAGE_TYPE);
                out = response.getOutputStream();
                out.write(srcImage);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }

    }

    private synchronized FileData findFileData(List<FileData> datas , String dataFileName){
        if(Utils.isEmpty(datas) || Utils.isEmpty(dataFileName)){
            return null;
        }
        for (int i = 0; i < datas.size(); i++) {
            FileData data = datas.get(i);
            if (dataFileName.equalsIgnoreCase(data.getFileName())) {
                return data;
            }
        }
        return null;
    }

}
