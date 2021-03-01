package com.stwijn.config;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
 
import org.glassfish.jersey.client.ClientConfig;
 
//als alternatief op Postman of browser om HTTP requests in te dienen (na opstarten van server, deze class runnen als Java Application).
public class TestClient {
 
    public static void main(String[] args) {
        String uri = "http://localhost:8080/counting-words/api/calc/the sun shines over the lake";
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(uri);
         
        String response = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
         
        System.out.println(response);
 
    }
 
}
