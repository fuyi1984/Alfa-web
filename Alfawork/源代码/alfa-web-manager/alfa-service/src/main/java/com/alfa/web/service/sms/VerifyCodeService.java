package com.alfa.web.service.sms;

import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public interface VerifyCodeService {

    int countByParams(Criteria example);

    VerifyCode selectByPrimaryKey(Long verifySid);

    List<VerifyCode> selectByParams(Criteria example);

    int deleteByPrimaryKey(Long verifySid);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(VerifyCode record, Criteria example);

    int updateByParams(VerifyCode record, Criteria example);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    int insertVerifyCode(VerifyCode verifyCode);

    String insertVerifyCodeAndReturn(VerifyCode verifyCode) ;
}
