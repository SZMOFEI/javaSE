package com.mofei.aop.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * JDK动态代理的方式实现日志记录
 *
 * @author
 * @version 2020/9/9 14:36
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    /**
     * 对外提供生成代理对象的方法
     *
     * @param target 目标对象
     * @return 返回代理对象
     */
    public Object getProxy(Object target) {
        this.target = target;
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        List<Object> params = Arrays.asList(args);
        System.out.println("... before calling method " + methodName + "... with args:" + params);
        Object result = null;
        try {
            result = method.invoke(target, args);
        } catch (Exception e) {
            System.out.println("...  calling method " + methodName + "... throw exception :" + e);
        }
        System.out.println("... after calling method " + methodName + "... with result = " +result);
        return result;
    }
}
