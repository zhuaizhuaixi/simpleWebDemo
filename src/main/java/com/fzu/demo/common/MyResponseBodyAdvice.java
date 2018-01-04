package com.fzu.demo.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * 处理 返回值
 * @Description:TODO
 * @author lh
 * @time:2015-8-31 上午11:24:08
 */

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice{

    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter methodParameter,
                                  MediaType mediaType, Class clas, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        //ServletServerHttpRequest sshr=(ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        //HttpServletRequest request=   sshr.getServletRequest();

        // System.out.println(returnValue);
        //返回修改后的值
        return returnValue;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clas) {

        return true;
    }
}
