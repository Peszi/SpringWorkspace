package com.example.springworkspace.trash;

public class Depreciated {
//    String url = "http://localhost:8080/user/login";
//    RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//
//    HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//            .queryParam("username", "John")
//            .queryParam("password", "1234");
//
//    HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        try {
//        HttpEntity<UserDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserDTO.class);
//        System.out.println("REQUEST id" + response.getBody().toString());
//    } catch (RestClientException e) {
//        System.err.println("REQUEST FAILED");
////            e.printStackTrace();
//    }
//JsonRequestFactory jsonRequestFactory = new JsonRequestFactory(SERVER_IP);
//
//    final String username = "John";
//    final String password = "1234";
//
//    ResponseEntity<UserDTO> userDataResult;
//
//    ResponseEntity<String> registerResult = jsonRequestFactory.makeRequest("user/register", HttpMethod.POST, new Param("username", username), new Param("password", password));
//        if (!registerResult.getStatusCode().isError()) {
//        System.out.println("Success");
//    }
//        System.out.println("Data " + registerResult.getBody());
//
//    String api = "";
//
//    userDataResult = jsonRequestFactory.makeRequest("user/login", HttpMethod.POST, UserDTO.class, new Param("username", username), new Param("password", password));
//        if (!userDataResult.getStatusCode().isError()) {
//        System.out.println("Data " + userDataResult.getBody().toString());
//        api = userDataResult.getBody().getApiKey();
//    } else {
//        System.out.println("Message " + userDataResult.getBody().getMessage());
//    }
//
//    userDataResult = jsonRequestFactory.makeRequest("user/login", HttpMethod.POST, UserDTO.class, new Param("username", username), new Param("password", "asd"));
//        if (!userDataResult.getStatusCode().isError()) {
//        System.out.println("Data " + userDataResult.getBody().toString());
//    } else {
//        System.out.println("Error " + userDataResult.getBody().getMessage());
//    }
}
