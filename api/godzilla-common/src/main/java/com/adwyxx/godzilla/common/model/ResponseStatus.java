package com.adwyxx.godzilla.common.model;

/**
 * 请求结果状态
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/21 14:29
 */
public enum ResponseStatus {
    SUCCESS("成功"),
    FAIL("失败");

    private String message;
    private ResponseStatus(String msg){
        this.message = msg;
    }

    /**
     * 获取枚举描述信息
     * @author: Leo.Wang, adwyxx@qq.com
     * @param:
     */
    public static String getMessage(String name){
        for (ResponseStatus item : ResponseStatus.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return null;
    }
}
