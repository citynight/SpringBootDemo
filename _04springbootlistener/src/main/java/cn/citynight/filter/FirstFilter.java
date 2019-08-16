package cn.citynight.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/*SpringBoot整合Filter方式一
*
* <filter>
*     <filter-name>FirstFilter</filter-name>
*     <filter-class>cn.citynight.filter</filter-class>
* </filter>
*
* <filter-mapping>
*     <filter-name>FirstFilter</filter-name>
*     <url-pattern>/hello</url-pattern>
* </filter-mapping>
* */
//@WebFilter(filterName = "FirstFilter",urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开filter");
    }

    @Override
    public void destroy() {

    }
}
