package com.kkb.spring.bean.registry;

/**
 * 访问单例Bean实例集合的接口
 * 
 * @author 灭霸詹
 *
 */
public interface SingletonBeanRegistry {

	Object getSingleton(String name);

	void addSingleton(String name, Object bean);
}
