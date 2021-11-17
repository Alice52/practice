package top.hubby.grao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-11-12 4:07 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class InnerClassTests {

    private String gender = "male";

    public static void main(String[] args) throws Exception {

        InnerClassTests application = new InnerClassTests();
        application.test();
    }

    private void test() {
        MyInnerClass myInnerClass = new MyInnerClass();
        log.info("{}", myInnerClass.name);
        myInnerClass.test();
    }

    class MyInnerClass {
        private String name = "zhuye";

        void test() {
            log.info("{}", gender);
        }
    }
}
