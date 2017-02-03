package task.prvi;

import matrix.Matrix;

import java.io.IOException;

public class Prvi {

    public static void main(String[] args) throws IOException {

        Matrix A = Matrix.load("src\\task\\prvi\\A.txt");

        Matrix after = A.times(0.00000000001).times(1 / 0.00000000001);

        for (int i = 0; i < A.n;i++) {
            System.out.println(A.mat[0][i] == after.mat[0][i] ? "ISTI" : "RAZLICITI");
        }

        Matrix.print(A, "A:");

        Matrix.print(after, "A':");

    }

}
