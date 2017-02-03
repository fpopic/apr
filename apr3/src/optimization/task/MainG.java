package optimization.task;

import matrix.Matrix;
import optimization.algorithm.GradientDescent;
import optimization.function.AFunctionWithDerivates;
import optimization.function.CostFunctionsWithDerivates;

public class MainG {

    public static void main(String[] args) {
        AFunctionWithDerivates f;
        Matrix min;

        f = CostFunctionsWithDerivates.f1();
        System.out.println(f);
        min = GradientDescent.gradientDescent(f, f.getX0(), true);
        System.out.println("Minimum" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println("Gradijent racunao: " + f.gradientCounter);
        System.out.println("Hessian racunao: " + f.laplaceCounter);
        f.resetCounters();
        System.out.println();

        f = CostFunctionsWithDerivates.f2();
        System.out.println(f);
        min = GradientDescent.gradientDescent(f, f.getX0(), true);
        System.out.println("Minimum" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println("Gradijent racunao: " + f.gradientCounter);
        System.out.println("Hessian racunao: " + f.laplaceCounter);
        f.resetCounters();
        System.out.println();


        f = CostFunctionsWithDerivates.f3();
        System.out.println(f);
        min = GradientDescent.gradientDescent(f, f.getX0(), true);
        System.out.println("Minimum" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println("Gradijent racunao: " + f.gradientCounter);
        System.out.println("Hessian racunao: " + f.laplaceCounter);
        f.resetCounters();
        System.out.println();

        f = CostFunctionsWithDerivates.f4();
        System.out.println(f);
        min = GradientDescent.gradientDescent(f, f.getX0(), true);
        System.out.println("Minimum" + min);
        System.out.println("Broj iteracija: " + f.getCounter());
        System.out.println("Gradijent racunao: " + f.gradientCounter);
        System.out.println("Hessian racunao: " + f.laplaceCounter);
        f.resetCounters();

//        System.out.println();
//
//        min = GradientDescent.gradientDescent(f, f.getX0(), true);
//        System.out.println("Minimum" + min);
//        System.out.println("Broj iteracija: " + f.getCounter());
//        System.out.println("Gradijent racunao: " + f.gradientCounter);
//        System.out.println("Hessian racunao: " + f.laplaceCounter);
//        f.resetCounters();

    }
}
