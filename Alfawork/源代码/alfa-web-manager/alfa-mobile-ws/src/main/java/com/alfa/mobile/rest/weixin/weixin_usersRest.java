package com.alfa.mobile.rest.weixin;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.td_weixin_users;
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
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/30.
 */
@Path("/OpenId")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface weixin_usersRest {

    /**
     * 新增OpenId
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertOpenId")
    public Response insertOpenId(td_weixin_users td_weixin_users) throws Exception;

    /**
     * 更新OpenId
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateOpenId")
    public Response updateOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException;


    /**
     * 获取OpenId
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/GetSingleOpenId")
    public Response GetSingleOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException;


    /**
     * 验证准备发送红包的openid用户合法性
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/GetSingleOpenIdForMoney")
    public Response GetSingleOpenIdForMoney(td_weixin_users td_weixin_users) throws UnsupportedEncodingException;

    /**
     * 获取OpenId
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/GetSingleOpenIdForMobile")
    public Response GetSingleOpenIdForMobile(td_weixin_users td_weixin_users) throws UnsupportedEncodingException;

    /**
     * 查询分页微信用户
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);
}
