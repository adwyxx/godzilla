package com.adwyxx.godzilla.common.interceptor;

import com.adwyxx.godzilla.common.annotation.IgnoreAuthorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权认证处理过滤器
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/28 17:41
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);
    /**
     * 请求执行前执行,Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(token)){
            return false;
        }
        else{
            //验证Token是否有效
            IgnoreAuthorization annotation = ((HandlerMethod) o).getMethodAnnotation(IgnoreAuthorization.class);
            //如果没有忽略权限认证，则验证Token是否有效
            if(annotation==null){

            }
        }
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
