### 项目目录说明
```
main
   +-java
   |     +-com.adwyxx.godilla.common
   |         +-annotation
   |         +-config
   |         +-handller
   |         +-interceptor
   |         +-lock
   |         +-model
   |         +-util
   +-resources
```
### 一、统一返回值对象设置
#### 1. 定义统一返回值对象
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = -1547725611438769211L;
    //HTTP 请求结果状态码
    private int code;
    //API处理结果状态
    private ResponseStatus stauts;
    //请求返回数据
    private T data;
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

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
``` 
#### 2. 设置Response返回值拦截
- 通过Filter设置统一返回值类型
- 使用@RestControllerAdvice (或@ControllerAdvice)设置统一返回值类型
https://segmentfault.com/a/1190000020517960?utm_source=tag-newest
- 实现HandlerMethodReturnValueHandler接口的方式设置统一返回值类型

<img src="https://ask.qcloudimg.com/http-save/2308184/3tams6kino.png?imageView2/2/w/1620"/>

### 二、统一异常处理
参考：https://zhuanlan.zhihu.com/p/38196945