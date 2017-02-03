package optimization.algorithm;

import optimization.function.AFunction;

public abstract class GA {

    protected final double eps;
    protected final int maxIter;
    protected final int dg;
    protected final int gg;
    public AFunction f;
    protected int popSize;
    protected double pM;
    protected int kTournament;
    protected int n;

    protected GA(double eps, int maxIter, int popSize, int k, int dg, int gg) {
        this.eps = eps;
        this.maxIter = maxIter;
        this.popSize = popSize;
        this.kTournament = k;
        this.dg = dg;
        this.gg = gg;
    }

    public void setF(AFunction f) {
        this.f = f;
        this.n = f.getxMin().n;
    }

    public void setkTournament(int kTournament) {
        this.kTournament = kTournament;
    }

    public void setpM(double pM) {
        this.pM = pM;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    @Override
    public String toString() {
        return "GA = {" +
                "eps=" + eps +
                ", maxIter=" + maxIter +
                ", popSize=" + popSize +
                ", k=" + kTournament +
                ", dg=" + dg +
                ", gg=" + gg +
                '}';
    }
}