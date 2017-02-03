package optimization.function;

import matrix.Vector;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class CostFunctions {

    public static AFunction f1() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                this.setx0(new Vector(-1.9, 2.0));
                this.setxMin(new Vector(1.0, 1.0));
                this.setName("f1");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double x1 = x[0], x2 = x[1];
                double value = 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };
    }

    public static AFunction f2() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setx0(new Vector(0.1, 0.3));
                setxMin(new Vector(4.0, 2.0));
                setName("f2");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double x1 = x[0], x2 = x[1];
                double value = Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };
    }

    public static AFunction f3() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setx0(new Vector(0.0, 0.0, 0.0, 0.0, 0.0)); //PAZI DOUBLE CONTRUCTOR
                setxMin(new Vector(1.0, 1.0, 1.0, 1.0, 1.0));
                setName("f3");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double sum = 0;
                for (int i = 0; i < x.length; i++) {
                    sum += Math.pow(x[i] - i - 1, 2);
                }
                double value = sum;
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };
    }

    public static AFunction f4() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setx0(new Vector(5.1, 1.1));
                setxMin(new Vector(0.0, 0.0));
                setName("f4");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double x1 = x[0], x2 = x[1];
                double value = Math.abs((x1 - x2) * (x1 + x2)) + Math.sqrt(x1 * x1 + x2 * x2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };

    }

    public static AFunction f6() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setx0(new Vector(1.0, 1.0, 1.0, 1.0));
                setxMin(new Vector(0.0, 0.0, 0.0, 0.0));
                setName("f6");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double sum = 0;
                for (double xi : x) {
                    sum += xi * xi;
                }
                double b = Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5;
                double n = Math.pow(1 + 0.001 * sum, 2);
                double value = 0.5 + b / n;
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };
    }
}
