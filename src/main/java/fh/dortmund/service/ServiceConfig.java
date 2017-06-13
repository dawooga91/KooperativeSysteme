package fh.dortmund.service;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path= ServiceConfig.BASE_PATH)
public class ServiceConfig {
	
	public static final String BASE_PATH= "/api";
	
}
