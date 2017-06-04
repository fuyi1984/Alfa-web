package com.alfa.ws.rest;

import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/4.
 */
@Path("/server")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface SysServerInfoRest {

    /**
     * 获取系统信息
     */
    @WebMethod
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getsysteminfo")
    public Response GetSystemInfo(@Context HttpServletRequest servletRequest);
}
