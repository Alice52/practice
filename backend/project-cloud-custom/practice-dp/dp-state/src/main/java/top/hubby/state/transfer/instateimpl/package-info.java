/**
 * 1. intros
 *
 * <pre>
 *     - Context: 持有某一种状态对象, 将行为委托给状态对象执行{context 有 open(), 但是会调用 state 执行}
 *     - LiftState: 抽象行为方法, 持有 context 对象(在具体的实现类中修改状态)
 *     - ClosingState | OpenningState | RunningState | StoppingState: 不同的状态实现
 *     - Client: 测试实例
 * </pre>
 *
 * 2. 状态流转控制
 *
 * <pre>
 *     - 此时的状态流转限制是在具体的实现类中控制的
 *     - 也可以在 client 中通过给 context 传递不同状态实现来做不同的事情: 这里没有使用
 * </pre>
 *
 * 3. 不同状态的行为可以完全不一样{像不同的类}
 *
 * <pre>
 *    - ClosingState 状态下可进行的操作完全不同: 也可以具有一些独立的行为
 * </pre>
 */
package top.hubby.state.transfer.instateimpl;
