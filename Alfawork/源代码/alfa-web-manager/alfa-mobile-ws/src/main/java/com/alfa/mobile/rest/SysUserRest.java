package com.alfa.mobile.rest;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.vo.ModifyPwdUser;
import com.alfa.web.vo.RegisterUser;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/6/11.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
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
     * 注册，输入验证码
     *
     * 1. 创建用户
     * 2. 创建对应账户
     * 3. 用户等级,等都为默认值
     * 4. 调用存储子系统创建默认目录结构（例如client_01）
     * @param registerUser
     * @return
     */
    @WebMethod
    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(RegisterUser registerUser);

    /**
     * 短信验证码方式登录
     *
     * 1. 判断是否为平台用户
     * 2. 获取相应的角色权限和账户信息
     * @param user
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @WebMethod
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(RegisterUser registerUser, @Context HttpServletRequest servletRequest,
                          @Context HttpServletResponse servletResponse) throws ParseException;

    /**
     *
     * @param user
     * @return
     */
    @WebMethod
    @POST
    @Path("/resetPwd")
    public Response resetPassword(ModifyPwdUser user);

    /**
     *
     * @param userSid
     * @return
     */
    @WebMethod
    @GET
    @Path("/getUserCenterInfo/{userSid}")
    public Response getUserCenterInfo(@PathParam("userSid") Long userSid);
}
