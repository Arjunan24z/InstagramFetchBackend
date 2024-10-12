package com.scaler.instagramopenapifetch.service;

import com.scaler.instagramopenapifetch.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class InstagramService {

    @Value("${instagram.api.base.url}")
    private String baseUrl;

    @Value("${instagram.api.version}")
    private String apiVersion;

    @Value("${instagram.access.token}")
    private String accessToken;

    private final WebClient webClient;

    public InstagramService(WebClient webClient) {
        this.webClient = webClient;
    }

    public User getInstagramUserData() {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "me")
                .queryParam("fields", "id,username,followers_count")
                .queryParam("access_token", accessToken)
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(User.class).block();
    }
}

