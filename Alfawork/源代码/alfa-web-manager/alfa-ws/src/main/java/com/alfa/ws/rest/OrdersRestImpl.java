package com.alfa.ws.rest;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.OrdersService;
import com.alfa.web.service.SysconfigService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7.
 */
public class OrdersRestImpl implements OrdersRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private OrdersService ordersService;

    @Override
    public Response insertorder(Orders order) throws Exception {

        boolean result = false;

        result = this.ordersService.insert(order);

        if(result){
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Insert_Success, null))).build();
        }else{
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Insert_Failtrue, null))).build();
        }
    }

    @Override
    public Response deleteorder(Orders order) {
        String json="";
        boolean result=this.ordersService.delete(order.getOrderid());

        if(result){
            json= JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.Order_Delete_Success,null));
            return Response.status(Response.Status.OK).entity(json).build();
        }else{
            json= JsonUtil.toJson(new RestResult(RestResult.FAILURE,WebConstants.MsgCd.Order_Insert_Failtrue,null));
            return Response.status(Response.Status.OK).entity(json).build();
        }
    }

    @Override
    public Response updateorder(Orders order) {
        String Json="";
        WebUtil.prepareUpdateParams(order);

        int result=this.ordersService.updateByPrimaryKeySelective(order);

        if(result==1){
            Json=JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.Order_Update_Success,null));
        }else{
            Json=JsonUtil.toJson(new RestResult(RestResult.FAILURE,WebConstants.MsgCd.Order_Update_Failtrue,null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }

    @Override
    public Response findOrder(String param, HttpServletRequest request, HttpServletResponse response) {
           /*  String sortName=request.getParameter("sortName");
        log.debug("sortName:"+sortName);*/

        Map map=WebUtil.getParamsMap(param,"utf-8");

        //分页排序处理
        BasePager pager=new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }

       /* if (!StringUtil.isNullOrEmpty(map.get("page"))) {
            pager.setPageIndex(Integer.parseInt(map.get("page").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("rows"))) {
            pager.setPageIndex(Integer.parseInt(map.get("rows").toString()));
        }*/

        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }

       /* if (!StringUtil.isNullOrEmpty(map.get("sort"))) {
            pager.setSortField(map.get("sort").toString());
        }*/

        if (!StringUtil.isNullOrEmpty(map.get("sortName"))) {
            pager.setSortField(map.get("sortName").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }

        /*if (!StringUtil.isNullOrEmpty(map.get("order"))) {
            pager.setSortOrder(map.get("order").toString());
        }*/

        //过滤
        Criteria criteria = new Criteria();

        if(!StringUtil.isNullOrEmpty(map.get("roleId"))){
            //收运人员
            if("9".equals(map.get("roleId").toString())){
                if (!StringUtil.isNullOrEmpty(map.get("phone"))) {
                    criteria.put("phone",  map.get("phone").toString());
                }
            }
            //产废单位
            else if("10".equals(map.get("roleId").toString())){
                if (!StringUtil.isNullOrEmpty(map.get("iphone"))) {
                    criteria.put("iphone",  map.get("iphone").toString());
                }
            }
        }

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");
        List<Orders> ordersList = this.ordersService.selectByParams(criteria);
        int count = this.ordersService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        /*data.put("TotalRows", count);
        data.put("Rows", configList);*/

        /**
         * easyui
         */
        data.put("total", count);
        data.put("rows", ordersList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
