//package com.example.hanghae99_mini2.security;
//
//import org.json.JSONObject;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Component
//public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException authException
//    ) throws IOException, ServletException {
//
//        String requestUri = request.getRequestURI();
//
//        // 로그인 창에서 AuthenticationException 발생시
//        if(requestUri.contains("/user/login")) {
//            // 400 에러로 지정
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            // json 리턴 및 한글깨짐 수정.
//            response.setContentType("application/json;charset=utf-8");
//            JSONObject json = new JSONObject();
//            String message = "아이디 또는 비밀번호를 확인해주세요.";
//            json.put("httpStatus", HttpStatus.BAD_REQUEST);
//            json.put("errorMessage", message);
//
//            PrintWriter out = response.getWriter();
//            out.print(json);
//        } else { // 로그인이 필요한 창에 접근시시
//            // 401 에러로 지정
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            // json 리턴 및 한글깨짐 수정.
//            response.setContentType("application/json;charset=utf-8");
//            JSONObject json = new JSONObject();
//            String message = "로그인 후 이용해주세요.";
//            json.put("httpStatus", HttpStatus.UNAUTHORIZED);
//            json.put("errorMessage", message);
//
//            PrintWriter out = response.getWriter();
//            out.print(json);
//        }
//
//
////        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//    }
//}
//
