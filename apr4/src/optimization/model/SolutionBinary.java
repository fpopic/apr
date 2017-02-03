package optimization.model;

public class SolutionBinary {
    public int[][] vectors;
    public double f;

    public SolutionBinary(int[][] vectors) {
        this.vectors = vectors;
    }

    @Override
    public String toString() {
        return String.valueOf(f);
    }

}
