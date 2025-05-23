package com.samgoldsee.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 配置放行路径
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(

                                // 静态资源
                                "/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**",

                                // 页面请求
                                "/toLogin", "/toRegister", "/toForgotPassword", "/email/sendCode", "/email/checkCode", "/user/register", "/user/login", "/user/updatePassword",

                                // knife4j接口文档路径
                                "/doc.html", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/**")

                        .permitAll().anyRequest().authenticated())

                // 配置登录相关属性
                .formLogin(form -> form
                        .loginPage("/toLogin")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll())

                // 配置退出相关属性
                .logout(logout -> logout
                        .logoutUrl("/toLogout")
                        .logoutSuccessUrl("/toLogin")
                        .invalidateHttpSession(true) // 销毁会话
                        .deleteCookies("JSESSIONID")) // 删除 Cookie
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
