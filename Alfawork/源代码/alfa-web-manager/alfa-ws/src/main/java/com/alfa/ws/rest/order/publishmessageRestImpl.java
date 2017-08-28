package com.alfa.ws.rest.order;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.service.message.messageuserService;
import com.alfa.web.service.message.publishmessageService;
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

    @Autowired
    private SysUsersService sysUsersService;

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

    @Override
    public Response findlistoutbox(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        //region 分页条件

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

        //过滤
        Criteria criteria = new Criteria();

        //phone
        if (!StringUtil.isNullOrEmpty(map.get("phone"))) {
            criteria.put("phone", map.get("phone").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

        int count = this.messageuserService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", messageuserList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

    }

    @Override
    public Response batchdeletemessageuserbyid(List<String> list) {
        int result = 0;

        result = this.messageuserService.batchdeleteByPrimaryKey(list);

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

    @Override
    public Response insertmessageuser(messageuser user) {

        Criteria criteria=new Criteria();
        criteria.put("phone",user.getPhone());

        List<SysUsers> sysUsersList=this.sysUsersService.selectByParams(criteria);

        if(sysUsersList.size()>0){

            SysUsers users=sysUsersList.get(0);

            if(users.getRoleId().equals(9L)||users.getRoleId().equals(10L)) {

                user.setUserid(users.getUserId());

                criteria.clear();
                criteria.put("userid",users.getUserId());
                criteria.put("messageid",user.getMessageid());

                List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

                if (messageuserList.size() == 0) {
                    int result = this.messageuserService.insertSelective(user);
                    if (result > 0) {
                        //插入成功
                        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "1", null)).build();
                    } else {
                        //插入失败
                        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2", null)).build();
                    }
                }else{
                    //消息已发送
                    return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "5", null)).build();
                }
            }else{
                //当前手机号不能发送消息
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "4",null)).build();
            }
        }else{

            //手机号不存在
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "3",null)).build();
        }
    }

    /**
     * 添加发布消息
     * @param message
     * @return
     */
    @Override
    public Response InsertPublishMessage(publishmessage message) {

        Criteria criteria = new Criteria();
        criteria.put("title",message.getTitle());
        criteria.put("content",message.getContent());

        List<publishmessage> publishmessageList=this.publishmessageService.selectByParams(criteria);

        if(publishmessageList.size()>0){
            //数据已存在
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "1",null)).build();
        }else{
            int result=this.publishmessageService.insertSelective(message);

            if(result>0){
                //添加成功
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "2",null)).build();
            }else{
                //添加失败
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "3",null)).build();
            }
        }
    }

    @Override
    public Response EditPublishMessage(publishmessage message) {

        String Json="";

        Criteria criteria = new Criteria();
        criteria.put("id",message.getId());

        List<publishmessage> publishmessageList=this.publishmessageService.selectByParams(criteria);

        if(publishmessageList.size()>0) {
            int result = this.publishmessageService.updateByPrimaryKeySelective(message);

            if (result == 1) {
                //更新成功
                Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));
            } else {
                //更新失败
                Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
            }
        }else{
            //数据不存在
            Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }

    @Override
    public Response BatchDeletePublishMessage(List<String> idlist) {

        int result = 0;

        result = this.publishmessageService.batchdeleteByPrimaryKey(idlist);

        if (result >= 1) {

            result=this.messageuserService.batchdeleteByMessageid(idlist);

            if(result>=1){
                log.info("批量删除成功");
            }else{
                log.info("批量删除失败");
            }

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
