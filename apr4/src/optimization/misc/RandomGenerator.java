package optimization.misc;

import java.util.Random;

public class RandomGenerator {

    private final static Random random = new Random();

    public static int randomInt(int a, int b) {
        return a + (int) (Math.random() * ((b - a) + 1));
    }

    public static double randomDouble(double a, double b) {
        return random.nextDouble() * (b - a) + a;
    }

    public static double randomGauss(double a, double b) {
        return random.nextGaussian() * (b - a) + a;
    }

}