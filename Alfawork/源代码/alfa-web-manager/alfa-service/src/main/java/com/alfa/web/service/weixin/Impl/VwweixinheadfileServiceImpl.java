package com.alfa.web.service.weixin.Impl;

import com.alfa.web.dao.weixin.VwweixinheadfileMapper;
import com.alfa.web.dao.weixin.weixin_usersMapper;
import com.alfa.web.pojo.Vwweixinheadfile;
import com.alfa.web.service.weixin.VwweixinheadfileService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class VwweixinheadfileServiceImpl implements VwweixinheadfileService {

    private static final Logger logger = LoggerFactory.getLogger(VwweixinheadfileServiceImpl.class);

    @Autowired
    private VwweixinheadfileMapper vwweixinheadfileMapper;

    @Override
    public List<Vwweixinheadfile> selectByParams(Criteria example) {
        return this.vwweixinheadfileMapper.selectByParams(example);
    }
}
