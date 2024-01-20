package com.vivek.myauth.social.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.oauth2.sdk.TokenRequest;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {
    @PostMapping("/github")
    public ResponseEntity<String> exchangeCodeForToken(@RequestBody TokenRequest request) {
        // Here, you can use the received code to exchange it for an access token with
        // GitHub.
        // Make a request to GitHub's OAuth endpoint with the code and your
        // application's credentials (client ID and client secret).
        // Parse the response to get the access token.

        // After receiving the access token, you can use it to make requests to GitHub's
        // API on behalf of the user.

        // For security reasons, make sure to validate the token with GitHub and handle
        // any potential errors properly.
        System.out.println("my token request" + request);
        return ResponseEntity.ok("Code exchanged successfully");
    }
}
