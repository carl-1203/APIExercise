package com.mypass.urlshortner.controllers;

import com.mypass.urlshortner.store.UrlStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

public class FollowController {

    private final UrlStore urlStore;

    public FollowController(UrlStore urlStore) {
        this.urlStore = urlStore;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> follow(@PathVariable String code) {
        String url = urlStore.store.get(code);

        if (url == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
