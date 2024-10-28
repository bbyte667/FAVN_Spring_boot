package com.favn.signon.instagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.DefaultMapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2AccessTokenResponseHttpMessageConverter extends OAuth2AccessTokenResponseHttpMessageConverter {



    private Converter<Map<String, Object>, OAuth2AccessTokenResponse> accessTokenResponseConverter = new DefaultMapOAuth2AccessTokenResponseConverter();
    private static final ParameterizedTypeReference<Map<String, Object>> STRING_OBJECT_MAP = new ParameterizedTypeReference<Map<String, Object>>() {
    };



    @Override
    protected OAuth2AccessTokenResponse readInternal(Class<? extends OAuth2AccessTokenResponse> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
        Map<String, Object> tokenResponseParameters = Collections.EMPTY_MAP;
        try {
            tokenResponseParameters = new ObjectMapper().readValue(inputMessage.getBody(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tokenResponseParameters.put(OAuth2ParameterNames.TOKEN_TYPE, OAuth2AccessToken.TokenType.BEARER.getValue());
        return this.accessTokenResponseConverter.convert(tokenResponseParameters);
    }


}
