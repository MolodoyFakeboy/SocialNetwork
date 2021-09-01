package com.social.network.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.social.network.config.Config;
import com.social.network.config.WebConfig;
import com.social.network.payLoad.loginRequest.LoginRequest;
import com.social.network.payLoad.signUpRequest.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Config.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class AuthControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void auth() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Zahar");
        loginRequest.setPassword("Zahar");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin").
                contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void registerUser()  throws Exception{
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUserName("ZaharTest");
        signupRequest.setPassword("ZaharTest");
        signupRequest.setBirthday(Date.valueOf("2021-08-26"));
        signupRequest.setEmail("ZaharTest@gmail.com");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(signupRequest)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}