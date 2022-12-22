package top.hubby.dp.proxy.dynamics.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂: 使用 Proxy 来创建代理对象
 *
 * @author zack <br>
 * @create 2022-12-22 16:10 <br>
 * @project practice-optimize <br>
 */
@Slf4j
public class ProxyFactory {
    private TrainStation station = new TrainStation();

    public SellTickets getProxyObject() {
        // 使用Proxy获取代理对象
        SellTickets sellTickets =
                (SellTickets)
                        // ClassLoader loader: 类加载, 用于加载代理类, 使用真实对象的类加载器即可
                        // Class<?>[] interfaces: 真实对象所实现的接口, 代理模式真实对象和代理对象实现相同的接口
                        // InvocationHandler h ： 代理对象的调用处理程序
                        Proxy.newProxyInstance(
                                station.getClass().getClassLoader(),
                                station.getClass().getInterfaces(),
                                new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object proxy, Method method, Object[] args)
                                            throws Throwable {
                                        log.info("do extension logic: jdk");
                                        // 执行真实对象
                                        return method.invoke(station, args);
                                    }
                                });
        return sellTickets;
    }
}
