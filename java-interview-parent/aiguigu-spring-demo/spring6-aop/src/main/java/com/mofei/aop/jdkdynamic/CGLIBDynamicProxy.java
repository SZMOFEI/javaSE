package com.mofei.aop.jdkdynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * cglib 的动态代理实现
 *
 * @author mofei
 * @version 2020/9/9 14:46
 */
public class CGLIBDynamicProxy implements MethodInterceptor {
    private Object target;

    public CGLIBDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 对外提供的获取代理对象的方法
     *
     * @return 返回代理对象
     */
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        List<Object> params = Arrays.asList(args);
        System.out.println("... before calling method " + methodName + "... with args:" + params);
        Object result = null;
        try {
            result = method.invoke(target, args);
        } catch (Exception e) {
            System.out.println("...  calling method " + methodName + "... throw exception :" + e);
        }
        System.out.println("... after calling method " + methodName + "... with result = " + result);
        return result;
    }
}
