package com.adwyxx.godzilla.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用RESTfull Api返回值对象
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/21 14:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity implements Serializable {
    private static final long serialVersionUID = -1547725611438769211L;
    //HTTP 请求结果状态码
    private int code;
    //API处理结果状态
    private ResponseStatus stauts;
    //请求返回数据
    private Object data;
    //请求结果消息，异常或非异常时日志
    private String message;

    /**
     * Http请求成功
     * @author: Leo.Wang, adwyxx@qq.com
     * @param data:返回请求结果数据
     * @return {@link ResponseEntity} : 通用RESTfull Api返回值对象
     */
    public static ResponseEntity success(Object data){
        ResponseEntity result =  new ResponseEntity();
        result.setData(data);
        result.setStauts(ResponseStatus.SUCCESS);
        result.setCode(200);
        return result;
    }

    /**
     * Http 请求失败
     * @author: Leo.Wang, adwyxx@qq.com
     * @param stateCode:Http 请求结果状态码
     * @param message:异常信息
     * @return {@link ResponseEntity} : 通用RESTfull Api返回值对象
     */
    public static ResponseEntity fail(int stateCode,String message){
        ResponseEntity result =  new ResponseEntity();
        result.setMessage(message);
        result.setStauts(ResponseStatus.FAIL);
        result.setCode(stateCode);
        return result;
    }
}
