package com.illumio.interview;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Map;

public class WordSearchTest {

    WordSearch wordSearch;

    @BeforeEach
    void setUp(){
        wordSearch = new WordSearch();
    }

    @Test
    @DisplayName("Read and process predefined words")
    void processMatchWordsFileTest() throws Exception {
        String predefinedWordsFileLocation = this.getClass().getResource("/predefined_words.txt").getPath();
        Map<String, String> processedWords = wordSearch.processMatchWordsFile(predefinedWordsFileLocation);
        assertEquals(3, processedWords.size());
    }

    @Test
    @DisplayName("Get match count for predefined input words")
    void getMatchCountTest() throws Exception {
        String inputFileLocation = this.getClass().getResource("/input_file.txt").getPath();
        String predefinedWordsFileLocation = this.getClass().getResource("/predefined_words.txt").getPath();
        Map<String, Integer> matchCount = wordSearch.getMatchCount(inputFileLocation, wordSearch.processMatchWordsFile(predefinedWordsFileLocation));
        assertEquals(3, matchCount.size());
        assertThat(matchCount.keySet(), Matchers.hasItems("Name", "Detect", "AI"));
        assertThat(matchCount, Matchers.hasEntry("Name", 2));
        assertThat(matchCount, Matchers.hasEntry("Detect", 0));
        assertThat(matchCount, Matchers.hasEntry("AI", 1));
    }
}
