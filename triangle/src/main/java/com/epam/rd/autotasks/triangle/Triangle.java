package com.epam.rd.autotasks.triangle;

class Triangle extends Figure{
    Point a;
    Point b;
    Point c;


    public Triangle(Point a, Point b, Point c) {
        if(a!=null && b!=null && c != null)
        {
            double ab = Segment.length(a,b);
            double bc = Segment.length(b,c);
            double ac = Segment.length(a,c);
            if(ab+bc > ac && ab+ac > bc && ac +bc > ab)
            {
                this.a = a;
                this.b = b;
                this.c = c;
            }
            else throw new RuntimeException("degenerative");
        } else throw new RuntimeException("degenerative");
    }
    @Override
    public double area()
    {
        return Math.abs((b.getX()-a.getX())*(c.getY()-a.getY())-(c.getX()-a.getX())*(b.getY()-a.getY()))/2;
    }
  
    public Point centroid()
    {
        double xC = (a.getX() + b.getX() + c.getX())/3;
        double yC = (a.getY() + b.getY() + c.getY())/3;
        return new Point(xC, yC);
    }
}
