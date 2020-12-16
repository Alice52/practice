package cn.edu.ntu.common.api.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Service
public class UnifiedMessageSource {

  @Resource private MessageSource messageSource;

  public String getMessage(String code) {

    return getMessage(code, null);
  }

  public String getMessage(String code, Object[] args) {

    return getMessage(code, args, "");
  }

  public String getMessage(String code, Object[] args, String defaultMessage) {
    Locale locale = LocaleContextHolder.getLocale();

    return messageSource.getMessage(code, args, defaultMessage, locale);
  }
}
