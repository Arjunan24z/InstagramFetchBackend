package com.scaler.instagramopenapifetch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String username;
    private long followers_count;
}
