package top.hubby.factory.practice.parser.v1;

import lombok.SneakyThrows;
import top.hubby.factory.practice.parser.common.IRuleConfigParser;
import top.hubby.factory.practice.parser.common.RuleConfig;

public class RuleConfigSource {
    @SneakyThrows
    public RuleConfig load(String ruleConfigFilePath) {
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFilePath);
        if (parser == null) {
            throw new Exception("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        String configText = "";
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        return parser.parse(configText);
    }
}
