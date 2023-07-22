package com.rentalapp.selectcountry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.rentalapp.exception.RentalAppException;



public abstract class BaseClient<V> {

	private final Logger logger = LoggerFactory.getLogger(BaseClient.class);

	public static final String ACCEPT = "Accept";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String AUTHORIZATION = "Authorization";
	public static final String API_KEY = "Apikey ";

	@Value("${rental.app.apikey}")
	private String apiKey;

	protected abstract RestTemplate getRestTemplate();

	protected Object postRequestForResponse(String endpoint, String postBody, Class<V> responseType) {
		HttpEntity<String> httpEntity = getHttpEntity(postBody);
		return callRestTemplateOnlyStatusCode(endpoint, HttpMethod.POST, httpEntity, responseType);
	}

	protected V putRequestForResponse(String endpoint, String putBody, Class<V> responseType) {
		HttpEntity<String> httpEntity = getHttpEntity(putBody);
		return callRestTemplate(endpoint, HttpMethod.PUT, httpEntity, responseType);
	}

	protected V deleteRequestForResponse(String endpoint, Class<V> responseType) {
		HttpEntity<String> httpEntity = getHttpEntity(null);
		return callRestTemplate(endpoint, HttpMethod.DELETE, httpEntity, responseType);
	}

	protected V getRequestForResponse(String endpoint, Class<V> responseType) {
		HttpEntity<String> httpEntity = getHttpEntity(null);
		return callRestTemplate(endpoint, HttpMethod.GET, httpEntity, responseType);
	}

	private V callRestTemplate(String endpoint, HttpMethod httpMethod, HttpEntity<?> httpEntity,
			Class<V> responseType) {
		UriComponents uri = UriComponentsBuilder.fromUriString(endpoint).build();

		try {
			logger.info(
					"Request to rest template api: \nHTTPMethod= {}, \nURL={}, \nHTTPHeaders={}, \nHTTP Request body = {}",
					httpMethod, endpoint, httpEntity.getHeaders(), httpEntity.getBody());

			ResponseEntity<V> response = getRestTemplate().exchange(uri.toUri(), httpMethod, httpEntity, responseType);
			logger.info("Response from rest template api: \nStatus Code :{}, \nResponseBody: {}",
					response.getStatusCodeValue(), response.getBody());

			return response.getBody();
		} catch (RestClientResponseException exception) {

			throw new RestClientResponseException(exception.getMessage(), exception.getRawStatusCode(),
					exception.getStatusText(), exception.getResponseHeaders(), exception.getResponseBodyAsByteArray(),
					null);
		} catch (ResourceAccessException exception) {
			throw new RentalAppException("Exception occured " + exception.getMessage());
		} catch (Exception exception) {
			throw new RentalAppException("Error: " + exception.getMessage());
		}

	}

	private Object callRestTemplateOnlyStatusCode(String endpoint, HttpMethod httpMethod, HttpEntity<?> httpEntity,
			Class<V> responseType) {
		UriComponents uri = UriComponentsBuilder.fromUriString(endpoint).build();

		try {
			logger.info(
					"Request to rest template api: \nHTTPMethod= {}, \nURL={}, \nHTTPHeaders={}, \nHTTP Request body = {}",
					httpMethod, endpoint, httpEntity.getHeaders(), httpEntity.getBody());

			ResponseEntity<V> response = getRestTemplate().exchange(uri.toUri(), httpMethod, httpEntity, responseType);
			logger.info("Response from rest template api: \nStatus Code :{}, \nResponseBody: {}",
					response.getStatusCodeValue(), response.getBody());

			return response.getStatusCodeValue();
		} catch (RestClientResponseException exception) {

			throw new RestClientResponseException(exception.getMessage(), exception.getRawStatusCode(),
					exception.getStatusText(), exception.getResponseHeaders(), exception.getResponseBodyAsByteArray(),
					null);
		} catch (ResourceAccessException exception) {
			throw new RentalAppException("Exception occured " + exception.getMessage());
		} catch (Exception exception) {
			throw new RentalAppException("Error: " + exception.getMessage());
		}

	}

	private void setDefaultHeaders(HttpHeaders httpHeaders) {
		httpHeaders.add(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add(AUTHORIZATION, API_KEY + apiKey);
	}

	private HttpEntity<String> getHttpEntity(String body) {
		HttpHeaders httpHeaders = new HttpHeaders();
		setDefaultHeaders(httpHeaders);

		HttpEntity<String> httpEntity;
		if (body != null)
			httpEntity = new HttpEntity<>(body, httpHeaders);
		else
			httpEntity = new HttpEntity<>(httpHeaders);

		return httpEntity;
	}
}