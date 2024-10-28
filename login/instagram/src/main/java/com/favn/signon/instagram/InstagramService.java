package com.favn.signon.instagram;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InstagramService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(@RegisteredOAuth2AuthorizedClient("instagram") OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        return accessToken.getTokenValue();
    }

    public String getFollowersList(String accessToken) {
        String url = "https://graph.instagram.com/me/followers?access_token=" + accessToken;
        return restTemplate.getForObject(url, String.class);
    }

    public String getFollowersList1(String accessToken) {
        String url = "https://graph.instagram.com/me?fields=id,username,media_count,account_type,media&access_token=" + accessToken;
        return restTemplate.getForObject(url, String.class);
    }
}
