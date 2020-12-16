package cn.edu.ntu.common.api.exception;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

/**
 * @author zack <br>
 * @create 2020-10-11 11:51 <br>
 * @project project-ec <br>
 */
public class ListValidException extends BaseException {

  private Map<Integer, Set<ConstraintViolation<Object>>> errors;

  public ListValidException(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
    this.errors = errors;
  }

  public Map<Integer, Set<ConstraintViolation<Object>>> getErrors() {
    return errors;
  }

  public void setErrors(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
    this.errors = errors;
  }
}
