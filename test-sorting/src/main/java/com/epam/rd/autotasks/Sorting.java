package com.epam.rd.autotasks;

public class Sorting {
    public void sort(int[] array){
        if (array == null)
            throw new IllegalArgumentException();
        int tmp, i, j;
        for(i = 0; i < array.length; i++)
            for(j = 0; j < array.length - i-1; j++){
                if(array[j] > array[j+1])
                {
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
    }
}
