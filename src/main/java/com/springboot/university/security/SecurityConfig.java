package com.springboot.university.security;

import com.springboot.university.filter.JwtFilter;
import com.springboot.university.service.serviceimpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
The most basic template to implement security, with this we define that no security has to be used and
we can bypass or use the APIs from controller without any username and password.
* */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //We can also mention UserDetailsService(built-in interface) and let Spring auto inject our Custom Implementation.
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    //Whenever we would want to use JWTAuthentication, first we need to tell to invoke JWTFilter first and then only invoke UsernamePasswordAuthenticationFilter(Done by compiler)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //disables CSRF - Since we disable the CSRF, we do not need to send X-CSRF-TOKEN for POST Request.
        http.csrf(AbstractHttpConfigurer::disable);
 
        //Adding these two statements will show forbidden access as
        // we need to add formLogin() method for UI login and httpBasic() method for REST API login
        http.authorizeHttpRequests(customizer -> customizer
                .requestMatchers("api/register","api/login")
                .permitAll()
                .anyRequest()
                .authenticated());

//        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //This does not maintains any state of current connection and creates new sessioon id every time user logsin.
        //If this statement is added, we cannot use formLogin(), it will keep redirecting us in the formLogin only.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    //Dynamic Method - With this method, we define authentication using custom authentication provider to authenticate the credentials using database.
    //  DaoAuthentication Provider is used for username and password based Authentication and JwtAuthenticationProvider for authenticating using JWT token
    //  https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        //We have defined a custom UserDetailsService in order to authenticate the credentials using database.
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    //Authentication Manager will then interact with Authentication Provider and we need to mention the login path as well to validate and generate the access token.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordencoder() {
        return new BCryptPasswordEncoder(12);
    }
}
