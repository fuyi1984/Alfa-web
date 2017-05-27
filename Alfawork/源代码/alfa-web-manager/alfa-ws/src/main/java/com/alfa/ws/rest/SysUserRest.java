package com.alfa.ws.rest;

import com.alfa.web.pojo.SysUsers;
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
 * Created by Administrator on 2017/5/26.
 */
@Path("/user")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface SysUserRest {

    /**
     * 手机注册用户
     *
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insertByMobile")
    public Response insertByMobile(SysUsers user,
                                   @Context HttpServletRequest servletRequest,
                                   @Context HttpServletResponse servletResponse);

    /**
     * 新增个人用户
     *
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insertUser")
    public Response insertUser(SysUsers user);
}
