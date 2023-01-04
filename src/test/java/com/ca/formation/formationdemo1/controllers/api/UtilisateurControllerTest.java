package com.ca.formation.formationdemo1.controllers.api;


import com.ca.formation.formationdemo1.services.UtilisateurService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UtilisateurService utillisateurService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v2";
    }

    private String tokenRequest;


    @Test
    @WithMockUser(username = "michel@formation.sn", password = "Passer@123", authorities = { "ADMIN" })
    public void registration() throws Exception {
        String body = "{\n" +
                "    \"username\": \"Tal@formation.ca\",\n" +
                "    \"password\": \"Passer@123\"\n" +
                "    \"name\": \"Tal\"\n" +
                "    \"authoritie\": \"READ\"\n" +
                "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v2/auth/registration")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String token = mvcResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        tokenRequest = token;
        System.out.println(body);
    }
}
