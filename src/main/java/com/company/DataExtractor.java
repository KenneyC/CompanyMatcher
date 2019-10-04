package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        HashMap<String,TreeMap<String,String>> lines = new HashMap<>();
        String line = "";
        while((line = br.readLine()) != null) {
            Map<String,String> data = mapper.readValue(line,Map.class);
            String lineid = data.get("line_id");
            String word = data.get("word");
            String posid = data.get("pos_id");
            TreeMap<String,String> map = new TreeMap<>();
            if(lines.get(lineid) != null) {
                map = lines.get(lineid);
            }
            map.put(posid,word);
            lines.put(lineid,map);
        }
        List<List<String>> result = new ArrayList<>();
        for(String s : lines.keySet()) {
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
            String newCombination = currentWord + " " + words.get(i);
            findCombinations(words,combinations,i+1,newCombination);
        }
    }

}

