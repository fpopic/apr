package optimization.task;

import matrix.Vector;
import optimization.function.AFunction;
import optimization.function.CostFunctions;

public class Prvi {

    public static void main(String[] args) {
        AFunction f3 = CostFunctions.f3();
        f3.setxMin(new Vector(3.0));


        f3.setx0(new Vector(10.0));
        Evaluate.evaluateSimplexHookeJeeves(f3);
        Evaluate.evaluateGoldenRatio(f3);

        f3.setx0(new Vector(20.0));
        Evaluate.evaluateSimplexHookeJeeves(f3);
        Evaluate.evaluateGoldenRatio(f3);

        f3.setx0(new Vector(90.0));
        Evaluate.evaluateSimplexHookeJeeves(f3);
        Evaluate.evaluateGoldenRatio(f3);

        f3.setx0(new Vector(300.0));
        Evaluate.evaluateSimplexHookeJeeves(f3);
        Evaluate.evaluateGoldenRatio(f3);

        f3.setx0(new Vector(1000.0));
        Evaluate.evaluateSimplexHookeJeeves(f3);
        Evaluate.evaluateGoldenRatio(f3);

    }
}
