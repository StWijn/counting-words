package com.stwijn.word;

	public class WordFrequencyImpl implements WordFrequency {
	    private String word;
	    private int frequency;

	    public WordFrequencyImpl(String word, int frequency) {
	        this.word = word;
	        this.frequency = frequency;
	    }
	    
	    public WordFrequencyImpl() {}

	    @Override
	    public String getWord() {
	        return word;
	    }

	    @Override
	    public int getFrequency() {
	        return frequency;
	    }
	}
