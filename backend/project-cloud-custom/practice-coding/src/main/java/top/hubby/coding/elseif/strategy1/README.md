1. 为了追踪JVM在运行过程中生成的JDK动态代理类, 我们可以设置VM启动参数如下:

    ```shell
    -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
    ```   

2. cglib所生成的代理类

    ```java
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "PATH");
    ```   

3. core thinking

    - map 构建时的 Key 的选择: HandlerType 注解的实现类[jdk生成的],

    ```java
    // 获取注解实现类  
    AnnotationUtils.findAnnotation(orderHandler.getClass(), HandlerType.class)
    // jdk will generate impl class, then set as Key
    @HandlerType(source = OrderConstants.TYPE_GROUP_ORDER, pay = OrderConstants.PAY_WECHAT)
    ```
    - 根据 Order 的属性构建 map 中的 Key: 注意重写 `hashCode and equals`
      


