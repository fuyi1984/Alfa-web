package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.td_weixin_users;
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
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/9/7.
 */
@Path("/OpenId")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface weixin_usersRest {

    /**
     * 查询分页微信用户
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 更新OpenId
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateOpenId")
    public Response updateOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException;


}
