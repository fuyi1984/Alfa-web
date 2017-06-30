package com.alfa.mobile.rest;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.HistoryAddressService;
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
        return null;
    }

    @Override
    public Response insertHistoryAddress(HistoryAddress record) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("iphone", record.getIphone());
        criteria.put("address",record.getAddress());

        List<HistoryAddress> list=this.historyAddressService.selectByParams(criteria);

        if(list.size()>=1){
            //收油地址已存在,无法再添加
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }else{
            int result=this.historyAddressService.insertSelective(record);

            if(result>=1){
                //收油地址插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
            }else{
                //收油地址插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }
        }
    }

    @Override
    public Response deleteHistoryAddress(HistoryAddress record) {
        return null;
    }
}
