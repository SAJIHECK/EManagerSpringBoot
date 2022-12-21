package com.emnager.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emnager.entity.Users;
import com.emnager.repository.UserRepository;

@Service
public class MyUserDetailServices implements UserDetailsService{
	

	private UserRepository userRepository;
	
	@Autowired
	public MyUserDetailServices(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Users user = userRepository.findByuserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	        return new User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(rolesToList(user.getRoleType())));
	    }
	    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	    }

	    private List<String>  rolesToList(String roles) {
	    	List<String> list = new ArrayList<String>();
	    	list.add(roles);
	    	  return list;
	    }
	
}
