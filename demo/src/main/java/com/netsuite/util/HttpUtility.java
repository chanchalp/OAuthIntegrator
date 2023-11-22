/*
 * package com.netsuite.util;
 * 
 * import java.io.BufferedReader; import java.io.IOException; import
 * java.io.InputStream; import java.io.InputStreamReader; import
 * java.io.StringWriter; import java.net.HttpURLConnection; import java.net.URI;
 * import java.net.URISyntaxException; import java.net.URL; import
 * java.net.URLEncoder; import java.net.http.HttpResponse; import
 * java.util.Iterator; import java.util.Map;
 * 
 * import org.apache.commons.io.IOUtils; import
 * org.apache.commons.lang3.StringUtils; import org.apache.http.HttpHost; import
 * org.apache.http.client.methods.HttpEntityEnclosingRequestBase; import
 * org.apache.http.client.methods.HttpPost; import
 * org.apache.http.client.methods.HttpRequestBase; import
 * org.apache.http.impl.client.DefaultHttpClient; import
 * org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.netsuite.config.OAuth1Config;
 * 
 * import oauth.signpost.OAuthConsumer; import
 * oauth.signpost.commonshttp.CommonsHttpOAuthConsumer; import
 * oauth.signpost.exception.OAuthCommunicationException; import
 * oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
 * 
 *//**
	 * This class encapsulates methods for requesting a server via HTTP GET/POST and
	 * provides methods for parsing response from the server.
	 *//*
		 * public class HttpUtility {
		 * 
		 * private OAuthConsumer oAuthConsumer; private static String realmID = null;
		 * 
		 * @Autowired private OAuth1Config oAuthConfig; private String consumerKey =
		 * "4c60cd806d3250439741d63350f39b17778d0451ca9dd8fb3c55ab30a770f17d"; private
		 * String consumerSecret =
		 * "2418a067a1fd0f10974594132f8ad15186ba42806910045c055703ee578f1f83"; private
		 * String accessToken=
		 * "c951482458bc35d86152be15acfc6920bc3ead4dfa06ae1240e5afcda96c94c3"; private
		 * String accessTokenSecret =
		 * "d3def791c9a2d52bd5b7db444ce355648a796ae3dde2db2e9c4bf5bf71958d7c";
		 * 
		 * 
		 * 
		 * public static String postRequest(String requestURL, String payload) throws
		 * IOException { String output = null; DefaultHttpClient client = new
		 * DefaultHttpClient();
		 * client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		 * 
		 * HttpRequestBase httpRequest = null; URI uri = null;
		 * 
		 * try { uri = new URI(requestURL); } catch (URISyntaxException e) {
		 * e.printStackTrace(); } httpRequest = new HttpPost(uri);
		 * 
		 * httpRequest.addHeader("content-type", "application/json");
		 * httpRequest.addHeader("Accept", "application/json");
		 * 
		 * try { new HttpUtility().authorize(httpRequest); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * HttpResponse httpResponse = null; try { HttpHost target = new
		 * HttpHost(uri.getHost(), -1, uri.getScheme()); httpResponse = (HttpResponse)
		 * client.execute(target, httpRequest);
		 * System.out.println("Connection status : " + ((org.apache.http.HttpResponse)
		 * httpResponse).getStatusLine());
		 * 
		 * InputStream inputStraem = ((HttpEntityEnclosingRequestBase)
		 * httpResponse).getEntity().getContent();
		 * 
		 * StringWriter writer = new StringWriter(); IOUtils.copy(inputStraem, writer,
		 * "UTF-8"); output = writer.toString();
		 * 
		 * System.out.println(output); } catch (Exception e) { e.printStackTrace(); }
		 * return output; }
		 * 
		 * public void authorize(HttpRequestBase httpRequest) throws Exception { try {
		 * this.oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey,
		 * consumerSecret); oAuthConsumer.setTokenWithSecret(accessToken,
		 * accessTokenSecret); oAuthConsumer.setSigningStrategy(new
		 * AuthorizationHeaderSigningStrategy()); oAuthConsumer.sign(httpRequest); }
		 * catch (OAuthCommunicationException e) { throw new Exception(e); } }
		 * 
		 * }
		 */