package com.example.hanghae99_mini2.security;

import com.example.hanghae99_mini2.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response
    ) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken;
        if(request.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
            try {
            LoginDto loginDto = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining()),
                    LoginDto.class);
            authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword());
                }
            catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Request Content-Type(application/json) Parsing Error");
            }
            } else {
            //form으로 받는 경우
            String username = obtainUsername(request);
            String password = obtainPassword(request);
            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        }
        this.setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
