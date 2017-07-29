package com.alfa.mobile.filter;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.TotalUrlFilters;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.service.UrlFilterService;
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

    private UrlFilterService urlFilterService;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        ServletContext sc = arg0.getServletContext();

        sysUsersService = WebApplicationContextUtils.getRequiredWebApplicationContext(sc).
                getBean(SysUsersService.class);

        urlFilterService = WebApplicationContextUtils.getRequiredWebApplicationContext(sc).getBean(UrlFilterService.class);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        //HttpSession session = request.getSession();
        //UserSession userSession = (UserSession) session.getAttribute(WebConstants.CURRENT_PLATFORM_USER);

        SysUsers platformUser = null;

        Criteria criteria = new Criteria();

        /*if(!StringUtil.isNullOrEmpty(userSession)){
            platformUser = userSession.getUser();
        }*/

        String mobiletoken = request.getParameter("mobiletoken");

        if (!StringUtil.isNullOrEmpty(mobiletoken)) {

            criteria.put("mobiletoken", mobiletoken);

            List<SysUsers> users = sysUsersService.selectByParams(criteria);
            if (users.size() > 0) {
                platformUser = users.get(0);
/*                //已经登录则清理session
                session.removeAttribute(WebConstants.CURRENT_PLATFORM_USER);
                //重新创建session
                sysUsersService.createSession(request, response, WebConstants.CURRENT_PLATFORM_USER, platformUser);*/
            }
        }

        // 输出请求路径
        log.debug("URI : " + request.getRequestURI());

        Boolean urlfilter=false;

        // 此处过滤的路径为不需要登陆验证的路径
        /*Boolean urlfilter = request.getRequestURI().contains("/loginforweixin")
                || request.getRequestURI().contains("/createUser")
                || request.getRequestURI().contains("/getCaptchaForWorker")
                || request.getRequestURI().contains("/getCaptchaForFactory")
                || request.getRequestURI().contains("/getCaptchaForFactoryForLogin")
                || request.getRequestURI().contains("/insertOpenId")
                || request.getRequestURI().contains("/updateOpenId")
                || request.getRequestURI().contains("/GetSingleOpenId");*/

        //region 从数据库中获取不需要身份验证的Url地址

        criteria.clear();
        criteria.put("types", "1");

        List<TotalUrlFilters> totalUrlFiltersList=urlFilterService.selectByParams(criteria);

        if(totalUrlFiltersList.size()>0){

            for (TotalUrlFilters totalUrlFilters:totalUrlFiltersList){

                if(request.getRequestURI().contains(totalUrlFilters.getApiAddress())){
                    urlfilter=true;
                    break;
                }

            }

        }

        //endregion

        if (!StringUtil.isNullOrEmpty(platformUser)) {

            log.info("URI : " + request.getRequestURI() + " current account name:" + (StringUtil.isNullOrEmpty(platformUser) ? "null" : platformUser.getUsername()));

            if (urlfilter) {
                chain.doFilter(arg0, arg1);
            } else {
                if (mobiletoken.equals(platformUser.getMobiletoken())) {
                    chain.doFilter(arg0, arg1);
                } else {
                    response.setStatus(320);
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
    }

    @Override
    public void destroy() {

    }
}
