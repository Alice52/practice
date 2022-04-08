package common.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.core.wrapper.RepeatedlyReadRequestWrapper;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author zack <br>
 * @create 2022-04-08 11:56 <br>
 * @project mc-platform <br>
 */
public class RepeatReadHttpServletFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain)
            throws ServletException, IOException {
        RepeatedlyReadRequestWrapper requestWrapper =
                new RepeatedlyReadRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }

    @Override
    public void destroy() {}
}
