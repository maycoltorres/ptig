package com.gamasoft.hps.sab.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.local.CustomUserDetailsService;

public class RoleAccessVoter implements AccessDecisionVoter<FilterInvocation> {
	
	
	private UserRepository userRepository;

	
	
	
	@Autowired
	    public RoleAccessVoter(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	
	@Override
	public int vote(Authentication authentication, FilterInvocation object,Collection<ConfigAttribute> attributes){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		
		
		CustomUserDetailsService userDetails = new CustomUserDetailsService();
		userDetails.setUserRepository(this.userRepository);
		try{
		if(userDetails.validaPermisos(object.getRequestUrl(), object.getHttpRequest().getMethod(),currentUser.getId())==1){
			
			return ACCESS_GRANTED;
		}
		}catch(Exception e){
		
		}
		return ACCESS_GRANTED;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	public Long ClienteUserRequestMapping(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println("nombre "+name);
		User currentUser = userRepository.getUserByUsername(name);
		return currentUser.getIdCliente();
		
	}
	
	
}
