package top.hubby.coding.elseif.executor.rule;

/**
 * @author asd <br>
 * @create 2021-12-27 3:17 PM <br>
 * @project project-cloud-custom <br>
 */
@FunctionalInterface
public interface BaseRule<T> {
    boolean execute(T dto);
}
