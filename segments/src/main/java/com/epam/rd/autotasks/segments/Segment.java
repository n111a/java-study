package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    Point start;
    Point end;
    public Segment(Point start, Point end) {
        if(start.getX() != end.getX() || start.getY() != end.getY()){
            this.start = start;
            this.end = end;
        }
        else throw new RuntimeException();
    }

    double length() {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) +
                Math.pow(start.getY() - end.getY(), 2));
    }

    Point middle() {
        return new Point((start.getX() + end.getX())/2, (start.getY() + end.getY())/2);
    }

    Point intersection(Segment another) {
        double x1 = start.getX();
        double x2 = end.getX();
        double y1 = start.getY();
        double y2 = end.getY();
        double x3 = another.start.getX();
        double x4 = another.end.getX();
        double y3 = another.start.getY();
        double y4 = another.end.getY();
        double n;

        if (y2 - y1 != 0) {  // a(y)
            double q = (x2 - x1) / (y1 - y2);
            double sn = (x3 - x4) + (y3 - y4) * q;
            if (sn == 0) { return null; }
            double fn = (x3 - x1) + (y3 - y1) * q;   // b(x) + b(y)*q
            n = fn / sn;
        }
        else {
            if (y3 - y4 != 0) { return null; }  // b(y)
            n = (y3 - y1) / (y3 - y4);   // c(y)/b(y)
        }
        double x = x3 + (x4 - x3) * n;
        double y = y3 + (y4 - y3) * n;
        if(Math.round((Math.sqrt(Math.pow(x-x1, 2)+Math.pow(y-y1,2)) + Math.sqrt(Math.pow(x-x2, 2)+Math.pow(y-y2, 2)))*1000000)/1000000 != Math.round(this.length()*1000000)/1000000)
            return null;
        if(Math.round((Math.sqrt(Math.pow(x-x3, 2)+Math.pow(y-y3, 2)) + Math.sqrt(Math.pow(x-x4, 2)+Math.pow(y-y4,2)))*1000000)/1000000 != Math.round(another.length()*1000000)/1000000)
            return null; 
        return new Point(x,y);

    }

}
