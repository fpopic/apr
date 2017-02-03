package optimization.task;

import matrix.Vector;
import optimization.algorithm.GABinary;
import optimization.algorithm.GAFloat;
import optimization.function.AFunction;

import java.util.Locale;

import static optimization.function.ObjectiveFunctions.f6;

@SuppressWarnings("Duplicates")
public class Task3F6 {
    public static void main(String[] args) {
        AFunction f = f6();
        double pM = 0.2;
        double K = 0.5;

        System.out.println("float3,bin3,float6,bin6");
        for (int r = 0; r < 10; r++) {
            GAFloat gaFloat3 = new GAFloat(1e-6, 20000, 30, 3, -50, 150);
            f.setxMin(new Vector(0.0, 0.0, 0.0));
            gaFloat3.setF(f);
            gaFloat3.setGaussK(K);
            gaFloat3.setpM(pM);
            double result = gaFloat3.run().f;
            System.out.print(String.format(Locale.getDefault(), "%.7f,", result));

            GAFloat gaFloat6 = new GAFloat(1e-6, 20000, 30, 3, -50, 150);
            f.setxMin(new Vector(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            gaFloat6.setF(f);
            gaFloat6.setGaussK(K);
            gaFloat6.setpM(pM);
            System.out.print(String.format(Locale.getDefault(), "%.7f,", gaFloat6.run().f));

            GABinary gaBinary3 = new GABinary(1e-6, 20000, 30, 3, -50, 150);
            f.setxMin(new Vector(0.0, 0.0, 0.0));
            gaBinary3.setF(f);
            gaBinary3.setpM(pM);
            System.out.print(String.format(Locale.getDefault(), "%.7f,", gaBinary3.run().f));

            GABinary gaBinary6 = new GABinary(1e-6, 20000, 30, 3, -50, 150);
            f.setxMin(new Vector(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            gaBinary6.setF(f);
            gaBinary6.setpM(pM);
            System.out.print(String.format(Locale.getDefault(), "%.7f", gaBinary6.run().f));

            System.out.println();
        }
    }
}
