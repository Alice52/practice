package custom.gateway.constants;

import common.core.constant.SecurityConstants;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
public interface CacheKeys {
    String CAPTCHA_CODE_KEY_PREFIX = SecurityConstants.PROJECT_PREFIX + ":admin_captcha:key:";
}
