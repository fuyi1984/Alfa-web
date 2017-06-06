package com.alfa.mobile.filter;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.UserSession;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
public class IdentityAuthenticationFilter implements Filter {

    private final Logger log = Logger.getLogger(this.getClass());

    private SysUsersService sysUsersService;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        ServletContext sc=arg0.getServletContext();
        sysUsersService= WebApplicationContextUtils.getRequiredWebApplicationContext(sc).
                getBean(SysUsersService.class);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(WebConstants.CURRENT_PLATFORM_USER);

        SysUsers platformUser = null;
        if(!StringUtil.isNullOrEmpty(userSession)){
            platformUser = userSession.getUser();
        }

        if(!StringUtil.isNullOrEmpty(request.getParameter("mobiletoken"))){

            Criteria criteria = new Criteria();
            criteria.put("mobiletoken", request.getParameter("mobiletoken").toString());

            List<SysUsers> users = sysUsersService.selectByParams(criteria);
            if (users.size() > 0) {
                platformUser = users.get(0);
                //已经登录则清理session
                session.removeAttribute(WebConstants.CURRENT_PLATFORM_USER);
                //重新创建session
                sysUsersService.createSession(request, response, WebConstants.CURRENT_PLATFORM_USER, platformUser);
            }
        }

        // 此处过滤的路径为不需要登陆验证的路径
        if (request.getRequestURI().contains("/verify")){
            chain.doFilter(arg0, arg1);
        }else if(null != platformUser){

        }else{
            // 用户未登陆返回320
            response.setStatus(320);
        }
    }

    @Override
    public void destroy() {

    }
}
