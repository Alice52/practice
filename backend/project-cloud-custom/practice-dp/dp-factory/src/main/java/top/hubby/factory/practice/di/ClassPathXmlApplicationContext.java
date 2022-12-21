package top.hubby.factory.practice.di;

import top.hubby.factory.practice.di.factory.BeansFactory;
import top.hubby.factory.practice.di.parser.BeanConfigParser;
import top.hubby.factory.practice.di.parser.BeanDefinition;
import top.hubby.factory.practice.di.parser.XmlBeanConfigParser;

import java.io.InputStream;
import java.util.List;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private BeansFactory beansFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        in = this.getClass().getResourceAsStream("/" + configLocation);
        if (in == null) {
            throw new RuntimeException("Can not find config file: " + configLocation);
        }
        List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
        beansFactory.addBeanDefinitions(beanDefinitions);
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
