package com.stwijn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.stwijn.word.*;

@TestInstance(Lifecycle.PER_CLASS)
class WordResourceTest extends JerseyTest {
	
	@Override
	protected Application configure() {		
		ResourceConfig config = new ResourceConfig(WordResource.class);
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(WordFrequencyAnalyzerImpl.class)
				.to(WordFrequencyAnalyzer.class).in(Singleton.class);
			}
		});		
		return config;
	}	
	
	@Test
	public void testJSON_calculateHighestFrequency() {
		Response response = target("/calc/The sun shines over the lake").request(MediaType.APPLICATION_JSON).get();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus(), "HTTP status code should be 200(OK)");
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE), "should return JSON file format (calculateHighestFrequency)");		
	}	
	
	@Test
	public void testCalculateHighestFrequency() {
		final String response = target("/calc/The sun shines over the lake").request().get(String.class);
		assertEquals("2", response);
	}
	
	@Test
	public void testJSON_calculateMostFrequentNWords() {				
		Response response = target("/calc/The sun shines over the lake/2").request(MediaType.APPLICATION_JSON).get();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus(), "HTTP status code should be 200(OK)");		
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE), "should return JSON file format (calculateMostFrequentNWords)");
	}	
	
	@Test
	public void testCalculateMostFrequentNWords() {		
		List<WordFrequencyImpl> list = (List<WordFrequencyImpl>) target("/calc/The sun shines over the lake/3").request()
				.get(new GenericType<List<WordFrequencyImpl>>() {});
		
		for (WordFrequencyImpl w : list) {
			assertTrue((w instanceof WordFrequency), "returned list should be of WordFrequency objects");
		}	
		
		assertEquals(3, list.size(), "size of list should be 3");		
	}	
	
	@Test
	public void testJSON_calculateFrequencyForWord() {
		Response response = target("/calc/forword/The sun shines over the lake/the").request(MediaType.APPLICATION_JSON).get();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus(), "HTTP status code should be 200(OK)");
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE), "should return JSON file format (calculateFrequencyForWord)");
	}	
	
	@Test
	public void testCalculateFrequencyForWord() {
		final String response = target("/calc/forword/The sun shines over the lake/sun").request().get(String.class);
		assertEquals("1", response);
	}	

	@BeforeAll
	public void before() throws Exception {
	    super.setUp();
	}
	
	@AfterAll
	public void after() throws Exception {
	    super.tearDown();
	}
	
}
