package com.kkb.spring.bean.registry.support;

import java.util.HashMap;
import java.util.Map;

import com.kkb.spring.bean.registry.SingletonBeanRegistry;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	// 只有单例的bean才需要放入Map集合中进行保存管理
	private Map<String, Object> singletonObjects = new HashMap<String, Object>();

	@Override
	public Object getSingleton(String name) {
		return this.singletonObjects.get(name);
	}

	@Override
	public void addSingleton(String name, Object bean) {
		this.singletonObjects.put(name, bean);
	}

}
