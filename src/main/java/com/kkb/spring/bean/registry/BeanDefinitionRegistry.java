package com.kkb.spring.bean.registry;

import com.kkb.springframework.beandefinition.BeanDefinition;

/**
 * 提供对于BeanDefinition集合的访问
 * 
 * @author 灭霸詹
 *
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String name);

	void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
