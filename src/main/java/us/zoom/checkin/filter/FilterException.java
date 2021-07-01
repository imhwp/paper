package us.zoom.checkin.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import us.zoom.checkin.common.ResultCode;
import us.zoom.checkin.common.ResultVO;
import us.zoom.checkin.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@WebFilter(filterName = "exceptionFilter",urlPatterns = "*")
@Order(1)
public class FilterException implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            ResultVO resultVO = new ResultVO(ResultCode.FAILED,e.getMessage());
            String s = JsonUtil.objectToJson(resultVO);
            OutputStream out = servletResponse.getOutputStream();
            out.write(s.getBytes("UTF-8"));
            out.flush();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
