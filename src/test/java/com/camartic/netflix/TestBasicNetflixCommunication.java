package com.camartic.netflix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.camartic.api.NetflixAPI;

public class TestBasicNetflixCommunication {
	
	private final static String CONSUMER_KEY = "azj736e58w5pf3uue3kspygr";
	private final static String CONSUMER_SECRET = "BK86sF48fe";
	private final static String AUTH_URL = "http://api.netflix.com/oauth/request_token";
	private final static String APP_NAME = "Netflix+Quick+Browser";
	private final static String CALLBACK = "OOB";
	private static final Token EMPTY_TOKEN = null;

	@Test
	public void testTitleRequest_noOauth_titleList() throws IOException{
		URL url = new URL("http://api.netflix.com/catalog/titles");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String response = readResponse(conn);
		System.out.println(response);
	}
	
	/**
	 * GET http://api.netflix.com/oauth/request_token&oauth_consumer_key=YourConsumerKey&oauth_nonce=nonce&oauth_signature_method=HMAC-SHA1&oauth_timestamp=timestamp&oauth_version=1.0&oauth_signature=YourSignature
	 * @throws IOException
	 */
	@Test
	public void testOauthLogin_normalLogin_successfulLogin() throws IOException{
		Scanner in = new Scanner(System.in);
		OAuthService service = new ServiceBuilder()
        .provider(NetflixAPI.class)
        .apiKey(CONSUMER_KEY)
        .apiSecret(CONSUMER_SECRET)
        .build();
		URL url = new URL(AUTH_URL);
		
		Token requestToken = service.getRequestToken();
		String authUrl = NetflixAPI.getAuthorizationUrlNew(requestToken, APP_NAME, CALLBACK, CONSUMER_KEY);
		
		System.out.println("Please login using the following url: "+'\n'+authUrl);
		System.out.println("Please paste the authorization code here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
		
	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    // http://api.netflix.com/oauth/access_token?oauth_consumer_key=YourConsumerKey&oauth_signature_method=HMAC-SHA1&oauth_timestamp=timestamp&oauth_token=YourAuthorizedToken&oauth_nonce=nonce&oauth_version=1.0&oauth_signature=YourSignature
	    
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    /**
	    request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getCode());
	    System.out.println(response.getBody());

	    System.out.println();
	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	    */
	}
	
	/**
	 * http://api.netflix.com/catalog/titles/autocomplete?oauth_consumer_key=YourConsumerKey&term=frances%20mc
	 * @throws IOException
	 */
	@Test
	public void testNonAuthenticatedRequest_usingAutoComplete_loginSuccess() throws IOException{
		URL url = new URL("http://api.netflix.com/catalog/titles");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
	}
	
	private String readResponse(HttpURLConnection conn) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String decodedString = null;
		StringBuilder response = new StringBuilder();
	
		while ((decodedString = in.readLine()) != null) {
			response.append(decodedString);
		}
		//in.close();
		return response.toString();
	}
	
}
