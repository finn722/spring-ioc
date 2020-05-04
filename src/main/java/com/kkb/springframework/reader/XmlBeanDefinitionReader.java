package com.kkb.springframework.reader;

import java.io.InputStream;

import org.dom4j.Document;

import com.kkb.spring.bean.registry.BeanDefinitionRegistry;
import com.kkb.utils.DocumentReader;

public class XmlBeanDefinitionReader {

	private BeanDefinitionRegistry beanDefinitionRegistry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	public void loadBeanDefinitions(InputStream inputStream) {
		Document document = DocumentReader.createDocument(inputStream);
		XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
		documentReader.registerBeanDefinitions(document.getRootElement());
	}

}
