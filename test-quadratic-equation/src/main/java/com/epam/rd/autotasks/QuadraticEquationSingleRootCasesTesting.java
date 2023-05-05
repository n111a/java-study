package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    double a;
    double b;
    double c;
    double expected;
    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

     @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 2, 1, -1},
                {-2, 4, -2, 1},
                {1, 2, 1, -1},
                {-2, 4, -2, 1}
        });
    }
    @Test
    public void testNoRootsCase() {
        String actual = quadraticEquation.solve(a, b, c);
        assertEquals(expected+"", actual);
    }
}