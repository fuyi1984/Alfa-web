package com.alfa.ws.rest;

import com.alfa.web.pojo.SysRole;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.ws.rs.*;
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
    @Path("/findrole")
    public Response findRole(SysRole role);

    /**
     * 添加角色
     */
    @WebMethod
    @POST
    @Path("/addrole")
    public Response addRole(SysRole role);

    /**
     * 编辑角色
     */
    @WebMethod
    @POST
    @Path("/editrole")
    public Response editRole(SysRole role);

    /**
     * 删除角色
     */
    @WebMethod
    @POST
    @Path("/deleterole")
    public Response deleteRole(SysRole role);

    /**
     * 查询所有角色
     */
    @WebMethod
    @GET
    @Path("/findAllRole")
    public Response findAllRole();
}
