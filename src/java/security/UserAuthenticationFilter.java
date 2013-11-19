/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author Imm
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jsf.LoginBean;

/**
 *
 * @author Imm
 */
public class UserAuthenticationFilter implements Filter {

    private FilterConfig config;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute(LoginBean.AUTH_KEY) == null) {
            ((HttpServletResponse) resp).sendRedirect("../login.xhtml");

        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
        config = null;
    }
}
