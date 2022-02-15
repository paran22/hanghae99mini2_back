//package com.example.hanghae99_mini2.security;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//
//public class LoginFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException exception
//    ) throws IOException, ServletException {
//
//        String errorMsg = "";
//
//        if(exception instanceof UsernameNotFoundException) {
//            errorMsg = "존재하지 않는 아이디입니다.";
//        } else if(exception instanceof BadCredentialsException) {
//            errorMsg = "아이디 또는 비밀번호가 잘못 입력되었습니다.";
//        }
//
//        if(!StringUtils.isEmpty(errorMsg)) {
//            request.setAttribute("errorMsg", errorMsg);
//        }
//
//        response.sendRedirect("/login?errorMsg=" + URLEncoder.encode(errorMsg, "UTF-8"));
//    }
//}
