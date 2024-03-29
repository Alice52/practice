package top.hubby.factory.practice.di.parser;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeanDefinition {
    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;
    // 省略必要的getter/setter/constructors
    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }

    public enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    @Data
    public static class ConstructorArg {
        private Boolean isRef;
        private Class type;
        private Object arg;
        // 省略必要的getter/setter/constructors
    }
}
