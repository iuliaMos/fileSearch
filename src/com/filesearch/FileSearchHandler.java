package com.filesearch;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

public class FileSearchHandler {

    private final String selectedFilePath;
    private final String keyString;
    private int lineNr = 0;

    public FileSearchHandler(String selectedFilePath, String text) {
        this.selectedFilePath = selectedFilePath;
        this.keyString = Optional.ofNullable(text).map(String::toLowerCase).orElse("-");
    }

    public void doSearchAndReturn() {
        File outFile = getValidFile();
        int length = this.keyString.length();
        try (BufferedReader br = new BufferedReader(new FileReader(this.selectedFilePath));
             FileWriter fw = new FileWriter(outFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                int index = line.toLowerCase().indexOf(this.keyString);
                if (index >= 0) {
                    writeToFile(fw, line.substring(index + length));
                } else {
                    String text = line.trim();
                    if (text.matches("[0-9]+") && text.length() > 3) {
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
