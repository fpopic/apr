package optimization.algorithm;

import matrix.Matrix;
import optimization.constraint.ExplicitConstraints;
import optimization.constraint.ImplicitConstraints;
import optimization.function.AFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Box {

    public static final double EPS = 10e-6;
    public static final double ALFA = 1.3;
    public static final double SIGMA = 0.5;
    public static final Random R = new Random();

    public static Matrix box(AFunction f, ImplicitConstraints imp, ExplicitConstraints exp, Matrix x0) {
        if (!imp.test(x0)) {
            System.out.println("Poèetna toèka " + x0 + " ne zadovoljava implicitna ogranièenja");
            return null;
        }
        if (!exp.test(x0)) {
            System.out.println("Poèetna toèka " + x0 + " ne zadovoljava eksplicitna ogranièenja");
            return null;
        }

        Matrix xc, xr;
        int h, h2, iter = 0;
        List<Matrix> X = new ArrayList<>();

        xc = x0;

        //Generiranje 2n tocaka simplexa
        for (int i = 0; i < x0.n * 2; i++) {
            Matrix p = new Matrix(x0.m, x0.n);
            for (int j = 0; j < p.n; j++) {
                p.mat[0][j] = exp.d + R.nextDouble() * (exp.g - exp.d);
            }
            while (!imp.test(p)) {
                p = p.plus(xc).times(SIGMA);
            }
            X.add(p);
            xc = getCentroidOfValidPoints(X);
        }


        //Box
        do {
            h = getMaxIndex(X, f);
            h2 = getSecondMaxIndex(X, f, h);
            xc = getCentroid(X, X.get(h));

            //refleksija
            xr = xc.times(1 + ALFA).minus(X.get(h).times(ALFA));

            //popravljaj refleksivnu tocku
            for (int j = 0; j < xr.n; j++) {
                if (xr.mat[0][j] < exp.d) {
                    xr.mat[0][j] = exp.d;
                }
                else if (xr.mat[0][j] > exp.g) {
                    xr.mat[0][j] = exp.g;
                }
            }

            while (!imp.test(xr)) {
                xr = xr.plus(xc).times(SIGMA);
            }

            if (f.valueAt(xr) > f.valueAt(X.get(h2))) {
                xr = xr.plus(xc).times(SIGMA);
            }

            X.set(h, xr);

            System.out.println(iter + ". Xc" + xc);
            iter++;

        } while (!stopCriteria(X.get(h), xc));

        return xc;
    }

    private static Matrix getCentroidOfValidPoints(List<Matrix> X) {
        Matrix centroid = new Matrix(1, X.get(0).n); //prazan vektor iste duljine
        for (Matrix xi : X) { //idi po svim vektorima simpleksa
            for (int j = 0; j < xi.mat[0].length; j++) {
                centroid.mat[0][j] += xi.mat[0][j]; //idi po svim dimenzijama jednog vektora i nadodaj na centroid
            }
        }
        for (int j = 0; j < centroid.mat[0].length; j++) {
            centroid.mat[0][j] /= X.size(); // idi po svim dimenzijama jednog vektora i podijeli s n
        }
        return centroid;
    }

    public static Matrix getCentroid(List<Matrix> X, Matrix xh) {
        Matrix centroid = new Matrix(1, xh.n); //prazan vektor iste duljine
        for (Matrix xi : X) { //idi po svim vektorima simpleksa
            if (xi.equals(xh)) continue;
            for (int j = 0; j < xi.mat[0].length; j++) {
                centroid.mat[0][j] += xi.mat[0][j]; //idi po svim dimenzijama jednog vektora i nadodaj na centroid
            }
        }
        for (int j = 0; j < centroid.mat[0].length; j++) {
            centroid.mat[0][j] /= X.size() - 1; // idi po svim dimenzijama jednog vektora i podijeli s n-1
        }
        return centroid;
    }

    private static int getSecondMaxIndex(List<Matrix> X, AFunction f, int h) {
        double secondMax = -1;
        int secondMaxIndex = -1;
        for (int i = 0; i < X.size(); i++) {
            Matrix xi = X.get(i);
            double fv = f.valueAt(xi);
            if (fv > secondMax && i != h) {
                secondMax = fv;
                secondMaxIndex = i;
            }
        }
        return secondMaxIndex;
    }

    public static int getMaxIndex(List<Matrix> X, AFunction f) {
        double max = 0;
        int index = 0;
        for (int i = 0; i < X.size(); i++) {
            Matrix xi = X.get(i);
            double fv = f.valueAt(xi);
            if (fv > max) {
                max = fv;
                index = i;
            }
        }
        return index;
    }

    public static boolean stopCriteria(Matrix xh, Matrix xc) {
        return abs(Matrix.euclideanNorm(xh) - Matrix.euclideanNorm(xc)) <= EPS;
    }

}
