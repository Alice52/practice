## core thinking

1. code
    
    ```java
    private Map<String, Function<?, ?>> checkResultDispatcher = new HashMap<>();
    ```

2. 可以通过定制 Key 的规则实现业务逻辑的分发
3. 每一个 `Function<>` 就相当于一个之前的侧略类 
