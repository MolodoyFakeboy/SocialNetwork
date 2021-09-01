package com.social.network.controller.interfac;

import com.social.network.payLoad.loginRequest.LoginRequest;
import com.social.network.payLoad.signUpRequest.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface IAuthController {

    String auth(LoginRequest request);

    ResponseEntity<Object> registerUser(SignupRequest signupRequest);
}
