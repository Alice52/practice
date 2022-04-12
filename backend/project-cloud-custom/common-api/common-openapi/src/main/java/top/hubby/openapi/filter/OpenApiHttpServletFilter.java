package top.hubby.openapi.filter;

import common.core.filter.RepeatReadHttpServletFilter;

import javax.servlet.annotation.WebFilter;

/**
 * @author zack <br>
 * @create 2022-04-08 16:07 <br>
 * @project mc-platform <br>
 */
@WebFilter("/openapi/*")
public class OpenApiHttpServletFilter extends RepeatReadHttpServletFilter {}
