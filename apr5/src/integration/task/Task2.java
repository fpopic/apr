package integration.task;

import integration.algorithm.RungeKutta;
import integration.algorithm.Trapeze;
import matrix.Matrix;

import java.io.IOException;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Matrix A = Matrix.load("src\\integration\\task\\2A.txt");
        Matrix B = Matrix.load("src\\integration\\task\\2B.txt");
        Matrix x = Matrix.load("src\\integration\\task\\2x.txt");

        int tMax = 10;
        double T = 0.001;

        int printFreq = 100;

        Trapeze.run(A, B, x, T, tMax, printFreq, "src\\integration\\result\\2T.txt");
        RungeKutta.run(A, B, x, T, tMax, printFreq, "src\\integration\\result\\2RK.txt");
    }
}
