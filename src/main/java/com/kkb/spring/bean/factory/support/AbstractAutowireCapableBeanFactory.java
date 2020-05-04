package com.kkb.spring.bean.factory.support;

import java.util.List;

import com.kkb.spring.bean.factory.AutowireCapableBeanFactory;
import com.kkb.springframework.beandefinition.BeanDefinition;
import com.kkb.springframework.beandefinition.PropertyValue;
import com.kkb.springframework.beandefinition.RuntimeBeanReference;
import com.kkb.springframework.beandefinition.TypedStringValue;
import com.kkb.utils.ReflectUtils;

/**
 * 该工厂是负责Bean的创建和装配的
 * 
 * @author 灭霸詹
 *
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
		implements AutowireCapableBeanFactory {

	@Override
	public Object createBean(BeanDefinition beanDefinition) {
		// 实例化 new
		Object bean = createInstance(beanDefinition);
		if (bean == null)
			return null;
		// 属性填充 set
		populateBean(bean, beanDefinition);
		// 初始化 init
		initBean(bean, beanDefinition);
		return bean;
	}

	private void initBean(Object bean, BeanDefinition beanDefinition) {
		// TODO Aware接口会在此时被处理

		invokeInitMethod(bean, beanDefinition);
	}

	private void invokeInitMethod(Object bean, BeanDefinition beanDefinition) {
		// bean标签配置了init-method属性
		String initMethod = beanDefinition.getInitMethod();
		if (initMethod == null || initMethod.equals("")) {
			return;
		}
		ReflectUtils.invokeMethod(bean, initMethod);
		// TODO bean标签实现了InitializingBean接口
	}

	private void populateBean(Object bean, BeanDefinition beanDefinition) {
		List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
		for (PropertyValue pv : propertyValues) {
			String name = pv.getName();
			Object value = pv.getValue();
			Object valueToUse = null;
			if (value instanceof TypedStringValue) {
				TypedStringValue typedStringValue = (TypedStringValue) value;
				String stringValue = typedStringValue.getValue();
				Class<?> targetType = typedStringValue.getTargetType();
				if (targetType == String.class) {
					valueToUse = stringValue;
				} else if (targetType == Integer.class) {
					valueToUse = Integer.parseInt(stringValue);
				} // ....
			} else if (value instanceof RuntimeBeanReference) {
				RuntimeBeanReference reference = (RuntimeBeanReference) value;
				String ref = reference.getRef();

				// 创建一个bean的时候，根据依赖注入情况，自动去创建另一个bean去注入
				valueToUse = getBean(ref);
			}
			ReflectUtils.setProperty(bean, name, valueToUse);
		}
	}

	private Object createInstance(BeanDefinition beanDefinition) {
		// TODO 可以通过静态工厂去创建？？？？
		// TODO 可以通过实例工厂去创建？？？？

		try {
			String clazzName = beanDefinition.getClazzName();
			Class<?> clazzType = resolveClass(clazzName);
			Object bean = ReflectUtils.createObject(clazzType);

			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<?> resolveClass(String clazzName) {
		try {
			Class<?> clazz = Class.forName(clazzName);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
