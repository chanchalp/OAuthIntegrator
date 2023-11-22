package com.netsuite.service;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.*;

public class OAuth1RequestInterceptor implements ClientHttpRequestInterceptor {

    private final String consumerKey;
    private final String consumerSecret;
    private final String accessToken;
    private final String tokenSecret;

    public OAuth1RequestInterceptor(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.tokenSecret = tokenSecret;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Add OAuth headers to the request
        request.getHeaders().add("Authorization", generateOAuthHeader(request.getMethodValue(), request.getURI().toString()));
        ClientHttpResponse clientHttpResponse= execution.execute(request, body);
        System.out.println("Client HTTP Response Code: "+clientHttpResponse.getStatusCode());
        System.out.println("Client HTTP Response Body: "+clientHttpResponse.getBody().toString());
        return clientHttpResponse;
    }

    private String generateOAuthHeader(String method, String apiUrl) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = generateNonce();
        String signatureBaseString = method + "&" + percentEncode(apiUrl) ;

        String signingKey = percentEncode(consumerSecret) + "&" + percentEncode(tokenSecret);

        @SuppressWarnings("deprecation")
		byte[] signatureBytes = HmacUtils.hmacSha256(signingKey.getBytes(StandardCharsets.UTF_8), signatureBaseString.getBytes(StandardCharsets.UTF_8));
        String signature = new String(Base64.encodeBase64(signatureBytes), StandardCharsets.UTF_8);
        return "OAuth " +
        		"realm=\"5691679_SB1\"," +
                "oauth_consumer_key=\"" + percentEncode(consumerKey) + "\"," +
                "oauth_token=\"" + percentEncode(accessToken) + "\"," +
                "oauth_signature_method=\"HMAC-SHA256\"," +
                "oauth_timestamp=\"" + percentEncode(timestamp) + "\"," +
                "oauth_nonce=\"" + percentEncode(nonce) + "\"," +
                "oauth_signature=\"" + percentEncode(signature) + "\"," +
                //"oauth_signature=\"lCk9LGTBKxdbchnyAMjBXrr9sB0kFsldP6+Ixjv6BI4=\", " +
                //"realm=\"5691679_SB1\"," +
                "oauth_version=\"1\"".trim();
    }

    private String percentEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding value: " + value, e);
        }
    }


    private String generateNonce() {
        // Generate a unique nonce (you may want to implement this more robustly)
        return String.valueOf(System.nanoTime());
    }
}