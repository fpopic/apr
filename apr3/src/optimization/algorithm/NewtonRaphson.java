package optimization.algorithm;

import matrix.Matrix;
import optimization.function.AFunctionWithDerivates;
import optimization.function.Wrapper1DFunction;

import static matrix.Matrix.euclideanNorm;
import static matrix.Matrix.permuteRows;
import static optimization.algorithm.GoldentRatio.goldenRatioMiddle;

public class NewtonRaphson {

    public static final double EPS = 10e-6;
    public static final int MAX_ITER = 100; //max useless iteration

    public static Matrix newtonRaphson(AFunctionWithDerivates f, Matrix x, boolean usingGoldenRatio) {
        Wrapper1DFunction wf = new Wrapper1DFunction(f); // 1-D funkcija po lambdi
        double lambda = 0; //min na pravcu
        Matrix nablaF, nablaH;
        Matrix deltaX, y, LUP;

        int iter = 0;
        double lastIterValue = 0.0;

        do {
            nablaF = f.gradientAt(x);
            nablaH = f.laplaceAt(x);

            // sustav  A * x = g  =>  nablaH * deltaX = -nablaF
            LUP = nablaH.lup();
            y = LUP.forwardSupstiton(permuteRows(nablaF.times(-1).T()));
            deltaX = LUP.backwardSupstition(y).T(); //tocka mi redak d lup vrati stupac

            System.out.println("x=" + x + " deltaX(x)=" + deltaX);

            if (usingGoldenRatio) {
                wf.v = deltaX; //priprema wrappera
                wf.x = x; //priprema wrappera
                lambda = goldenRatioMiddle(wf, 1, x.mat[0][0]);
                deltaX = deltaX.times(lambda);
            }
            x = x.plus(deltaX);

            iter++;
            if (lastIterValue != f.valueAt(x)) {
                iter = 0;
                lastIterValue = f.valueAt(x);
            }

        } while (euclideanNorm(deltaX) > EPS && iter < MAX_ITER);
        return x;
    }
}
