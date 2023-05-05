package com.epam.rd.autotasks;


import java.util.*;

import java.util.stream.Collectors;

public class Words {
    public String replace(String s)
    {
        return s.replaceAll("[^A-Za-zА-Яа-я0-9 áóúíüêëöéïè]", " ").toLowerCase();
    }

    public String countWords(List<String> lines) {
        StringBuilder output = new StringBuilder();
        List<String> allWords = lines.stream()
                .map(this::replace)
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .collect(Collectors.toList());
        allWords.stream()
                .filter(word -> word.length() >=4)
                .distinct()
                .collect(Collectors.toMap(s->s, s->allWords.stream()
                        .filter(word -> word.equals(s)).count()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(s-> output.append("\n").append(s.getKey()).append(" - ").append(s.getValue()));
        output.deleteCharAt(0);
        return output.toString();
    }



}
