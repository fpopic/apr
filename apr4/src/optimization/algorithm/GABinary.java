package optimization.algorithm;

import optimization.model.SolutionBinary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.*;
import static optimization.misc.RandomGenerator.randomDouble;
import static optimization.misc.RandomGenerator.randomInt;

@SuppressWarnings("Duplicates")
public class GABinary extends GA {

    private int nBits;
    private SolutionBinary best;

    public GABinary(double maxError, int maxIteration, int popSize, int k, int dg, int gg) {
        super(maxError, maxIteration, popSize, k, dg, gg);
        double p = 4;
        this.nBits = (int) ceil(log10(floor(1 + (gg - dg) * pow(10, p))) / log10(2));
    }

    int[][] R; //maska

    private List<SolutionBinary> generateStartPopulation() {
        List<SolutionBinary> population = new ArrayList<>(popSize);
        for (int i = 0; i < popSize; i++) {
            int[][] vectors = new int[n][nBits];
            for (int var = 0; var < vectors.length; var++) {
                vectors[var] = code(randomInt(dg, gg));
            }
            SolutionBinary solution = new SolutionBinary(vectors);
            solution.f = evaluate(solution);
            population.add(i, solution);
            if (i == 0 || solution.f < best.f) {
                best = solution; //najbolja
            }
        }
        return population;
    }

    public SolutionBinary run() {
        best = null;
        R = new int[n][nBits];
        if (f == null || pM == 0) {
            System.out.println("F is not set.");
            System.exit(-1);
        }
        List<SolutionBinary> population = generateStartPopulation();

        int iter = 0;
        while (iter < maxIter && best.f > eps) {

            //Selekcija
            List<SolutionBinary> selected = selection(population);
            SolutionBinary parent1 = selected.get(0);
            SolutionBinary parent2 = selected.get(1);
            SolutionBinary worst = selected.get(2);

            //Krizanje
            SolutionBinary child = crossoverUniform(parent1, parent2);

            //Mutiranje
            mutation(child);
            child.f = evaluate(child);

            //Eliminacija
            if (child.f < worst.f) { //bolja od trece
                if (child.f < best.f) {
                    best = child; //novi najbolji
                }
                population.remove(worst);
                population.add(child);
            }
            iter++;
        }
        return best;
    }

    private List<SolutionBinary> selection(List<SolutionBinary> population) {
        Set<Integer> set = new HashSet<>(3);
        while (set.size() < kTournament) {
            Integer chosen = (randomInt(0, popSize - 1));
            set.add(chosen);
        }
        List<Integer> chosenIndexes = new ArrayList<>(3);
        chosenIndexes.addAll(set);

        List<SolutionBinary> tournament = new ArrayList<>(3);
        SolutionBinary worst = null;
        for (int i = 0; i < kTournament; i++) {
            SolutionBinary solution = population.get(chosenIndexes.get(i));
            tournament.add(solution);
            if (i == 0 || solution.f > worst.f) {
                worst = solution;
            }
        }

        //stavi na kraj najgoru
        tournament.remove(worst);
        tournament.add(worst);
        return tournament;
    }

    private SolutionBinary crossoverUniform(SolutionBinary A, SolutionBinary B) {
        //maska (aritmeticko)
        for (int var = 0; var < R.length; var++) {
            R[var] = code(randomInt(dg, gg));
        }
        SolutionBinary C = new SolutionBinary(new int[n][nBits]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nBits; j++) {
                C.vectors[i][j] = (A.vectors[i][j] & B.vectors[i][j]) | (R[i][j] & (A.vectors[i][j] ^ B.vectors[i][j]));
            }
        }
        return C;
    }

    private SolutionBinary crossoverTwoPoints(SolutionBinary A, SolutionBinary B) {
        SolutionBinary C = new SolutionBinary(new int[n][nBits]);
        //s dvije tockom prekida
        int first = randomInt(0, n - 1);
        int second = randomInt(first, n - 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nBits; j++) {
                if (j <= first) C.vectors[i][j] = A.vectors[i][j];
                else if (j <= second) C.vectors[i][j] = B.vectors[i][j];
                else C.vectors[i][j] = A.vectors[i][j];
            }
        }
        return C;
    }

    private void mutation(SolutionBinary C) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nBits; j++) {
                if (randomDouble(0, 1) < pM) {
                    C.vectors[i][j] = 1 - C.vectors[i][j];
                }
            }
        }
    }

    private double evaluate(SolutionBinary solution) {
        double[] decodedVars = new double[n];
        for (int i = 0; i < n; i++) {
            decodedVars[i] = decode(solution.vectors[i]);
        }
        return abs(f.valueAt(decodedVars));
    }

    int[] code(double x) { //-2.1 realni
        long b = round((x - dg) * (pow(2, nBits) - 1) / (gg - dg)); //37 dekadski
        String binString = Long.toBinaryString(b); // "_100101"
        int numZeroes = nBits - binString.length(); //how much leading zeroes
        int[] binVector = new int[nBits];
        for (int i = numZeroes; i < binVector.length; i++) {
            binVector[i] = binString.charAt(i - numZeroes) - '0';
        }
        return binVector; //[0, 1, 0, 0, 1, 0, 1]
    }

    double decode(int[] binVector) { //0100101 binarni
        long b = 0;  //37 dekadski
        for (int i = 0; i < binVector.length; i++) {
            int tezina = binVector.length - 1 - i;
            b += binVector[i] * pow(2, tezina);
        }
        return dg + b / (pow(2, nBits) - 1) * (gg - dg); //-2.1
    }

}