package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.service.sys.userregisterbehaviorService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.util.pojo.UserManager;
import com.alfa.web.util.pojo.UserSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public class SysUserServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(SysUserServiceTest.class);

    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private com.alfa.web.service.weixin.weixin_usersService weixin_usersService;

    @Autowired
    private userregisterbehaviorService userregisterbehaviorService;

    @Test
    public void InsertUser() throws Exception {

        SysUsers user = new SysUsers();
        user.setUsername("9999");
        user.setPhone("9999");
        user.setRoleId(5L);
        user.setAddress("12345");
        user.setRealname("12345");

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

        logger.info("UsersList Size:" + UsersList.size());

        if (UsersList.size() > 0) {
            logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EXIST_SUCCESS, null)));
        } else {
            //user.setSexname(user.getSex()=="0"?"男":"女");
            user.setPassword(WebUtil.encrypt(user.getPassword(), user.getUsername()));
            boolean result = this.sysUsersService.insertUser(user);
            if (result) {
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                logger.info(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null)));
            } else {
                logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null)));
            }
        }
    }

    @Test
    public void current() {
        try {
            // 当前用户信息已在验证用户登录时放入UserManager中
            UserSession currentUser = UserManager.getUserSession();
            if (currentUser != null && currentUser.getId() != null && currentUser.getUser() != null) {
                System.out.println(currentUser.getUser().getRealname());
            } else {
                currentUser = new UserSession();
                currentUser.setId(null);
                System.out.println(currentUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void batchdeleteuser() throws IOException {
        String params = "userId=27&userId=20";

        Map<String, Object> map;

        map = JsonUtil.fromJson(params, Map.class);

        System.out.println(map.size());
    }

    @Test
    public void selectByParamsForWeixin() {
        Criteria criteria = new Criteria();
        List<SysUsers> userlist = this.sysUsersService.selectByParamsForWeixin(criteria);
        System.out.println(JsonUtil.toJson(userlist));
    }

    @Test
    public void loginforweixin() {
        //region 用户信息判断

        Criteria criteria = new Criteria();

        criteria.put("username", "13220356963");
        criteria.put("phone", "13220356963");

        // 根据用户名密码查询用户信息
        List<SysUsers> users = this.sysUsersService.selectByParamsForWeixin(criteria);

        if (users.size() > 0) {

            //region 用户信息不为空

            SysUsers currentUser = users.get(0);


            //region 查询OpenId

            criteria.clear();

            criteria.put("openid", "openidonsayw7BLsbVN3TzO-Nysb4Mda4M");

            List<td_weixin_users> weixinlist = this.weixin_usersService.selectByParams(criteria);

            if (weixinlist.size() > 0) {

                //region 用户信息关联微信账号信息

                td_weixin_users weixin = weixinlist.get(0);

                currentUser.setWeixinid(weixin.getId());
                currentUser.setOpenid(weixin.getOpenid());
                currentUser.setHeadimgurl(weixin.getHeadimgurl());
                currentUser.setState(weixin.getState());

                //endregion
            } else {
                currentUser.setOpenid("");
                currentUser.setHeadimgurl("");
                currentUser.setState("");
            }

            //endregion

            currentUser.setCaptcha("432943");
            currentUser.setVerifyCode("432943");

            /**
             * 角色为产废单位的时候用验证码替换用户密码
             */
            if (currentUser.getRoleId().equals(10L)) {
                String passwordEncrypt = WebUtil.encrypt("432943", currentUser.getUsername());
                currentUser.setPassword(passwordEncrypt);
            }

            currentUser.setMobiletoken(StringUtil.getUUID());

            this.sysUsersService.updateByPrimaryKeySelective(currentUser);

            System.out.println(JsonUtil.toJson(currentUser));

        }

    }

    @Test
    public void edituser(){

        SysUsers user=new SysUsers();
        user.setUserId(32L);
        //user.setPhone("13062317530");
        user.setPhone("18581043708");
        user.setRemarks("11111111111");

        Criteria criteria=new Criteria();
        criteria.put("username", user.getPhone());
        criteria.put("phone", user.getPhone());

        List<SysUsers> users = sysUsersService.selectByParams(criteria);

        if(users.size() == 0) {

            //region  没有查询到相同的手机号

            user.setUsername(user.getPhone());

            WebUtil.prepareUpdateParams(user);

            int result = this.sysUsersService.updateByPrimaryKeySelective(user);

            if (result == 1) {
                //用户信息更新成功
                System.out.println("1");
            } else {
                //用户信息更新失败
                System.out.println("3");
            }

            //endregion

        }else{

            //region 查询到相同的手机号

            SysUsers tmp=users.get(0);

            if(!tmp.getUserId().equals(user.getUserId())){
                //联系电话已存在
                System.out.println("2");
            }else{

                user.setUsername(user.getPhone());

                WebUtil.prepareUpdateParams(user);

                int result = this.sysUsersService.updateByPrimaryKeySelective(user);

                if (result == 1) {
                    //用户信息更新成功
                    System.out.println("1");
                } else {
                    //用户信息更新失败
                    System.out.println("3");
                }
            }

            //endregion
        }
    }

    @Test
    public void test1(){

        List<String> list=new ArrayList<String>();
        list.add("19");

        Criteria criteria=new Criteria();
        criteria.put("userIdlist",list);

        List<SysUsers> sysUsersList=this.sysUsersService.selectByParams(criteria);

        if(sysUsersList.size()>0){

            List<String> usersList=new ArrayList<String>();
            List<String> registerlist=new ArrayList<String>();

            for(SysUsers user:sysUsersList){

                if(user.getRoleId().equals(10L)){
                    //产废单位
                    registerlist.add(String.valueOf(user.getUserId()));

                }else if(user.getRoleId().equals(15L)){
                    //业务人员
                    usersList.add(String.valueOf(user.getUserId()));
                }

            }

            if(usersList.size()>0){
                criteria.clear();
                criteria.put("useridlist",usersList);
                this.userregisterbehaviorService.deleteByParams(criteria);
            }

            if(registerlist.size()>0){
                criteria.clear();
                criteria.put("registeridlist",registerlist);
                this.userregisterbehaviorService.deleteByParams(criteria);
            }

        }

    }

    @Test
    public void loginforwap(){

        Criteria criteria = new Criteria();
        criteria.put("username", "15320295813");
        criteria.put("phone", "15320295813");

        List<SysUsers> users = sysUsersService.selectByParams(criteria);

        System.out.println(users);


    }

}
