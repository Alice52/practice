/**
 * 1. intros
 *
 * <pre>
 *     - JdLogisticsContext: 持有某一种状态对象, 将行为委托给状态对象执行{context 有 doAction(), 但是会调用 state 执行}
 *     - LogisticsState: 抽象行为方法
 *     - LogisticsStateImpl1 | LogisticsStateImpl2: 不同的状态实现, 该状态可进行的所有操作内聚到一起
 *     - StateClient: 测试实例
 * </pre>
 *
 * 2. 状态流转控制
 *
 * <pre>
 *     - 此时的状态流转限制是在具体的实现类中控制的
 *     - 也可以在 client 中通过给 context 传递不同状态实现来做不同的事情
 * </pre>
 *
 * 3. 不同状态的行为可以完全不一样{像不同的类}
 *
 * <pre>
 *    - LogisticsStateImpl1 状态下可进行的操作完全不同: 也可以具有一些独立的行为
 * </pre>
 *
 * 4. 和 strategy 有些类似但不同: 都是根据条件做某些任务(if/else/switch)
 *
 * <pre>
 *     - strategy: 可替换的一组实现 + 相同任务 + context 持有条件和实现的映射关系
 *     - state: 对象内部的状态流转(带来的行为不同) + 可以是不同的任务 + 状态流转控制 + context 没有持有映射关系
 *     - 没有 state 流转的情况下, state 就是简易版 strategy: 条件就是 status + 没有映射关系
 * </pre>
 */
package top.hubby.state.transfer.incontext;
