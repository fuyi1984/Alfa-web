package com.alfa.ws.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.UserSession;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by Administrator on 2017/4/25.
 */
public class IdentityAuthenticationFilter implements Filter {

    private final Logger log = Logger.getLogger(this.getClass());

    private SysUsersService sysUsersService;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        ServletContext sc = arg0.getServletContext();
        sysUsersService = WebApplicationContextUtils.getRequiredWebApplicationContext(sc).
                getBean(SysUsersService.class);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(WebConstants.CURRENT_PLATFORM_USER);

        SysUsers platformUser = null;

        //if (!StringUtil.isNullOrEmpty(userSession)) {
        //    platformUser = userSession.getUser();
        //}

        String mobiletoken = request.getParameter("mobiletoken");

        log.debug("mobiletoken:"+mobiletoken);

        if(StringUtil.isNullOrEmpty(mobiletoken)) {
            //region 网页Web接口身份验证
            String token = request.getParameter("token");

            log.debug("token:" + token);

            if (!StringUtil.isNullOrEmpty(token)) {

                Criteria criteria = new Criteria();
                criteria.put("token", token);

                List<SysUsers> users = sysUsersService.selectByParams(criteria);
                if (users.size() > 0) {
                    platformUser = users.get(0);
                    //已经登录则清理session
                    //session.removeAttribute(WebConstants.CURRENT_PLATFORM_USER);
                    //重新创建session
                    //sysUsersService.createSession(request, response, WebConstants.CURRENT_PLATFORM_USER, platformUser);
                }
            }

            // 输出请求路径
            log.debug("URI : " + request.getRequestURI());

            log.info("URI : " + request.getRequestURI() + " current account name:" + (StringUtil.isNullOrEmpty(platformUser) ? "null" : platformUser.getUsername()));

            // 此处过滤的路径为不需要登陆验证的路径
/*        Boolean urlfilter = request.getRequestURI().contains("/verify")
                || request.getRequestURI().contains("/logout") //platform logout
                || request.getRequestURI().contains("/current");*/

            Boolean urlfilter = request.getRequestURI().contains("/verify");


            if (!StringUtil.isNullOrEmpty(userSession) && !StringUtil.isNullOrEmpty(platformUser)) {
                if (urlfilter) {
                    chain.doFilter(arg0, arg1);
                } else {
                    if (token.equals(platformUser.getToken())) {
                        chain.doFilter(arg0, arg1);
                    } else {
                        response.setStatus(403);
                    }
                }
            } else {
                if (urlfilter) {
                    chain.doFilter(arg0, arg1);
                } else {
                    response.setStatus(320);
                }
            }
            //endregion
        }else{
            //region 手机Web接口身份验证
            Criteria criteria = new Criteria();
            criteria.put("mobiletoken", mobiletoken);

            List<SysUsers> users = sysUsersService.selectByParams(criteria);
            if (users.size() > 0) {
                platformUser = users.get(0);
            }

            // 输出请求路径
            log.debug("URI : " + request.getRequestURI());

            log.info("URI : " + request.getRequestURI() + " current account name:" + (StringUtil.isNullOrEmpty(platformUser) ? "null" : platformUser.getUsername()));


            // 此处过滤的路径为不需要登陆验证的路径
            Boolean urlfilter = request.getRequestURI().contains("/login")
                    || request.getRequestURI().contains("/createUser")
                    || request.getRequestURI().contains("/getCaptcha");

            if (!StringUtil.isNullOrEmpty(platformUser)) {
                if (urlfilter) {
                    chain.doFilter(arg0, arg1);
                } else {
                    if (mobiletoken.equals(platformUser.getMobiletoken())) {
                        chain.doFilter(arg0, arg1);
                    } else {
                        response.setStatus(403);
                    }
                }
            } else {
                log.debug("session is null!");
                if (urlfilter) {
                    chain.doFilter(arg0, arg1);
                } else {
                    response.setStatus(320);
                }
            }
            //endregion
        }
    }

    @Override
    public void destroy() {

    }
}
