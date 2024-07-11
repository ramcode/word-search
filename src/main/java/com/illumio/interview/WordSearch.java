package com.illumio.interview;

import java.io.*;
import java.util.*;

public class WordSearch {

    public Map<String, Integer> getMatchCount(String fileLocation, Map<String,String> words){
        Map<String, Integer> matchCountMap = new HashMap<>();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matchCountMap;
    }

    public String processLine(String line){
        return line.replaceAll("\\p{Punct}", "");
    }

    public InputStream getFileAsStream(String fileLocation){
        return this.getClass().getResourceAsStream(fileLocation);
    }

    public Map<String,String> processMatchWordsFile(String location){
        Map<String, String> lowerCaseToOriginalWordMap = new HashMap<>();
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
        String predefinedWordsFileLocation = "/predefined_words.txt";
        String inputFileLocation = "/input_file.txt";
        WordSearch wordSearch = new WordSearch();
        Map<String, String> lowereCaseToOriginalMap = wordSearch.processMatchWordsFile(predefinedWordsFileLocation);
        Map<String, Integer> matchCount = wordSearch.getMatchCount(inputFileLocation, lowereCaseToOriginalMap);
        System.out.println("Predefined word" +"\t\t"+"Match count");
        for (Map.Entry<String, Integer> entry : matchCount.entrySet()){
            System.out.printf("\n"+"%"+"Predefined word".length()+"s",entry.getKey()+"\t\t"+entry.getValue());
        }
    }
}