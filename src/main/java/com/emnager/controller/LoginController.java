package com.emnager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.emnager.model.AuthenticationRequest;
import com.emnager.model.UserLoginDetails;
import com.emnager.services.EManagerServices;

@CrossOrigin
@RestController
public class LoginController {
	
    private EManagerServices eManagerServices;

    @Autowired
    public LoginController (EManagerServices eManagerServices) {
        this.eManagerServices = eManagerServices;  
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public ResponseEntity<UserLoginDetails> login(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException{
    	
        return new ResponseEntity<>(eManagerServices.validateUser(authenticationRequest), HttpStatus.OK);
        
    }
	
	


}
