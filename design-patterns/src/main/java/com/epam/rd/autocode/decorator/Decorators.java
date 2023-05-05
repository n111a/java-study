package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        List<String> evenList = new ArrayList<>();
        for(int i=0; i<sourceList.size(); i+=2)
            evenList.add(sourceList.get(i));
        return evenList;
    }

    private Decorators() {
    }
}
