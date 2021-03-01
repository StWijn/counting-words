package com.stwijn;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.stwijn.word.WordFrequency;
import com.stwijn.word.WordFrequencyAnalyzer;

@Path("/calc")
public class WordResource {

	@Inject
	WordFrequencyAnalyzer impl;	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{text}")
	public int calculateHighestFrequency(@PathParam("text") String text) {
		
		return impl.calculateHighestFrequency(text);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{text}/{n}")
	public List<WordFrequency> calculateMostFrequentNWords(@PathParam("text") String text, @PathParam("n") int n) {
		
		return impl.calculateMostFrequentNWords(text, n);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/forword/{text}/{word}")
	public int calculateFrequencyForWord(@PathParam("text") String text, @PathParam("word") String word) {
						
		return impl.calculateFrequencyForWord(text, word);		
	}
}