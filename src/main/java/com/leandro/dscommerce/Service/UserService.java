package com.leandro.dscommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.leandro.dscommerce.Entity.User;
import com.leandro.dscommerce.Repository.UserRepository;

public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}
	

}
