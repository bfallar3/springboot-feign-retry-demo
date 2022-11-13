package com.benjsoft.feignretryableexample;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseHeaderWebFilter implements Filter {
   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
      httpServletResponse.addCookie(new Cookie("appName","feign-retryable-example"));
      filterChain.doFilter(servletRequest, servletResponse);
   }
}
