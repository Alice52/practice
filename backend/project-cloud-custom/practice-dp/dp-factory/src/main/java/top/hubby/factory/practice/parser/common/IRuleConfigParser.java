package top.hubby.factory.practice.parser.common;

public interface IRuleConfigParser {
    default RuleConfig parse(String configText) {
        return new RuleConfig();
    }
}
