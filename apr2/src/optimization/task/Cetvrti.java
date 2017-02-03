package optimization.task;

import matrix.Matrix;
import matrix.Vector;
import optimization.algorithm.Simplex;
import optimization.function.AFunction;
import optimization.function.CostFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Cetvrti {
    public static void main(String[] args) {

        List<Integer> steps = Stream.of(1, 3, 7, 15, 20).collect(toList());
        List<Integer> evals = new ArrayList<>(steps.size());
        AFunction f4 = CostFunctions.f4();

        f4.setx0(new Vector(0.5, 0.5));
        for (Integer step : steps) {
            Simplex.step = step;
            Matrix min = Simplex.simplex(f4, f4.getX0());
            evals.add(f4.getCounter());
            f4.resetCounterAndMap();
        }
        System.out.println(f4.getX0());
        System.out.println("Steps " + steps);
        System.out.println("Evals " + evals);

        System.out.println();
        evals = new ArrayList<>(steps.size());

        f4.setx0(new Vector(20, 20));
        for (Integer step : steps) {
            Simplex.step = step;
            Matrix min = Simplex.simplex(f4, f4.getX0());
            evals.add(f4.getCounter());
            f4.resetCounterAndMap();
        }
        System.out.println(f4.getX0());
        System.out.println("Steps " + steps);
        System.out.println("Evals " + evals);


    }
}
