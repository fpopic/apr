package optimization.algorithm;

import matrix.Matrix;
import optimization.function.AFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Simplex {

    // region Simplex Constants

    public static final double e = 10e-6;

    public static double alfa = 1.0;
    public static double beta = 0.5;
    public static double gamma = 2.0;
    public static double sigma = 0.5;
    public static double step = 1;

    //endregion

    public static Matrix simplex(AFunction f, Matrix x0) {
        List<Matrix> X = generateSimplexPoints(x0);
        Matrix xc, xr, xk, xe;
        int h, l;

        do {
            h = getMaxIndex(X, f);
            l = getMinIndex(X, f); //najbolja tocka
            xc = getCentroid(X, X.get(h));
            xr = reflexion(xc, X.get(h));
            if (f.valueAt(xr) < f.valueAt(X.get(l))) {
                xe = expansion(xc, xr);
                if (f.valueAt(xe) < f.valueAt(X.get(l))) {
                    X.set(h, xe);
                }
                else {
                    X.set(h, xr);
                }
            }
            else {
                boolean condition = true;
                for (Matrix xj : X) {
                    if (xj.equals(X.get(h))) continue;
                    if (f.valueAt(xr) < f.valueAt(xj)) {
                        condition = false;
                        break;
                    }
                }
                if (condition) {
                    if (f.valueAt(xr) < f.valueAt(X.get(h))) {
                        X.set(h, xr);
                    }
                    xk = contraction(xc, X.get(h));
                    if (f.valueAt(xk) < f.valueAt(X.get(h))) {
                        X.set(h, xk);
                    }
                    else {
                        X = shift(X, X.get(l));
                    }
                }
                else {
                    X.set(h, xr);
                }
            }
            System.out.println("Xc:" + xc + " f(Xc):" + String.format(Locale.getDefault(), "%+.4f", f.valueAt(xc)));
        }
        while (!stopCriteria(X, f, xc));
        return xc;
    }

    public static List<Matrix> generateSimplexPoints(Matrix x0) {
        List<Matrix> l = new ArrayList<>();
        l.add(x0); //dodaj pocetnu da bude n+1
        for (int i = 0; i < x0.n; i++) { //idi po svim dimenzijama
            Matrix point = new Matrix(x0.mat); //uzmi staru
            point.mat[0][i] += Simplex.step; //pomakni staru
            l.add(point);
        }
        return l;
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

    public static int getMinIndex(List<Matrix> X, AFunction f) {
        double min = 0;
        int index = 0;
        for (int i = 0; i < X.size(); i++) {
            Matrix xi = X.get(i);
            double fv = f.valueAt(xi);
            if (fv < min) {
                min = fv;
                index = i;
            }
        }
        return index;
    }

    public static Matrix reflexion(Matrix xc, Matrix xh) {
        // xr = (1+alfa)*xc -alfa*xh
        return xc.times(1 + alfa).minus(xh.times(alfa));
    }

    private static Matrix expansion(Matrix xc, Matrix xr) {
        // xe = (1-gamma)*xc + gamma*xr
        return xc.times(1 - gamma).plus(xr.times(gamma));
    }

    private static Matrix contraction(Matrix xc, Matrix xh) {
        // xk = (1-beta)xc + beta*xh
        return xc.times(1 - beta).plus(xh.times(beta));
    }

    private static List<Matrix> shift(List<Matrix> X, Matrix xl) {
        List<Matrix> shiftedX = new ArrayList<>();
        for (Matrix xi : X) {
            Matrix shifted = xi.times(sigma).plus(xl.times(sigma));
            shiftedX.add(shifted);
        }
        return shiftedX;
    }

    public static boolean stopCriteria(List<Matrix> X, AFunction f, Matrix xc) {
        double sum = 0;
        double fxc = f.valueAt(xc);
        for (Matrix xj : X) {
            sum += Math.pow(f.valueAt(xj) - fxc, 2);
        }
        return Math.sqrt(sum / xc.n) <= e;
    }

}