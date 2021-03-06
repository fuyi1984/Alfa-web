package com.alfa.web.service.weixin;

import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface weixin_usersService {

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    //int insert(HistoryAddress record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(td_weixin_users record);

    /**
     * 根据条件查询记录集
     */
    List<td_weixin_users> selectByParams(Criteria example);

    List<td_weixin_users> selectByParamsForMobile(Criteria example);

    /**
     * 根据主键查询记录
     */
    td_weixin_users selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(td_weixin_users record);

    /**
     * 根据主键更新记录
     */
    //int updateByPrimaryKey(td_weixin_users record);
}
