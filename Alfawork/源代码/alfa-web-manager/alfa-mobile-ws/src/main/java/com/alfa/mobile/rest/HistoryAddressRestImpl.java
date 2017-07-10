package com.alfa.mobile.rest;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.HistoryAddressService;
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
 * Created by Administrator on 2017/6/29.
 */
public class HistoryAddressRestImpl implements HistoryAddressRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private HistoryAddressService historyAddressService;

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

        //手机号

        if (!StringUtil.isNullOrEmpty(map.get("iphone"))) {
            criteria.put("iphone", map.get("iphone").toString());
        }
        //地址
        if (!StringUtil.isNullOrEmpty(map.get("addressLike"))) {
            criteria.put("addressLike", map.get("addressLike").toString());
        }

        //endregion

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        //region 分页处理

        List<HistoryAddress> historyAddressList = this.historyAddressService.selectByParams(criteria);

        //region 拼接收油地址

        String Province,city,area,townandstreets;

        for(HistoryAddress item:historyAddressList){

            //region 地址相关属性的赋值

            Province=StringUtil.isNullOrEmpty(item.getProvince())?"":item.getProvince();

            city=StringUtil.isNullOrEmpty(item.getCity())?"":item.getCity();

            area=StringUtil.isNullOrEmpty(item.getArea())?"":item.getArea();

            townandstreets=StringUtil.isNullOrEmpty(item.getTownandstreets())?"":item.getTownandstreets();

            //endregion

            item.setFulladdress(Province+city+area+townandstreets);
        }

        //endregion

        int count = this.historyAddressService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", historyAddressList);

        String json = JsonUtil.toJson(data);

        //endregion

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response updateHistoryAddress(HistoryAddress record) throws UnsupportedEncodingException {

        String Json = "";

        WebUtil.prepareUpdateParams(record);

        int result = this.historyAddressService.updateByPrimaryKeySelective(record);

        if (result == 1) {
            //收油地址更新成功
            Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));
        } else {
            //收油地址更新失败
            Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }

    @Override
    public Response insertHistoryAddress(HistoryAddress record) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("iphone", record.getIphone());

        int count=this.historyAddressService.countByParams(criteria);

        if(count>Integer.parseInt(PropertiesUtil.getProperty("currentUser.address.maxnum"))) {

            //region test

            if (!StringUtil.isNullOrEmpty(record.getAddress())) {
                criteria.put("address", record.getAddress());
            } else {
                criteria.put("city", record.getCity());
                criteria.put("province", record.getProvince());
                criteria.put("area", record.getArea());
                criteria.put("townandstreets", record.getTownandstreets());
            }

            List<HistoryAddress> list = this.historyAddressService.selectByParams(criteria);

            if (list.size() >= 1) {
                //收油地址已存在,无法再添加
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
            } else {
                int result = this.historyAddressService.insertSelective(record);

                if (result >= 1) {
                    //收油地址插入成功
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
                } else {
                    //收油地址插入失败
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
                }
            }

            //endregion
        }else {
            //收油地址数据大于最大数量限制
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "4", null))).build();
        }
    }

    @Override
    public Response deleteHistoryAddress(HistoryAddress record) {
        String json = "";

        int result = this.historyAddressService.deleteByPrimaryKey(record.getId());

        if (result>=1) {
            //收油地址删除成功
            json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            //收油地址删除失败
            json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
            return Response.status(Response.Status.OK).entity(json).build();
        }
    }

    @Override
    public Response batchdeleteHistoryAddress(List<String> list) {
        int result = 0;

        result = this.historyAddressService.batchdeleteByPrimaryKey(list);

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
    }
}
