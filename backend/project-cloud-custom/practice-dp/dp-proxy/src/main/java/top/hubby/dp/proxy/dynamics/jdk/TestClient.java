package top.hubby.dp.proxy.dynamics.jdk;

import top.hubby.dp.proxy.statics.ProxyPoint;

/**
 * @author zack <br/>
 * @create 2022-12-22 16:05 <br/>
 * @project practice-optimize <br/>
 */
public class TestClient {

    public static void main(String[] args) {
        //获取代理对象
        ProxyFactory factory = new ProxyFactory();
        SellTickets proxyObject = factory.getProxyObject();
        proxyObject.sell();
    }
}
