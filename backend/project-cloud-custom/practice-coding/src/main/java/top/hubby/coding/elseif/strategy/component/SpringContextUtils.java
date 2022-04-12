package top.hubby.coding.elseif.strategy.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static <T> T getBean(String beanName) {
        if (applicationContext.containsBean(beanName)) {
            return (T) applicationContext.getBean(beanName);
        } else {
            return null;
        }
    }

    /**
     * This method will throw {@link
     * org.springframework.beans.factory.NoSuchBeanDefinitionException}
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> T getBean(Class<T> clazz) {

        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> baseType) {
        return applicationContext.getBeansOfType(baseType);
    }

    public static <T> T getBeansOfTypeOfTop1(Class<T> baseType) {
        Map<String, T> beansOfType = getBeansOfType(baseType);

        if (beansOfType.size() > 0) {
            String key = beansOfType.keySet().stream().findFirst().get();

            return beansOfType.getOrDefault(key, null);
        }

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }
}
