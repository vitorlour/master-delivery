package br.com.masterdelivery.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.masterdelivery.security.User;


public class UserService {
	
	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
