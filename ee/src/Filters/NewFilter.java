package Filters;

import javax.servlet.*;
import java.io.IOException;

public class NewFilter implements Filter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.setName(filterConfig.getInitParameter("name"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("TEXT_1") != "") {
            this.setName(servletRequest.getParameter("TEXT_1"));
        }
    }

    @Override
    public void destroy() {
    }
}
