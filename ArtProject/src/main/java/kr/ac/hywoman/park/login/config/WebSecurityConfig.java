package kr.ac.hywoman.park.login.config;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.ac.hywoman.park.login.service.UserDetailService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CSRF 비활성화 (HTML form POST 사용)
            .csrf(AbstractHttpConfigurer::disable)
            
            // 이미지 관련 
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives(
                        "default-src 'self'; " +
                        "img-src 'self' https: data:; " +   // ⭐ 외부 이미지 허용
                        "script-src 'self' https: 'unsafe-inline'; " +
                        "style-src 'self' https: 'unsafe-inline'; " +
                        "font-src 'self' https: data:;"
                    )
                )
            )

            // 접근 권한 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/login",
                    "/signup",
                    "/user",
                    "/error",
                    "/find_account",
                    "/api/user/find-id",
                    "/api/user/find-password",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                // 그 외는 로그인 필요
                .anyRequest().authenticated()
            )

            // 로그인 설정
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/main", true)
                .failureHandler(authenticationFailureHandler()) // 로그인 실패 처리
                .permitAll()
            )

            // 로그인 상태 유지
            .rememberMe(remember -> remember
                .rememberMeParameter("remember-me") // checkbox name
                .key("a-view-of-mind-remember-key") // 고유 키 (임의 문자열)
                .tokenValiditySeconds(60 * 60 * 24 * 14) // 14일 유지
            )

            // 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                // .deleteCookies("remember-me") // 로그아웃 시 자동 로그인까지 끊고 싶으면 주석 해제
                .permitAll()
            );

        return http.build();
    }

    // 로그인 실패 핸들러(username 유지)
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (HttpServletRequest request,
                HttpServletResponse response,
                org.springframework.security.core.AuthenticationException exception) -> {

            String username = request.getParameter("username");

            // URL 인코딩 (한글, 특수문자 대응)
            String encodedUsername = username != null
                    ? URLEncoder.encode(username, StandardCharsets.UTF_8)
                    : "";

            response.sendRedirect("/login?error&username=" + encodedUsername);
        };
    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            BCryptPasswordEncoder passwordEncoder,
            UserDetailService userDetailService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }

    // Password Encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
