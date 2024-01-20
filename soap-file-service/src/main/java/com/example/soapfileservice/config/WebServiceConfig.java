package com.example.soapfileservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

import java.util.ArrayList;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.soapfileservice.dto");
        marshaller.setMtomEnabled(true); // IMPORTANT
        return marshaller;
    }

    @Bean(name = "files")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema filesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FilesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://docs.oasis-open.org/ns/cmis/messaging/200908/");
        wsdl11Definition.setSchema(filesSchema);
        return wsdl11Definition;
    }

    @Bean
    public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
        List<MethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(methodProcessor());
        List<MethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
        returnValueHandlers.add(methodProcessor());
        DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
        adapter.setMethodArgumentResolvers(argumentResolvers);
        adapter.setMethodReturnValueHandlers(returnValueHandlers);
        return adapter;
    }

    @Bean
    public MarshallingPayloadMethodProcessor methodProcessor() {
        return new MarshallingPayloadMethodProcessor(marshaller());
    }

    @Bean
    public SoapFaultAnnotationExceptionResolver exceptionResolver() {
        return new SoapFaultAnnotationExceptionResolver();
    }

    @Bean
    public SaajSoapMessageFactory messageFactory() {
        return new SaajSoapMessageFactory();
    }

    @Bean
    public XsdSchema contentStreamSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/contentStream.xsd"));
    }
}
