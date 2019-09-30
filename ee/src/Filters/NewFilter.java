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
        name = filterConfig.getInitParameter("name");
        System.out.println("Filter initialization!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("TEXT_1") != "") {
            name = servletRequest.getParameter("TEXT_1");
        }
    }

    @Override
    public void destroy() {
        System.out.println("Filter destruction!");
    }
}
