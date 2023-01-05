package com.ca.formation.formationdemo1.config.jwtconfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtFilter jwtFilter;

    @Test
    @DisplayName("Should not set the authentication when the header is null")
    void doFilterInternalWhenHeaderIsNullThenNotSetAuthentication() throws ServletException, IOException {
        jwtFilter.doFilterInternal(request, response, filterChain);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should not set the authentication when the header does not start with bearer")
    void doFilterInternalWhenHeaderDoesNotStartWithBearerThenNotSetAuthentication() throws ServletException, IOException {


        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should not set the authentication when the token is invalid")
    void doFilterInternalWhenTokenIsInvalidThenNotSetAuthentication() throws ServletException, IOException {


        try {
            jwtFilter.doFilterInternal(request, response, filterChain);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should set the authentication when all conditions are met")
    void doFilterInternalWhenAllConditionsAreMetThenSetAuthentication() throws ServletException, IOException {

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }



}