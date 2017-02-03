package optimization.task;

import matrix.Matrix;
import matrix.Vector;
import optimization.algorithm.Simplex;
import optimization.function.AFunction;
import optimization.function.CostFunctions;

import java.util.Random;

public class Peti {
    public static void main(String[] args) {
        AFunction f6 = CostFunctions.f6();
        f6.setxMin(new Vector(0.0, 0.0));
        double ok = 0;
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            double x = -50 + (50 - (-50)) * r.nextDouble();
            double y = -50 + (50 - (-50)) * r.nextDouble();
            f6.setx0(new Vector(x, y));
            Matrix min = Simplex.simplex(f6, f6.getX0());
            System.out.println("Minimum=" + min + " " + f6.getCounter() + "x");
            f6.resetCounterAndMap();
            if (Math.abs(f6.valueAt(min) - f6.valueAt(f6.getxMin())) < 10e-4) {
                ok++;
            }
        }
        System.out.println(ok / 10000);//10000
    }
}
