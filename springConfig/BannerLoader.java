package com.spring.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class BannerLoader implements ResourceLoaderAware {

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		Resource resource = resourceLoader.getResource("classpath:banner.txt");
		try (
				
				BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream()));				
				){
			String line = bf.readLine();
			while(line != null) {
				System.out.println(line);
				line = bf.readLine();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
