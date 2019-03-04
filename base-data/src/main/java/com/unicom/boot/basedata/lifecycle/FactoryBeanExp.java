package com.unicom.boot.basedata.lifecycle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author dengh
 */
@Component
public class FactoryBeanExp implements FactoryBean{

    @Override
    public Object getObject() throws Exception {
        return new String("hello factoryBean");
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
