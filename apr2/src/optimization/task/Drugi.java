package optimization.task;

import optimization.algorithm.HookeJeeves;
import optimization.algorithm.Simplex;
import optimization.function.AFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static optimization.function.CostFunctions.*;

public class Drugi {
    public static void main(String[] args) {

        List<Integer> simplex = new ArrayList<>();
        List<Integer> hookeJeeves = new ArrayList<>();
        List<AFunction> fs = Stream
                .of(f1(), f2(), f3(), f4())
                .collect(toList());


        for (AFunction f : fs) {
            Simplex.simplex(f, f.getX0());
            simplex.add(f.getCounter());
            f.resetCounterAndMap();

            HookeJeeves.hookeJeeves(f, f.getX0());
            hookeJeeves.add(f.getCounter());
            f.resetCounterAndMap();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Simplex     ");
        for (Integer i : simplex) {
            sb.append(i).append("\t");
        }
        sb.append("\n").append("HookeJeeves ");
        for (Integer i : hookeJeeves) {
            sb.append(i).append("\t");
        }
        System.out.println(sb.toString());
    }
}