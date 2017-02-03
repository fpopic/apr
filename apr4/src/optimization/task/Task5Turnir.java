package optimization.task;

import optimization.algorithm.GAFloat;
import optimization.function.AFunction;
import optimization.function.ObjectiveFunctions;

import java.util.Locale;


@SuppressWarnings("Duplicates")
public class Task5Turnir {

    public static void main(String[] args) {
        double eps = 1e-6;
        int maxIter = 50, dg = -50, gg = +150, popSize = 10;

        int[] kTurnir = {3, 6};
        System.out.println("3,6");
        AFunction f = ObjectiveFunctions.f6();

        int runs = 20;

        double[][] results = new double[runs][kTurnir.length];
        GAFloat ga = new GAFloat(eps, maxIter, popSize, 0, dg, gg);
        ga.setF(f);
        ga.setpM(0.8);
        ga.setGaussK(0.1);


        for (int run = 0; run < runs; run++) {
            for (int i = 0; i < kTurnir.length; i++) {
                ga.setkTournament(kTurnir[i]);
                results[run][i] = ga.run().f;
//                System.out.print(String.format(Locale.getDefault(), "%1.1e", results[run][k]));
                System.out.print(String.format(Locale.getDefault(), "%.7f", results[run][i]));
                if (i != kTurnir.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
/*
        k3,k6
        0.0053709,0.0151488
        0.0313496,2.5098006
        0.0027852,0.0053645
        0.0117481,0.0053645
        0.0058328,2.2457646
        0.0032050,0.0151488
        0.0055445,0.0053645
        0.0083437,0.0024865
        0.0055606,0.0549662
        0.0061553,0.0009278
*/

    }
}

