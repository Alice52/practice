package top.hubby.factory.practice.parser.v3;

import top.hubby.factory.practice.parser.common.IRuleConfigParser;
import top.hubby.factory.practice.parser.common.JsonRuleConfigParser;
import top.hubby.factory.practice.parser.common.XmlRuleConfigParser;
import top.hubby.factory.practice.parser.v3.parser.ISystemConfigParser;
import top.hubby.factory.practice.parser.v3.parser.JsonSystemConfigParser;
import top.hubby.factory.practice.parser.v3.parser.XmlSystemConfigParser;

public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();
}

class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}

class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
