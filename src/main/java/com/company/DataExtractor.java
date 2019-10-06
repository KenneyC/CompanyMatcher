package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataExtractor {
    private BufferedReader br;
    ObjectMapper mapper = new ObjectMapper();

    public Set<String> readCompanyData(String file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        HashSet<String> companies = new HashSet<>();
        String line = "";
        br.readLine();
        while((line = br.readLine()) != null) {
            String[] data = line.split(",");
            companies.add(data[1]);
        }
        return companies;
    }

    public List<List<String>> readInvoiceData(String file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        HashMap<Integer,TreeMap<Integer,String>> lines = new HashMap<>();
        String line = "";
        while((line = br.readLine()) != null) {
            line = line.replaceAll("'","\"");
            OCRLine data = mapper.readValue(line,OCRLine.class);
            Integer lineid = data.line_id;
            String word = data.word;
            Integer posid = data.post_id;
            TreeMap<Integer,String> map = new TreeMap<>();
            if(lines.get(lineid) != null) {
                map = lines.get(lineid);
            }
            map.put(posid,word);
            lines.put(lineid,map);
        }
        List<List<String>> result = new ArrayList<>();
        for(Integer s : lines.keySet()) {
            ArrayList<String> words = new ArrayList<>(lines.get(s).values());
            List<String> combination = new ArrayList<>();
            findCombinations(words,combination,0,"");
            result.add(combination);
        }
        return result;
    }

    public void findCombinations(List<String> words, List<String> combinations, int wordIndex, String currentWord) {
        if (!currentWord.equals("")) combinations.add(currentWord);
        for(int i =wordIndex;i<words.size();i++) {
            String newCombination = "";
            if(currentWord.length() > 0) {
                newCombination = currentWord + " " + words.get(i);
            } else {
                newCombination = words.get(i);
            }
            findCombinations(words,combinations,i+1,newCombination);
        }
    }

    static private class OCRLine {
        @JsonProperty("pos_id")
        public Integer post_id;
        @JsonProperty("cspan_id")
        public Integer cspan_id;
        @JsonProperty("rspan_id")
        public Integer rspan_id;
        @JsonProperty("right")
        public Integer right;
        @JsonProperty("top")
        public Integer top;
        @JsonProperty("height")
        public Integer height;
        @JsonProperty("width")
        public Integer width;
        @JsonProperty("left")
        public Integer left;
        @JsonProperty("page_id")
        public Integer page_id;
        @JsonProperty("word_id")
        public Integer word_id;
        @JsonProperty("word")
        public String word;
        @JsonProperty("line_id")
        public Integer line_id;
    }

}

