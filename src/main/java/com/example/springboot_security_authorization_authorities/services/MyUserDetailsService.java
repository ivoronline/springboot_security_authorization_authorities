package com.example.springboot_security_authorization_authorities.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

  //LOAD PROPERTIES
  @Value("${spring.security.user.profile}")  private String storedProfile;
  @Value("${spring.security.user.name}")     private String storedUsername;
  @Value("${spring.security.user.password}") private String storedPassword;
  @Value("${profile.user}")                  private String profileUser;
  @Value("${profile.admin}")                 private String profileAdmin;

  //=======================================================================
  // LOAD USER BY USERNAME
  //=======================================================================
  @Override
  public UserDetails loadUserByUsername(String enteredUsername) throws UsernameNotFoundException {

    //CHECK USERNAME
    if(!enteredUsername.equals(storedUsername) ) { throw new UsernameNotFoundException(enteredUsername); }

    //GET AUTHORITIES FOR GIVEN USER PROFILE
    String userAuthorities = "";
    if(storedProfile.equals("USER") ) { userAuthorities = profileUser;  }
    if(storedProfile.equals("ADMIN")) { userAuthorities = profileAdmin; }

    //GET AUTHORITIES FROM STRING PROPERTY
    String[]     authoritiesArray = userAuthorities.split(", ");
    List<String> authoritiesList  = Arrays.asList(authoritiesArray);

    //CREATE AUTHORITIES (FOR USER OBJECT)
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (String authority : authoritiesList) {
      authorities.add(new SimpleGrantedAuthority(authority.trim()));
    }

    //CREATE USER
    UserDetails userDetails = new User(enteredUsername, storedPassword, authorities);

    //RETURN USER
    return userDetails;

  }

}



