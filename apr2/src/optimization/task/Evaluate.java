package optimization.task;

import matrix.Matrix;
import optimization.algorithm.GoldentRatio;
import optimization.algorithm.HookeJeeves;
import optimization.algorithm.Simplex;
import optimization.function.AFunction;

import static optimization.algorithm.GoldentRatio.Interval;

public class Evaluate {

    public static void evaluateSimplexHookeJeeves(AFunction f) {
        StringBuilder sb = new StringBuilder();
        sb.append("Funkcija: ").append(f).append("\nPoèetna:").append(f.getX0()).append(":\n");


        Matrix xC6 = Simplex.simplex(f, f.getX0());
        sb.append("Simplex N&M: ").append("Min").append(xC6).append(" ").append(f.getCounter()).append("x\n");

        f.resetCounterAndMap();

        Matrix xB6 = HookeJeeves.hookeJeeves(f, f.getX0());
        sb.append("HookeJeeves: ").append("Min").append(xB6).append(" ").append(f.getCounter()).append("x");;

        f.resetCounterAndMap();

        System.out.println(sb.toString());
    }

    public static void evaluateGoldenRatio(AFunction f) {
        StringBuilder sb = new StringBuilder();

        Interval minInterval = GoldentRatio.goldenRatio(f, 1, f.getX0().mat[0][0]);
        sb.append("GoldenRatio: ").append("Min").append(minInterval).append(" ").append(f.getCounter()).append("x\n");

        f.resetCounterAndMap();

        System.out.println(sb.toString());
    }
}
