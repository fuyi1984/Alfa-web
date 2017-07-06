package com.alfa.ws.rest;

import com.alfa.web.pojo.CommonComment;
import com.alfa.web.service.CommonCommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public class CommonCommentRestImpl implements CommonCommentRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonCommentService commonCommentService;

    @Override
    public Response insertCommonComment(CommonComment commonComment) throws Exception {
        return null;
    }

    @Override
    public Response deleteCommonComment(CommonComment commonComment) {
        return null;
    }

    @Override
    public Response updateCommonComment(CommonComment commonComment) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public Response findOrder(String param, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Response batchdeleteCommonComment(List<String> list) {
        return null;
    }
}
