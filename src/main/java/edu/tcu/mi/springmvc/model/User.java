package edu.tcu.mi.springmvc.model;

import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String password;
	private boolean enable;
	private Collection<Role> authorities ;
	
	private String fName;
	private String lName;
	private boolean gender;
	
	@Transient
	private ShaPasswordEncoder encoder;
	public User(){
		this.encoder = new ShaPasswordEncoder();
		this.authorities = new ArrayList<Role>();
		this.authorities.add(new Role("ROLE_USER"));
		this.authorities.add(new Role("ROLE_ANONYMOUS"));
	}
	
	public User(String username, String password, Collection<? extends GrantedAuthority> authorities){
		this();
		this.username = username;
		this.password = encoder.encodePassword(password, "Gaduo");
		if(authorities == null){
			this.authorities = new ArrayList<Role>();
			this.authorities.add(new Role("ROLE_USER"));
			this.authorities.add(new Role("ROLE_ANONYMOUS"));
		}
	}
	
	
	public User(String username, String password, boolean enable, Collection<Role> authorities, String fName, String lName, boolean gender) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;	
		if(authorities == null){
			this.authorities = new ArrayList<Role>();
			this.authorities.add(new Role("ROLE_USER"));
			this.authorities.add(new Role("ROLE_ANONYMOUS"));
		}
	}

	public void setAuthorities(Collection<Role> authorities) {
		this.authorities = authorities;
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public ShaPasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(ShaPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setPassword(String password){
		this.password = encoder.encodePassword(password, "Gaduo");
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.enable;
	}
	
	public boolean matchPassword(String rawPassword){
		return rawPassword.equals(password);
	}

	@Override
	public String toString(){
		return "{"
				+ "\"fName\" : \"" + fName + "\" "
				+ "\"lName\" : \"" + lName + "\" "
				+ "\"gender\" : \"" + gender + "\" "
				+ "\"username\" : \"" + username + "\", "
				+ "\"password\" : \"" + password + "\" "
				+ "}";
	}
	
}
