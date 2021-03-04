package com.mofei.aop.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class JDKProxy implements InvocationHandler {
    private Object target;

    public JDKProxy(Object target) {
        this.target = target;
    }

    /**
     * 当代理对象调用invoke 方法的时候， 那么就会委派到真实对象的方法上面去执行。
     *
     * @param proxy  代理对象
     * @param method 方法
     * @param args   方法参数
     * @return 返回值
     * @throws Throwable 异常信息
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        List<Object> objects = Arrays.asList(args);
        System.out.println(" 执行 " + methodName + " 方法之前，参数是 " + objects);
        Object invoke = null;
        try {
            System.out.println(" 执行 " + methodName + " 方法");
            invoke = method.invoke(target, args);
        } catch (Exception e) {
            System.out.println("执行方法出现异常了");
            e.printStackTrace();
        }
        System.out.println(" 执行 " + methodName + " 方法之后，结果 result = " + invoke);
        return invoke;
    }

    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
