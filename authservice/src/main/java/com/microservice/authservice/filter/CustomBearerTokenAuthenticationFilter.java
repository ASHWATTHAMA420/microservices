package com.microservice.authservice.filter;

import com.microservice.authservice.client.UserServiceClient;
import com.microservice.authservice.model.auth.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomBearerTokenAuthenticationFilter extends OncePerRequestFilter {

    private final UserServiceClient userServiceClient;


    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest httpServletRequest,
                                    @NonNull final HttpServletResponse httpServletResponse,
                                    @NonNull final FilterChain filterChain) throws ServletException, IOException {

        if(httpServletRequest.getServletPath().startsWith("/actuator") || httpServletRequest.getServletPath().contains("swagger") || httpServletRequest.getServletPath().contains("v3/api-docs")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        log.debug("API Request was secured with Security!");
        final String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (Token.isBearerToken(authorizationHeader)) {
            final String jwt = Token.getJwt(authorizationHeader);
            userServiceClient.validateToken(jwt);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
