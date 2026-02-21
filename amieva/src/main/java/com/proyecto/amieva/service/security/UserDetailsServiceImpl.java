package com.proyecto.amieva.service.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.proyecto.amieva.entity.Profesor;
import com.proyecto.amieva.repository.ProfesorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final ProfesorRepository profesorRepository;

	  public UserDetailsServiceImpl(ProfesorRepository profesorRepository) {
	    this.profesorRepository = profesorRepository;
	  }

	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Profesor profesor = profesorRepository.findByNombreIgnoreCase(username);

	    if (profesor == null) {
	      throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	    }

	    return User.withUsername(profesor.getNombre())
	      .password(profesor.getContrasena())       
	      .roles(profesor.isDirectiva() ? "DIRECTIVA" : "PROFESOR")      
	      .build();
	  }
}
