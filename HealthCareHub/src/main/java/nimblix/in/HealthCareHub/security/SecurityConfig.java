package nimblix.in.HealthCareHub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())   // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()   // Allow ALL requests without login
                        .requestMatchers(
                                "/auth/**",
                                "/patients/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/doctors/**",
                                "/api/hospital/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())   // Disable login page
                .httpBasic(basic -> basic.disable()); // Disable basic authentication

        return http.build();
    }
}