package com.kyan.gitlabdingtalk.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SpringContext Util Class
 *
 * @author kyan
 * @date 2019/4/25
 */
@Component
public class SpringContextUtil implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringContextUtil.beanFactory = configurableListableBeanFactory;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        T result = beanFactory.getBean(clz);
        return result;
    }

    public static <T> List<T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type).entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }

    /**
     * 获取所有已特定注解命名的Bean
     *
     * @param annotationType
     * @return
     */
    public static List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(annotationType);

        // java 8 的写法，将 map 的 value 收集起来到一个 list 中
        return beansWithAnnotation.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());

//        // java 7
//        List<Object> result = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
//            result.add(entry.getValue());
//        }
//        return result;
    }
}
