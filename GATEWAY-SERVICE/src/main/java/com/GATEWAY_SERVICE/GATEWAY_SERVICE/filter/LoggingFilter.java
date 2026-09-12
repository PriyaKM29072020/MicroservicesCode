/*
package com.GATEWAY_SERVICE.GATEWAY_SERVICE.filter;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import java.util.logging.Logger;
//This logs every incoming request.
@Component
public class LoggingFilter implements Filters {

    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Request Path: {}"+
                exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}*/
