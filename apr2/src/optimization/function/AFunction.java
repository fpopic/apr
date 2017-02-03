package optimization.function;

import matrix.Matrix;
import matrix.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract optimization.function which for number <code>x</code> returns value
 * and for each method call <code>valueAt</code> increases it's counter.
 */
public abstract class AFunction implements IFunction {

    public Map<String, Double> valueMap;
    protected Vector x0;
    protected Vector xMin;
    protected int counter;
    protected String name;

    public AFunction() {
        this.valueMap = new HashMap<>();
        initProperties();
    }

    protected abstract void initProperties();

    //region Wrappers

    public double valueAt(Matrix x) {
        return valueAt(x.mat[0]);
    }

    //endregion

    //region Java methods

    public int getCounter() {
        return this.counter;
    }

    public void incrementCounter() {
        this.counter += 1;
    }

    public void resetCounterAndMap() {
        this.counter = 0;
        this.valueMap = new HashMap<>();
    }

    public Vector getX0() {
        return x0;
    }

    public void setx0(Vector x0) {
        this.x0 = x0;
    }

    public Vector getxMin() {
        return xMin;
    }

    public void setxMin(Vector xMin) {
        this.xMin = xMin;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    //endregion

}
