package optimization.task;

import matrix.Matrix;
import optimization.algorithm.Box;
import optimization.constraint.Constraints;
import optimization.function.AFunctionWithDerivates;
import optimization.function.CostFunctionsWithDerivates;

public class MainB {
    public static void main(String[] args) {
        AFunctionWithDerivates f;
        Matrix min;

//        f = CostFunctionsWithDerivates.f1();
//        System.out.println(f);
//        min = Box.box(f, implicits12(), explicits(), f.getX0());
//        System.out.println("Minimum bez ogranièenja:" + f.getxMin());
//        System.out.println("Minimum " + min);
//        System.out.println("Broj iteracija: " + f.getCounter());
//        f.resetCounters();

        f = CostFunctionsWithDerivates.f2();
        System.out.println(f);
        min = Box.box(f, Constraints.implicits12(), Constraints.explicits(), f.getX0());
        System.out.println("Minimum bez ogranièenja:" + f.getxMin());
        System.out.println("Minimum " + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        f.resetCounters();
    }
}
