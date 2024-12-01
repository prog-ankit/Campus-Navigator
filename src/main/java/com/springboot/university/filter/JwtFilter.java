package com.springboot.university.filter;

import com.springboot.university.service.serviceimpl.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//for every request, we want the filter chain to execute this filter ONLY ONCE.
@Configuration
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTServiceImpl jwtService;

    @Autowired
    UserDetailsService userDetailsService;
    /*
    Steps for doFilterInterval:
        i. Get the Token from Authorization Header from API request
        ii. From the token, username is extracted from the method defined in JWTService
        iii. A check has to be made to make sure, if any authenticated user is already running or not
    **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        //With SecurityContextHolder, we can check if we are not already authenticated.
        //Following condition checks that if username is not null and SecurityContext is authenticated now, then do not perform authentication again.
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token, userDetails)) {
                // After this point, JWT Authentication is done, now you need to inform to use Username-Password Authentication filter internally.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
