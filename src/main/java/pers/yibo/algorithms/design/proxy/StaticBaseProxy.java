package pers.yibo.algorithms.design.proxy;

/**
 * 静态代理
 *
 * @author yibo
 * 2022/2/20 17:55
 */
public class StaticBaseProxy implements BaseService {

    private final BaseService baseService;

    public StaticBaseProxy(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public String receive() {
        System.out.println("Static proxy method: receive.");
        return baseService.receive();
    }

    @Override
    public void send(String message) {
        System.out.println("Static proxy method: send.");
        baseService.send(message);
    }
}
