package com.alfa.mobile.rest.File;

import com.alfa.web.vo.Base64;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Path("/file")
@Scope("singleton")
public interface FileResourceRest {

    /**
     * 上传图片
     * @param pathKey
     * @param atts
     * @return
     */
    @WebMethod
    @POST
    @Path("/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response upload(List<Attachment> atts);


    /**
     * 上传图片(Base64)
     * @return
     */
    @WebMethod
    @POST
    @Path("/uploadbase64")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadBase64(Base64 base) throws IOException;
}
