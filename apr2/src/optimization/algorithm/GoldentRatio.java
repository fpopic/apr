package optimization.algorithm;

import optimization.function.AFunction;

public class GoldentRatio {

    public static final double EPS = 10e-6; //10e-6; TODO !!!!!
    public static final double K = 0.5 * (Math.sqrt(5) - 1);

    //region Unimodal Interval

    /**
     * Postupak traženja unimodalnog intervala
     *
     * @param f  funkcija cilja
     * @param h  poèetni korak pretraživanja
     * @param x0 poèetna toèka pretraživanja
     * @return unimodalni interval [l,r]
     */
    public static Interval unimodalInterval(AFunction f, double h, double x0) {
        double l = x0 - h;
        double r = x0 + h;
        double m = x0;
        double fl, fm, fr;
        int step = 1;

        fm = f.valueAt(x0);
        fl = f.valueAt(l);
        fr = f.valueAt(r);

        if (fm < fr && fm < fl) {
            return new Interval(l, r);
        }
        else if (fm > fr) {
            do {
                l = m;
                m = r;
                fm = fr;
                r = x0 + h * (step *= 2);
                fr = f.valueAt(r);
            } while (fm > fr);
        }
        else {
            do {
                r = m;
                m = l;
                fm = fl;
                l = x0 - h * (step *= 2);
                fl = f.valueAt(l);
            } while (fm > fl);
        }
        return new Interval(l, r);
    }

    //endregion

    //region Golden Ratio


    /**
     * Algoritam zlatnog reza koji za dani unimodalni interval <code>I</code>izraèunava
     * interval u kojemu je sadržan minimum.
     *
     * @param f funkcija cilja
     * @param I unimodalni interval I = [a,b]
     * @param e preciznost
     * @return interval [a,b]
     */
    public static Interval goldenRatio(AFunction f, Interval I, double e) {
        // a c d b
        double a = I.a;
        double b = I.b;
        double c = b - K * (b - a);
        double d = a + K * (b - a);

        double fc = f.valueAt(c);
        double fd = f.valueAt(d);

        int i = 1;
//        StringBuilder sb = new StringBuilder();
//        sb.append("Zlatni rez za f na intervalu ").append(I).append("\n");

        while ((b - a) > e) {

            // lijeva unutarnja bolja
            if (fc < fd) {
                b = d;
                d = c;
                c = b - K * (b - a);
                fd = fc;
                fc = f.valueAt(c);
            }

            // desna unutarnja bolja
            else {
                a = c;
                c = d;
                d = a + K * (b - a);
                fc = fd;
                fd = f.valueAt(d);
            }

//            sb.append(i).append(".").append(" a=").append(a).append(" c=").append(c)
//                    .append(" d=").append(d).append(" b=").append(b).append("\n");
            i++;
        }
//        sb.append("\n");
//        System.out.println(sb.toString());

        return new Interval(a, b);
    }

    /**
     * Preko poèetne toèke <code>x0</code> i zadanog <code>h</code>
     * prvo se izraèuna unimodalni interval koji se predaje algoritmu
     * zlatnog reza za izraèun intervala u kojemu je sadržan minimum.
     *
     * @param f  funkcija cilja
     * @param h  poèetni korak pretraživanja
     * @param x0 poèetna toèka
     * @return interval [a,b]
     */
    public static Interval goldenRatio(AFunction f, double h, double x0) {
        Interval unimodalInterval = unimodalInterval(f, h, x0);
        return goldenRatio(f, unimodalInterval);
    }

    public static double goldenRatioMiddle(AFunction f, double h, double x0) {
        Interval i = goldenRatio(f, h, x0);
        return (i.a + i.b) / 2;
    }

    /**
     * Algoritam zlatnog reza koji za dani unimodalni interval <code>I</code>izraèunava
     * interval u kojemu je sadržan minimum.
     *
     * @param f funkcija cilja
     * @param I poèetni unimodalni interval
     * @return interval [a,b]
     */
    public static Interval goldenRatio(AFunction f, Interval I) {
        return goldenRatio(f, I, EPS);
    }

    //endregion

    //region Interval

    public static class Interval {
        public final double a;
        public final double b;

        public Interval(double a, double b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "[" + a + "," + b + "]";
        }
    }

    //endregion

}
