package top.hubby.dp.adapter.practice;

/**
 * @author zack <br>
 * @create 2022-12-22 23:25 <br>
 * @project practice-optimize <br>
 */
public interface IA {
    void fa();
}

class A implements IA {
    @Override
    public void fa() {}
}

class Demo {
    private IA a;

    public Demo(IA a) {
        this.a = a;
    }

    Demo d = new Demo(new A());
}

/******************* after *****************/

interface IB {
    void fb();
}

class B {
    void fb() {}
}

class BAdaptor implements IA {
    private B b;

    public BAdaptor(B b) {
        this.b = b;
    }

    @Override
    public void fa() {
        // ...
        b.fb();
    }

    // 借助BAdaptor，Demo的代码中，调用IA接口的地方都无需改动，
    // 只需要将BAdaptor如下注入到Demo即可。
    Demo d = new Demo(new BAdaptor(new B()));
}
