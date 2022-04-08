package top.hubby.grao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * @author asd <br>
 * @create 2021-11-12 3:19 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class AnnotationTests {

    private static String getAnnotationValue(MyAnnotation annotation) {
        if (annotation == null) {
            return "";
        }
        return annotation.value();
    }

    @Test
    public void wrong() throws NoSuchMethodException {
        Parent parent = new Parent();
        log.info(
                "ParentClass:{}",
                getAnnotationValue(parent.getClass().getAnnotation(MyAnnotation.class)));
        log.info(
                "ParentMethod:{}",
                getAnnotationValue(
                        parent.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));

        Child child = new Child();
        log.info(
                "ChildClass:{}",
                getAnnotationValue(child.getClass().getAnnotation(MyAnnotation.class)));
        log.info(
                "ChildMethod:{}",
                getAnnotationValue(
                        child.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));
    }

    @Test
    public void right() throws NoSuchMethodException {
        Parent parent = new Parent();
        log.info(
                "ParentClass:{}",
                getAnnotationValue(parent.getClass().getAnnotation(MyAnnotation.class)));
        log.info(
                "ParentMethod:{}",
                getAnnotationValue(
                        parent.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));

        Child child = new Child();
        log.info(
                "ChildClass:{}",
                getAnnotationValue(
                        AnnotatedElementUtils.findMergedAnnotation(
                                child.getClass(), MyAnnotation.class)));
        log.info(
                "ChildMethod:{}",
                getAnnotationValue(
                        AnnotatedElementUtils.findMergedAnnotation(
                                child.getClass().getMethod("foo"), MyAnnotation.class)));
    }

    @MyAnnotation(value = "Class")
    @Slf4j
    static class Parent {

        @MyAnnotation(value = "Method")
        public void foo() {}
    }

    @Slf4j
    static class Child extends Parent {
        @Override
        public void foo() {}
    }
}
