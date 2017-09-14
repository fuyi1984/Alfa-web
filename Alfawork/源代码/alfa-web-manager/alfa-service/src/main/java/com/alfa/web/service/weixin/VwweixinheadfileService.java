package com.alfa.web.service.weixin;

import com.alfa.web.pojo.Vwweixinheadfile;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface VwweixinheadfileService {
    /**
     * 根据条件查询记录集
     */
    List<Vwweixinheadfile> selectByParams(Criteria example);
}
