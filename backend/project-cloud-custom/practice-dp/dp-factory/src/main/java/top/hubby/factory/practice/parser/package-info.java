/**
 * 根据配置文件的后缀(json、xml、yaml、properties), 选择不同的解析器(JsonRuleConfigParser、XmlRuleConfigParser……) <br>
 * <br>
 * v1: 简单工厂 best
 *
 * <pre>
 *   1. v0: 没有使用设计模式, 创建 parser 的逻辑与其他业务耦合在一起
 *   2. v1: 简单工厂, 将创建 parser 与其他业务拆分(职责单一|代码清晰), 并将 parser 缓存复用
 *   3. v2: 工厂方法, 将每个类型的 parser 创建延迟到具体的类(开闭原则|简单性), 使用时还是需要工厂的工厂(Map 映射关系)
 *   4. v3: 抽象工厂, 添加为 Rule 的其他辨识维度, 比如 System
 *       - 此时就可以按 System | Rule 划分成两个类: SystemConfigParserFactory | RuleConfigParserFactory
 *       - 或者按照 json/xml/yml 划分成4个类: JsonConfigParserFactory | YamlConfigParserFactory | XmlConfigParserFactory
 * </pre>
 */
package top.hubby.factory.practice.parser;
