package com.kkb.spring.bean.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.kkb.spring.bean.registry.BeanDefinitionRegistry;
import com.kkb.springframework.beandefinition.BeanDefinition;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

	// 封装xml中的bean标签表示的信息
	private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	@Override
	public BeanDefinition getBeanDefinition(String name) {
		return this.beanDefinitions.get(name);
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		this.beanDefinitions.put(name, beanDefinition);
	}

}
