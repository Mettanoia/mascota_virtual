package com.solafides.mascota_virtual.adapters.web.controllers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class CreatureRouter {

    @Bean
    public RouterFunction<ServerResponse> route(CreatureHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/"), handler::serveIndex)
                .andRoute(RequestPredicates.GET("/creatures"), handler::getCreatures)
                .andRoute(RequestPredicates.GET("/creatures/{id}"), handler::getCreatureById)
                .andRoute(RequestPredicates.DELETE("/creatures/{id}"), handler::deleteCreature)
                .andRoute(RequestPredicates.POST("/creatures/{id}/interact"), handler::interactWithCreature);
    }
}
