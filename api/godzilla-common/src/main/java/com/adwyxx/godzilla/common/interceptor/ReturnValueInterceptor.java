package com.adwyxx.godzilla.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义请求返回值处理 拦截器 {@link HandlerInterceptor}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/28 16:51
 */
@Component
public class ReturnValueInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ReturnValueInterceptor.class);
    /**
     * 请求执行前执行,Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }
    /**
     * 请求结束执行,Controller方法调用之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 视图渲染完成后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
