package com.alfa.mobile.rest;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.td_weixin_users;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/30.
 */
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
}
