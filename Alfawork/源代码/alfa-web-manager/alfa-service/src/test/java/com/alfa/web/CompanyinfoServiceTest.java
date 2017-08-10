package com.alfa.web;

import com.alfa.web.pojo.Companyinfo;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.CompanyinfoService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */
public class CompanyinfoServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(CommonCommentServiceTest.class);

    @Autowired
    private CompanyinfoService companyinfoService;

    @Autowired
    private SysUsersService sysUsersService;

    @Test
    public void selectByParams(){
        Criteria criteria=new Criteria();
        //criteria.put("companyid","126550");
        List<Companyinfo> companyinfoList=this.companyinfoService.selectByParams(criteria);

        System.out.println(JsonUtil.toJson(companyinfoList));

        for (Companyinfo companyinfo:companyinfoList) {

            criteria.clear();
            criteria.put("username", companyinfo.getPhone());
            criteria.put("phone",companyinfo.getPhone());

            List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

            if(UsersList.size()==0){

                System.out.println(JsonUtil.toJson(companyinfo));

                if(!StringUtil.isNullOrEmpty(companyinfo.getPhone())&&!StringUtil.isNullOrEmpty(companyinfo.getCompany_name())) {

                    System.out.println("phone:"+companyinfo.getPhone());

                    try {
                        SysUsers user = new SysUsers();
                        user.setPhone(companyinfo.getPhone().trim());
                        user.setUsername(companyinfo.getPhone().trim());

                        if (!StringUtil.isNullOrEmpty(companyinfo.getContacts())) {
                            user.setRealname(companyinfo.getContacts().trim());
                        } else {
                            user.setRealname(companyinfo.getPhone().trim());
                        }

                        user.setOrgname(companyinfo.getCompany_name().trim());
                        user.setAddress(companyinfo.getAddress().trim());

                        user.setRoleId(10L);
                        user.setStatus("1");
                        user.setPassword(WebUtil.encrypt(StringUtil.getUUID(), companyinfo.getPhone()));
                        user.setToken(StringUtil.getUUID());
                        user.setMobiletoken(StringUtil.getUUID());

                        user.setLongitude(companyinfo.getAddr_lon());
                        user.setLatitude(companyinfo.getAddr_lat());

                        user.setCreatedBy("15320295813");
                        user.setUpdatedBy("15320295813");

                        WebUtil.prepareInsertParams(user);
                        this.sysUsersService.insertUser(user);
                    }catch (Exception e)
                    {
                        System.out.println("phone:"+companyinfo.getPhone());
                    }
                }
            }
        }

    }

}
