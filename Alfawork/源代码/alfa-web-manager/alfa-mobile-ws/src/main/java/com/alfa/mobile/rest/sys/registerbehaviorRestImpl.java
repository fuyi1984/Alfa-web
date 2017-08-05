package com.alfa.mobile.rest.sys;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.fileinfo;
import com.alfa.web.pojo.userregisterbehavior;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.service.fileinfoService;
import com.alfa.web.service.userregisterbehaviorService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.registerbehaviorvo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
public class registerbehaviorRestImpl implements registerbehaviorRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private userregisterbehaviorService userregisterbehaviorService;

    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private HistoryAddressService historyAddressService;

    @Autowired
    private fileinfoService fileinfoService;

    @Override
    public Response insertUser(registerbehaviorvo user) throws Exception {

        //region

        fileinfo fileinfo=new fileinfo();

        if(!StringUtil.isNullOrEmpty(user.getFileurl())){

            File file=new File(user.getFileurl());

            fileinfo.setUrl(user.getFileurl());
            fileinfo.setType("image");
            fileinfo.setFile_name(file.getName());

            int result=this.fileinfoService.insertSelective(fileinfo);

            if(result>0){
                log.info("文件信息表插入成功");
            }else{
                log.info("文件信息表插入失败");
            }
        }

        //endregion

        Criteria criteria = new Criteria();

        //region 查询条件

        /**
         * 真实姓名
         */
        if (!StringUtil.isNullOrEmpty(user.getRealname())) {
            criteria.put("realname", user.getRealname());
        }

        /**
         * 手机号
         */
        if (!StringUtil.isNullOrEmpty(user.getPhone())) {
            criteria.put("phone", user.getPhone());
        }

        /**
         * 单位地址
         */
        if (!StringUtil.isNullOrEmpty(user.getAddress())) {
            criteria.put("address", user.getAddress());
        }

        /**
         * 单位名称
         */
        if (!StringUtil.isNullOrEmpty(user.getOrgname())) {
            criteria.put("orgname", user.getOrgname());
        }

        //endregion

        List<SysUsers> usersList = this.sysUsersService.selectByParams(criteria);

        if (usersList.size() == 0) {

            //region 添加用户数据

            SysUsers users = new SysUsers();

            users.setRealname(user.getRealname());
            users.setOrgname(user.getOrgname());
            users.setPhone(user.getPhone());
            users.setAddress(user.getAddress());
            users.setUsername(user.getPhone());
            users.setRemarks(user.getRemark());

            //经纬度
            users.setLongitude(user.getLongitude());
            users.setLatitude(user.getLatitude());

            //用户表和文件信息表建立关联
            if(!StringUtil.isNullOrEmpty(fileinfo.getId())) {
                users.setFileid(fileinfo.getId());
            }

            users.setPassword(WebUtil.encrypt(StringUtil.getUUID(), user.getPhone()));
            users.setRoleId(10L);
            users.setStatus("0");

            boolean userresult = this.sysUsersService.insertUser(users);

            //endregion

            //region 添加收油地址历史记录

            criteria.clear();
            criteria.put("iphone", user.getPhone());
            criteria.put("address", user.getTargetaddress());

            List<HistoryAddress> historyAddressList=this.historyAddressService.selectByParams(criteria);

            if(historyAddressList.size()==0) {

                HistoryAddress historyAddress = new HistoryAddress();
                historyAddress.setIphone(user.getPhone());
                historyAddress.setAddress(user.getTargetaddress());
                int historyresult = this.historyAddressService.insertSelective(historyAddress);
                if(historyresult>0){
                    log.info("收油地址添加成功!");
                }else{
                    log.info("收油地址添加失败!");
                }

            }

            //endregion

            //region 结果判断

            if (userresult) {

                //region 业务人员与注册用户建立关联

                log.info("用户数据添加成功!");

                userregisterbehavior userregisterbehavior = new userregisterbehavior();
                userregisterbehavior.setUserid(user.getUserid());
                userregisterbehavior.setRegisterid(users.getUserId());

                int sum = this.userregisterbehaviorService.insertSelective(userregisterbehavior);

                if (sum > 0) {
                    //数据添加失败
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
                } else {
                    //数据添加失败
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
                }

                //endregion

            } else {
                //数据添加失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }

            //endregion

        } else {
            //数据已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }

    }

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

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

        Criteria criteria = new Criteria();

        //业务人员电话
        if (!StringUtil.isNullOrEmpty(map.get("businessphone"))) {
            criteria.put("businessphone", map.get("businessphone").toString());
        }

        //业务人员姓名
        if (!StringUtil.isNullOrEmpty(map.get("businessrealname"))) {
            criteria.put("businessrealname", map.get("businessrealname").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<userregisterbehavior> userregisterbehaviorList = this.userregisterbehaviorService.selectByParams(criteria);

        int count = this.userregisterbehaviorService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", userregisterbehaviorList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response batchdeleteUser(List<String> list) throws IOException {
        int result = 0;

        result = this.userregisterbehaviorService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            //删除成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
        } else {
            //删除失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }
}
