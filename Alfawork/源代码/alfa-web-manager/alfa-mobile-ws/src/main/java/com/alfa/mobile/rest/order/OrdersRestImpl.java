package com.alfa.mobile.rest.order;

import com.alfa.web.pojo.HostoryOrderStatus;
import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import com.alfa.web.service.order.HistoryAddressService;
import com.alfa.web.service.order.HostoryOrderStatusService;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.sms.SmsService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
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
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private SmsService smsService;

    @Autowired
    private HostoryOrderStatusService hostoryOrderStatusService;

    @Autowired
    private HistoryAddressService historyAddressService;

    @Autowired
    private activitiesorderService activitiesorderService;

    @Autowired
    private moneyactivitiesconcernServcie moneyactivitiesconcernServcie;

    //region 单项操作

    @Override
    public Response insertorder(Orders order) throws Exception {

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Criteria criteria = new Criteria();
        criteria.put("iphone", order.getIphone());
        criteria.put("createdDtLike", sdf.format(dt));


        int num = this.ordersService.countByParams(criteria);

        log.debug("num:" + num);

        int maxnum = Integer.parseInt(PropertiesUtil.getProperty("orders.maxnum"));

        log.debug("maxnum:" + maxnum);

        if (num + 1 <= maxnum) {

            //region  小于规定的范围

            boolean result = false;

            sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            order.setOrderno("SN"+sdf.format(dt));
            order.setIsSms("0");

            result = this.ordersService.insert(order);

            if (result) {

                //region 红包活动

                if(!StringUtil.isNullOrEmpty(order.getCopenid())) {

                    criteria.clear();
                    criteria.put("openid", order.getCopenid());

                    //获取用户关注的红包活动
                    List<moneyactivitiesconcern> moneyactivitiesconcernlist=this.moneyactivitiesconcernServcie.selectByParams(criteria);

                    for (moneyactivitiesconcern item:moneyactivitiesconcernlist) {

                        criteria.put("activitiesid",item.getActivitiesid());

                        List<activitiesorder> activitiesorderlist=this.activitiesorderService.selectByParams(criteria);

                        if(activitiesorderlist.size()>0){

                            activitiesorder aorder=activitiesorderlist.get(0);

                            if(StringUtil.isNullOrEmpty(aorder.getOrderno()))
                            {
                                aorder.setOrderid(order.getOrderid());
                                aorder.setOrderno(order.getOrderno());
                                aorder.setIssubmit("1");
                                this.activitiesorderService.updateByPrimaryKeySelective(aorder);
                            }
                        }
                    }

                }

                //endregion

                //订单插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
            } else {
                //订单插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
            }

            //endregion

        } else {

            //region 大于规定的范围

            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "3", null)
                    )).build();

            //endregion
        }

    }

    @Override
    public Response deleteorder(Orders order) {
        String json = "";
        boolean result = this.ordersService.delete(order.getOrderid());

        if (result) {
            json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Delete_Success, null));
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Delete_Failtrue, null));
            return Response.status(Response.Status.OK).entity(json).build();
        }
    }

    @Override
    public Response updateorder(Orders order) throws UnsupportedEncodingException {

        String Json = "";

        //region 收运人员确认订单的时候需要记录一个确认时间
        if(order.getOrgstatus().equals("3")){
            order.setConfirmDt(new Date());
        }
        //endregion

        WebUtil.prepareUpdateParams(order);

        int result = this.ordersService.updateByPrimaryKeySelective(order);

        if (result == 1) {

            //region 管理员分配订单给收油人员后发送短信通知
            /*
            if(order.getOrgstatus().equals("2")) {
                if (PropertiesUtil.getProperty("sms.open").equals("true")) {

                    String ret = this.smsService.sendSMS(order.getPhone(), PropertiesUtil.getProperty("notice.transporter") + order.getIphone());

                    if (ret == "0") {
                        log.info("通知收运人员的短信发送成功!");
                    } else {
                        log.info("通知收运人员的短信发送失败!");
                    }

                }
            }*/
            //endregion

            //region 订单完成后把记录存放到历史记录表

            if(order.getOrgstatus().equals("4")){

                HostoryOrderStatus hostoryOrderStatus=new HostoryOrderStatus();
                hostoryOrderStatus.setRealnum(order.getRealnum());
                hostoryOrderStatus.setOrderid(order.getOrderid());

                result=hostoryOrderStatusService.insertSelective(hostoryOrderStatus);

                if(result==1){
                    log.info("插入成功!");
                }else{
                    log.info("插入失败!");
                }
            }

            //endregion

            //region 订单完成后更新参加了红包活动订单的状态

            if(order.getOrgstatus().equals("4")){

                Criteria criteria=new Criteria();
                criteria.put("orderid",order.getOrderid());

                List<activitiesorder> activitiesorderlist=this.activitiesorderService.selectByParams(criteria);

                for (activitiesorder orderitem:activitiesorderlist) {
                    orderitem.setIsfinish("3");
                    orderitem.setVisible("4");
                    this.activitiesorderService.updateByPrimaryKeySelective(orderitem);
                }
            }

            //endregion

            //订单更新成功
            Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"1", null));
        } else {
            //订单更新失败
            Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }

    @Override
    public Response findOrder(String param, HttpServletRequest request, HttpServletResponse response) {
           /*  String sortName=request.getParameter("sortName");
        log.debug("sortName:"+sortName);*/

        Map map = WebUtil.getParamsMap(param, "utf-8");

        //分页排序处理
        BasePager pager = new BasePager();

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

        if (!StringUtil.isNullOrEmpty(map.get("roleId"))) {
            //收运人员
            if ("9".equals(map.get("roleId").toString())) {
                //criteria.put("roleId",  map.get("roleId").toString());
                if (!StringUtil.isNullOrEmpty(map.get("phone"))) {
                    criteria.put("phone", map.get("phone").toString());
                }
            }
            //产废单位
            else if ("10".equals(map.get("roleId").toString())) {
                //criteria.put("roleId",  map.get("roleId").toString());
                if (!StringUtil.isNullOrEmpty(map.get("iphone"))) {
                    criteria.put("iphone", map.get("iphone").toString());
                }
            }
        }

        //订单ID
        if (!StringUtil.isNullOrEmpty(map.get("orderid"))) {
            criteria.put("orderid", map.get("orderid").toString());
        }

        //真实姓名
        if (!StringUtil.isNullOrEmpty(map.get("realname"))) {
            criteria.put("realname", map.get("realname").toString());
        }

        //提交开始时间
        if(!StringUtil.isNullOrEmpty(map.get("startDt"))){
            criteria.put("createDtFrom",map.get("startDt").toString()+" 00:00:00");
        }

        //提交结束时间
        if(!StringUtil.isNullOrEmpty(map.get("endDt"))){
            criteria.put("createDtTo",map.get("endDt").toString()+" 23:59:59");
        }

        //完成开始时间
        if(!StringUtil.isNullOrEmpty(map.get("startUpdateDt"))){
            criteria.put("updatedDtFrom",map.get("startUpdateDt").toString()+" 00:00:00");
        }

        //完成结束时间
        if(!StringUtil.isNullOrEmpty(map.get("endUpdateDt"))){
            criteria.put("updatedDtTo",map.get("endUpdateDt").toString()+" 23:59:59");
        }

/*        //订单状态
        if(!StringUtil.isNullOrEmpty(map.get("orgstatus"))){
            criteria.put("orgstatus", map.get("orgstatus").toString());
        }*/

        //订单状态
        if(!StringUtil.isNullOrEmpty(map.get("orgstatus"))){
            if(!map.get("orgstatus").toString().equals("0")) {
                criteria.put("orgstatus", map.get("orgstatus").toString());
            }
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.orgstatus,A.createdDt desc");

        List<Orders> ordersList = this.ordersService.selectByParams(criteria);

        //region 设置地址

        /*
        String Province,City,Area,Townandstreets;

        for(Orders order:ordersList){
            if(!StringUtil.isNullOrEmpty(order.getAddressId())){

                //region 地址的相关属性的赋值

                Province=StringUtil.isNullOrEmpty(order.getProvince())?"":order.getProvince();

                City=StringUtil.isNullOrEmpty(order.getCity())?"":order.getCity();

                Area=StringUtil.isNullOrEmpty(order.getArea())?"":order.getArea();

                Townandstreets=StringUtil.isNullOrEmpty(order.getTownandstreets())?"":
                        order.getTownandstreets();

                //endregion

                order.setAddress(Province+City+Area+Townandstreets);
            }
        }
        */

        //endregion

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

    //endregion

    //region 批量操作

    @Override
    public Response batchupdateorderStatus(List<String> orderlist) throws UnsupportedEncodingException {

        int result = 0;

        /*Orders order=new Orders();
        order.setOrderid(Long.parseLong(orderlist.get(0)));

        WebUtil.prepareUpdateParams(order);*/

        result = this.ordersService.batchupdateorderStatus(orderlist);

        if (result >= 1) {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Update_Success, null)))
                    .build();
        } else {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Update_Failtrue, null)))
                    .build();
        }
    }

    @Override
    public Response batchcompleteorderStatus(List<String> orderlist) throws UnsupportedEncodingException {
        int result = 0;

        result = this.ordersService.batchcompleteorderStatus(orderlist);

        if (result >= 1) {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Update_Success, null)))
                    .build();
        } else {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Update_Failtrue, null)))
                    .build();
        }
    }

    /**
     * 批量分配订单给指定的收运人员
     * @param param
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public Response batchupdateorderWorker(String param, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("status"))) {
            criteria.put("status", map.get("status").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("worker"))) {
            criteria.put("worker", map.get("worker").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("orderidlist"))) {

            criteria.put("orderidlist", map.get("orderidlist").toString().split(","));

            /*Orders order=new Orders();
            order.setOrderid(Long.parseLong(map.get("orderidlist").toString().split(",")[0]));

            WebUtil.prepareUpdateParams(order);*/

        }

        criteria.put("confirmDt", sdf.format(new Date()));
        //criteria.put("isSms","0");


        int result = 0;

        result = this.ordersService.batchupdateorderWorker(criteria);

        if (result >= 1) {

            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Update_Success, null)))
                    .build();
        } else {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Update_Failtrue, null)))
                    .build();
        }
    }

    /**
     * 批量删除订单
     * @param list
     * @return
     */
    @Override
    public Response batchdeleteorder(List<String> list) {

        int result = 0;

        result = this.ordersService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Order_Delete_Success, null)))
                    .build();
        } else {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Order_Delete_Failtrue, null)))
                    .build();
        }

    }

    //endregion


}
