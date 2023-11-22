package com.netsuite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

@Data
@Configuration
public class OAuth1Config {

	@Value("${oauth.consumerKey}")
	private String consumerKey;

	@Value("${oauth.consumerSecret}")
	private String consumerSecret;

	@Value("${oauth.accessToken}")
	private String accessToken;

	@Value("${oauth.accessTokenSecret}")
	private String accessTokenSecret;
	
	@Value("${oauth.importInvoice.apiUrl}")
	private String importInvoiceAPIUrl;
	
	@Bean
    public OAuthConsumer oAuthConsumer() {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        return consumer;
    }
}
