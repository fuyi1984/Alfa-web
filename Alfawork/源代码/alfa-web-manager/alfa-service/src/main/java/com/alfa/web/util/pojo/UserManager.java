package com.alfa.web.util.pojo;

import com.alfa.web.util.constant.WebConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/4/27.
 */
public class UserManager {
    private static Log logger = LogFactory.getLog(UserManager.class);

    public static UserSession getUserSession() {
        HttpSession httpSession = getCurrUserSession();
        if (null != httpSession) {
            return (UserSession) httpSession.getAttribute(WebConstants.CURRENT_PLATFORM_USER);
        }
        return null;
    }

    /**
     * 获取含有当前用户的Session对象
     *
     * @return Session
     */
    private static HttpSession getCurrUserSession() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception ex) {
            return null;
        }
        return request.getSession();
    }

    public static boolean hasAdminRole() {
        UserSession userSession = getUserSession();
/*        List<Role> roles = userSession.getUser().getRoles();
        for(Role role : roles) {
            if(role.getRoleSid().longValue() == WebConstants.RoleSid.ROLE_SUPER_ADMIN.longValue() ||
                    role.getRoleSid().longValue() == WebConstants.RoleSid.ROLE_OP_ADMIN.longValue()||
                    role.getRoleSid().longValue() == 11013L||//惠映销售部客服
                    role.getRoleSid().longValue() == 11014L||//惠映市场部
                    role.getRoleSid().longValue() == 11016L||//惠映销售部销售内勤
                    role.getRoleSid().longValue() == 11017L)//惠映销售部管理员
                return true;
        }*/
        return false;
    }

    public static boolean hasEnterRole() {
        UserSession userSession = getUserSession();
     /*   List<SysRole> roles = userSession.getUser().getRoles();
        for(Role role : roles) {
            if(role.getRoleSid().longValue() == WebConstants.RoleSid.ROLE_ENTERPRISE_ADMIN.longValue()){
                return true;
            }
        }*/
        return false;
    }
}
