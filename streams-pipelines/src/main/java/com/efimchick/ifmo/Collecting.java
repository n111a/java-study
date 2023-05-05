package com.efimchick.ifmo;


import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {

    public int sum(IntStream intStream) {
        return intStream.reduce(0, Integer::sum);
    }

    public int production(IntStream intStream) {
        return intStream.reduce(1, (acc, number) -> number*acc);
    }

    public int oddSum(IntStream intStream) {
        return intStream.filter(number -> number%2 != 0).reduce(0, Integer::sum);
    }

    public Map<Integer, Integer> sumByRemainder(int divider, IntStream intStream) {
        return intStream.boxed().collect(Collectors.groupingBy(num -> num%divider, Collectors.summingInt(n -> n)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> results) {
        List<CourseResult> course = results.collect(Collectors.toList());
        Long num = course.stream().map(CourseResult::getTaskResults)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toList())
                .stream().map(Map.Entry::getKey).distinct().count();
        return course.stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(CourseResult::getPerson, Collectors.summingDouble(entry-> (Double) entry.getTaskResults()
                        .values().stream().mapToDouble(v -> v).sum())), map -> map.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e ->  e.getValue() / num))));

    }

    public double averageTotalScore(Stream<CourseResult> results) {
        return totalScores(results)
                .entrySet().stream()
                .collect(Collectors.averagingDouble(Map.Entry::getValue));
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> results) {
        List<CourseResult> course = results.collect(Collectors.toList());
        Long num = course.stream().map(CourseResult::getPerson)
                .collect(Collectors.toList())
                .stream().distinct().count();
        return course.stream()
                .flatMap(c -> c.getTaskResults().entrySet().stream())
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingDouble(Map.Entry::getValue)),
                        map -> map.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e ->  e.getValue() / num))));

    }

    public Map<Person, String> defineMarks(Stream<CourseResult> results) {
        return totalScores(results)
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getPersonMark(entry.getValue())));
    }
    public String defineMarkForPerson(Stream<CourseResult> results, Person person) {
        return totalScores(results)
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getPersonMark(entry.getValue())))
                .entrySet().stream()
                .filter(e -> person.equals(e.getKey()))
                .findFirst().map(Map.Entry::getValue).get();
    }

    public String getPersonMark(double averageScore)
    {
        if(averageScore > 90)
            return "A";
        else if(averageScore >=83)
                return "B";
        else if(averageScore >=75)
            return "C";
        else if(averageScore >=68)
            return "D";
        else if(averageScore >=60)
            return "E";
        else
            return "F";
    }
    public String easiestTask(Stream<CourseResult> results) {
        return averageScoresPerTask(results).entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No easy task found");
    }


    public Collector<CourseResult, List<CourseResult>, String> printableStringCollector() {
        return new Collector<CourseResult, List<CourseResult>, String>() {

            @Override
            public Supplier<List<CourseResult>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<CourseResult>, CourseResult> accumulator() {
                BiConsumer <List<CourseResult>, CourseResult> biConsumer = List::add;
                return biConsumer;
            }

            @Override
            public BinaryOperator<List<CourseResult>> combiner() {
                return null;
            }

            @Override
            public Function<List<CourseResult>, String> finisher() {
                Function<List<CourseResult>, String> func = (result) -> {
                    String delimiter = " |";
                    String newLine = "\n";
                    int maxNameLength = result.stream()
                            .map(CourseResult::getPerson)
                            .map(person -> person.getFirstName().length()+person.getLastName().length())
                            .max(Comparator.naturalOrder()).get();
                    Map<String,  Double> subjects = averageScoresPerTask(result.stream());


                    StringBuilder sb = new StringBuilder();
                    sb.append("Student");
                    sb.append(String.format( "%1$" + (maxNameLength - 3) + "s", delimiter + " "));
                    sb.append(subjects.keySet().stream()
                            .sorted().collect(Collectors.joining(delimiter +" ")));
                    sb.append(delimiter).append(" Total").append(delimiter).append(" Mark").append(delimiter);
                    sb.append(newLine);
                    sb.append(result.stream()
                            .sorted(Comparator.comparing(p -> p.getPerson().getLastName()))
                            .map(e-> e.getPerson().getLastName()+ " " + e.getPerson().getFirstName()
                                    + String.format( "%1$" + (maxNameLength - (e.getPerson().getLastName().length() + e.getPerson().getFirstName().length()-2)) + "s", delimiter)
                                    + subjects.entrySet().stream()
                                    .sorted(Map.Entry.comparingByKey())
                                    .map(subj -> String.format( "%1$" + (subj.getKey().length() +1) + "s",
                                            e.getTaskResults().get(subj.getKey()) == null ? " 0":e.getTaskResults().get(subj.getKey()))).collect(Collectors.joining(delimiter)) +delimiter
                                    +  String.format(" %.2f", totalScores(result.stream()).get(e.getPerson())).replace(',', '.') + delimiter +"    "+ defineMarkForPerson(result.stream(), e.getPerson()) + delimiter )
                            .collect(Collectors.joining(newLine)));

                    sb.append("\nAverage");
                    sb.append(String.format( "%1$" + (maxNameLength - 4) + "s", delimiter ));
                    sb.append(subjects.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey()).map(subj -> String.format( "%1$" + (subj.getKey().length() +1) + "s",
                                    String.format(" %.2f", subj.getValue()).replace(',', '.'))).collect(Collectors.joining(delimiter)) +delimiter);
                    Double avg = averageTotalScore(result.stream());
                    sb.append( String.format(" %.2f", avg).replace(',', '.') + delimiter + "    " + getPersonMark(avg) + delimiter);
                  return sb.toString();
                };
                return func;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }


}

