package com.unicom.boot.basedata.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * bean初始化第一步：执行BeanFactoryPostProcessor的postProcessorBeanFactory()方法
 *      ?? 为什么除NormalBean之外的bean没执行，是只执行一次吗 ??
 * @author dengh
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("执行自定义:beanFactoryPostProcessor的postProcessBeanFactory方法");
    }
}
