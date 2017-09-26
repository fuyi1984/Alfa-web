package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.userregisterbehavior;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.service.sys.userregisterbehaviorService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.registerbehaviorvo;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class userregisterbehaviorServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(userregisterbehaviorServiceTest.class);

    @Autowired
    private userregisterbehaviorService userregisterbehaviorService;

    @Autowired
    private SysUsersService sysUsersService;

    @Test
    public void insertSelective(){

        userregisterbehavior record=new userregisterbehavior();
        //业务人员ID
        record.setUserid(18L);
        //注册用户的ID
        record.setRegisterid(19L);

        int result=this.userregisterbehaviorService.insertSelective(record);
        if(result==1){
            System.out.println("1");
        }else{
            System.out.println("2");
        }
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();
        criteria.put("regstatus","0");
        List<userregisterbehavior> userregisterbehaviorlist = this.userregisterbehaviorService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(userregisterbehaviorlist));
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        criteria.put("businessphone","15320295813");
        criteria.put("businessrealname","付益");
        int count = this.userregisterbehaviorService.countByParams(criteria);
        System.out.println(count);
    }

    @Test
    public void batchdeleteByPrimaryKey(){
       List<String> list=new ArrayList<String>();
       list.add("2");
       this.userregisterbehaviorService.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void insertuser() throws Exception {

        registerbehaviorvo user=new registerbehaviorvo();
        user.setRealname("张三");
        user.setAddress("重庆");
        user.setOrgname("中国移动");
        user.setPhone("12345");
        user.setUserid(18L);

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

            SysUsers users = new SysUsers();

            users.setRealname(user.getRealname());
            users.setOrgname(user.getOrgname());
            users.setPhone(user.getPhone());
            users.setAddress(user.getAddress());
            users.setUsername(user.getPhone());

            users.setPassword(WebUtil.encrypt(StringUtil.getUUID(), user.getPhone()));
            users.setRoleId(10L);
            users.setStatus("0");

            boolean result = this.sysUsersService.insertUser(users);

            if (result) {

                userregisterbehavior userregisterbehavior = new userregisterbehavior();
                userregisterbehavior.setUserid(user.getUserid());
                userregisterbehavior.setRegisterid(users.getUserId());

                int sum = this.userregisterbehaviorService.insertSelective(userregisterbehavior);

                if(sum>0){
                    //数据添加失败
                    System.out.println("1");
                }else{
                    //数据添加失败
                    System.out.println("3");
                }


            } else {
                //数据添加失败
                System.out.println("3");
            }

        } else {
            //数据已存在
            System.out.println("2");
        }
    }
}
