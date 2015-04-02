package edu.tcu.mi.springmvc.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;


@Document
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String authority;
	
	public Role(String authority) {
		super();
		this.authority = authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
	
	@Override
	public String toString(){
		return authority;
	}
}
