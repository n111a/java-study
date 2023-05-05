package com.epam.rd.autotasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    double a;
    double b;
    double c;
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][] {
            {0, 1, 1},
            {0, 2, 1},
            {0, 0, 0},
            {0, -1, 1},
            {0, -100, 200},
            {0, -1, 0},
            {0, 0, 1}
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestZeroA()
    {
        quadraticEquation.solve(a, b, c);
    }




}
