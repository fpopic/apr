package integration.task;

import integration.algorithm.RungeKutta;
import integration.algorithm.Trapeze;
import matrix.Matrix;

import java.io.IOException;

public class Task1 {
    public static void main(String[] args) throws IOException {
        Matrix A = Matrix.load("src\\integration\\task\\1A.txt");
        Matrix B = Matrix.load("src\\integration\\task\\1B.txt");
        Matrix x = Matrix.load("src\\integration\\task\\1x.txt");

        int tMax = 10;
        double T = 0.1;

        int printFreq = 1;

        Trapeze.run(A, B, x, T, tMax, printFreq, "src\\integration\\result\\1T.txt");
        RungeKutta.run(A, B, x, T, tMax, printFreq, "src\\integration\\result\\1RK.txt");
    }

}
