package com.fzu.demo.common;

import com.fzu.demo.web.entity.UserEntity;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by linqz on 2017/10/12.
 *
 * @author zzx
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        String contextPath = request.getServletContext().getContextPath();
        String requestPath = requestURI.substring(contextPath.length());
        String sessionId = request.getRequestedSessionId();
        HttpSession session = request.getSession();
        if ("/".equals(requestPath) || "/login".equals(requestPath) || "/admin".equals(requestPath) || "/initRegister".equals(requestPath) || "/user/allTags".equals(requestPath) || "/register".equals(requestPath) || "/homepage/randomGames".equals(requestPath)) {
            return true;
        }
        UserEntity user = (UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY);
        if (user != null) {
            return true;
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
