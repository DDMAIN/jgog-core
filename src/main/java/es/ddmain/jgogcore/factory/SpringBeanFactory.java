package es.ddmain.jgogcore.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class SpringBeanFactory implements IFactory {

    private final ApplicationContext applicationContext;
    private final IFactory defaultFactory = CommandLine.defaultFactory();

    public SpringBeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <K> K create(Class<K> clazz) throws Exception {
        try {
            return applicationContext.getBean(clazz);
        } catch (NoSuchBeanDefinitionException e) {
            return defaultFactory.create(clazz);
        }
    }
}