package com.mypass.urlshortner.controllers;

import com.mypass.urlshortner.models.DecodeRequest;
import com.mypass.urlshortner.models.DecodeResponse;
import com.mypass.urlshortner.store.UrlStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DecodeController {
    private final UrlStore urlStore;

    public DecodeController(UrlStore urlStore) {
        this.urlStore = urlStore;
    }

    @PostMapping("/decode")
    public ResponseEntity<DecodeResponse> decode(@RequestBody DecodeRequest req) {
        if (req.shortUrl == null)
            return ResponseEntity.badRequest().build();

        String code = req.shortUrl.substring(req.shortUrl.lastIndexOf('/') + 1);
        String original = urlStore.store.get(code);

        if (original == null)
            return ResponseEntity.status(404).build();

        return ResponseEntity.ok(new DecodeResponse(original));
    }
}
