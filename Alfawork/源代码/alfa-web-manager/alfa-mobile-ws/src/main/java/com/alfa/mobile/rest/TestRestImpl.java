package com.alfa.mobile.rest;

import com.alfa.web.common.pojo.RestResult;
import com.alfa.web.common.utils.JsonUtil;

import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/4/28.
 */
public class TestRestImpl implements TestRest {
    @Override
    public Response findAllConfig() {
        return Response.status(Response.Status.OK).entity(
                JsonUtil.toJson(new RestResult(RestResult.SUCCESS))
        ).build();
    }
}
