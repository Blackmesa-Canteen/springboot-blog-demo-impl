package com.learn.blog_demo.interceptor;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 14:36
 */
//@Component
@WebFilter(urlPatterns = "/*", filterName = "SQLInjection", initParams = { @WebInitParam(name = "regx", value = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" +
        "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)") })

public class SqlInjectFilter implements Filter {
    private String regx;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.regx = filterConfig.getInitParameter("regx");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Map parametersMap = servletRequest.getParameterMap();
        Iterator it = parametersMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String[] value = (String[]) entry.getValue();
            for (int i = 0; i < value.length; i++) {
                if (null != value[i] && value[i].matches(this.regx)) {
                    servletRequest.setAttribute("err", "您输入的参数有非法字符，请输入正确的参数！");
                    servletRequest.setAttribute("pageUrl",req.getRequestURI());
                    servletRequest.getRequestDispatcher(servletRequest.getServletContext().getContextPath() + "/error").forward(servletRequest, servletResponse);
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}