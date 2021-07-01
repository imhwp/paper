package us.zoom.checkin.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "JwtFilter",urlPatterns = "*")
@Order(2)
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getRequestURI().equals("/api/login") || request.getRequestURI().startsWith("/api")){
            filterChain.doFilter(request,response);
        }else{
            throw new ServletException("error request uri");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
