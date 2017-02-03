package optimization.function;

import matrix.Vector;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class ObjectiveFunctions {

    //PAZI! nemoj ovo koristiti u sljedecim labosima ne set-am x0 jer genetskom ne treba


    public static AFunction f1() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                this.setxMin(new Vector(1.0, 1.0)); // n = 2 fiksno
                this.setName("f1");
            }

            @Override
            public double valueAt(double... x) {
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

    public static AFunction f3() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setxMin(new Vector(1.0, 2.0, 3.0, 4.0, 5.0)); // n = 5
                setName("f3");
            }

            @Override
            public double valueAt(double... x) {
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

    public static AFunction f6() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setxMin(new Vector(0.0, 0.0)); // n = 2
                setName("f6");
            }

            @Override
            public double valueAt(double... x) {
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

    public static AFunction f7() {
        return new AFunction() {
            @Override
            protected void initProperties() {
                setxMin(new Vector(0.0, 0.0)); // n = 2
                setName("f7");
            }

            @Override
            public double valueAt(double... x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                this.incrementCounter();
                double sum = 0;
                for (double xi : x) {
                    sum += pow(xi, 2);
                }
                double firstParantheses = pow(sum, 0.25);
                double secondParantheses = 1 + pow(sin(50 * pow(sum, 0.1)), 2);
                double value = firstParantheses * secondParantheses;
                valueMap.put(Arrays.toString(x), value);
                return value;
            }
        };
    }

}