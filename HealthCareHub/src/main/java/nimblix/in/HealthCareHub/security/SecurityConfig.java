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
                .csrf(csrf -> csrf.disable())   // Disable CSRF for Postman testing

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/create").permitAll()  // Allow create API
                        .anyRequest().authenticated()  // Secure other APIs
                )

                .formLogin(form -> form.disable())   // Disable default login page
                .httpBasic(basic -> basic.disable()); // Disable basic auth (optional)

        return http.build();
    }
}