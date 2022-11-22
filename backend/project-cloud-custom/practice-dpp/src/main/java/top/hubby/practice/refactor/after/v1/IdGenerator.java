package top.hubby.practice.refactor.after.v1;

import top.hubby.practice.refactor.after.v5.IdGenerationFailureException;

/**
 * @author asd <br>
 * @create 2021-12-24 12:01 PM <br>
 * @project pattern <br>
 */
public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
