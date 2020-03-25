package com.test;

import com.test.react.model.Item;
import com.test.service.Service;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ItemHandler {

    private Service service;

    public ItemHandler(Service service) {
        this.service = service;
    }

    public Mono<ServerResponse> getItems(ServerRequest request){
        Flux<Item> items = service.getItems();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(items, Item.class);
    }

    public Mono<ServerResponse> getItem(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(service.getItem(Integer.valueOf(request.pathVariable("id"))), Item.class);
    }

    public Mono<ServerResponse> createItem(ServerRequest request){
        Mono<Item> itemMono = request.bodyToMono(Item.class).flatMap(item -> service.createItem(item));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(itemMono,Item.class);
    }
}
