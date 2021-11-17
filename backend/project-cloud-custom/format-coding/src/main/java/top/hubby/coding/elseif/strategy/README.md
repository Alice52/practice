## 策略模式

1. 本质: `接口 + 实现类 + 逻辑分派[context]`
2. 去除了 `if-else`, 不同的逻辑有不同的处理类, 彼此解耦且可以任意替换
3. 策略类会很多, 且分派逻辑散落, 无法俯视整个业务逻辑
   - 可以使用 `Map + Functional` 来进行一定的优化

## thinking

1. 自定义一个注解, 用于表示不同的 handler
2. 以注解的指定值 `value()` 为 key, 对应的 handler 为 value 构建一个 map, 并放入 ioc
   - 获取所有被指定直接标注的 handler
   - 可以在 `BeanFactoryPostProcessor` 中实现: `这里只能放入 Class, 不能具体到指定类`
   - 或者在 `xxHandlerContext` 的构造函数构建: `可以放入指定的精确的类`
3. 并在策略上下文中提供根据 `指定值的 value()` 获取 handler 的方法
   - Class 就要根据 Class 去 IOC 中获取对应的 xxHandler
   - 具体类就可以直接使用
4. 调用策略上下文的方法, 之后执行 handler 中的方法

## optimize

1. 上面的模式有一个小问题: `指定的注解 value() 是一个值, 不能过于定制化`
2. 所以可以自己实现注解: `注解的本质是接口`
3. 所以构建 map 时, 以注解的实现类作为 `key`, 不同的 handler 作为 value
   - 获取所有被指定直接标注的 handler
4. 并在策略上下文中提供根据 `指定值的 value()` 获取 handler 的方法需要重写
   - new 一个新的注解实现类, 并传入判断标准: 注意重写 `hashCode() and equals()`
   - 由此获取对应的 xxHandler
