package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String FILE_PATH = "10m.txt";
    private static final String RES_FILE_PATH = "res.txt";

    public static void main(String[] args) {
        DataFetcher fetcher = new DataFetcher(FILE_PATH);
        List<Integer> data = fetcher.getData();
        DataProcessor dataProcessor = new DataProcessor(data);
        dataProcessor.writeToFile(RES_FILE_PATH);
    }
}