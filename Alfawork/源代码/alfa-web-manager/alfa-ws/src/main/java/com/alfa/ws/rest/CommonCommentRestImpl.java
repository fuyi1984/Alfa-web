package com.alfa.ws.rest;

import com.alfa.web.pojo.CommonComment;
import com.alfa.web.pojo.Orders;
import com.alfa.web.service.CommonCommentService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 */
public class CommonCommentRestImpl implements CommonCommentRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonCommentService commonCommentService;

    @Override
    public Response insertCommonComment(CommonComment commonComment) throws Exception {

        Criteria criteria=new Criteria();
        criteria.put("Content",commonComment.getContent());

        List<CommonComment> commonCommentList=this.commonCommentService.selectByParams(criteria);

        if(commonCommentList.size()>0){

            int result=this.commonCommentService.insertSelective(commonComment);

            if(result>=1){
                //常用评语插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
            }else{
                //常用评语插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
            }
        }else{
            //数据已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
        }
    }

    @Override
    public Response deleteCommonComment(CommonComment commonComment) {

        String json = "";

        int result = this.commonCommentService.deleteByPrimaryKey(commonComment.getId());

        if (result>=1) {
            System.out.println(result);
            //删除成功
            json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"1", null));
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            //删除失败
            json = JsonUtil.toJson(new RestResult(RestResult.FAILURE,"2", null));
            return Response.status(Response.Status.OK).entity(json).build();
        }

    }

    @Override
    public Response updateCommonComment(CommonComment commonComment) throws UnsupportedEncodingException {

        String Json = "";

        WebUtil.prepareUpdateParams(commonComment);

        int result = this.commonCommentService.updateByPrimaryKeySelective(commonComment);

        if (result == 1) {

            //评语更新成功
            Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"1", null));
        } else {
            //评语更新失败
            Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        //分页排序处理
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

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<CommonComment> ordersList = this.commonCommentService.selectByParams(criteria);
        int count = this.commonCommentService.countByParams(criteria);

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

    @Override
    public Response batchdeleteCommonComment(List<String> list) {

        int result = 0;

        result = this.commonCommentService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            //批量删除成功
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.SUCCESS, "1", null)))
                    .build();
        } else {
            //批量删除失败
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "2", null)))
                    .build();
        }

    }
}
