package com.alfa.mobile.rest.order;

import com.alfa.web.pojo.CommonComment;
import com.alfa.web.service.CommonCommentService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public class CommonCommentRestImpl implements CommonCommentRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonCommentService commonCommentService;

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        //分页排序处理
        BasePager pager = new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortName"))) {
            pager.setSortField(map.get("sortName").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }

        //过滤
        Criteria criteria = new Criteria();

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<CommonComment> ordersList = this.commonCommentService.selectByParams(criteria);
        int count = this.commonCommentService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        /*data.put("TotalRows", count);
        data.put("Rows", configList);*/

        /**
         * easyui
         */
        data.put("total", count);
        data.put("rows", ordersList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
