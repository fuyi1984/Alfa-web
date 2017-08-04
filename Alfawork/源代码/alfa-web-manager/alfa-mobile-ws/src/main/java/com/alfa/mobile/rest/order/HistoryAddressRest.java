package com.alfa.mobile.rest.order;

import com.alfa.web.pojo.HistoryAddress;
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
 * Created by Administrator on 2017/6/29.
 */
@Path("/historyaddress")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Scope("singleton")
public interface HistoryAddressRest {

    /**
     * 查询分页收油地址历史记录
     */
    @WebMethod
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/findlist")
    public Response findlist(String param, @Context HttpServletRequest request, @Context HttpServletResponse response);

    /**
     * 修改收油地址
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateHistoryAddress")
    public Response updateHistoryAddress(HistoryAddress record) throws UnsupportedEncodingException;

    /**
     * 新增收油地址
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/insertHistoryAddress")
    public Response insertHistoryAddress(HistoryAddress record) throws Exception;

    /**
     * 删除收油地址
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteHistoryAddress")
    public Response deleteHistoryAddress(HistoryAddress record);

    /**
     * 批量删除收油地址
     *
     * @param order
     * @return
     */
    @WebMethod
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/batchdeleteHistoryAddress")
    public Response batchdeleteHistoryAddress(List<String> list);
}
