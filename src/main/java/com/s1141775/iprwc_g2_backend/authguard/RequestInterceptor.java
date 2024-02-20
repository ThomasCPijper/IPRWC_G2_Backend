package com.s1141775.iprwc_g2_backend.authguard;


import com.s1141775.iprwc_g2_backend.model.Account;
import com.s1141775.iprwc_g2_backend.model.AccountJWTDTO;
import com.s1141775.iprwc_g2_backend.model.AccountType;
import com.s1141775.iprwc_g2_backend.service.JwtDtoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
        System.out.println("preHandle()");
        System.out.println("Token: " + token);
        System.out.println("Requested endpoint: " + endpoint);

        List<String> whitelistedEndpoints = new ArrayList<>();
        whitelistedEndpoints.add("/register");
        whitelistedEndpoints.add("/login");
        whitelistedEndpoints.add("/order");
        whitelistedEndpoints.add("/products");
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
        if(this.jwtDtoService.getByJwtToken(jwtToken).isPresent()){
            Account account = this.jwtDtoService.getByJwtToken(jwtToken).get().getAccount();
            return account.getType().equals(AccountType.Admin);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
    }
}
