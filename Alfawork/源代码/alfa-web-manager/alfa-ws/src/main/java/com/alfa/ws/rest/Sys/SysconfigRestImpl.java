package com.alfa.ws.rest.Sys;

import com.alfa.web.util.StringUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.sys.SysconfigService;
import com.alfa.web.util.LicenseUtil;
import com.alfa.web.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/27.
 */
public class SysconfigRestImpl implements SysconfigRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private SysconfigService sysconfigService;

    /**
     * 查询所有的配置
     * @param config
     * @return
     */
    @Override
    public Response findAllConfig(SysConfig config) {
        Criteria example=new Criteria();
        if(config!=null){
            if (!StringUtil.isNullOrEmpty(config.getConfigName())){
                example.put("configNameLike",config.getConfigName());
            }
            if(!StringUtil.isNullOrEmpty(config.getConfigKey())){
                example.put("configKeyLike",config.getConfigKey());
            }
        }

        List<SysConfig> list=this.sysconfigService.selectByParams(example);
        String json=JsonUtil.toJson(list);

        //return Response.status(Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.Configuration_Select_Success,json))).build();

        return Response.status(Status.OK).entity(json).build();
    }

    /**
     * 新增配置
     *
     * @param config
     * @return
     */
    @Override
    public Response insertConfig(SysConfig config) {

        //String json = "";

        Criteria criteria = new Criteria();
        criteria.put("configName", config.getConfigName());
        List<SysConfig> configList = this.sysconfigService.selectByParams(criteria);

        if (configList.size() > 0) {
            return Response.status(Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Configuration_Exists_Success, null))).build();
        } else {
            boolean result = this.sysconfigService.insertSysConfig(config);
            if(result){
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                return Response.status(Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Configuration_Insert_Success, null))).build();
            }else{
                return Response.status(Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Configuration_Insert_Failtrue, null))).build();
            }
        }

    }

    /**
     * 更新配置
     */
    @Override
    public Response updateConfig(SysConfig config) {
        String Json="";
        WebUtil.prepareUpdateParams(config);

        int result=this.sysconfigService.updateByPrimaryKeySelective(config);

        if(result==1){
             Json=JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.Configuration_Update_Success,null));
        }else{
            Json=JsonUtil.toJson(new RestResult(RestResult.FAILURE,WebConstants.MsgCd.Configuration_Update_Failtrue,null));
        }

        return Response.status(Status.OK).entity(Json).build();
    }

    /**
     * 删除配置
     */
    @Override
    public Response deleteConfig(SysConfig config) {
        String json="";
        boolean result=this.sysconfigService.deleteSysConfig(config.getId());

        if(result){
            json= JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.Configuration_Delete_Success,null));
            return Response.status(Status.OK).entity(json).build();
        }else{
            json= JsonUtil.toJson(new RestResult(RestResult.FAILURE,WebConstants.MsgCd.Configuration_Delete_Failtrue,null));
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }

    }

    @Override
    public Response batchdeleteConfig(List<String> list) {

        int result = 0;

        result=this.sysconfigService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.Configuration_Delete_Success, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.Configuration_Delete_Failtrue, null))).build();
        }
    }

    @Override
    public Response findConfig(String param, HttpServletRequest request, HttpServletResponse response) {

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
        if (!StringUtil.isNullOrEmpty(map.get("configName"))) {
            criteria.put("configNameLike",  map.get("configName").toString());
        }
        if (!StringUtil.isNullOrEmpty(map.get("configKey"))) {
            criteria.put("configKeyLike",  map.get("configKey").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<SysConfig> configList = this.sysconfigService.selectByParams(criteria);
        int count = this.sysconfigService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        /*data.put("TotalRows", count);
        data.put("Rows", configList);*/

        /**
         * easyui
         */
        data.put("total", count);
        data.put("rows", configList);

        String json = JsonUtil.toJson(data);

        return Response.status(Status.OK).entity(json).build();
    }

    @Override
    public Response export(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * 验证License是否过期
     *
     * @return return response
     */
    @Override
    public Response licenseValid() {
        if (LicenseUtil.isDateValid()) {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(new RestResult(RestResult.SUCCESS))
            ).build();
        } else {
            /*return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(
                            RestResult.FAILURE,
                            WebUtil.getMessage(WebConstants.MsgCd.ERROR_LICENSE_INVALID),
                            null))).build();*/
            log.debug(JsonUtil.toJson(new RestResult(RestResult.FAILURE)));
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(new RestResult(RestResult.FAILURE))
            ).build();
        }
    }
}
