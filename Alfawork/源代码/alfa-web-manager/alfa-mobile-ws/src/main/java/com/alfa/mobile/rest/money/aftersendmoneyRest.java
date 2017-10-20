package com.alfa.mobile.rest.money;


import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.pojo.moneyactivitiesconcern;
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

/**
 * 微信红包发送成功记录表
 */
@Path("/aftersendmoney")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface aftersendmoneyRest {

    /**
     * 分页查询微信红包发送成功记录表
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);


    /**
     * 查询每个用户获取的红包总数和红包总金额
     * @param money
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/gettotalmoneyandtotalcount")
    public Response gettotalmoneyandtotalcount(aftersendmoney money);

}
