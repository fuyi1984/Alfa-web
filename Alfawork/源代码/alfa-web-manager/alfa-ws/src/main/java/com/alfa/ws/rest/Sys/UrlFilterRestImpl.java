package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.TotalUrlFilters;
import com.alfa.web.service.url.UrlFilterService;
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
 * Created by Administrator on 2017/7/14.
 */
public class UrlFilterRestImpl implements UrlFilterRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private UrlFilterService urlFilterService;

    /**
     * 新增Url
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public Response inserturl(TotalUrlFilters record) throws Exception {

        Criteria criteria=new Criteria();

        criteria.put("ApiAddress",record.getApiAddress());
        criteria.put("types",record.getTypes());

        List<TotalUrlFilters> urlFilterList=this.urlFilterService.selectByParams(criteria);

        if(urlFilterList.size()==0){
            WebUtil.prepareInsertParams(record);
            int result=this.urlFilterService.insertSelective(record);
            if(result>0){
                //插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
            }else{
                //插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }
        }else{
            //数据已存在,无法添加
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }
    }

    /**
     * 删除Url
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public Response deleteturl(TotalUrlFilters record) throws Exception {

        String json = "";

        int result = this.urlFilterService.deleteByPrimaryKey(record.getId());

        if (result>0) {
            //删除成功
            json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            //删除失败
            json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
            return Response.status(Response.Status.OK).entity(json).build();
        }
    }

    /**
     * 批量删除
     * @param list
     * @return
     */
    @Override
    public Response batchdeleteurl(List<String> list) {

        int result = 0;

        result=this.urlFilterService.batchdeleteurl(list);

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

    /**
     * 分页查询Url
     * @param param
     * @param request
     * @param response
     * @return
     */
    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {
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

        //Url类型
        if (!StringUtil.isNullOrEmpty(map.get("types"))) {
            criteria.put("types", map.get("types").toString());
        }

        //endregion

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<TotalUrlFilters> urlFilterList = this.urlFilterService.selectByParams(criteria);

        int count = this.urlFilterService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", urlFilterList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
