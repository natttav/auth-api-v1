package com.nattav.controller;

import com.nattav.config.JwtTokenUtil;
import com.nattav.model.JwtRequest;
import com.nattav.model.JwtResponse;
import com.nattav.model.UserDto;
import com.nattav.model.common.CommonMsgObject;
import com.nattav.model.common.CommonResponse;
import com.nattav.model.common.CommonResponseDataObject;
import com.nattav.model.common.CommonStatusObject;
import com.nattav.service.JwtUserDetailsService;

import java.math.BigDecimal;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authentication/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		CommonResponseDataObject reponseData = new CommonResponseDataObject();
		reponseData.setStatus(new CommonStatusObject("0000", "Success"));
		reponseData.setData(new JwtResponse(token));
		return ResponseEntity.ok(reponseData);
	}

	@RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {

		if (user.getPhoneno().length() != 10) {
			throw new Exception("INVALID_PHONENO");
		} else {
			if (user.getSalary().compareTo(new BigDecimal("50000")) == 0
					|| user.getSalary().compareTo(new BigDecimal("50000")) == 1) {
				user.setMember_level("Platinum");
			} else if (user.getSalary().compareTo(new BigDecimal("30000")) == 0
					|| (user.getSalary().compareTo(new BigDecimal("50000")) == -1
							&& user.getSalary().compareTo(new BigDecimal("30000")) == 1)) {
				user.setMember_level("Gold");
			} else if ((user.getSalary().compareTo(new BigDecimal("30000")) == -1
					&& user.getSalary().compareTo(new BigDecimal("15000")) == 1)) {
				user.setMember_level("Silver");
			} else {
				user.setMember_level("Undefined");

				CommonMsgObject responseError = new CommonMsgObject();
				responseError.setStatus(new CommonStatusObject("R001", "Registeration failed"));
				responseError.setMessage(
						"Your salary is not pass member registration criteria, thank you for your interesting.");

				userDetailsService.save(user);
				return ResponseEntity.ok(responseError);
			}
			CommonResponseDataObject responseDataObject = new CommonResponseDataObject();
			responseDataObject.setStatus(new CommonStatusObject("0000", "Success"));
			responseDataObject.setData(userDetailsService.save(user));

			return ResponseEntity.ok(responseDataObject);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
