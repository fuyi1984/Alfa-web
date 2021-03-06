package com.alfa.ws.rest.money;

import com.alfa.web.pojo.SysConfig;
import com.alfa.web.pojo.moneyactivities;
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
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
@Path("/money")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface moneyactivitiesRest  {

    /**
     * 分页查询分页红包活动
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 新增红包活动
     *
     * @param moneyactivities
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertmoneyactivities")
    public Response insertmoneyactivities(moneyactivities money) throws ParseException;


    /**
     * 更新红包活动
     *
     * @param moneyactivities
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updatemoneyactivities")
    public Response updatemoneyactivities(moneyactivities money) throws ParseException;

    /**
     * 批量删除红包活动
     *
     * @param configSid
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeletemoneyactivities")
    public Response batchdeletemoneyactivities(List<String> list);


    /**
     * 批量启用红包活动
     * @param list
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchStartmoneyactivities")
    public Response batchStartmoneyactivities(List<String> list);


    /**
     * 批量停用红包活动
     * @param list
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchStopmoneyactivities")
    public Response batchStopmoneyactivities(List<String> list);

}
