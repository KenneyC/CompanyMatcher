# Company Matcher

## How to run

1. Clone the project on the command line with `git clone https://github.com/KenneyC/CompanyMatcher.git`
2. Copy and paste `CompanyMatcher.jar` to your chosen directory
3. Make sure that the invoice text file and the supplier text file are in the same directory as the jar file.
4. Make sure you can run Java on your system
5. On you command line, in the same directory as the three files, run: 
```
java -jar CompanyMatcher.jar nameofinvoicefile.txt nameofsuppliernamefile.txt
```
Make sure that the invoice text file is the first argument after CompanyMatcher.jar, and the supplier name text file is the second argument after.

6. On the command line, it should print the company names it has found that matches the words of invoice file.
