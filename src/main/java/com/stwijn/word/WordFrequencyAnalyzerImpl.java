package com.stwijn.word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

	public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

	    @Override
	    public int calculateHighestFrequency(String text) {
	    	//(rekening houdend met requirement van "various separator characters")
	        List<String> wordsList = Arrays.asList(text.split("[ \n\t\r,.;:?!(){]"));

	        Map<String, Long> wordsMap = wordsList.stream().map(word -> word.toLowerCase()).filter(word -> !word.isBlank()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	        return Collections. max(wordsMap.values()).intValue();
	    }

	    @Override
	    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
	    	if (!(n > 0)) {
	            throw new IllegalArgumentException("n must be greater than zero");
	        }

	        List<WordFrequency> tempList = new ArrayList<>();
	        Map<String, Integer> tempMap = new HashMap<>();

	        String[] tempArray = text.toLowerCase().split("[ \n\t\r,.;:?!(){]");

	        for (String word : tempArray) {
	            if (!word.isBlank()) {
	                if (tempMap.containsKey(word)) {
	                    tempMap.put(word, tempMap.get(word) + 1);
	                } else {
	                    tempMap.put(word, 1);
	                }
	            }
	        }

	        tempMap.forEach((k, v) -> tempList.add(new WordFrequencyImpl(k, v)));

	        Comparator<WordFrequency> comp = Comparator
	                .comparing(WordFrequency::getFrequency).reversed()
	                .thenComparing(WordFrequency::getWord);

	        Collections.sort(tempList, comp);

	        return new ArrayList<WordFrequency>(tempList.subList(0, n));
	    }

	    @Override
	    public int calculateFrequencyForWord(String text, String word) {
	    	
	    	List<String> wordsList = Arrays.asList(text.split("[ \n\t\r,.;:?!(){]"));
	        wordsList = wordsList.stream().map(filteredWord -> filteredWord.toLowerCase()).filter(filteredWord -> !filteredWord.isBlank()).collect(Collectors.toList());

	        if (!wordsList.contains(word)) {
	            throw new IllegalArgumentException("are you sure the text contains your specified word: " + word + "?");
	        }

	        Map<String, Long> wordsMap = wordsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	        return wordsMap.get(word).intValue();
	    }
	}