package com.social.network.controller;

import com.social.network.controller.interfac.IAuthController;
import com.social.network.model.User;
import com.social.network.payLoad.loginRequest.LoginRequest;
import com.social.network.payLoad.signUpRequest.SignupRequest;
import com.social.network.payLoad.response.MessageResponse;
import com.social.network.service.interfac.IUserService;
import com.social.network.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController implements IAuthController {

    private IUserService userService;

    private JwtProvider jwtProvider;

    @Autowired
    public AuthController (IUserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @PostMapping("/signin")
    public String auth(@RequestBody LoginRequest request) {
        User user = userService.findByNamePassword(request);
        return jwtProvider.generateToken(user.getUsername());
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody SignupRequest signupRequest) {
        userService.createUser(signupRequest);
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно зарегестрирован " + signupRequest.getUserName()) , HttpStatus.OK);
    }
}
