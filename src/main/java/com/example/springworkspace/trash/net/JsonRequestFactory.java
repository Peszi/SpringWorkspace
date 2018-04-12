package com.example.springworkspace.trash.net;

import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;

public class JsonRequestFactory implements ResponseErrorHandler {

    private String serverIp;

    private RestTemplate restTemplate;
    private HttpEntity<?> httpEntity;

    public JsonRequestFactory(String serverIp) {
        this.serverIp = serverIp;
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(this);
        this.restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        this.httpEntity = new HttpEntity<>(httpHeaders);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println(response.getStatusCode().value());
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println(response.getStatusCode().value());
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        System.out.println(response.getStatusCode().value());
    }

    public <T> HttpStatus makeRequestForResult(String path, HttpMethod method, Class<T> responseType, Param... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.serverIp + path);
        Arrays.stream(params).forEach(param -> builder.queryParam(param.getKey(), param.getValue()));
        return this.makeRequest(path, method, builder, responseType).getStatusCode();
    }

    public ResponseEntity<String> makeRequest(String path, HttpMethod method, Param... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.serverIp + path);
        Arrays.stream(params).forEach(param -> builder.queryParam(param.getKey(), param.getValue()));
        return this.makeRequest(path, method, builder, String.class);
    }

    public <T> ResponseEntity<T> makeRequest(String path, HttpMethod method, Class<T> responseType, Param... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.serverIp + path);
        Arrays.stream(params).forEach(param -> builder.queryParam(param.getKey(), param.getValue()));
        return this.makeRequest(path, method, builder, responseType);
    }

    public <T> ResponseEntity<T> makeRequest(String path, HttpMethod method, Class<T> responseType, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.serverIp + path);
        params.forEach((k, v) -> builder.queryParam(k, v));
        return this.makeRequest(path, method, builder, responseType);
    }

    private <T> ResponseEntity<T> makeRequest(String path, HttpMethod method, UriComponentsBuilder componentsBuilder, Class<T> responseType) {
        try {
            return this.restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, this.httpEntity, responseType);
        } catch (RestClientException e) {
            System.err.println("Request failed! " + e.getMessage());
        } catch (NullPointerException e){
            System.err.println("Build request factory first!");
        }
        return null;
    }
}
