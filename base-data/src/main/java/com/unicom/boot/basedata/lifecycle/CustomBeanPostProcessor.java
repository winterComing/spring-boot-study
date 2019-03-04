package com.unicom.boot.basedata.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 *  bean初始化第七步：BeanPostProcessor的postProcessBeforeInitialization()方法：
 *      如果某个IoC容器中增加的实现BeanPostProcessor接口的实现类Bean，那么在该容器中实例化Bean之后，
 *      执行初始化之前会调用BeanPostProcessor中的postProcessBeforeInitialization()方法执行预初始化处理。
 *
 *      定义之后，所有bean实例化之后，初始化之前均会执行，这里限制只对NormalBean 打印内容
 * @author dengh
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NormalBean) {
            System.out.println("bean实例化之后，初始化之前执行CustomBeanPostProcessor的postProcessBeforeInitialization方法：" + beanName);
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NormalBean){
            System.out.println("bean实例化之后，初始化之后执行CustomBeanPostProcessor的postProcessAfterInitialization方法：" + beanName);
        }
        return null;
    }
}
