package fh.dortmund.service;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = ServiceConfig.BASE_PATH)
public class ServiceConfig {

	public static final String BASE_PATH = "/api";

	static {
		log.info("Basepath = > {}", BASE_PATH);
	}

}
