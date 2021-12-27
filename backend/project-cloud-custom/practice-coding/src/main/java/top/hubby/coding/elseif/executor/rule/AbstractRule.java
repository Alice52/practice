package top.hubby.coding.elseif.executor.rule;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-27 3:17 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public abstract class AbstractRule<T> implements BaseRule<T> {
    protected <T> T convert(T dto) {
        return (T) dto;
    }

    @Override
    public boolean execute(T dto) {
        return executeRule(convert(dto));
    }

    protected boolean executeRule(T t) {
        return true;
    }
}
