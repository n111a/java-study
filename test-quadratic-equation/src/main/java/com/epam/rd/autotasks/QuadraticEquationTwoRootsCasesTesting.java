package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    double a;
    double b;
    double c;
    String expected;
    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 4, 3, "-1.0 -3.0"},
                {1, -70, 600, "60.0 10.0"},
                {4, -6, 0, "1.5 0.0"},
                {2, -8, 0, "4.0 0.0"}
        });
    }
    @Test
    public void testNoRootsCase() {
        String actual = quadraticEquation.solve(a, b, c);
        String[] parts = expected.split(" ");
        assertTrue((parts[1]+" "+ parts[0]).equals(actual) || (expected).equals(actual));
    }


}
