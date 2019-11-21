package com.adwyxx.godzilla.common.handler;

import com.adwyxx.godzilla.common.model.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Response 返回结果包装处理 {@link HandlerMethodReturnValueHandler}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/21 17:13
 */
public class ResultWarpReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;
    public ResultWarpReturnValueHandler(HandlerMethodReturnValueHandler delegate){
        this.delegate = delegate;
    }

    //表示是否支持该返回类型
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    //处理返回值
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        ResponseEntity result = null;
        if(returnValue instanceof ResponseEntity){
            result = (ResponseEntity)returnValue;
        }
        else{
            result = ResponseEntity.success(returnValue);
        }
        this.delegate.handleReturnValue(result,returnType,mavContainer,webRequest);
    }
}
