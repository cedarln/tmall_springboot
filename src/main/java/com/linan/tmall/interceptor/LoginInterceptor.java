package com.linan.tmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.linan.tmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器器,访问需要登录的页面时必须要是登录状态，否则跳转到login
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();// tmall_springboot
        String[] requireAuthPages = new String[]{ //
                "buy",
                "alipay",
                "payed",
                "cart",
                "bought",
                "confirmPay",
                "orderConfirmed",
                "review",
                "forebuyone",
                "forebuy",
                "foreaddCart",
                "forecart",
                "forechangeOrderItem",
                "foredeleteOrderItem",
                "forecreateOrder",
                "forepayed",
                "forebought",
                "foreconfirmPay",
                "foreorderConfirmed",
                "foredeleteOrder",
                "forereview",
                "foredoreview"
        };

        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath + "/");//去掉请求链接里的  'tmall_springboot/'
        String page = uri;

        if (beginWith(page, requireAuthPages)) {
//            User user = (User) session.getAttribute("user");
//            if (user == null) {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}