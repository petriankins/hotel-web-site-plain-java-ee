package com.epam.javalab.hotelproject.filter;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.SecurityService;
import com.epam.javalab.hotelproject.service.SecurityServiceImpl;
import com.epam.javalab.hotelproject.service.UserService;
import com.epam.javalab.hotelproject.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final Logger          LOGGER          = Logger.getLogger(AuthenticationFilter.class);
    private final        SecurityService securityService = new SecurityServiceImpl();
    private final        UserService     userService     = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String requestURI = request.getRequestURI();

        //        session.setAttribute("user", new User(1,"Test User","test","test","test last name"));
        if (requestURI.equals("/") || requestURI.equals("/registration") || requestURI.equals("/login") ||
            requestURI.contains("/css") || requestURI.contains("/js") || requestURI.contains("/fonts") ||
            requestURI.contains("/locale") || requestURI.equals("/favicon.ico")) {

            filterChain.doFilter(servletRequest, servletResponse);
        } else if (user == null || !securityService.authenticate(user)) {
            LOGGER.info("Unauthorized access attempt to uri \"" + request.getRequestURI() + "\"");

            response.sendRedirect("/");

        } else if (!userService.isAdmin(user) &&
                   (requestURI.contains("/administrator") || requestURI.contains("/appointRoom"))) {
            LOGGER.info("Non admin tried to access uri \"" + request.getRequestURI() + "\"");
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
