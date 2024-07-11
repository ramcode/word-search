package com.illumio.interview;

import java.io.*;
import java.util.*;

public class WordSearch {

    /**
     * Method to fetch match count of predefined words from the given input file
     * @param fileLocation - location of inout file with text
     * @param words - map of lower case words and original
     * @return Map - map of word and ma
     * tch count
     * @throws Exception - if exception occurs
     */
    public Map<String, Integer> getMatchCount(String fileLocation, Map<String,String> words) throws Exception{
        Map<String, Integer> matchCountMap = new HashMap<>();
        Map<String, Integer> matchCount = new LinkedHashMap<>();
        InputStream inputStream = getFileAsStream(fileLocation);
        Set<String> wordsToBeMatched = words.keySet();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String processedLine = processLine(line);
                String[] wordsInLine = processedLine.split("\\s+");
                for (String word: wordsInLine){
                    if(wordsToBeMatched.contains(word.toLowerCase())){
                        matchCountMap.put(words.get(word.toLowerCase()), matchCountMap.getOrDefault(words.get(word.toLowerCase()), 0)+1);
                    }
                }
            }
            for (Map.Entry<String, String> entry : words.entrySet()) {
                String predefinedWord = entry.getValue();
                matchCount.put(predefinedWord, matchCountMap.getOrDefault(predefinedWord, 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matchCount;
    }

    private String processLine(String line){
        return line.replaceAll("\\p{Punct}", "");
    }

    private InputStream getFileAsStream(String fileLocation) throws Exception{
        return new FileInputStream(new File(fileLocation));
    }

    /**
     * Method to read pre-defined words from file and create map of lower case to original word
     * @param location - predefined words file location
     * @return Map - map of predefined words in lower case to original word
     * @throws Exception - if exception occurs
     */
    public Map<String,String> processMatchWordsFile(String location) throws Exception{
        Map<String, String> lowerCaseToOriginalWordMap = new LinkedHashMap<>();
        InputStream inputStream = getFileAsStream(location);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lowerCaseToOriginalWordMap.put(line.strip().toLowerCase(), line.strip());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lowerCaseToOriginalWordMap;
    }

    public static void main(String[] args) {
        String predefinedWordsFileLocation = args[0];
        String inputFileLocation = args[1];
        WordSearch wordSearch = new WordSearch();
        try {
            Map<String, String> lowereCaseToOriginalWordMap = wordSearch.processMatchWordsFile(predefinedWordsFileLocation);
            Map<String, Integer> matchCount = wordSearch.getMatchCount(inputFileLocation, lowereCaseToOriginalWordMap);
            System.out.printf("%25s", "Predefined word");
            System.out.printf("\t\t" + "%10s", "Match count");
            for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {
                System.out.printf("\n" + "%25s", entry.getKey());
                System.out.printf("\t\t" + "%10s", entry.getValue());
            }
        } catch (Exception ex){
            System.out.println("Exception: "+ex.getMessage());
        }
    }
}