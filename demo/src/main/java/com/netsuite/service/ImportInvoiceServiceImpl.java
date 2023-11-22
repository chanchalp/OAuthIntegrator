package com.netsuite.service;

import java.io.IOException;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netsuite.config.OAuth1Config;
import com.netsuite.model.ImportInvoiceRequest;
import com.netsuite.model.ImportInvoiceResponse;

import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ImportInvoiceServiceImpl implements ImportInvoiceService {
	
	@Autowired
	OAuth1Config oAuth1Config;
	
	@Autowired
	OAuthConsumer oAuthConsumer;
	

	@Override
	public ImportInvoiceResponse callImportInvoiceAPI(ImportInvoiceRequest importInvoiceReq) throws IOException {
		ImportInvoiceResponse response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			
	        // Create an instance of OAuth1RequestInterceptor with your OAuth credentials
	        OAuth1RequestInterceptor oauthInterceptor = new OAuth1RequestInterceptor(
	        		oAuth1Config.getConsumerKey(), oAuth1Config.getConsumerSecret(), oAuth1Config.getAccessToken(), oAuth1Config.getAccessTokenSecret());

	        // Create a RestTemplate and add the OAuth1RequestInterceptor as an interceptor
	        restTemplate.getInterceptors().add(new OAuth1RequestInterceptor(oAuth1Config.getConsumerKey(), oAuth1Config.getConsumerSecret(), oAuth1Config.getAccessToken(), oAuth1Config.getAccessTokenSecret()));

	       // Set the timeout for the restTemplate
	        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	        requestFactory.setConnectTimeout(100000);  // 100 seconds
	        requestFactory.setReadTimeout(100000);     // 100 seconds
	        restTemplate.setRequestFactory(requestFactory);
	        restTemplate.setInterceptors(Collections.singletonList(oauthInterceptor));
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        String jsonPayload = new ObjectMapper().writeValueAsString(importInvoiceReq);
	        // Note: You don't need to add the Authorization header explicitly here
	        // The OAuth1RequestInterceptor will automatically add it to the request

	        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
	        
	        System.out.println("Request Headers: " + requestEntity.getHeaders());
	        System.out.println("Request Body: " + requestEntity.getBody());
	        // Make the POST request
	        ResponseEntity<ImportInvoiceResponse> responseEntity = restTemplate.exchange(
	        		oAuth1Config.getImportInvoiceAPIUrl(),
	                HttpMethod.POST,
	                requestEntity,
	                ImportInvoiceResponse.class
	        );

	        // Handle the response as needed
	       // String responseBody = responseEntity.getBody();
	        if (responseEntity.getStatusCode() == HttpStatus.OK) {
	            // Successful response
	             response = responseEntity.getBody();
	            // Process the response as needed
	        } else {
	            // Handle error
	            System.out.println("Error: " + responseEntity.getStatusCode());
	            System.out.println("Response: " + responseEntity.getBody());
	        }
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * 
	 * @param consumerKey
	 * @param consumerSecret
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public void setupContext(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		this.oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
		oAuthConsumer.setTokenWithSecret(accessToken, accessTokenSecret);
		oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
	}

	/**
	 * 
	 * @param httpRequest
	 * @throws Exception
	 */
	   public void authorize(HttpRequestBase httpRequest) throws Exception {
	        try {
	            oAuthConsumer.sign(httpRequest);
	        } catch (OAuthMessageSignerException e) {
	            throw new Exception(e);
	        } catch (OAuthExpectationFailedException e) {
	            throw new Exception(e);
	        } catch (OAuthCommunicationException e) {
	            throw new Exception(e);
	        }
	    }

}
