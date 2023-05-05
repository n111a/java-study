package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.Assert;

public class SortingTest {
Sorting sorting = new Sorting();

    @Test(expected = IllegalArgumentException.class)
    public void testNullCase(){
        sorting.sort(null);
        
    }

    @Test
    public void testEmptyCase(){
        int []expected = {};
        int[] actual = {};
        sorting.sort(actual);
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void testSingleElementArrayCase() {
        int []expected = {1};
        int[] actual = {1};
        sorting.sort(actual);
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void testSortedArraysCase() {
        int []expected = {1,2,3};
        int[] actual = {1,2,3};
        sorting.sort(actual);
        Assert.assertArrayEquals(expected,actual);
    }


    @Test
    public void testOtherCases() {
        int []expected = {-9, -3, -1, 0, 2, 2, 2, 5, 8, 9, 102};
        int[] actual =   {8, 9, 2, -1, 2, -3, 0, 5, 2, 102, -9};
        sorting.sort(actual);
        Assert.assertArrayEquals(expected,actual);
    }

}