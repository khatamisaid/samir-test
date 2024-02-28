package com.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
            "/dummy",
            "/css/**",
            "/js/**",
            "/login",
            "/logout/**",
            "/dummy",
            "/"
    };

    private static final String[] AUTH = {
        "/api/**",
        "/list_users",
        "/add_user",
        "/delete_user/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.securityContext(securiyContext->securiyContext.requireExplicitSave(false))
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .csrf(csrf->csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers(AUTH_WHITELIST)
        .csrfTokenRepository(new CookieCsrfTokenRepository()))
        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
        .authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll().requestMatchers(AUTH).authenticated())
        .formLogin(formLogin -> formLogin.loginPage("/login"))
        .logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
        .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
