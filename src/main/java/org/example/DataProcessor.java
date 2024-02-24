package org.example;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataProcessor {
    private final List<Integer> nums;

    public DataProcessor(List<Integer> nums) {
        this.nums = nums;
    }

    public int getMax() {
        return Collections.max(nums);
    }

    public int getMin() {
        return Collections.min(nums);
    }

    public double getMedian() {
        List<Integer> sortedNums = nums.stream().sorted().toList();
        int size = sortedNums.size();
        int half = size / 2;
        if (size % 2 == 0) {
            return sortedNums.get(half) + sortedNums.get(half + 1);
        }
        return sortedNums.get(half);
    }

    public double getAvg() {
        return nums.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);
    }

    public List<Integer> findLongestIncreasingSequence() {
        List<Integer> res = new ArrayList<>();
        int currentStart = 0;
        int currentLength = 1;
        int maxStart = 0;
        int maxLength = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) >= nums.get(i - 1)) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = currentStart;
                }
            } else {
                currentStart = i;
                currentLength = 1;
            }

        }
        for (int i = maxStart; i < maxStart + maxLength; i++) {
            res.add(nums.get(i));
        }
        return res;
    }

    public List<Integer> findLongestDescendingSequence() {
        List<Integer> res = new ArrayList<>();
        int currentStart = 0;
        int currentLength = 1;
        int maxStart = 0;
        int maxLength = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) <= nums.get(i - 1)) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = currentStart;
                }
            } else {
                currentStart = i;
                currentLength = 1;
            }

        }
        for (int i = maxStart; i < maxStart + maxLength; i++) {
            res.add(nums.get(i));
        }
        return res;
    }

    public void writeToFile(String filePath) {
        String answer = getAnswer();
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filePath, false));
            buffWriter.write(answer);
            buffWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file with filePath : " + filePath, e);
        }
    }


    private String getAnswer() {
        StringBuilder generator = new StringBuilder();
        generator.append("Max number = ").append(getMax())
                .append("\n Min number =  ").append(getMin())
                .append("\n Median = ").append(getMedian())
                .append("\n Avg = ").append(getAvg())
                .append("\n Longest Increasing Sequence = ").append(findLongestIncreasingSequence()).append(" with SIZE = ").append(getSizeSequenceInc())
                .append("\n Longest Descending Sequence = ").append(findLongestDescendingSequence()).append(" with SIZE = ").append(getSizeSequenceDesc());
        return generator.toString();
    }

    private int getSizeSequenceInc() {
        List<Integer> result = findLongestIncreasingSequence();
        return result.size();
    }

    private int getSizeSequenceDesc() {
        List<Integer> result = findLongestDescendingSequence();
        return result.size();
    }
}
