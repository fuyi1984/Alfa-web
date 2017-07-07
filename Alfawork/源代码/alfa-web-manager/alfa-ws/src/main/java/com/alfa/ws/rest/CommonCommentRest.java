package com.alfa.ws.rest;

import com.alfa.web.pojo.CommonComment;
import com.alfa.web.pojo.Orders;
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
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Path("/CommonComment")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface CommonCommentRest {
    /**
     * 新增常见评语
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertCommonComment")
    public Response insertCommonComment(CommonComment commonComment) throws Exception;

    /**
     * 删除常见评语
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteCommonComment")
    public Response deleteCommonComment(CommonComment commonComment);

    /**
     * 修改常见评语
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateCommonComment")
    public Response updateCommonComment(CommonComment commonComment) throws UnsupportedEncodingException;

    /**
     * 查询分页常见评语
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);


    /**
     * 批量删除常见评语
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeleteCommonComment")
    public Response batchdeleteCommonComment(List<String> list);

}
