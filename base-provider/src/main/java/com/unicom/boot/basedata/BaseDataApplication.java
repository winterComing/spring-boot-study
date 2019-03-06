package com.unicom.boot.basedata;

import com.unicom.boot.basedata.lifecycle.ApplicationContextBean;
import com.unicom.boot.basedata.lifecycle.BeanPreConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author dengh
 */
@SpringBootApplication
public class BaseDataApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(BaseDataApplication.class, args);
		System.out.println(run.getBean("factoryBeanExp"));
		ApplicationContextBean bean = (ApplicationContextBean) run.getBean("appCtxBean");
		System.out.println(bean.getApplicationContext());
		System.out.println(run);
	}

	@Bean(initMethod = "init")
	public BeanPreConstruct beanLifeCycle(){
		return new BeanPreConstruct();
	}

}

