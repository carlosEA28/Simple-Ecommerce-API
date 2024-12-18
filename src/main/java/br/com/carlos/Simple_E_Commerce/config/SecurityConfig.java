package br.com.carlos.Simple_E_Commerce.config;

import br.com.carlos.Simple_E_Commerce.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtConfig jwtConfig;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/redeem-password").hasAuthority(String.valueOf(Role.CLIENT))
                        .requestMatchers(HttpMethod.POST, "/user/reset-password").hasAuthority(String.valueOf(Role.CLIENT))
                        .requestMatchers(HttpMethod.GET, "/product/{productId}").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtConfig.jwtDecoder())));

        return http.build();
    }
}
