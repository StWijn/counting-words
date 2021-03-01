package com.stwijn.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.stwijn.word.WordFrequencyAnalyzer;
import com.stwijn.word.WordFrequencyAnalyzerImpl;
import com.stwijn.WordResource;

public class ApplicationConfig extends ResourceConfig{

	public ApplicationConfig() {
		register(WordResource.class);
		register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(WordFrequencyAnalyzerImpl.class)
				.to(WordFrequencyAnalyzer.class);
			}			
			
		});
	}

}
