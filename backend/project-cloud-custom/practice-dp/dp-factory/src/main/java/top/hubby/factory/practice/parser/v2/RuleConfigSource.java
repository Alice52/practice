package top.hubby.factory.practice.parser.v2;

import lombok.SneakyThrows;
import top.hubby.factory.practice.parser.common.IRuleConfigParser;
import top.hubby.factory.practice.parser.common.RuleConfig;

/** 工厂类对象的创建逻辑又耦合进了 load() 函数中 */
@Deprecated
public class RuleConfigSource {
    @SneakyThrows
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParserFactory parserFactory = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new XmlRuleConfigParserFactory();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new PropertiesRuleConfigParserFactory();
        } else {
            throw new Exception("Rule config file format is not supp");
        }
        IRuleConfigParser parser = parserFactory.createParser();
        String configText = "";
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        return parser.parse(configText);
    }

    private String getFileExtension(String filePath) {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
