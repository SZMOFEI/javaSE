package com.mofei.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CGLIBProxy implements MethodInterceptor {
    private Object target;

    public CGLIBProxy(Object target) {
        this.target = target;
    }

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        //代理对象设置真实的委派对象
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);

        Object proxyObj = enhancer.create();
        return proxyObj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        List<Object> list = Arrays.asList(objects);
        System.out.println(" 执行 " + methodName + " 方法之前，参数是 " + list);
        Object invoke = null;
        try {
            invoke = method.invoke(target, objects);
        } catch (Exception e) {
            System.out.println("执行" + methodName + "方法出现异常: " + e);
        }
        System.out.println(" 执行 " + methodName + " 方法之后，结果 result = " + invoke);

        return invoke;
    }
}
