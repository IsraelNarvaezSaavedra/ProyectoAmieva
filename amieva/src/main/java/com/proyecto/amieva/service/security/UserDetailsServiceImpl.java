package com.proyecto.amieva.service.security;

import org.springframework.security.core.userdetails.*;
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
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    
	    Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profesor no encontrado con email: " + email));
        
        return User.builder()
      	      .username(profesor.getEmail())
    	      .password(profesor.getContrasena())
    	      .roles(profesor.isDirectiva() ? "DIRECTIVA" : "PROFESOR")
    	      .accountExpired(false)
    	      .accountLocked(false)
    	      .credentialsExpired(false)
    	      .disabled(false)
    	      .build();
        
	  }
}

