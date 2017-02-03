package optimization.function;

import matrix.Vector;

import java.util.Arrays;

import static java.lang.Math.pow;

@SuppressWarnings("Duplicates")
public class CostFunctionsWithDerivates {

    public static AFunctionWithDerivates f1() {
        return new AFunctionWithDerivates() {
            @Override
            protected void initProperties() {
                setx0(new Vector(-1.9, 2.0));
                setxMin(new Vector(1.0, 1.0));
                setName("f1");
            }

            @Override
            public double valueAt(double... x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                incrementCounter();
                final double x1 = x[0], x2 = x[1];
                final double value = 100 * pow(x2 - pow(x1, 2), 2) + pow(1 - x1, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }

            @Override
            public void createJacobian() {
                this.J = new IFunction[]{
                        x -> -400 * x[0] * x[1] + 400 * pow(x[0], 3) - 2 + 2 * x[0],   //d1f/dx1
                        x -> +200 * x[1] - 200 * pow(x[0], 2)                          //d1f/dx2
                };
            }

            @Override
            public void createHessian() {
                this.H = new IFunction[][]{
                        {
                                x -> -400 * x[1] + 1200 * pow(x[0], 2) + 2,   //d2f/dx1dx1
                                x -> -400 * x[0]                              //d2f/dx1dx2
                        },
                        {
                                x -> -400 * x[0],   //d2f/dx2dx1
                                x -> +200           //d2f/dx2dx2
                        }
                };
            }
        };
    }

    public static AFunctionWithDerivates f2() {
        return new AFunctionWithDerivates() {
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
                incrementCounter();
                final double x1 = x[0], x2 = x[1];
                final double value = Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2); // f(x1,x2)
                valueMap.put(Arrays.toString(x), value);
                return value;
            }

            @Override
            public void createJacobian() {
                this.J = new IFunction[]{
                        x -> 2 * (x[0] - 4),    // d1f/dx1
                        x -> 8 * (x[1] - 2)     // d1f/dx2
                };
            }

            @Override
            public void createHessian() {
                this.H = new IFunction[][]{
                        {x -> 2, x -> 0},       // d2f/dx1dx1  d2f/dx1dx2
                        {x -> 0, x -> 8}        // d2f/dx2dx1  d2f/dx2dx2
                };
            }
        };
    }

    public static AFunctionWithDerivates f3() {
        return new AFunctionWithDerivates() {
            @Override
            protected void initProperties() {
                setx0(new Vector(0.0, 0.0));
                setxMin(new Vector(2.0, -3.0));
                setName("f3");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                incrementCounter();
                final double x1 = x[0], x2 = x[1];
                final double value = pow(x1 - 2, 2) + pow(x2 + 3, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }

            @Override
            public void createJacobian() {
                this.J = new IFunction[]{
                        x -> 2 * (x[0] - 2),
                        x -> 2 * (x[1] + 3)
                };
            }

            @Override
            public void createHessian() {
                this.H = new IFunction[][]{
                        {x -> 2, x -> 0},
                        {x -> 0, x -> 2}
                };
            }
        };
    }

    public static AFunctionWithDerivates f4() {
        return new AFunctionWithDerivates() {
            @Override
            protected void initProperties() {
                setx0(new Vector(0.0, 0.0));
                setxMin(new Vector(3.0, 0.0));
                setName("f4");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                incrementCounter();
                final double x1 = x[0], x2 = x[1];
                final double value = pow(x1 - 3, 2) + pow(x2, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }

            @Override
            public void createJacobian() {
                this.J = new IFunction[]{
                        x -> 2 * (x[0] - 3),
                        x -> 2 * (x[1])
                };
            }

            @Override
            public void createHessian() {
                this.H = new IFunction[][]{
                        {x -> 2, x -> 0},
                        {x -> 0, x -> 2}
                };
            }
        };
    }

    public static AFunctionWithDerivates fPredavanja() {
        return new AFunctionWithDerivates() {
            @Override
            protected void initProperties() {
                setx0(new Vector(0.0, 0.0));
                setxMin(new Vector(2.0, -1.0));
                setName("f s predavanja");
            }

            @Override
            public double valueAt(double[] x) {
                if (valueMap.containsKey(Arrays.toString(x))) {
                    return valueMap.get(Arrays.toString(x));
                }
                incrementCounter();
                final double x1 = x[0], x2 = x[1];
                final double value = pow(x1 - 2, 2) + pow(x2 + 1, 2);
                valueMap.put(Arrays.toString(x), value);
                return value;
            }

            @Override
            public void createJacobian() {
                this.J = new IFunction[]{
                        x -> 2 * (x[0] - 2),
                        x -> 2 * (x[1] + 1)
                };
            }

            @Override
            public void createHessian() {
                this.H = new IFunction[][]{
                        {x -> 2, x -> 0},
                        {x -> 0, x -> 2}
                };
            }
        };
    }

}
