package pers.yibo.algorithms.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理工厂
 *
 * @author yibo
 * 2022/2/20 18:14
 */
public class CglibDynamicProxyFactory {
    public static Object getProxy(Class<?> clazz) {
        // 动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 类加载器配置
        enhancer.setClassLoader(clazz.getClassLoader());
        // 被代理类
        enhancer.setSuperclass(clazz);
        // 调用代理方法
        enhancer.setCallback(new MethodInterceptor() {
            /**
             *
             * @param o 代理对象
             * @param method 被拦截的方法（需要增强的方法）
             * @param args 方法传入参数
             * @param methodProxy 用于调用原始方法
             * @return 方法返回结果
             */
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Cglib dynamic proxy method: " + method.getName() + " start.");
                Object result = methodProxy.invokeSuper(o, args);
                System.out.println("Cglib dynamic proxy method: " + method.getName() + " end.");
                return result;
            }
        });
        return enhancer.create();
    }
}
