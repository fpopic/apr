package task.drugi;

import matrix.Matrix;

import java.io.IOException;

public class Drugi {

    public static void main(String[] args) throws IOException {

        Matrix A = Matrix.load("src\\task\\drugi\\A.txt");
        Matrix b = Matrix.load("src\\task\\drugi\\b.txt");

//        Matrix LU = A.lu(); //baci exception
//        Matrix y = LU.forwardSupstiton(b);
//        Matrix.print(y, "y:");
//        Matrix x = LU.backwardSupstition(y);
//        Matrix.print(x, "x:");


        Matrix LUP = A.lup(); //prodje

        b = Matrix.permuteRows(b);
        Matrix.print(b, "b permutiran:");

        Matrix y1 = LUP.forwardSupstiton(b);
        Matrix.print(y1, "y1:");

        Matrix x1 = LUP.backwardSupstition(y1);
        Matrix.print(x1, "x1:");
    }
}
