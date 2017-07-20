package com.alfa.mobile.rest;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.OrdersService;
import com.alfa.web.service.messageuserService;
import com.alfa.web.service.publishmessageService;
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
 * Created by Administrator on 2017/7/20.
 */
public class publishmessageRestImpl implements publishmessageRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private publishmessageService publishmessageService;

    @Autowired
    private messageuserService messageuserService;

    /**
     * 查看分页消息
     *
     * @param param
     * @param request
     * @param response
     * @return
     */
    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        //region

        Map map = WebUtil.getParamsMap(param, "utf-8");

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

        //过滤
        Criteria criteria = new Criteria();

        //userid
        if (!StringUtil.isNullOrEmpty(map.get("userid"))) {
            criteria.put("userid", map.get("userid").toString());
        }

        //isread
        if (!StringUtil.isNullOrEmpty(map.get("isread"))) {
            criteria.put("isread", map.get("isread").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<publishmessage> publishmessageList = this.publishmessageService.selectByParams(criteria);

        int count = this.publishmessageService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", publishmessageList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

        //endregion
    }

    /**
     * 消息已读未读
     *
     * @param param
     * @param request
     * @param response
     * @return
     */
    @Override
    public Response isread(messageuser user) {
        //region

        //过滤
        Criteria criteria = new Criteria();

        //userid
        criteria.put("userid", user.getUserid());

        //messageid
        criteria.put("messageid", user.getMessageid());

        //isread
        criteria.put("isread", user.getIsread());


        List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

        if (messageuserList.size() == 0) {

            int result = this.messageuserService.insertSelective(user);

            if(result>=1){
                //数据插入成功
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "1",null)).build();
            }else{
                //数据插入失败
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2",null)).build();
            }
        }else{
            //数据已存在
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "3",null)).build();
        }

        //endregion
    }


}
