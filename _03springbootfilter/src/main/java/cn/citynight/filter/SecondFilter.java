package cn.citynight.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入2222filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开2222filter");
    }

    @Override
    public void destroy() {

    }
}
