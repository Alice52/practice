package top.hubby.dp.adapter.practice;

/**
 * 依赖的外部 CD 系统在接口设计方面有缺陷, 为了隔离设计上的缺陷, <br>
 * 我们希望对外部系统提供的接口进行二次封装 ITarget + CDAdaptor
 *
 * @author zack <br>
 * @create 2022-12-22 23:12 <br>
 * @project practice-optimize <br>
 */
public class CD { // 这个类来自外部sdk，我们无权修改它的代码
    // ...
    public static void staticFunction1() { // ...
    }

    public void uglyNamingFunction2() { // ...
    }

    public void tooManyParamsFunction3(int paramA, int paramB) { // ...
    }

    public void lowPerformanceFunction4() { // ...
    }
}

interface ITarget {
    void function1();

    void function2();

    void fucntion3(Object paramsWrapper);

    void function4();
}

class CDAdaptor extends CD implements ITarget {

    @Override
    public void function1() {
        CD.staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void fucntion3(Object paramsWrapper) {
        super.tooManyParamsFunction3(1, 2);
    }

    @Override
    public void function4() {
        // ...reimplement it...
    }
}
