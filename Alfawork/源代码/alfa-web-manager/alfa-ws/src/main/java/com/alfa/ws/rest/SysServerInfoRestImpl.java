package com.alfa.ws.rest;

import com.alfa.web.pojo.SystemInfo;
import com.alfa.web.util.JsonUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/4.
 */
public class SysServerInfoRestImpl implements SysServerInfoRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public Response GetSystemInfo(HttpServletRequest servletRequest) {

        Properties props=System.getProperties();

        SystemInfo info=new SystemInfo();
        info.setJava_version(props.getProperty("java.version"));
        info.setJava_io_tmpdir(props.getProperty("java.io.tmpdir"));
        info.setOs_name(props.getProperty("os.name"));
        info.setOs_arch(props.getProperty("os.arch"));
        info.setOs_version(props.getProperty("os.version"));
        //info.setOs_user_home(props.getProperty("user.home"));
        info.setOs_user_dir(props.getProperty("user.dir"));
        info.setSun_desktop(props.getProperty("sun.desktop"));
        info.setOs_cpus(Runtime.getRuntime().availableProcessors());
        info.setOs_name(props.getProperty("os.name"));
        info.setOs_date(new Date());
        info.setServer_name(servletRequest.getServerName());
        info.setServer_port(servletRequest.getServerPort());
        info.setServer_addr(servletRequest.getRemoteAddr());
        info.setServer_host(servletRequest.getRemoteHost());
        info.setServer_protocol(servletRequest.getProtocol());
        info.setServer_context(servletRequest.getContextPath());

        return Response.status(Response.Status.OK).entity(JsonUtil.toJson(info)).build();
    }
}
