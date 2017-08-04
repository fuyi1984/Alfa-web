package com.alfa.ws.rest.order;

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
 * Created by Administrator on 2017/6/7.
 */
@Path("/order")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface OrdersRest {

    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertorders")
    public Response insertorder(Orders order) throws Exception;

    /**
     * 删除订单
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteorders")
    public Response deleteorder(Orders order);


    /**
     * 批量删除订单
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeleteorders")
    public Response batchdeleteorder(List<String> list);

    /**
     * 更新订单
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateorders")
    public Response updateorder(Orders order) throws UnsupportedEncodingException;


    /**
     * 批量确认订单状态
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchupdateorderstatus")
    public Response batchupdateorderStatus(List<String> orderlist) throws UnsupportedEncodingException;

    /**
     * 批量完成订单状态
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchcompleteorderStatus")
    public Response batchcompleteorderStatus(List<String> orderlist) throws UnsupportedEncodingException;

    /**
     * 批量分配订单
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchupdateorderWorker")
    public Response batchupdateorderWorker(String param, @Context HttpServletRequest request, @Context HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 查询分页订单
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findOrder(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);


}
