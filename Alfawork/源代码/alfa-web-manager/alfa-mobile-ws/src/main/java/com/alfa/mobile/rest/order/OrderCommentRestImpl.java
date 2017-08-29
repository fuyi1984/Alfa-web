package com.alfa.mobile.rest.order;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.Orders;
import com.alfa.web.service.comment.OrderCommentService;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
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
 * Created by Administrator on 2017/7/12.
 */
public class OrderCommentRestImpl implements OrderCommentRest{

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private OrderCommentService orderCommentService;

    @Autowired
    private OrdersService ordersService;

    @Override
    public Response batchinsertordercomment(List<OrderComment> commentlist) throws Exception {

        //region

        for(OrderComment item:commentlist)
        {
            int sum=(Integer.parseInt(item.getOne())+Integer.parseInt(item.getTwo())+Integer.parseInt(item.getThree()))/3;
            item.setAverage(String.valueOf(sum));
        }

        int result=this.orderCommentService.Batchinsert(commentlist);

        if(result>=1){

            Orders order=new Orders();
            
            //插入成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
        }else{
            //插入失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }

        //endregion
    }

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        //region

        Map map = WebUtil.getParamsMap(param, "utf-8");

        //region 分页排序处理
        BasePager pager = new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortName"))) {
            pager.setSortField(map.get("sortName").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }

        //endregion

        //region 过滤

        Criteria criteria = new Criteria();

        //订单ID
        if (!StringUtil.isNullOrEmpty(map.get("orderId"))) {
            criteria.put("orderId", map.get("orderId").toString());
        }

        //收运人员手机号
        if(!StringUtil.isNullOrEmpty(map.get("mobile"))){
            criteria.put("mobile",map.get("mobile").toString());
        }

        //收运人员手机号
        if(!StringUtil.isNullOrEmpty(map.get("phone"))){
            criteria.put("phone",map.get("phone").toString());
        }

        //endregion

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<OrderComment> orderCommentList = this.orderCommentService.selectByParams(criteria);

        int count = this.orderCommentService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", orderCommentList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

        //endregion
    }

    @Override
    public Response batchdeleteordercomment(List<String> list) {

        //region

        int result = 0;

        result = this.orderCommentService.batchdeleteordercomment(list);

        if (result >= 1) {
            //删除成功
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, "1", null)))
                    .build();
        } else {
            //删除失败
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "2", null)))
                    .build();
        }

        //endregion
    }
}
