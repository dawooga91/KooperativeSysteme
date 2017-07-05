package fh.dortmund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fh.dortmund.logic.Usermanager;
import fh.dortmund.logic.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = ServiceConfig.BASE_PATH + "/user")
public class UserService {

	@Autowired
	Usermanager usermanager;

	@RequestMapping(path = "/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User saveUser(@RequestBody User user) {
		log.info("POST User '{}'", user);
		return usermanager.join(user);
	}

}
