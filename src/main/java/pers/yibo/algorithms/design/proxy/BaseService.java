package pers.yibo.algorithms.design.proxy;

/**
 * 基础服务接口
 *
 * @author yibo
 * 2022/2/20 17:52
 */
public interface BaseService {
    /**
     * 接受信息
     *
     * @return 返回消息内容
     */
    String receive();

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    void send(String message);
}
