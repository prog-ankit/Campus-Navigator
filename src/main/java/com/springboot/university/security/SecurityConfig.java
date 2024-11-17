package com.springboot.university.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/*
The most basic template to implement security, with this we define
that no security has to be used and we can bypass or use the
APIs from controller without any username and password.
* */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //disables CSRF - Since we disable the CSRF, we do not need to send X-CSRF-TOKEN for POST Request.
        http.csrf(AbstractHttpConfigurer::disable);

        //Adding these two statements will show forbidden access as
        // we need to add formLogin() method for UI login and httpBasic() method for REST API login
        http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        //This does not maintains any state of current connection and creates new sessioon id every time user logsin.
        //If this statement is added, we cannot use formLogin(), it will keep redirecting us in the formLogin only.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
