package com.alfa.web.service.common;

import com.alfa.web.pojo.Companyinfo;
import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */
public interface CompanyinfoService {

    /**
     * 根据条件查询记录集
     */
    List<Companyinfo> selectByParams(Criteria example);
}
