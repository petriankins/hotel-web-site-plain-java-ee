package com.epam.javalab.hotelproject.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "InternationalizationFilter",
        urlPatterns = "/*"
)
public class InternationalizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(InternationalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (session.getAttribute("lang") == null) {
            LOGGER.info("Setting default language for session " + req.getSession().getId());

            session.setAttribute("lang", "en");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
