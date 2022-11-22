package top.hubby.practice.metrics;

/*
 1. refactor diagram
     https://github.com/Alice52/digrams/blob/master/static/images/pattern/metrics.png
 2. 性能问题
     - 外部存储和采集可以异步执行
     - 当聚合大量数据时, 一次性加载太多的数据到内存会导致OOM的风险: 分治-聚合
*/
