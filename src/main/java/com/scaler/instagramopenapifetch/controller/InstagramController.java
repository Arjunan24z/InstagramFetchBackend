package com.scaler.instagramopenapifetch.controller;

import com.scaler.instagramopenapifetch.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scaler.instagramopenapifetch.service.InstagramService;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/instagram")
public class InstagramController {
    private final InstagramService instagramService;
    public InstagramController(InstagramService instagramService){
        this.instagramService = instagramService;
    }
    // get user data
    @GetMapping("/user")
    public ResponseEntity<Long> getUserData() {
        try{
            User user = instagramService.getInstagramUserData();
            return new ResponseEntity<>(user.getFollowers_count(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return new ResponseEntity<>("Bad Request: " + ex.getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

