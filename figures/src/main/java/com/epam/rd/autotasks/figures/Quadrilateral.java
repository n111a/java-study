package com.epam.rd.autotasks.figures;

class Quadrilateral extends  Figure{
    Point a;
    Point b;
    Point c;
    Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    @Override
    public double area()
    {
        return  (Math.abs((a.getX() - b.getX())*(a.getY() + b.getY()) + (b.getX() - c.getX())*(b.getY() + c.getY()) + (c.getX() - d.getX())*(c.getY() + d.getY()) + (d.getX() - a.getX())*(d.getY() + a.getY())) / 2);
    }
    @Override
    public  String pointsToString()
    {
        return a.toString() + b.toString() + c.toString() + d.toString();
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
        if(d.getX() < min.getX())
        {
            min.setX(d.getX());
            min.setY(d.getY());
        }
        return min;
    }
}
