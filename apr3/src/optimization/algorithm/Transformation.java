package optimization.algorithm;

import matrix.Matrix;
import optimization.constraint.ImplicitConstraints;
import optimization.function.AFunctionWithDerivates;
import optimization.function.UnconstrainedFunction;

import static java.lang.Math.abs;
import static matrix.Matrix.euclideanNorm;

public class Transformation {

    public static final double EPS = 10e-6;

    public static Matrix transformation(AFunctionWithDerivates f, ImplicitConstraints imp, double t, Matrix x0) {
        UnconstrainedFunction U = new UnconstrainedFunction(t, f, imp);

        Matrix previousMin, min;
        double minEuclidean, previousMinEuclidean;

        previousMin = HookeJeeves.hookeJeeves(U, x0);
//        previousMin = Simplex.simplex(U, U.getX0());

        do {
            U.t *= 10;
            min = HookeJeeves.hookeJeeves(U, previousMin);
//            min = Simplex.simplex(U, previousMin);
            minEuclidean = euclideanNorm(min);
            previousMinEuclidean = euclideanNorm(previousMin);
            previousMin = min;
        } while (abs(minEuclidean - previousMinEuclidean) >= EPS);

        return min;
    }
}
