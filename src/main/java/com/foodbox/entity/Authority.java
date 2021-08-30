package com.foodbox.entity;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7361335317509977723L;
	
	
	private String authority;
	
	
	
	public Authority(String authority) {
		super();
		this.authority = authority;
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	
	

}
