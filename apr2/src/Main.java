import optimization.task.Evaluate;

import static optimization.function.CostFunctions.*;

public class Main {

    public static void main(String[] args) {
        Evaluate.evaluateSimplexHookeJeeves(f1());
        System.out.println();
        Evaluate.evaluateSimplexHookeJeeves(f2());
        System.out.println();
        Evaluate.evaluateSimplexHookeJeeves(f3());
        System.out.println();
        Evaluate.evaluateSimplexHookeJeeves(f4());
        System.out.println();
        Evaluate.evaluateSimplexHookeJeeves(f6());
    }



}
