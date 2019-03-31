package threadLocal;

import org.springframework.http.HttpRequest;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther: shchenbang
 * @Date: 2018/12/11 17:48
 * @Description:
 */
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpRequest request = (HttpRequest) servletRequest;
        try {
            UserObj userObj = ThreadContext.getUser();
            if(null == userObj){
                ThreadContext.bindUser(new UserObj("zhangsan"));
            }
            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            ThreadContext.unBindUser();

        }

    }

    @Override
    public void destroy() {
    }
}
