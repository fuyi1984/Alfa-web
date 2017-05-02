package com.alfa.ws.rest;

import com.alfa.web.common.constant.WebConstants;
import com.alfa.web.common.pojo.RestResult;
import com.alfa.web.common.utils.JsonUtil;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.SysconfigService;
import com.alfa.web.util.LicenseUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.ws.rest.SysconfigRest;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/4/27.
 */
public class SysconfigRestImpl implements SysconfigRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SysconfigService sysconfigService;

    @Override
    public Response findAllConfig(SysConfig config) {
        return null;
    }

    @Override
    public Response insertConfig(SysConfig config) {
        return null;
    }

    @Override
    public Response updateConfig(SysConfig config) {
        return null;
    }

    @Override
    public Response deleteConfig(SysConfig config) {
        return null;
    }

    @Override
    public Response findConfig(String param, HttpServletRequest request, HttpServletResponse response) {
        return null;
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
