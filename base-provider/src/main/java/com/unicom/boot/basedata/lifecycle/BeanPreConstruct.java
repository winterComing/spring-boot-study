package com.unicom.boot.basedata.lifecycle;

import javax.annotation.PostConstruct;

/**
 *  @PostConstruct和@PreDestroy是仅对servlet的生命周期生效，生效条件待完善 ？？
 *
 * @author dengh
 */
public class BeanPreConstruct {

    public BeanPreConstruct() {
        System.out.println("执行构造函数");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("执行post-construct");
    }

    public void init(){
        System.out.println("执行init-method");
    }
}
