package com.alfa.web.service;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.util.exception.ServiceException;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface SysUsersService {

    /**
     * 执行用户登录
     *
     * @param criteria 条件
     * @return 00：失败，01：成功 ,其他情况
     */
    String executeLogin(Criteria criteria);

    /**
     * 查询数据总行数
     *
     * @param example 查询条件
     * @return 行数
     */
    int countByParams(Criteria example);

    /**
     * 通过主键查询用户
     *
     * @param userSid 用户主键
     * @return 用户管理对象
     */
    SysUsers selectByPrimaryKey(Long userSid, Integer moduleCategory);

    /**
     * 根据条件查询用户集合
     *
     * @param example 查询条件
     * @return 用户List
     */
    List<SysUsers> selectByParams(Criteria example);

    /**
     * 根据条件查询微信记录集
     * @param example
     * @return
     */
    List<SysUsers> selectByParamsForWeixin(Criteria example);
    /**
     * 根据条件查询用户集合
     *
     * @param example 查询条件
     * @return 用户List
     */
    List<SysUsers> selectByParamsNew(Criteria example);
    /**
     * 根据租户Sid查询租户用户列表
     *
     * @param example 查询条件
     * @return 用户List
     */
    List<SysUsers> selectTUserByTenantSid(Criteria example);

    /**
     * 根据用户主键删除用户
     *
     * @param userSid 用户主键
     * @return 执行条数
     */
    int deleteByPrimaryKey(Long userSid);

    /**
     * 根据用户主键批量删除用户
     * @param list
     * @return
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 根据条件更新用户
     *
     * @param record 用户管理
     * @return 执行条数
     */
    int updateByPrimaryKeySelective(SysUsers record);

    /**
     * 根据条件更新用户
     *
     * @param record 用户管理
     * @return 执行条数
     */
    int updateByPrimaryKey(SysUsers record);

    /**
     * 插入用户信息
     *
     * @param record 用户信息
     * @return 执行条数
     */
    int insert(SysUsers record);

    /**
     * 选择性插入用户信息
     *
     * @param record 用户信息
     * @return 执行条数
     */
    int insertSelective(SysUsers record);

    /**
     * 通过服务实例主键查询用户邮箱地址
     *
     * @param serviceInstanceSid 服务实例主键
     * @return 用户邮箱列表
     */
    List<SysUsers> selectEmailByServiceInstanceSid(Long serviceInstanceSid);

    /**
     * 获取用户状态
     *
     * @param userSid 用户主键
     * @return "00"禁用;"01"启用
     */
    String getUserStatus(Long userSid);

    /**
     * 前台删除用户
     *
     * @param userSid
     * @return
     */
    boolean deleteUser(Long userSid);
    /**
     * 前台新增用户
     *
     * @param user
     * @return
     */
    boolean insertUser(SysUsers user) throws Exception;


    /**
     * 手机新增用户
     * @param user
     * @return
     * @throws Exception
     */
    boolean inserMobileUser(SysUsers user) throws Exception;

    /**
     * 后台新增用户
     *
     * @param user
     * @return
     */
    boolean insertPlatformUser(SysUsers user);

    /**
     * 后台更新用户
     *
     * @param user
     * @return
     */
    boolean updatePlatformUser(SysUsers user);

    /**
     * 注册用户
     *
     * @param user
     * @return 0：成功；1：失败；2：用户名重复；3：租户名重复
     */
    int addRegisterUser(SysUsers user);

    /**
     * 根据租户sid和服务sid查询user实例
     */
    List<SysUsers> selectAllocatedUserByParams(Criteria example);

    /**
     * 根据租户sid和服务sid查询user实例
     */
    List<SysUsers> selectUnallocatedUserByParams(Long tenantSid);

    /**
     *
     * 创建session
     * @param session
     * @param servletResponse
     * @param currentPlatformUser
     * @param currentUser
     * @return
     */
    UserSession createSession(HttpSession session,
                              HttpServletResponse servletResponse, String currentPlatformUser,
                              SysUsers currentUser);


    /**
     *
     * 创建session
     * @param session
     * @param servletResponse
     * @param currentPlatformUser
     * @param currentUser
     * @return
     */
    UserSession createSession(HttpServletRequest servletRequest,
                              HttpServletResponse servletResponse, String currentPlatformUser,
                              SysUsers currentUser);

    /**
     * 根据user sid  查询用户
     * @param userSid
     * @return
     */
    SysUsers selectByPrimaryKey(Long userSid);

    /**
     * 激活用户
     *
     * @param user
     * @return
     * @throws ServiceException
     */
    public int updateUserForActive(SysUsers user, int type) throws ServiceException;


    /**
     * Email用户、账号创建
     * @param user
     * @return
     * @throws ServiceException
     */
    public int insertEmailUser(SysUsers user) throws ServiceException;


    /**
     * 修改用户促销活动
     * @param criteria
     * @return
     */
    int updateIsFirstPagePromotion(Criteria criteria);


    /**
     * 批量更新用户状态
     * @param list
     * @return
     */
    int batchUpdateUserStatus(List<String> list);


}
