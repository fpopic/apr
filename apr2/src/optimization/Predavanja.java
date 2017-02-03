package optimization;

import matrix.Matrix;
import matrix.Vector;
import optimization.algorithm.HookeJeeves;
import optimization.function.AFunction;

public class Predavanja {

    public static void main(String[] args) {
        AFunction f = new AFunction() {
            @Override
            protected void initProperties() {
                setx0(new Vector(5,3));
            }

            @Override
            public double valueAt(double... x) {
                double x1 = x[0], x2 = x[1];
                return (x1 * x1) + 4 * (x2 * x2);
            }
        };

        double dx = 1.0;

        Matrix xBPredavanja = HookeJeeves.hookeJeeves(f, f.getX0(), dx);
        System.out.println(xBPredavanja.toString());

    }
}
