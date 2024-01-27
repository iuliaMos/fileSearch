package com.filesearch;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

public class FileSearchHandler {

    private final String selectedFilePath;
    private final String keyBeginning;
    private final String keyInside;
    private final String keyEnd;
    private int lineNr = 0;

    public FileSearchHandler(String selectedFilePath, String keyBeginning, String keyInside, String keyEnd) {
        this.selectedFilePath = selectedFilePath;
        this.keyBeginning = Optional.ofNullable(keyBeginning).map(String::toLowerCase).orElse(null);
        this.keyInside = Optional.ofNullable(keyInside).map(String::toLowerCase).orElse(null);
        this.keyEnd = Optional.ofNullable(keyEnd).map(String::toLowerCase).orElse(null);
    }

    public void doSearchAndReturn() {
        File outFile = getValidFile();
        try (BufferedReader br = new BufferedReader(new FileReader(this.selectedFilePath));
             FileWriter fw = new FileWriter(outFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                String lineLowerCase = line.toLowerCase().trim();

                if (lineLowerCase.matches("[0-9]+") && lineLowerCase.length() > 3) {
                    writeToFile(fw, line);
                    continue;
                }

                if (this.keyBeginning != null) {
                    boolean key1IsFound = lineLowerCase.startsWith(keyBeginning);
                    if (key1IsFound) {
                        writeToFile(fw, line.trim().substring(keyBeginning.length()));
                    }
                }
                if (this.keyInside != null) {
                    int indexKey2 = lineLowerCase.indexOf(keyInside);
                    if (indexKey2 >= 0) {
                        writeToFile(fw, line);
                    }
                }
                if (this.keyEnd != null) {
                    boolean key3IsFound = lineLowerCase.endsWith(keyEnd);
                    if (key3IsFound) {
                        writeToFile(fw, line);
                    }
                }
                this.lineNr++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(FileWriter fw, String str) {
        try {
            fw.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getValidFile() {
        String pathOut = Paths.get(this.selectedFilePath).getParent().toString();
        String outFileName = "out.found.txt";
        File outFile = new File(pathOut + "/" + outFileName);
        boolean exists = outFile.exists();
        int i = 1;
        while (exists) {
            outFile = new File(pathOut + "/" + "out.found" + i + ".txt");
            i++;
            exists = outFile.exists();
        }
        return outFile;
    }
}
