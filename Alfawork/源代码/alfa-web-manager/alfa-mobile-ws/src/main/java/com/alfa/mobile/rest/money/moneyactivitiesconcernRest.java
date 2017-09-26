package com.alfa.mobile.rest.money;

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
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */
@Path("/moneyconcern")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface moneyactivitiesconcernRest {

    /**
     * 分页查询分页红包活动关注
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 新增红包活动关注
     *
     * @param moneyactivitiesconcern
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertmoneyactivitiesconcern")
    public Response insertmoneyactivitiesconcern(moneyactivitiesconcern money);


    /**
     * 更新红包活动关注
     *
     * @param moneyactivities
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updatemoneyactivitiesconcern")
    public Response updatemoneyactivitiesconcern(moneyactivitiesconcern money);


    /**
     * 批量删除红包活动关注
     *
     * @param id
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeletemoneyactivitiesconcern")
    public Response batchdeletemoneyactivitiesconcern(List<String> list);
}
