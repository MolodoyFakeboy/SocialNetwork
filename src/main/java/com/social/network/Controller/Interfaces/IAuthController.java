package com.social.network.Controller.Interfaces;

import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface IAuthController {

    String auth(LoginRequest request);

    ResponseEntity<Object> registerUser(SignupRequest signupRequest);
}
