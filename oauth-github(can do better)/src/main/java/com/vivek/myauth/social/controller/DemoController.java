package com.vivek.myauth.social.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/user")
    public OAuth2AuthenticationToken user(OAuth2AuthenticationToken authentication) {
        return authentication;
    }

    @GetMapping
    public String test() {
        return "testing";
    }

}
