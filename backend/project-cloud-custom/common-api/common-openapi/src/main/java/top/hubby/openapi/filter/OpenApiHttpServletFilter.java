package top.hubby.openapi.filter;

import javax.servlet.annotation.WebFilter;

import common.core.filter.RepeatReadHttpServletFilter;

/**
 * @author zack <br>
 * @create 2022-04-08 16:07 <br>
 * @project mc-platform <br>
 */
@WebFilter("/openapi/*")
public class OpenApiHttpServletFilter extends RepeatReadHttpServletFilter {}
