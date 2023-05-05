package com.epam.rd.autotasks.figures;

class Triangle extends Figure{
    Point a;
    Point b;
    Point c;


    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double area()
    {
        return Math.abs((b.getX()-a.getX())*(c.getY()-a.getY())-(c.getX()-a.getX())*(b.getY()-a.getY()))/2;
    }
    @Override
    public  String pointsToString()
    {
        return a.toString() + b.toString() + c.toString() ;
    }
    @Override
    public  Point leftmostPoint()
    {
        Point min = new Point(a.getX(), a.getY());
        if(b.getX() < min.getX())
        {
            min.setX(b.getX());
            min.setY(b.getY());
        }
        if(c.getX() < min.getX())
        {
            min.setX(c.getX());
            min.setY(c.getY());
        }

        return min;
    }
}
