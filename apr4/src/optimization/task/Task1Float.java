package optimization.task;

import optimization.algorithm.GAFloat;
import optimization.function.AFunction;

import java.util.Locale;

import static optimization.function.ObjectiveFunctions.*;
import static optimization.misc.ResultHelper.score;

@SuppressWarnings("Duplicates")
public class Task1Float {

    public static void main(String[] args) {
        double eps = 1e-6;
        int maxIter = 100000, popSize = 90, kTourSize = 3, dg = -50, gg = +150;
        GAFloat ga = new GAFloat(eps, maxIter, popSize, kTourSize, dg, gg);

        int runs = 20;

        AFunction[] functions = {f1(), f3(), f6(), f7()};
        double[] pM = {0.3, 0.5, 0.8, 0.5};
        double[] K = {0.1, 0.01, 1.0, 0.1};

        System.out.println(ga + " " + runs + "x float");

        for (int i = 0; i < functions.length; i++) {
            ga.setF(functions[i]);
            ga.setpM(pM[i]);
            ga.setGaussK(K[i]);
            System.out.print("F=" + functions[i] + " pM=" + pM[i] + " K=" + K[i] + "\t[");
            double[] results = new double[runs];
            for (int s = 0; s < runs; s++) {
                results[s] = ga.run().f;
                System.out.print(String.format(Locale.getDefault(), "%1.0e", results[s]));
                System.out.print(s != runs - 1 ? "," : "]");
            }
            System.out.println(" Score=" + score(results, eps) + "/" + runs);
        }
    }
}


/*
GA = {eps=1.0E-6, maxIter=100000, popSize=90, k=3, dg=-50, gg=150} 20x float
F=f1 pM=0.3 K=0.1	[6e+00,1e-06,6e-06,1e+00,3e-08,8e-07,5e-06,8e-07,4e+00,8e-07,9e-07,6e-07,7e-07,6e-07,5e-06,7e-07,8e-07,3e+00,5e-07,9e-06] Score=11/20
F=f3 pM=0.5 K=0.01	[9e-07,9e-07,8e-07,3e-06,1e-06,9e-07,8e-07,1e-06,1e-06,4e-07,1e-06,7e+01,1e-06,4e-06,7e-07,6e+00,2e-06,4e-06,9e-07,9e-07] Score=13/20
F=f6 pM=0.8 K=1.0	[9e-07,4e-06,8e-08,2e-06,5e-07,8e-07,1e-06,1e-05,7e-07,1e-06,7e-09,1e-06,7e-07,4e-07,7e-07,2e-06,1e-06,6e-07,3e-07,6e-06] Score=15/20
F=f7 pM=0.5 K=0.1	[2e-03,2e-02,1e-06,1e-06,8e-07,5e-07,8e-07,7e-07,7e-07,5e-03,3e-04,7e-07,7e-07,2e-06,8e-07,5e-03,1e-06,1e-02,2e-04,9e-07] Score=12/20
 */