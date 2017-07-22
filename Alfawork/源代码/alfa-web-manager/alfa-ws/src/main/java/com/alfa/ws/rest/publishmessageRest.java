package com.alfa.ws.rest;

import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Path("/message")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface publishmessageRest {

    /**
     * 查询分页消息
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 查询分页发件箱中的消息
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlistoutbox")
    public Response findlistoutbox(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);


    /**
     * 根据id批量删除messageuser
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/batchdeletemessageuserbyid")
    public Response batchdeletemessageuserbyid(List<String> list);


    /**
     * 消息已读未读
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/isread")
    public Response isread(messageuser user);

    /**
     * 插入数据进消息用户关系表
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/insertmessageuser")
    public Response insertmessageuser(messageuser user);

    /**
     * 添加发布消息
     * @param message
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addmessage")
    public Response InsertPublishMessage(publishmessage message);


    /**
     * 修改发布消息
     * @param message
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/editmessage")
    public Response EditPublishMessage(publishmessage message);


    /**
     * 批量删除发布消息
     * @param message
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/batchdelmessage")
    public Response BatchDeletePublishMessage(List<String> idlist);
}
