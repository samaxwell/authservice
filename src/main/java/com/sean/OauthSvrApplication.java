package com.sean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* https://spring.io/guides/tutorials/spring-boot-oauth2/ */
/* https://www.baeldung.com/rest-api-spring-oauth2-angular */

@RestController
@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class OauthSvrApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(OauthSvrApplication.class, args);
	}
	
	@RequestMapping(value = { "/user "}, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put(
				"user",
				user.getUserAuthentication().getPrincipal());
		userInfo.put(
				"authorities",
				AuthorityUtils.authorityListToSet(
						user.getUserAuthentication()
							.getAuthorities()));
		return userInfo;
		
	}

}
