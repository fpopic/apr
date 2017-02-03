package optimization.algorithm;

import matrix.Matrix;
import optimization.function.AFunction;

public class HookeJeeves {

    public static final double e = 10e-6;
    public static final double dx = 0.5;

    public static Matrix hookeJeeves(AFunction f, Matrix x0) {
        return hookeJeeves(f, x0, HookeJeeves.dx);
    }

    public static Matrix hookeJeeves(AFunction f, Matrix x0, double dx) {
        Matrix xB, xP, xN;
        xP = xB = x0;
        StringBuilder sb = new StringBuilder();

        do {
            xN = explore(f, xP, dx);
            sb.append("xB=").append(xB).append(" xP=").append(xP).append(" xN=").append(xN).append("\n");

            if (f.valueAt(xN) < f.valueAt(xB)) { //prihvacamo baznu tocku
                xP = xN.times(2).minus(xB); //definiramo novu tocku pretrazivanja
                xB = xN;
            }

            else {
                dx = dx / 2; //smanji ga
                xP = xB; //vracamo se na zadnju baznu tocku
            }

        } while (dx >= e);
        //System.out.println(sb.toString());
        return xB;
    }

    private static Matrix explore(AFunction f, Matrix xP, double dx) {
        Matrix x = new Matrix(xP.mat);
        for (int i = 0; i < x.n; i++) { //Idi po svim dimenzijama vektora/tocke
            double P = f.valueAt(x);
            x.mat[0][i] += dx; //povecamo za dx domenziju
            double N = f.valueAt(x); //provjerimo za pozitivni pomak
            if (N > P) { //ne valja ni pozitivni pomak
                x.mat[0][i] -= 2 * dx; //samnjimo z dx
                N = f.valueAt(x);
                if (N > P) { //ne valja ni negativni pomak
                    x.mat[0][i] += dx; //vratimo na staro
                }
            }
        }
        return x;
    }

}
