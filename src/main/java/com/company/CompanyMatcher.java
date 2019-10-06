package com.company;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CompanyMatcher {
    Set<String> _companies;
    public CompanyMatcher(Set<String> companies) {
        _companies = companies;
    }

    public List<String> findMatch(List<List<String>> data) {
        ArrayList<String> matches = new ArrayList<>();

        for(List<String> strings : data) {
            for(String s : strings) {
                if(_companies.contains(s)) matches.add(s);
            }
        }

        return matches;
    }
}

