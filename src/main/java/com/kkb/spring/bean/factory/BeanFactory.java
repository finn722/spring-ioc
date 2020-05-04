package com.kkb.spring.bean.factory;

public interface BeanFactory {

	/**
	 * 根据bean的名称去ioc容器中获取实例
	 * 
	 * @param name
	 * @return
	 */
	Object getBean(String name);

	Object getBean(Class<?> type, String name);
}
