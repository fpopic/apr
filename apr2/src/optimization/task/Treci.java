package optimization.task;

import matrix.Vector;
import optimization.function.AFunction;
import optimization.function.CostFunctions;

public class Treci {
    public static void main(String[] args) {

        AFunction f4 = CostFunctions.f4();
        f4.setx0(new Vector(5.0, 5.0));
        Evaluate.evaluateSimplexHookeJeeves(f4);
    }
}
