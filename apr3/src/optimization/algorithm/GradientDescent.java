package optimization.algorithm;

import matrix.Matrix;
import optimization.function.AFunctionWithDerivates;
import optimization.function.Wrapper1DFunction;

import static matrix.Matrix.euclideanNorm;
import static optimization.algorithm.GoldentRatio.goldenRatioMiddle;

public class GradientDescent {

    public static final double EPS = 10e-6;
    public static final int MAX_ITER = 100; //max useless iteration

    public static Matrix gradientDescent(AFunctionWithDerivates f, Matrix x, boolean usingGoldenRatio) {
        Wrapper1DFunction wf = new Wrapper1DFunction(f); // 1-D funkcija po lambdi
        double lambda; //min na pravcu
        Matrix grad, v;

        int iter = 0;
        double lastIterValue = 0.0;

        do {
            grad = f.gradientAt(x);
            v = grad.times(-1);

            System.out.println("x=" + x + " grad(x)=" + grad);

            if (usingGoldenRatio) {
                wf.v = v; //priprema wrappera
                wf.x = x; //priprema wrappera
                lambda = goldenRatioMiddle(wf, 1, x.mat[0][0]);
                v = v.times(lambda);
            }
            x = x.plus(v);

            iter++;
            if (lastIterValue != f.valueAt(x)) {
                iter = 0;
                lastIterValue = f.valueAt(x);
            }
            if (iter == MAX_ITER)
                System.out.println("Dosegnut broj uzastopnih iteracija " + iter);
        }
        while (euclideanNorm(grad) > EPS && iter < MAX_ITER);
        return x;
    }
}
