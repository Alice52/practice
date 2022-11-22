package top.hubby.pattern.singleton;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
public class LazySingleton {
    private static volatile LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

    /**
     * https://blog.csdn.net/qq_31748587/article/details/105498566<br>
     * 8 lock: synchronized 只会锁两样东西: 对象实例 || Class
     *
     * <pre>
     *      1. 一个对象里面如果有多个 synchronized 方法, 在某一时刻, 只要一个线程去调用其中的一个 synchronized 方法
     *          - 锁的是锁的是当前的对象
     *      2. 资源类内添加普通方法[非同步方法]， 普通方法与锁无关， 会直接执行
     *      3. 两个资源类, 两个同步方法则两个线程分别使用不同的资源类导致不是同一把锁, 所以彼此互不相关
     *      4. 两个静态同步方法, 无论资源个数, 都会按调用的顺序执行
     *          - [static 锁的是类加载器中的模板，即所有这个类的实例]
     *      5. 一个静态的锁方法, 一个普通锁方法, new一个对象调用
     *          - 锁的不是一个对象, 不会阻塞
     *      6. 普通的非静态方法使用xx.class, 无论资源个数, 同一时间只能有一个在执行
     * </pre>
     *
     * @return
     */
    public static synchronized LazySingleton getInstanceThreadSafe() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

    /**
     * volatile: 禁止指令重排
     *
     * <pre>
     *   1. new LazySingleton() 过程
     *     - 分配内存空间
     *     - 初始化对象
     *     - 设置 instance 指向刚分配的内存地址
     *   2. 由于指令重排的话可能是1-3-2, 此时返回的只是内存空间还没有初始化
     *   3. 在变量前加 volatile 禁止指令重排
     * </pre>
     *
     * @return
     */
    public static LazySingleton getInstanceThreadSafeV2() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }
}
