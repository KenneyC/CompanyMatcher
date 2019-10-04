package com.company;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CompanyMatcher {
    Set<String> _companies;
    ObjectMapper mapper = new ObjectMapper();
    public CompanyMatcher(Set<String> companies) {
        _companies = companies;
    }

    public String findMatch(ArrayList<String> data) {
        String match = "Not found any";
        for(String s : data) {

        }
        return match;
    }


}

