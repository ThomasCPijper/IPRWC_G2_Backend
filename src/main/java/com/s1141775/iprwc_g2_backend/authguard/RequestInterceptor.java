package com.s1141775.iprwc_g2_backend.authguard;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountType;
import com.s1141775.iprwc_g2_backend.service.JwtDtoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final JwtDtoService jwtDtoService;

    public RequestInterceptor(JwtDtoService jwtDtoService) {
        this.jwtDtoService = jwtDtoService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        String token = extractToken(request);
        String endpoint = request.getRequestURI();

        //Add whitelisted endpoints to list
        List<String> whitelistedEndpoints = new ArrayList<>();
        whitelistedEndpoints.add("/register");
        whitelistedEndpoints.add("/login");
        whitelistedEndpoints.add("/order");
        whitelistedEndpoints.add("/products");

        System.out.println("preHandle(): " + token);

        //Check if requested endpoint is in list
        if(!whitelistedEndpoints.contains(endpoint)){
            return checkJwtToken(token);
        }
        return true;
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean checkJwtToken(String jwtToken){
        System.out.println("CheckToken-token: " + jwtToken);

        //Check if token is not null
        if(jwtToken == null || jwtToken.isEmpty()){
            System.out.println("Blocked, token is null");
            return false;
        }

        //Check if token is expired
        if(isTokenExpired(jwtToken)){
            System.out.println("Blocked, token is expired");
            return false;
        }

        //Check if account is an Admin
        if(this.jwtDtoService.getByJwtToken(jwtToken).isPresent()){
            Account account = this.jwtDtoService.getByJwtToken(jwtToken).get().getAccount();

            if(account.getType().equals(AccountType.Customer)){
                System.out.println("Account is an customer");
            }

            return account.getType().equals(AccountType.Admin);
        }

        System.out.println("Blocked, token is not present");
        return false;
    }

    private boolean isTokenExpired(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        Date expiration = decodedJWT.getExpiresAt();
        Date now = Date.from(Instant.now());
        return expiration.before(now);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
    }
}
