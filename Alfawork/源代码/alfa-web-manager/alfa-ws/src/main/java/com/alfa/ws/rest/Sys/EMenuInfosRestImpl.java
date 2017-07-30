package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.EMenuInfosService;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public class EMenuInfosRestImpl implements EMenuInfosRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private EMenuInfosService eMenuInfosService;


    @Override
    public Response insertmenu(EMenuInfos menu) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);
        return null;
    }

    @Override
    public Response batchdeletemenu(List<String> list) {
        return null;
    }

    @Override
    public Response editRole(EMenuInfos menu) {
        return null;
    }

    @Override
    public Response findist(String param, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
