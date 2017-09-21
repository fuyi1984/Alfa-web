package com.alfa.ws.rest.money;

import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */
public class moneyactivitiesconcernRestImpl implements moneyactivitiesconcernRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private moneyactivitiesconcernServcie moneyactivitiesconcernService;

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Response insertmoneyactivitiesconcern(moneyactivitiesconcern money) {
        return null;
    }

    @Override
    public Response updatemoneyactivitiesconcern(moneyactivitiesconcern money) {
        return null;
    }

    @Override
    public Response batchdeletemoneyactivitiesconcern(List<String> list) {
        return null;
    }
}
