package pers.yibo.algorithms.design.proxy;

/**
 * 代理测试方法
 * <p>
 * JDK 动态代理和 CGLIB 动态代理对比
 * <p>
 * JDK 动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。
 * <p>
 * 另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。
 * <p>
 * 就二者的效率来说，大部分情况都是 JDK 动态代理更优秀，随着 JDK 版本的升级，这个优势更加明显。
 * <p>
 * <p>
 * 静态代理和动态代理的对比
 * <p>
 * 灵活性 ：动态代理更加灵活，不需要必须实现接口，可以直接代理实现类，并且可以不需要针对每个目标类都创建一个代理类。
 * 另外，静态代理中，接口一旦新增加方法，目标对象和代理对象都要进行修改，这是非常麻烦的！
 * <p>
 * JVM 层面 ：静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。
 * 而动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。
 *
 * @author yibo
 * 2022/2/20 17:59
 */
public class Test {
    public static void main(String[] args) {
        // 服务
        BaseService service = new BaseServiceImpl();
        /*
        静态代理实现
         */
        System.out.println("-----------------Static Proxy-------------------");
        StaticBaseProxy staticBaseProxy = new StaticBaseProxy(service);
        staticBaseProxy.send("Send message by static proxy.");
        System.out.println(staticBaseProxy.receive());

        /*
        JDK 动态代理
         */
        System.out.println("---------------JDK Dynamic Proxy-----------------");
        BaseService jdkBaseService = (BaseService) JdkDynamicProxyFactory.getProxyInstance(service);
        jdkBaseService.send("Send message by jdk dynamic proxy.");
        System.out.println(jdkBaseService.receive());

        /*
        Cglib 动态代理
         */
        System.out.println("--------------Cglib Dynamic Proxy----------------");
        BaseService cglibBaseService = (BaseService) CglibDynamicProxyFactory.getProxy(service.getClass());
        cglibBaseService.send("Send message by cglib dynamic proxy.");
        System.out.println(cglibBaseService.receive());
    }
}
