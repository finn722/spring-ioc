package com.kkb.spring.bean.factory.support;

import com.kkb.spring.bean.factory.BeanFactory;
import com.kkb.spring.bean.registry.support.DefaultSingletonBeanRegistry;
import com.kkb.springframework.beandefinition.BeanDefinition;

/**
 * 一个接口中同名方法设计重载时，更多的是为了更方便的给使用者使用
 * 
 * @author 灭霸詹
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

	@Override
	public Object getBean(String name) {
		Object bean = doGetBean(name, null);
		return bean;
	}

	@Override
	public Object getBean(Class<?> type, String name) {
		Object bean = doGetBean(name, type);
		return bean;
	}

	private Object doGetBean(String name, Class<?> type) {
		// 先查询单例bean集合是否存在该bean实例
		Object singleton = getSingleton(name);
		// 有则直接返回
		if (singleton != null) {
			return singleton;
		}
		// 没有则需要查询BeanDefinition集合中是否存在该BeanDefinition
		BeanDefinition beanDefinition = getBeanDefinition(name);
		// 没有BeanDefinition则返回null
		if (beanDefinition == null) {
			return null;
		}
		// 有BeanDefinition则判断是单例还是多例
		if (beanDefinition.isSingleton()) {
			singleton = createBean(beanDefinition);

			addSingleton(name, singleton);
		} else if (beanDefinition.isPrototype()) {
			singleton = createBean(beanDefinition);
		}
		return singleton;
	}

	public abstract Object createBean(BeanDefinition beanDefinition);

	public abstract BeanDefinition getBeanDefinition(String name);
}
