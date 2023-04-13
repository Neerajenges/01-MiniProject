package com.eg.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
//used to read the user defined properties from Application.yml file
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix="plan-api")
@Configuration
public class AppProperties {
	
	private Map<String,String> messages=new HashMap<>();
	
	

}
