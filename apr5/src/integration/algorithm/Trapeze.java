package integration.algorithm;

import matrix.Matrix;

import static matrix.Matrix.eye;
import static matrix.Matrix.inverse;

@SuppressWarnings("Duplicates")
public class Trapeze {

    public static void run(Matrix A, Matrix B, Matrix x, double T, int tMax, int printFreq, String fileName) {
        StringBuilder sb = new StringBuilder();
        // Sustav x' = Ax + B
        // xk=x za k=0
        Matrix xk = new Matrix(x.mat);

        Matrix U = eye(A.n);
        Matrix AT2 = A.times((1.0 / 2.0) * T);
        Matrix R = inverse(U.minus(AT2)).multiply(U.plus(AT2));
        Matrix S = inverse(U.minus(AT2)).times(T / 2.0).multiply(B);

        int i = 0;
        for (double t = 0; t <= tMax; t += T) {
            xk = R.multiply(xk).plus(S);

            if (i % printFreq == 0) System.out.println(i + "\t" + xk.toString());
            sb.append(xk.mat[0][0]).append(",").append(xk.mat[1][0]).append(System.lineSeparator());
            i++;
        }
        Matrix.saveStringToFile(sb.toString(), fileName);
        System.out.println();
    }


}
