package com.alfa.web.service.Impl;

import com.alfa.web.dao.VerifyCodeMapper;
import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.service.SmsService;
import com.alfa.web.service.VerifyCodeService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/11.
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);

    @Autowired
    private SmsService smsService;

    @Autowired
    private VerifyCodeMapper verifyCodeMapper;


    @Override
    public int countByParams(Criteria example) {
        int count = this.verifyCodeMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public VerifyCode selectByPrimaryKey(Long verifySid) {
        return this.verifyCodeMapper.selectByPrimaryKey(verifySid);
    }

    @Override
    public List<VerifyCode> selectByParams(Criteria example) {
        return this.verifyCodeMapper.selectByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long verifySid) {
        return this.verifyCodeMapper.deleteByPrimaryKey(verifySid);
    }

    @Override
    public int updateByPrimaryKeySelective(VerifyCode record) {
        return this.verifyCodeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VerifyCode record) {
        return this.verifyCodeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.verifyCodeMapper.deleteByParams(example);
    }

    @Override
    public int updateByParamsSelective(VerifyCode record, Criteria example) {
        return this.verifyCodeMapper.updateByParamsSelective(record, example.getCondition());
    }

    @Override
    public int updateByParams(VerifyCode record, Criteria example) {
        return this.verifyCodeMapper.updateByParams(record, example.getCondition());
    }

    @Override
    public int insert(VerifyCode record) {
        return this.verifyCodeMapper.insert(record);
    }

    @Override
    public int insertSelective(VerifyCode record) {
        return this.verifyCodeMapper.insertSelective(record);
    }

    @Override
    public int insertVerifyCode(VerifyCode verifyCode) {
        if(null == verifyCode || verifyCode.getType() == null){
            return -1;
        }
        int type = verifyCode.getType();
        switch (type){
            case WebConstants.VerifyCode.type0://个人注册-手机
                verifyCode.setCode(WebUtil.randomCaptcha(6));
                try {
                    //smsService.sendMessage(verifyCode.getBoundAccount(), PropertiesUtil.getProperty("verify.content") + verifyCode.getCode());
                    smsService.sendSMS(verifyCode.getBoundAccount(),PropertiesUtil.getProperty("verify.content") + verifyCode.getCode());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("发送短信失败");
                }
                break;
        }
        WebUtil.prepareInsertParams(verifyCode);
        int r = this.verifyCodeMapper.insertSelective(verifyCode);
        return r;
    }

    @Override
    public String insertVerifyCodeAndReturn(VerifyCode verifyCode) {
        if(null==verifyCode||verifyCode.getType()==null) {
            return null;
        }
        int type=verifyCode.getType();
        //verifyCode.setCode(WebUtil.randomCaptcha(6));
        switch (type){
            case WebConstants.VerifyCode.type0://个人注册-手机
                verifyCode.setCode(WebUtil.randomCaptcha(6));
                try{
                    //smsService.sendMessage(verifyCode.getBoundAccount(), PropertiesUtil.getProperty("verify.content")+ verifyCode.getCode());
                    smsService.sendSMS(verifyCode.getBoundAccount(),PropertiesUtil.getProperty("verify.content") + verifyCode.getCode());
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.println("发送短信失败");
                }
                break;
        }
        WebUtil.prepareInsertParams(verifyCode);
        this.verifyCodeMapper.insertSelective(verifyCode);
        return verifyCode.getCode();
    }
}
