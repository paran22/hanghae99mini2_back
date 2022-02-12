package com.example.hanghae99_mini2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(corsConfigurationSource());

        http.csrf().disable();
        //        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());



        http.authorizeRequests()
                // 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
                .and()
// [로그인 기능]
                .formLogin().disable()
                .addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //// 로그인 View 제공 (GET /user/login)
//                .loginPage("/user/login")
//// 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//// 로그인 처리 후 성공 시 URL
//                .defaultSuccessUrl("/boards")
//// 로그인 처리 후 실패 시 URL
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()

// [로그아웃 기능]
        http.logout()
// 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/boards")
                .permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://ministudy.s3-website.ap-northeast-2.amazonaws.com");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    protected CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
    CustomUsernamePasswordAuthenticationFilter authenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
    try {
        authenticationFilter.setFilterProcessesUrl("/user/login");
        authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        authenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return authenticationFilter;
    }
}