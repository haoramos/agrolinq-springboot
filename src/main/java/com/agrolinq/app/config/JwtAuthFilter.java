package com.agrolinq.app.config;

import com.agrolinq.app.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // If no token, skip filter
        if (authHeader ==  null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // Spring Security will then block it if the route requires authentication
        }

        final String token  = authHeader.substring(7);
        final String email = jwtService.extractEmail(token);

        // If email extracted and user not yet authenticated
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Loads the user from the database
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, email)){

                // Create authentication token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null, // We don't need the password here since the token already proved identity
                                userDetails.getAuthorities()
                        );

                // Good practice: attaches extra request metadata to the authentication object
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Stores the authenticated user in Spring's security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Passes the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
