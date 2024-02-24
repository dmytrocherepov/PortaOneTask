package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class DataFetcher {
    private final String filePath;

    public DataFetcher(String filePath) {
        this.filePath = filePath;
    }

    public List<Integer> getData(){
        try {
            return Files.lines(Path.of(filePath))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException("No such file with filePath : " + filePath);
        }

    }
}
