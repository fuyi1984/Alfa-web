package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.SysConfig;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@Path("/Sysconfig")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface SysconfigRest {

    /**
     * 查询所有配置
     */
    @WebMethod
    @POST
    @Path("/findAll")
    public Response findAllConfig(SysConfig config);

    /**
     * 新增配置
     *
     * @param config
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertConfig")
    public Response insertConfig(SysConfig config);

    /**
     * 更新配置
     *
     * @param config
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateConfig")
    public Response updateConfig(SysConfig config);

    /**
     * 删除配置
     *
     * @param configSid
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteConfig")
    public Response deleteConfig(SysConfig config);


    /**
     * 批量删除配置
     *
     * @param configSid
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeleteConfig")
    public Response batchdeleteConfig(List<String> list);

    /**
     * 查询分页配置
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findConfig(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 导出系统配置
     *
     * @param request
     * @param response
     * @return return response with excel File
     */
    @GET
    @Path("/export")
    public Response export(@Context HttpServletRequest request,
                           @Context HttpServletResponse response);

    /**
     * 验证License是否过期
     *
     * @return return response
     */
    @WebMethod
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/licenseValid")
    public Response licenseValid();
}
