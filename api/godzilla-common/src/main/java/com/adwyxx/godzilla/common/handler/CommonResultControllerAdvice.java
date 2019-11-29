package com.adwyxx.godzilla.common.handler;

import com.adwyxx.godzilla.common.model.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 通过{@link RestControllerAdvice} 注解实现 {@link ResponseBodyAdvice}接口。
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/28 16:05
 */
@RestControllerAdvice
public class CommonResultControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseEntity){
            return body;
        } else if(body == null){
            return ResponseEntity.success();
        }
        return  ResponseEntity.success(body);
    }
    //验证返回值类型
    private boolean filter(MethodParameter returnType){
        if(returnType.getParameterType().equals(ResponseEntity.class))
        {
            return true;
        }
        return false;
    }
}
