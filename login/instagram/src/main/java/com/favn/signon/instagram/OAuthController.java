package com.favn.signon.instagram;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OAuthController {

    @Autowired
    private InstagramService instagramService;

    @GetMapping("/instagram-data")
    public String getInstagramData(@RegisteredOAuth2AuthorizedClient("instagram") OAuth2AuthorizedClient authorizedClient) {
        String accessToken = instagramService.getAccessToken(authorizedClient);
        String followersList = instagramService.getFollowersList(accessToken);
        return "{ \"access_token\": \"" + accessToken + "\", \"followers\": " + followersList + " }";
    }

    @GetMapping("/error")
    public void error(
            HttpServletRequest request,
            HttpServletResponse response){
        log.info("JAI Ganesh");
    }

    @GetMapping("/")
    public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
        log.info("userName {}", oauth2User.getName());
        log.info("clientName {}", authorizedClient.getClientRegistration().getClientName());
        log.info("userAttributes {}", oauth2User.getAttributes());
        return "index";
    }

}
