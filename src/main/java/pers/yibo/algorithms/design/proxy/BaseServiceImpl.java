package pers.yibo.algorithms.design.proxy;

/**
 * 基础服务接口实现
 *
 * @author yibo
 * 2022/2/20 17:54
 */
public class BaseServiceImpl implements BaseService {

    @Override
    public String receive() {
        return "Received message.";
    }

    @Override
    public void send(String message) {
        System.out.println("Send message.");
    }
}
