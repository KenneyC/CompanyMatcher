package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataExtractor {
    private BufferedReader br;

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

    public List<String> readInvoiceData(String file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line = "";
        while((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

}

