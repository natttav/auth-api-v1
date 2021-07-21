package com.nattav.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nattav.model.UserDao;
import com.nattav.model.UserDto;
import com.nattav.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserDao getUserObjByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return user;
	}

	public UserDao save(UserDto user) {
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAddress(user.getAddress());
		newUser.setPhoneno(user.getPhoneno());
		newUser.setRefcode(genReferenceCode(user.getPhoneno()));
		newUser.setSalary(user.getSalary());
		newUser.setMember_level(user.getMember_level());
		return userDao.save(newUser);
	}
	
	public String genReferenceCode (String phoneNo) {
		Date toDay = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
	    String strDate = formatter.format(toDay);  
		String refcodeString = strDate+phoneNo.substring(phoneNo.length()-4, phoneNo.length());
		return refcodeString;
	}
}