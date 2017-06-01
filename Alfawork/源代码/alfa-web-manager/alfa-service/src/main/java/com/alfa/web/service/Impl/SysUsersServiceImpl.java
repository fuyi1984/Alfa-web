package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysRoleMapper;
import com.alfa.web.dao.SysUsersMapper;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.exception.ServiceException;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUsersServiceImpl.class);


    @Override
    public String executeLogin(Criteria criteria) {
        return null;
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.sysUsersMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public SysUsers selectByPrimaryKey(Long userSid, Integer moduleCategory) {
        return null;
    }

    @Override
    public List<SysUsers> selectByParams(Criteria example) {
        List<SysUsers> users=this.sysUsersMapper.selectByParams(example);
        return users;
    }

    @Override
    public List<SysUsers> selectByParamsNew(Criteria example) {
        List<SysUsers> users=this.sysUsersMapper.selectByParams(example);
        return users;
    }

    @Override
    public List<SysUsers> selectTUserByTenantSid(Criteria example) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long userSid) {
        return this.sysUsersMapper.deleteByPrimaryKey(userSid);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUsers record) {
        return this.sysUsersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUsers record) {
        return 0;
    }

    @Override
    public int insert(SysUsers record) {
        return 0;
    }

    @Override
    public int insertSelective(SysUsers record) {
        return this.sysUsersMapper.insertSelective(record);
    }

    @Override
    public List<SysUsers> selectEmailByServiceInstanceSid(Long serviceInstanceSid) {
        return null;
    }

    @Override
    public String getUserStatus(Long userSid) {
        return null;
    }

    @Override
    public boolean deleteUser(Long userSid) {
        return false;
    }

    @Override
    public boolean insertUser(SysUsers user) throws Exception {
        boolean result = false;

        WebUtil.prepareInsertParams(user);
        int levelResult = this.sysUsersMapper.insertSelective(user);

        if (levelResult == 1) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean insertPlatformUser(SysUsers user) {
        return false;
    }

    @Override
    public boolean updatePlatformUser(SysUsers user) {
        return false;
    }

    @Override
    public int addRegisterUser(SysUsers user) {
        return 0;
    }

    @Override
    public List<SysUsers> selectAllocatedUserByParams(Criteria example) {
        return null;
    }

    @Override
    public List<SysUsers> selectUnallocatedUserByParams(Long tenantSid) {
        return null;
    }

    @Override
    public UserSession createSession(HttpSession session, HttpServletResponse servletResponse, String currentPlatformUser, SysUsers currentUser) {
        return null;
    }

    @Override
    public UserSession createSession(HttpServletRequest servletRequest,
                                     HttpServletResponse servletResponse,
                                     String currentPlatformUser,
                                     SysUsers currentUser) {

        HttpSession session=servletRequest.getSession();

        //存在则删除重建session
        UserSession userSession = (UserSession) session.getAttribute(currentPlatformUser);
        if(!StringUtil.isNullOrEmpty(userSession)){
            session.removeAttribute(currentPlatformUser);
        }

        // 放入角色信息
        SysRole roles = this.sysRoleMapper.selectByPrimaryKey(currentUser.getRoleId());

        session.setMaxInactiveInterval(60*60);

        //清空密码
        currentUser.setPassword("");

        userSession=new UserSession();
        userSession.setId(session.getId());
        userSession.setCreationTime(session.getCreationTime());
        userSession.setUser(currentUser);
        userSession.setRole(roles);

        session.setAttribute(currentPlatformUser,userSession);

        return userSession;
    }

    @Override
    public SysUsers selectByPrimaryKey(Long userSid) {
        return null;
    }

    @Override
    public int updateUserForActive(SysUsers user, int type) throws ServiceException {
        return 0;
    }

    @Override
    public int insertEmailUser(SysUsers user) throws ServiceException {
        return 0;
    }

    @Override
    public int updateIsFirstPagePromotion(Criteria criteria) {
        return 0;
    }
}
