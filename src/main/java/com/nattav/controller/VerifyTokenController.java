package com.nattav.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nattav.config.JwtTokenUtil;
import com.nattav.model.JwtResponse;
import com.nattav.model.UserDao;
import com.nattav.model.VerificationResponse;
import com.nattav.model.common.CommonResponse;
import com.nattav.model.common.CommonResponseDataObject;
import com.nattav.model.common.CommonStatusObject;
import com.nattav.repository.UserRepository;
import com.nattav.service.JwtUserDetailsService;
@RestController
@CrossOrigin()
public class VerifyTokenController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authentication/verify", method = RequestMethod.POST)
    public ResponseEntity<?>  verify(HttpServletRequest request) {

		final String requestTokenHeader = request.getHeader("Authorization");
		final String token = requestTokenHeader.replaceAll("Bearer ", "");
		JwtResponse jwtResponse = new JwtResponse(token);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDao userDao = userDetailsService.getUserObjByUsername(username);
		
		CommonResponseDataObject responseDataObject = new CommonResponseDataObject();
		responseDataObject.setStatus(new CommonStatusObject("0000", "Success"));
		responseDataObject.setData(setResponse(userDao, jwtResponse));
		return ResponseEntity.ok(responseDataObject);
    }
    
    
    public VerificationResponse setResponse(UserDao userDao, JwtResponse jwtResponse) {
    	VerificationResponse response = new VerificationResponse();
    	
    	response.setJwt(jwtResponse.getToken());
    	response.setVerifyStatus("success");
    	response.setUsername(userDao.getUsername());
    	response.setRefCode(userDao.getRefcode());
    	
    	return response;
    }
}