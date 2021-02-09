package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return Arrays.stream(text.split("\\W+")).mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text).stream()
                .distinct()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.asList(text.split("\\W+"));
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return Arrays.stream(text.split("\\W+")).collect(Collectors.toMap(k -> k, k -> 1, (a, b) -> a + 1));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> words = Arrays.stream(text.split("\\W+"))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        if (direction == Direction.DESC) {
            Collections.reverse(words);
        }
        return words;
    }
}
