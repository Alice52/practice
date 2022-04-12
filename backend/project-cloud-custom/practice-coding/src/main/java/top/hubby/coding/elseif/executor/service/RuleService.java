package top.hubby.coding.elseif.executor.service;

import lombok.extern.slf4j.Slf4j;
import top.hubby.coding.elseif.executor.rule.BaseRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-27 3:19 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
/**
 * @Builder
 */
public class RuleService<T> {
    private static final int AND = 1;
    private static final int OR = 0;
    private final Map<Integer, List<BaseRule<T>>> hashMap = new HashMap<>();

    public static <T> RuleService<T> create() {
        return new RuleService<>();
    }

    public RuleService<T> and() {
        return this;
    }

    public RuleService<T> and(BaseRule<T> rule) {
        hashMap.computeIfAbsent(AND, x -> new ArrayList<>()).add(rule);

        return this;
    }

    public RuleService<T> or() {
        return this;
    }

    public RuleService<T> or(BaseRule<T> rule) {
        hashMap.computeIfAbsent(OR, ArrayList::new).add(rule);
        return this;
    }

    @Deprecated
    public RuleService<T> and(List<BaseRule<T>> ruleList) {
        hashMap.put(AND, ruleList);
        return this;
    }

    @Deprecated
    public RuleService<T> or(List<BaseRule<T>> ruleList) {
        hashMap.put(OR, ruleList);
        return this;
    }

    public boolean execute(T dto) {
        for (Map.Entry<Integer, List<BaseRule<T>>> item : hashMap.entrySet()) {
            List<BaseRule<T>> ruleList = item.getValue();
            switch (item.getKey()) {
                case AND:
                    if (!and(dto, ruleList)) {
                        return false;
                    }
                    break;
                case OR:
                    if (!or(dto, ruleList)) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private boolean and(T dto, List<BaseRule<T>> ruleList) {
        for (BaseRule<T> rule : ruleList) {
            if (!rule.execute(dto)) {
                return false;
            }
        }

        return true;
    }

    private boolean or(T dto, List<BaseRule<T>> ruleList) {
        for (BaseRule<T> rule : ruleList) {
            if (rule.execute(dto)) {
                return true;
            }
        }

        return false;
    }
}
