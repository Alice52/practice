package top.hubby.practice.refactor.after.v5;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-24 3:21 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class IdGenerationFailureException extends Exception {
    public IdGenerationFailureException(String s) {
        super(s);
    }
}
