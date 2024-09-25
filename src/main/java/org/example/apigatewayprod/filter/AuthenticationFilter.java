package org.example.apigatewayprod.filter;

import org.example.apigatewayprod.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test( exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey("Authorization")){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                String token = exchange.getRequest().getHeaders().getOrEmpty("Authorization").get(0);
                // token = token.replace("Bearer", "");
                token = token.substring(7);
                try{
                    jwtUtil.validateToken(token);
                }catch (Exception e){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        });
    }
    public static class Config{

    }
}
