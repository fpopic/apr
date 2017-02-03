package optimization.function;

import matrix.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class AFunctionWithDerivatesTest {

    @Test
    public void testGradientAt() throws Exception {
        // f(x1,x2) = (x1 - 3)^2 + (x2)^2
        // f'x1 = 2 * x1 - 6
        // f'x2 = 2 * x2
        // x = [-4 +3]
        // grad(x) = [-14 +6]
        AFunctionWithDerivates f4 = CostFunctionsWithDerivates.f4();
        Matrix actual = f4.gradientAt(-4.0, +3.0);
        Matrix expected = new Matrix(new double[][]{
                {-14.0, 6.0}
        });
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLaplaceAt() throws Exception {
        // f"x1x1 = 2
        // f"x1x2 = 0
        // f"x2x1 = 0
        // f"x2x2 = 2
        // x = [-4 +3]
        // laplace(x) = [[+2 0] [0 +2]]
        AFunctionWithDerivates f4 = CostFunctionsWithDerivates.f4();
        Matrix actual = f4.laplaceAt(-4.0, +3.0);
        Matrix expected = new Matrix(new double[][]{
                {2.0, 0.0},
                {0.0, 2.0}
        });
        Assert.assertEquals(expected, actual);
    }

}