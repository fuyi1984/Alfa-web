package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.service.messageuserService;
import com.alfa.web.service.publishmessageService;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class messageuserServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(messageuserServiceTest.class);

    @Autowired
    private messageuserService messageuserService;

    @Autowired
    private SysUsersService sysUsersService;

    @Test
    public void insertSelective() {
        messageuser user = new messageuser();
        user.setUserid(18L);
        user.setMessageid(3L);
        user.setIsread("1");
        this.messageuserService.insertSelective(user);
    }

    @Test
    public void batchdeleteByMessageid() {
        List<String> list = new ArrayList<String>();
        list.add("2");
        this.messageuserService.batchdeleteByMessageid(list);
    }


    @Test
    public void isread() {
        //region

        messageuser user = new messageuser();
        user.setUserid(18L);
        user.setMessageid(2L);
        user.setIsread("1");

        //过滤
        Criteria criteria = new Criteria();

        //userid
        criteria.put("userid", user.getUserid());

        //messageid
        criteria.put("messageid", user.getMessageid());

        //isread
        //criteria.put("isread", user.getIsread());


        List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

        if (messageuserList.size() > 0) {

            int result = this.messageuserService.updateByParamsSelective(user);

            if (result >= 1) {
                //数据更新成功
                System.out.println("1");
            } else {
                //数据更新失败
                System.out.println("2");
            }
        } else {
            //数据不存在
            System.out.println("3");
        }

        //endregion
    }


    @Test
    public void insertmessageuser() {

        messageuser user = new messageuser();
        user.setPhone("15320295813");
        user.setIsread("0");
        user.setMessageid(2L);

        Criteria criteria = new Criteria();
        criteria.put("phone", user.getPhone());

        List<SysUsers> sysUsersList = this.sysUsersService.selectByParams(criteria);

        if (sysUsersList.size() > 0) {

            SysUsers users = sysUsersList.get(0);

            if (users.getRoleId().equals(9L) || users.getRoleId().equals(10L) || users.getRoleId().equals(15L)) {

                user.setUserid(users.getUserId());

                criteria.clear();
                criteria.put("userid",users.getUserId());
                criteria.put("messageid",user.getMessageid());

                List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

                if (messageuserList.size() == 0) {

                    int result = this.messageuserService.insertSelective(user);
                    if (result > 0) {
                        //插入成功
                        System.out.println("1");
                    } else {
                        //插入失败
                        System.out.println("2");
                    }

                } else {
                    //消息已经发送过了
                    System.out.println("5");
                }
            } else {
                //当前手机号不能发送消息
                System.out.println("4");
            }
        } else {

            //手机号不存在
            System.out.println("3");
        }
    }

    @Test
    public void selectByParams()
    {
        Criteria criteria = new Criteria();
        List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);
        Assert.assertEquals("1",messageuserList.size());
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        //criteria.put("phone","15320295813");
        int count = this.messageuserService.countByParams(criteria);
        System.out.println(count);
    }


}
