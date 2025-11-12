package com.mypass.urlshortner.controllers;

import com.mypass.urlshortner.store.UrlStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.mypass.urlshortner.models.EncodeRequest;
import com.mypass.urlshortner.models.EncodeResponse;

import java.util.Base64;

@RestController
public class EncodeController {
    private final UrlStore urlStore;

    public EncodeController(UrlStore urlStore) {
        this.urlStore = urlStore;
    }

    @PostMapping("/encode")
    public ResponseEntity<EncodeResponse> encode(@RequestBody EncodeRequest req) {
        if (req.url == null || req.url.isEmpty())
            return ResponseEntity.badRequest().build();

        String code = Base64.getEncoder().encodeToString(req.url.getBytes()).substring(0, 6);
        urlStore.store.put(code, req.url);

        return ResponseEntity.ok(new EncodeResponse("http://localhost:8080/" + code));
    }
}