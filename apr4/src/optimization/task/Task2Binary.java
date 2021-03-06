package optimization.task;

import matrix.Vector;
import optimization.algorithm.GABinary;
import optimization.function.AFunction;
import optimization.function.ObjectiveFunctions;

import java.util.Locale;

import static optimization.misc.ResultHelper.score;

@SuppressWarnings("Duplicates")
public class Task2Binary {

    public static void main(String[] args) {
        double eps = 1e-6;
        int maxIter = 100000, popSize = 30, kTourSize = 3, dg = -50, gg = +150;
        GABinary ga = new GABinary(eps, maxIter, popSize, kTourSize, dg, gg);

        int runs = 10;

        AFunction f = ObjectiveFunctions.f6();
        f.setxMin(new Vector(0.0, 0.0, 0.0));
//        f.setxMin(new Vector(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        AFunction[] functions = {f};
        double[] pM = {0.1};

        System.out.println(ga + " for " + runs + " runs binary");

        for (int i = 0; i < functions.length; i++) {
            ga.setF(functions[i]);
            ga.setpM(pM[i]);
            System.out.println("F=" + functions[i] + " pM=" + pM[i]);
            double[] results = new double[runs];
            for (int s = 0; s < runs; s++) {
                results[s] = ga.run().f;
                System.out.print(String.format(Locale.getDefault(), "%1.0e ", results[s]));
            }
            System.out.println();
            System.out.println("Score=" + score(results, eps) + "/" + runs);
        }
    }
}

