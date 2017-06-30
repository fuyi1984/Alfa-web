package com.alfa.mobile.rest;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.service.weixin_usersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
@Path("/OpenId")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Scope("singleton")
public class weixin_usersRestImpl implements weixin_usersRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private weixin_usersService weixin_usersService;


    @Override
    public Response insertOpenId(td_weixin_users td_weixin_users) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("openid", td_weixin_users.getOpenid());

        List<td_weixin_users> list=this.weixin_usersService.selectByParams(criteria);

        if(list.size()>=1){
            //OpenId已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }else{
            int result=this.weixin_usersService.insertSelective(td_weixin_users);
            if(result>=1){
                //OpenId插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
            }else{
                //OpenId插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }
        }
    }

    @Override
    public Response updateOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException   {

        String Json = "";

        int result = this.weixin_usersService.updateByPrimaryKeySelective(td_weixin_users);

        if (result >= 1) {
            //OpenId更新成功
            Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"1", null));
        }else{
            //OpenId更新失败
            Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE,"2", null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();
    }
}
