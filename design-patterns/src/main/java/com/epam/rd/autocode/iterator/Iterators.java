package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {
    private Iterators() {
    }

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<>() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current / 2 < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[current++/2];
            }

        };

    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<>() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current / 3 < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[current++/3];
            }

        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<>() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current / 5 < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[current++/5];
            }

        };
    }

    public static Iterable<String> table(String[] columns, int[] rows){

        return () -> new Iterator<>() {
            int curRow = 0;
            int curCol = 0;

            public boolean hasNext() {
                return curRow < columns.length || curCol < rows.length;
            }

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if(curRow + 1 == columns.length && curCol + 1 == rows.length)
                    return columns[curRow++] + rows[curCol++];
                else if(curCol + 1 == rows.length) {
                    curCol = 0;
                    return columns[curRow++] + rows[rows.length - 1];
                }
                else
                    return columns[curRow] + rows[curCol++];
            }
        };
    }
}