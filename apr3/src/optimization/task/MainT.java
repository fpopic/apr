package optimization.task;

import matrix.Matrix;
import optimization.algorithm.Transformation;
import optimization.function.AFunctionWithDerivates;
import optimization.function.CostFunctionsWithDerivates;

import static optimization.constraint.Constraints.implicits12;
import static optimization.constraint.Constraints.implicits4;

public class MainT {
    public static void main(String[] args) {

        double t = 1.0;
        AFunctionWithDerivates f;
        Matrix min;

        f = CostFunctionsWithDerivates.f1();
        System.out.println(f);
        min = Transformation.transformation(f, implicits12(), t, f.getX0());
        System.out.println("Minimum bez ogranièenja:" + f.getxMin());
        System.out.println("Pronadjeni minimum:" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println();
        f.resetCounters();

        t = 1.0;
        f = CostFunctionsWithDerivates.f2();
        System.out.println(f);
        min = Transformation.transformation(f, implicits12(), t, f.getX0());
        System.out.println("Minimum bez ogranièenja:" + f.getxMin());
        System.out.println("Pronadjeni minimum:" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println();
        f.resetCounters();


        t = 1.0;
        f = CostFunctionsWithDerivates.f4();
        System.out.println(f);
        min = Transformation.transformation(f, implicits4(), t, f.getX0());
        System.out.println("Minimum bez ogranièenja:" + f.getxMin());
        System.out.println("Pronadjeni minimum:" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println();
        f.resetCounters();
    }
}
