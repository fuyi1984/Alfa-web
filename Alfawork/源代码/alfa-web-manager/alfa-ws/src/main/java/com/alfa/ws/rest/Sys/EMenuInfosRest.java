package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.EMenuInfos;
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
 * Created by Administrator on 2017/7/30.
 */
@Path("/menu")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public interface EMenuInfosRest {

    /**
     * 新增目录
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertmenu")
    public Response insertmenu(EMenuInfos menu) throws Exception;

    /**
     * 批量删除目录
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeletemenu")
    public Response batchdeletemenu(List<String> list);


    /**
     * 编辑目录
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/editmenu")
    public Response editmenu(EMenuInfos menu);

    /**
     * 查询分页目录
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 树形菜单
     * @param param
     * @param request
     * @param response
     * @return
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findtreeMenu")
    public Response findtreeMenu(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);
}
