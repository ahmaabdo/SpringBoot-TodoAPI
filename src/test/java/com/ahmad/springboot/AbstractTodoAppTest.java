package com.ahmad.springboot;

import com.ahmad.springboot.security.AppUser;
import com.ahmad.springboot.security.SignInRequest;
import com.ahmad.springboot.security.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by ahmad on Fri - Jun 26, 2020
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractTodoAppTest {

    private final String USERNAME_FOR_TEST = "a7maabdo@gmail.com";
    private final String PASSWORD_FOR_TEST = "pass";
    private final String AUTH_HEADER = "Authorization";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected UserService userService;

    @Before
    public void setup() {
        AppUser user = new AppUser(USERNAME_FOR_TEST, new BCryptPasswordEncoder().encode(PASSWORD_FOR_TEST), "Ahmad");
        user.setId("1234");

        given(userService.loadUserByUsername(user.getUsername())).willReturn(user);
    }

    public ResultActions login(String username, String password) throws Exception {
        SignInRequest signInRequest = new SignInRequest(username, password);
        return mockMvc.perform(
                post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signInRequest))
        );
    }

    public MockHttpServletRequestBuilder doGet(String url) {
        return get(url).header(AUTH_HEADER, getHeaders());
    }
    public MockHttpServletRequestBuilder doPost(String url) {
        return post(url).header(AUTH_HEADER, getHeaders());
    }

    private String getHeaders() {
        try {
            MvcResult result = login(USERNAME_FOR_TEST, PASSWORD_FOR_TEST).andReturn();
            String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
            return String.format("Bearer %s", token);
        } catch (Exception e) {
            return null;
        }
    }
}
