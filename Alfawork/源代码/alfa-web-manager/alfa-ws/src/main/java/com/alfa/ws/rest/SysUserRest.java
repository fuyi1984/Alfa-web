package com.alfa.ws.rest;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.util.pojo.UserSession;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Path("/user")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface SysUserRest {

    /**
     * 手机号做为用户名，返回验证码
     *
     * 1.查询系统是否有此手机号注册的用户
     * 2.调用短信平台接口，发送相应验证码到此手机
     * @param mobile
     * @return
     */
    @WebMethod
    @GET
    @Path("/getCaptcha/{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCaptcha(@PathParam("mobile") String mobile);

    /**
     * 手机注册用户
     *
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
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
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertUser")
    public Response insertUser(SysUsers user) throws Exception;

    /**
     * 删除用户
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteUser")
    public Response deleteUser(SysUsers user);

    /**
     *批量删除用户
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeleteUser")
    public Response batchdeleteUser(List<String> list) throws IOException;

    /**
     * 编辑用户
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/editUser")
    public Response editUser(SysUsers user);

    /**
     * 退出用户
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/logout")
    public Response logoutUser(@Context HttpServletRequest servletRequest,
                               @Context HttpServletResponse servletResponse);

    /**
     * 用户登录
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/verify")
    public Response verifyUser(SysUsers user,
                               @Context HttpServletRequest servletRequest,
                               @Context HttpServletResponse servletResponse);

    /**
     * 查询分页用户
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findUserlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 验证用户是否登录
     */
    @WebMethod
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/current")
    public Response current(@Context HttpServletRequest servletRequest);

    /**
     * 验证客户端用户是否登录
     */
    @WebMethod
    @GET
    @Path("/redirect")
    public Response redirect(String token,
                             @Context HttpServletRequest servletRequest,
                             @Context HttpServletResponse servletResponse);

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/modifyPassword")
    public Response modifyPassword(String params);

    /**
     * 查找所有的废油收运人员
     * @return
     */
    @WebMethod
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/findAllTransporter")
    public Response findAllTransporter();


    /**
     * 验证手机是否存在
     */
    @WebMethod
    @POST
    @Path("/validatMobile")
    public Response validatMobile(SysUsers user);

    /**
     * 批量更新用户状态
     * @param list
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchUpdateUserStatus")
    public Response batchUpdateUserStatus(List<String> list);

}
