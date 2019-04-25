package br.com.masterdelivery.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.security.User;
import br.com.masterdelivery.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService service;
	
	@Override
	public UserDetails loadUserByUsername(String email){
		Usuario usuario = service.encontrarPorEmail(email);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
				   .id(usuario.getId())
				   .email(usuario.getEmail())
				   .senha(usuario.getSenha())
				   .build();
	}
}
