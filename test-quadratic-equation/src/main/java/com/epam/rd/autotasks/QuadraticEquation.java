package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if(a == 0)
            throw new IllegalArgumentException();
        double d = b*b-4*a*c;
        if(d<0)
            return "no roots";
        else if(d == 0)
            return (0-b)/(2*a)+"";
        else
        {
            d = Math.sqrt(d);
            return ((d-b)/(2*a)+ " " + (-d-b)/(2*a));
        }
    }

}