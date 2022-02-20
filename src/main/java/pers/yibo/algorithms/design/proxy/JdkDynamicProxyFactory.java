package pers.yibo.algorithms.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理工厂
 *
 * @author yibo
 * 2022/2/20 18:01
 */
public class JdkDynamicProxyFactory {

    /**
     * 获取代理实例
     *
     * @param service 代理服务对象
     * @return 代理实例
     */
    public static Object getProxyInstance(Object service) {
        return Proxy.newProxyInstance(
                // 类加载器，用于加载代理对象
                service.getClass().getClassLoader(),
                // 被代理类实现的一些接口
                service.getClass().getInterfaces(),
                // 实现了 InvocationHandler 接口的对象
                new InvocationHandler() {
                    /**
                     * 使用动态代理时实际调用的方法
                     * @param proxy 动态生成的代理类
                     * @param method  与代理类对象调用的方法相对应
                     * @param args 当前 method 方法的参数
                     * @return 代理方法返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK dynamic proxy method: " + method.getName() + " start.");
                        Object result = method.invoke(service, args);
                        System.out.println("JDK dynamic proxy method: " + method.getName() + " end.");
                        return result;
                    }
                }
        );
    }
}
