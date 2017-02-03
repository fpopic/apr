package optimization.task;

import optimization.algorithm.GABinary;
import optimization.function.AFunction;

import java.util.Locale;

import static optimization.function.ObjectiveFunctions.*;
import static optimization.misc.ResultHelper.score;

@SuppressWarnings("Duplicates")
public class Task1Binary {

    public static void main(String[] args) {
        double eps = 1e-6;
        int maxIter = 200000, popSize = 30, kTourSize = 3, dg = -50, gg = +150;
        GABinary ga = new GABinary(eps, maxIter, popSize, kTourSize, dg, gg);

        int runs = 10;

        AFunction[] functions = {f1(), f3(), f6(), f7()};
        double[] pM = {0.1, 0.1, 0.1, 0.1};
        int[] popSizes = {100, 30, 30, 100};

        System.out.println(ga + " " + runs + "x" + " binary");

        for (int i = 0; i < functions.length; i++) {
            ga.setF(functions[i]);
            ga.setpM(pM[i]);
            ga.setPopSize(popSizes[i]);
            ga.f.resetCounterAndMap();
            System.out.print("F=" + functions[i] + " pM=" + pM[i] + "\t[");
            double[] results = new double[runs];
            for (int s = 0; s < runs; s++) {
                results[s] = ga.run().f;
                System.out.print(String.format(Locale.getDefault(), "%1.0e", results[s])); //+"("+ga.f.getCounter()+")"
                System.out.print(s != runs - 1 ? "," : "]");
            }
            System.out.println(" Score=" + score(results, eps) + "/" + runs);
        }
    }

/*
AFunction[] functions = {f1(), f3(), f6(), f7()};
double[] pM = {0.1, 0.1, 0.1, 0.1};
int[] popSizes = {100, 30, 30, 100};
GA = {eps=1.0E-6, maxIter=200000, popSize=30, k=3, dg=-50, gg=150} 10x binary
F=f1 pM=0.1	[2e-06,2e-07,6e-07,1e+00,3e-07,4e-07,8e-01,1e+00,6e-04,6e-04] Score=4/10
F=f3 pM=0.1	[1e+01,4e+00,4e+00,1e+01,1e+00,2e+01,4e+00,7e-05,1e+00,1e+00] Score=0/10
F=f6 pM=0.1	[1e-02,1e-02,6e-07,3e-07,4e-07,3e-07,1e-02,1e-02,5e-07,5e-07] Score=6/10
F=f7 pM=0.1	[2e-02,6e-03,6e-03,2e-02,2e-02,2e-02,2e-02,6e-03,2e-02,6e-03] Score=0/10
*/
}