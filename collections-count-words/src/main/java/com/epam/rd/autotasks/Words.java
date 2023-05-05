package com.epam.rd.autotasks;
import java.util.*;

public class Words {

    public static <String, Integer> Map<String, Integer> sortByValues(Map<String, Integer> map)
    {
        CustomComparator comparator = new CustomComparator(map);

        Map<String, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(map);

        return sortedMap;
    }
    public String countWords(List<String> lines) {
        String[] wordsInString;
        String s;
        //^A-Za-zА-Яа-я áóúíéáè
        Map<String, Integer> numberOfWords = new TreeMap<>();
        for (String line : lines) {
            s = line.replaceAll("[^A-Za-zА-Яа-я0-9 áóúíüêëöéïè]", " ").toLowerCase(); // delete all insignificant symbols
            wordsInString = s.split(" ");
            for (String w : wordsInString)
                if (w.length() >= 4)
                    if (numberOfWords.containsKey(w))
                        numberOfWords.put(w, numberOfWords.get(w) + 1);
                    else
                        numberOfWords.put(w, 1);
        }

        numberOfWords = sortByValues(numberOfWords);
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Integer> elem : numberOfWords.entrySet()) {
            if(elem.getValue() < 10)
              break;
            if(!output.toString().equals(""))
               output.append("\n");
            output.append(elem.getKey()).append(" - ").append(elem.getValue());
        }
        return output.toString();
    }
}
class CustomComparator<String, Integer extends Comparable> implements Comparator<String>
{
    private Map<String, Integer> map;

    public CustomComparator(Map<String, Integer> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public int compare(String s1, String s2) {

        if(map.get(s2).compareTo(map.get(s1) ) == 0)
             return 1;
        else
            return map.get(s2).compareTo(map.get(s1));
    }


}
