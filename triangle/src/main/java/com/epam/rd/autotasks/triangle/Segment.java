package com.epam.rd.autotasks.triangle;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    static double length(Point start, Point end) {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) +
                Math.pow(start.getY() - end.getY(), 2));
    }
}
