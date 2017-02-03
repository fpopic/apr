package task;

import matrix.Matrix;

import java.io.IOException;

public class vjezba {
    public static void main(String[] args) throws IOException {
        Matrix A = Matrix.load("..\\apr1\\src\\task\\vjezba.txt"); //ako ga pokrecem iz apr3 projekta
        Matrix b = Matrix.load("..\\apr1\\src\\task\\vjezbaB.txt");

        Matrix.print(A, "A:");

        Matrix LU = A.lu();

        Matrix.print(b, "b:");

        Matrix y = LU.forwardSupstiton(b);
        Matrix.print(y, "y:");

        Matrix x = LU.backwardSupstition(y);
        Matrix.print(x, "x:");
    }
}
