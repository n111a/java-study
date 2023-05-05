package com.epam.rd.autotasks;
import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
            cycleSwap(array, 1);
        }

        static void cycleSwap(int[] array, int shift) {
            if(array.length != 0 && shift !=0)
            {
                int temp = array[0];
                int arr[] = Arrays.copyOf(array, array.length);
                for (int i = 0; i < array.length; i++) {
                    array[(i+shift)%array.length] = arr[i];
                }
            }
        

        }
}
