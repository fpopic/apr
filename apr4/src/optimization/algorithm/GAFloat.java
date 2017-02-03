package optimization.algorithm;

import optimization.model.SolutionFloat;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Math.abs;
import static optimization.misc.RandomGenerator.*;

@SuppressWarnings("Duplicates")
public class GAFloat extends GA {

    private double GaussK;
    private SolutionFloat best;

    public GAFloat(double eps, int maxIter, int popSize, int gaussK, int dg, int gg) {
        super(eps, maxIter, popSize, gaussK, dg, gg);
    }

    public void setGaussK(double gaussK) {
        GaussK = gaussK;
    }

    public SolutionFloat run() {
        best = null;
        if (f == null || GaussK == 0 || pM == 0) {
            System.out.println("Something is not set.");
            System.exit(-1);
        }
        List<SolutionFloat> population = generateStartPopulation();

        int iter = 0;
        while (iter < maxIter && best.f > eps) {

            //Selekcija
            List<SolutionFloat> selected = selection3(population);
            SolutionFloat parent1 = selected.get(0);
            SolutionFloat parent2 = selected.get(1);
            SolutionFloat worst = selected.get(2);
//            SortedSet<SolutionFloat> selected = selection(population);
//            SolutionFloat parent1 = selected.first();
//            selected.remove(parent1);
//            SolutionFloat parent2 = selected.first();
//            SolutionFloat worst = selected.last();

            //Krizanje
            SolutionFloat child = crossoverA(parent1, parent2);

            //Mutiranje
            mutation(child);
            child.f = evaluate(child);

            //Eliminacija
            if (child.f < worst.f) {
                population.remove(worst);
                population.add(child);
                if (child.f < best.f) {
                    best = child;
                }
            }
            iter++;
        }
        return best;
    }

    private List<SolutionFloat> generateStartPopulation() {
        List<SolutionFloat> population = new ArrayList<>(popSize);
        for (int i = 0; i < popSize; i++) {
            double[] vector = new double[this.n];
            for (int x = 0; x < vector.length; x++) {
                vector[x] = randomDouble(dg, gg);
            }
            SolutionFloat solution = new SolutionFloat(vector);
            solution.f = evaluate(solution);
            population.add(i, solution);
            if (i == 0 || solution.f < best.f) {
                best = solution;
            }
        }
        return population;
    }

    private List<SolutionFloat> selection3(List<SolutionFloat> population) {
        List<SolutionFloat> tournament = new ArrayList<>();
        while (tournament.size() < kTournament) {
            SolutionFloat solution = population.get(randomInt(0, popSize - 1));
            if (!tournament.contains(solution)) {
                tournament.add(solution);
            }
        }
        SolutionFloat worst = null;
        for (int i = 0; i < tournament.size(); i++) {
            SolutionFloat solution = tournament.get(i);
            if (i == 0 || solution.f > worst.f) {
                worst = solution;
            }
        }
        //stavi na kraj najgoru
        tournament.remove(worst);
        tournament.add(worst);
        return tournament;
    }

    private SortedSet<SolutionFloat> selectionK(final List<SolutionFloat> population) {
        SortedSet<SolutionFloat> tournament = new TreeSet<>((s1, s2) -> Double.compare(s1.f, s2.f));
        while (tournament.size() < kTournament) {
            tournament.add(population.get(randomInt(0, popSize - 1)));
        }
        return tournament;
    }

    private SolutionFloat crossoverA(SolutionFloat p1, SolutionFloat p2) {
        SolutionFloat child = new SolutionFloat(new double[n]);
        //aritmeticko krizanje (sve varijable s razlicitm parametrom a)
        for (int i = 0; i < n; i++) {
            child.vector[i] = randomDouble(p1.vector[i], p2.vector[i]);
        }
        return child;
    }

    private SolutionFloat crossoverHeuristic(SolutionFloat p1, SolutionFloat p2) {
        SolutionFloat child = new SolutionFloat(new double[n]);
        for (int i = 0; i < n; i++) {
            double a = randomDouble(0, 1);
            double c;
            do {
                c = p2.f < p1.f ?
                        a * (p2.vector[i] - p1.vector[i]) + p2.vector[i] :
                        a * (p1.vector[i] - p2.vector[i]) + p1.vector[i]
                ;
            } while (c < dg || c > gg);
            child.vector[i] = c;
        }
        return child;
    }

    private void mutation(SolutionFloat solution) {
        if (randomDouble(0, 1) < pM) {
            for (int i = 0; i < solution.vector.length; i++) {
                double m = solution.vector[i] + randomGauss(0, GaussK);
                if (m > dg && m < gg) { //
                    solution.vector[i] = m;
                }//
            }
        }
    }

    private double evaluate(SolutionFloat solution) {
        return abs(f.valueAt(solution.vector));
    }

}