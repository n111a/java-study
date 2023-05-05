package com.epam.rd.autotasks;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = b*b-4*a*c;
        if(d<0)
            System.out.println("no roots");
        else if(d == 0)
            System.out.println((0-b)/(2*a));
        else
        {
            d = Math.sqrt(d);
            
            System.out.println((d-b)/(2*a)+ " " + (-d-b)/(2*a));
        }



    }

}