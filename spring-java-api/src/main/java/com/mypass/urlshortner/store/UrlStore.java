package com.mypass.urlshortner.store;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UrlStore {
    public Map<String, String> store = new HashMap<>();
}
