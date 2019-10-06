package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        DataExtractor extractor = new DataExtractor();
        Set<String> companyData = new HashSet<>();
        List<List<String>> invoiceData = new ArrayList<>();
        try {
            invoiceData = extractor.readInvoiceData(args[0]);
            companyData = extractor.readCompanyData(args[1]);
        } catch (Exception e) {
            System.out.print(e);
        }

        CompanyMatcher cm = new CompanyMatcher(companyData);
        for(String s : cm.findMatch(invoiceData)) {
            System.out.println("Company found matched: " + s);
        }
    }
}
