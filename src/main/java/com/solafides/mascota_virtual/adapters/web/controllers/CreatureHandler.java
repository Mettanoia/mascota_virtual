package com.solafides.mascota_virtual.adapters.web.controllers;// CreatureHandler.java
import com.solafides.mascota_virtual.domain.Creature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreatureHandler {

    private final Map<String, Creature> creatureData = new ConcurrentHashMap<>();

    public CreatureHandler() {
        // Initialize some creatures
        creatureData.put("1", new Creature(
                "1",
                "Fluffy",
                "/images/pet1.webp",
                "Happy",
                "Energetic"
        ));
        creatureData.put("2", new Creature(
                "2",
                "Sparky",
                "/images/pet2.webp",
                "Content",
                "Full"
        ));
    }

    // Serve the index.html file
    public Mono<ServerResponse> serveIndex(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .bodyValue(new ClassPathResource("static/index.html"));
    }

    // Get list of creatures
    public Mono<ServerResponse> getCreatures(ServerRequest request) {
        Collection<Creature> creatures = creatureData.values();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creatures);
    }

    public Mono<ServerResponse> deleteCreature(ServerRequest request) {
        String id = request.pathVariable("id");
        if (creatureData.containsKey(id)) {
            creatureData.remove(id);
            return ServerResponse.ok().build();
        } else {
            return ServerResponse.notFound().build();
        }
    }


    // Get creature by ID
    public Mono<ServerResponse> getCreatureById(ServerRequest request) {
        String id = request.pathVariable("id");
        Creature creature = creatureData.get(id);
        if (creature != null) {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(creature);
        } else {
            return ServerResponse.notFound().build();
        }
    }

    // Interact with creature
    public Mono<ServerResponse> interactWithCreature(ServerRequest request) {

        String id = request.pathVariable("id");
        Mono<Map<String, String>> actionMono = request.bodyToMono(Map.class)
                .map(map -> new HashMap<String, String>(map));

        return actionMono.flatMap(actionMap -> {
            String action = actionMap.get("action");
            Creature creature = creatureData.get(id);
            if (creature != null) {
                // Update mood and energy based on action
                switch (action) {
                    case "feed":
                        creature.setMood("Satisfied");
                        creature.setEnergy("Full");
                        break;
                    case "gift":
                        creature.setMood("Excited");
                        creature.setEnergy("Energetic");
                        break;
                    default:
                        break;
                }
                return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(creature);
            } else {
                return ServerResponse.notFound().build();
            }
        });
    }
}
