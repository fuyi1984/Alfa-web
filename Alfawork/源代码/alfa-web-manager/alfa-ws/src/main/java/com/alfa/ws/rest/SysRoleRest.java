package com.alfa.ws.rest;

import com.alfa.web.pojo.SysRole;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/4/27.
 */
@Path("/roles")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface SysRoleRest {
    /**
     * 查询角色列表
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/findrole")
    public Response findRole(SysRole role);

    /**
     * 添加角色
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/addrole")
    public Response addRole(SysRole role);

    /**
     * 编辑角色
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/editrole")
    public Response editRole(SysRole role);

    /**
     * 删除角色
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleterole")
    public Response deleteRole(SysRole role);

    /**
     * 查询所有角色
     */
    @WebMethod
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/findAllRole")
    public Response findAllRole();

    /**
     * 查询分页角色
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findRolelist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

}
