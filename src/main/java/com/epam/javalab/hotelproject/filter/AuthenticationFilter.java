package com.epam.javalab.hotelproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Auth filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String requestURI = request.getRequestURI();
        System.out.println("URI : " + requestURI);
        if (requestURI.equals("/") || requestURI.equals("/registration") || requestURI.equals("/login") ||
            requestURI.contains("/css") || requestURI.contains("/js") || requestURI.contains("/fonts")) {
            System.out.println("Chaining first");
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (session.getAttribute("user") == null) {
            System.out.println("Redirect");
            response.sendRedirect("/login");
        } else {
            System.out.println("Chaining second");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
