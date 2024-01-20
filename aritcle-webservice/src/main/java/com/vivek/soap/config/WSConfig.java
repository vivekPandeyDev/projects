package com.vivek.soap.config;

import com.vivek.soap.exception.CustomException;
import com.vivek.soap.exception.DetailSoapFaultDefinitionExceptionResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The type Ws config.
 */
@Configuration
@EnableWs
public class WSConfig extends WsConfigurerAdapter {


	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soapws/*");
	}
	@Bean(name = "articles")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema articlesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ArticlesPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.vivek.com/article-ws");
		wsdl11Definition.setSchema(articlesSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema articlesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/articles.xsd"));
	}

	@Bean
	public SoapFaultMappingExceptionResolver exceptionResolver() {
		SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();

		SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
		faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
		exceptionResolver.setDefaultFault(faultDefinition);

		Properties errorMappings = new Properties();
		errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
		errorMappings.setProperty(CustomException.class.getName(), SoapFaultDefinition.CLIENT.toString());
		exceptionResolver.setExceptionMappings(errorMappings);
		exceptionResolver.setOrder(1);
		return exceptionResolver;
	}

}
