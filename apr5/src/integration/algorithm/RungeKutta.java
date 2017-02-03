package integration.algorithm;

import matrix.Matrix;

@SuppressWarnings("Duplicates")
public class RungeKutta {

    public static void run(Matrix A, Matrix B, Matrix x, double T, int tMax, int printFreq, String fileName) {
        StringBuilder sb = new StringBuilder();
        // Sustav x' = Ax + B
        // xk=x za k=0
        Matrix xk = new Matrix(x.mat);

        int i = 0;
        for (double t = 0; t <= tMax; t += T) {
            Matrix m1 = A.multiply(xk).plus(B);
            Matrix m2 = A.multiply(xk.plus(m1.times(T / 2.0))).plus(B);
            Matrix m3 = A.multiply(xk.plus(m2.times(T / 2.0))).plus(B);
            Matrix m4 = A.multiply(xk.plus(m3.times(T))).plus(B);
            xk = xk.plus((m1.plus(m2.times(2)).plus(m3.times(2)).plus(m4)).times(T / 6.0));

            if (i % printFreq == 0) System.out.println(i + "\t" + xk.toString());
            sb.append(xk.mat[0][0]).append(",").append(xk.mat[1][0]).append(System.lineSeparator());
            i++;
        }
        Matrix.saveStringToFile(sb.toString(), fileName);
        System.out.println();
    }

}
