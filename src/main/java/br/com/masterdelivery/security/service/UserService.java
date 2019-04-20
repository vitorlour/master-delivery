package br.com.masterdelivery.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.masterdelivery.security.UserSS;


public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
