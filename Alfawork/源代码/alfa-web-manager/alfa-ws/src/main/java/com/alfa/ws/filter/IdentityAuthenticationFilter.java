package com.alfa.ws.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.TotalUrlFilters;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.service.UrlFilterService;
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

        //region

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(WebConstants.CURRENT_PLATFORM_USER);

        SysUsers platformUser = null;

        String token = request.getParameter("token");

        log.debug("token:" + token);

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(token)) {

            criteria.put("token", token);

            List<SysUsers> users = sysUsersService.selectByParams(criteria);

            if (users.size() > 0) {
                platformUser = users.get(0);
            }
        }

        Boolean urlfilter=false;

        // 输出请求路径
        log.debug("URI : " + request.getRequestURI());

        //region 从数据库中获取不需要身份验证的Url地址

        criteria.clear();
        criteria.put("types", "0");

        List<TotalUrlFilters> totalUrlFiltersList=urlFilterService.selectByParams(criteria);

        if(totalUrlFiltersList.size()>0){

            for (TotalUrlFilters totalUrlFilters:totalUrlFiltersList){

                if(request.getRequestURI().contains(totalUrlFilters.getApiAddress())){
                    urlfilter=true;
                    break;
                }
                
            }

        }

        //urlfilter = request.getRequestURI().contains("/verify");

        //ednregion

        //region

        if (!StringUtil.isNullOrEmpty(userSession) && !StringUtil.isNullOrEmpty(platformUser)) {

            log.info("URI : " + request.getRequestURI() + " current account name:" + (StringUtil.isNullOrEmpty(platformUser) ? "null" : platformUser.getUsername()));

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
    }

    @Override
    public void destroy() {

    }
}
