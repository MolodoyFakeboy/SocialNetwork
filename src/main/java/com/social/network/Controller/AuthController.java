//package com.social.network.Controller;
//
//import com.social.network.Model.User;
//import com.social.network.PayLoad.LoginRequest.LoginRequest;
//import com.social.network.PayLoad.SignUpRequest.SignupRequest;
//import com.social.network.PayLoad.response.MessageResponse;
//import com.social.network.Services.Interfaces.IUserService;
//import com.social.network.jwt.JwtProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//@PreAuthorize("permitAll()")
//public class AuthController implements IAuthController {
//
//    private IUserService userService;
//
//    private JwtProvider jwtProvider;
//
//    @Autowired
//    public AuthController (IUserService userService, JwtProvider jwtProvider) {
//        this.userService = userService;
//        this.jwtProvider = jwtProvider;
//    }
//
//    @Override
//    @PostMapping("/signin")
//    public String auth(LoginRequest request) {
//        User user = userService.findByNamePassword(request);
//        return jwtProvider.generateToken(user.getUsername());
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<Object> registerUser(SignupRequest signupRequest) {
//        userService.createUser(signupRequest);
//        return new ResponseEntity<>(new MessageResponse("Пользователь успешно зарегестрирован " + signupRequest.getUserName()) , HttpStatus.OK);
//    }
//}
