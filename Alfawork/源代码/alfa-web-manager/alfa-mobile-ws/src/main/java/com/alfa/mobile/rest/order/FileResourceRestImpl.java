package com.alfa.mobile.rest.order;

import com.alfa.web.service.FileResourceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.Base64;
import org.apache.cxf.helpers.FileUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public class FileResourceRestImpl implements FileResourceRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private FileResourceService fileResourceService;

    @Override
    @WebMethod
    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Produces({"application/json", "application/xml"})
    public Response upload(List<Attachment> atts) {

        //图片一次只能上传一个
        Attachment file = null;

        for (Attachment atta : atts) {
            if (atta.getContentType().getType().contains("image")) {
                file = atta;
                break;
            }
        }

        String path = PropertiesUtil.getProperty("platform.root");
        String path_private = PropertiesUtil.getProperty("entuser.path");

        log.info("target file path is: " + (path + path_private));

        FileUtils.mkDir(new File(path + path_private));

        String fileName = file.getDataHandler().getName();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");

        String targetFileName = sdf.format(cal.getTime()) + "_" + Math.round(Math.random() * 1000) + fileName.substring(fileName.indexOf("."));

        long fileSize = fileResourceService.upload(file, path + path_private, targetFileName);

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("length", fileSize);
        properties.put("fileName", "alfa-platform"+"/upload/" + targetFileName);

        String json = JsonUtil.toJson(properties);
        return Response.status(Response.Status.OK).entity(JsonUtil.toJson(json)).build();
    }

    @Override
    public Response uploadBase64(Base64 base) throws IOException {

        if(base.getFiles()==null){
            //数据不能为空
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }

        String path = PropertiesUtil.getProperty("platform.root");
        String path_private = PropertiesUtil.getProperty("entuser.path");

        log.info("target file path is: " + (path + path_private));

        BASE64Decoder decoder = new BASE64Decoder();

        String[] fileStr=base.getFiles().split("data:image/jpeg;base64,");

        byte[] b = decoder.decodeBuffer(fileStr[1]);

        for(int i=0;i<b.length;++i)
        {
            if(b[i]<0)
            {//调整异常数据
                b[i]+=256;
            }
        }

        String targetFileName = StringUtil.getUUID()+".jpg";

        String imgFilePath = path + path_private+"\\"+targetFileName;//新生成的图片

        OutputStream out = new FileOutputStream(imgFilePath);
        out.write(b);
        out.flush();
        out.close();

        File f = new File(imgFilePath);

        long fileSize=0;

        if(f.exists()){
           fileSize=f.length();
        }

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("length", fileSize);
        properties.put("fileName", "alfa-platform"+"/upload/" + targetFileName);

        String json = JsonUtil.toJson(properties);
        return Response.status(Response.Status.OK).entity(JsonUtil.toJson(json)).build();
    }
}
