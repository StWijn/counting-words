package com.stwijn.word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordFrequencyAnalyzerImplTest {

	WordFrequencyAnalyzer analyzerObj;

	@BeforeEach
	void init() {
		analyzerObj = new WordFrequencyAnalyzerImpl();
	}

	@ParameterizedTest
	@ValueSource(strings = { "The sun shines over the lake", "the sun shines over the lake", "tHE sun shines over the lake", "The;sun;shines;over;the;lake;",
			"The ; sun, . shines ? over! the, lake:", "the! sun Shines over thE? lake", "the    sun    shines    over    the    lake", 
			"the,sun,shines,over,the,lake", "the.sun.shines.over.the.lake." })
	void testFunctionalityCaseSensitivityAndSeperatorsOf__calculateHighestFrequency(String text) {

		assertEquals(analyzerObj.calculateHighestFrequency(text), 2, "failure, the word \"the\" appears twice and thus this method should return 2");
	}

	@ParameterizedTest
	@ValueSource(strings = { "The sun shines over the lake", "the sun shines over the lake", "tHE sun shines over the lake", "The;sun;shines;over;the;lake;",
			"The ; sun, . shines ? over! the, lake:", "the! sun Shines over thE? lake", "the    sun    shines    over    the    lake", 
			"the,sun,shines,over,the,lake", "the.sun.shines.over.the.lake." })
	void testFunctionalityCaseSensitivityAndSeperatorsOf__calculateFrequencyForWord(String text) {

		assertEquals(analyzerObj.calculateFrequencyForWord(text, "the"), 2,
				"failure, the word \"the\" appears twice and thus method should return 2");
		assertEquals(analyzerObj.calculateFrequencyForWord(text, "sun"), 1,
				"failure, the word \"sun\" appears once and thus method should return 1");
		assertEquals(analyzerObj.calculateFrequencyForWord(text, "lake"), 1,
				"failure, the word \"lake\" appears once and thus method should return 1");
	}

	@ParameterizedTest
	@ValueSource(strings = { "The sun shines over the lake", "the sun shines over the lake", "tHE sun shines over the lake", "The;sun;shines;over;the;lake;",
			"The ; sun, . shines ? over! the, lake:", "the! sun Shines over thE? lake", "the    sun    shines    over    the    lake", 
			"the,sun,shines,over,the,lake", "the.sun.shines.over.the.lake." })
	void testFunctionalityCasesSeperatorsAndListFunctionalityOf__calculateFrequencyForWord(String text) {

		WordFrequency wordFreq1 = new WordFrequencyImpl("the", 2);
		WordFrequency wordFreq2 = new WordFrequencyImpl("lake", 1);
		WordFrequency wordFreq3 = new WordFrequencyImpl("over", 1);

		List<WordFrequency> expectedList = new ArrayList<>();
		expectedList.add(wordFreq1);
		expectedList.add(wordFreq2);
		expectedList.add(wordFreq3);

		List<WordFrequency> actualWordFrequencyList = analyzerObj.calculateMostFrequentNWords(text, 3);

		for (int i = 0; i < actualWordFrequencyList.size(); i++) {
			WordFrequency expectedWordFrequencyObject = expectedList.get(i);

			assertEquals(expectedWordFrequencyObject.getWord(), actualWordFrequencyList.get(i).getWord(),
					"the expected and actual word are not the same");
			assertEquals(expectedWordFrequencyObject.getFrequency(), actualWordFrequencyList.get(i).getFrequency(),
					"the expected and actual frequency are not the same");
		}
	}

	@Test
	void testExceptionOf_calculateFrequencyForWord() {
		assertThrows(IllegalArgumentException.class,
				() -> analyzerObj.calculateFrequencyForWord("The sun shines over the lake", "th"),
				"should throw IllegalArgumentException for word: \"th\"");
	}

	@Test
	void testExceptionOf_calculateMostFrequentNWords() {
		assertThrows(IllegalArgumentException.class, () -> analyzerObj.calculateMostFrequentNWords("The sun shines over the lake", 0),
				"should throw IllegalArgumentException for zero and below");
	}

	@Test
	void testExceptionOf_calculateMostFrequentNWords2() {
		assertThrows(IllegalArgumentException.class, () -> analyzerObj.calculateMostFrequentNWords("The sun shines over the lake", -5),
				"should throw IllegalArgumentException for zero and below");
	}

	@Test
	void testExceptionOf_calculateMostFrequentNWords3() {
		assertThrows(IndexOutOfBoundsException.class,
				() -> analyzerObj.calculateMostFrequentNWords("The sun shines over the lake", 6),
				"should throw IndexOutOfBoundsException. Be reminded that if a word occurs more than once, the \"n\" has to be less than the total number of words");
	}

}
