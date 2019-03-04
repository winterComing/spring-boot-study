package com.unicom.boot.basedata.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;


/**
 * 初始化bean：
 * 第二步：执行bean的构造函数
 * 第三步：执行bean的属性注入
 * 第四步：执行BeanNameAware的setBeanName()方法：
 *      如果某个Bean实现了BeanNameAware接口，那么Spring将会将Bean实例的ID传递给setBeanName()方法，
 *      这样是该bean能获取到其beanName的值；
 * 第五步：BeanFactoryAware的setBeanFactory()方法：
 *      如果Bean实现了BeanFactoryAware接口，那么Spring将会将创建Bean的BeanFactory传递给setBeanFactory()方法，
 *      这样是该bean能获取到其beanFactory的值
 * 第六步：ApplicationContextAware的setApplicationContext()方法：
 *      如果Bean实现了ApplicationContextAware接口，那么Spring将会将该Bean所在的上下文环境ApplicationContext传递给setApplicationContext()方法，
 *      这样是该bean能获取到其ApplicationContext的值
 * 第八步：InitializingBean的afterPropertiesSet()方法：
 *      如果Bean实现了InitializingBean接口，那么Bean在实例化完成后将会执行接口中的afterPropertiesSet()方法来进行初始化。
 * 第九步：自定义的inti-method指定的方法：
 *      如果配置文件中使用init-method属性指定了初始化方法，那么Bean在实例化完成后将会调用该属性指定的初始化方法进行Bean的初始化。
 * 第十步：BeanPostProcessor的postProcessAfterInitialization()方法：
 *      如果某个IoC容器中增加的实现BeanPostProcessor接口的实现类Bean，
 *      那么在该容器中实例化Bean之后并且完成初始化调用后执行该接口中的postProcessorAfterInitialization()方法进行初始化后处理。
 *
 * 第十一步：
 *      此时有关Bean的所有准备工作均已完成，Bean可以被程序使用了，它们将会一直驻留在应用上下文中，直到该上下文环境被销毁。
 *
 * 第十二步：DisposableBean的destory()方法：
 *      如果Bean实现了DisposableBean接口，Spring将会在Bean实例销毁之前调用该接口的destory()方法，来完成一些销毁之前的处理工作。
 * 第十三步：自定义的destory-method指定的方法：
 *      如果在配置文件中使用destory-method指定了销毁方法，那么在Bean实例销毁之前会调用该指定的方法完成一些销毁之前的处理工作。
 *
 * 注意： @PostConstruct和@PreDestroy是仅对servlet的生命周期生效，生效条件待完善 ？？
 *
 * @author dengh
 */
//@Component
public class NormalBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        InitializingBean{
    public NormalBean() {
        System.out.println("执行NormalBean的构造");
    }

    private InjectBean injectBean;

    @Autowired
    public void setInjectBean(InjectBean injectBean) {
        System.out.println("执行normalBean的setter方法");
        this.injectBean = injectBean;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("执行BeanNameAware的setBeanName方法：" + s );
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行BeanFactoryAware的setBeanFactory方法：" + beanFactory);
        System.out.println("当前beanFactory容器是否包含InjectBean：" + beanFactory.containsBean("injectBean"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行ApplicationContextAware的setApplicationContext方法：" + applicationContext);
        System.out.println("当前ApplicationContext容器是否包含InjectBean：" + applicationContext.containsBean("injectBean"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean接口的afterPropertiesSet来初始化bean：NormalBean");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("执行NormalBean的post-contruct方法");
    }
}
