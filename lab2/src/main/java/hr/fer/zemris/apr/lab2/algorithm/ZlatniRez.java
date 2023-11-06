package hr.fer.zemris.apr.lab2.algorithm;

import hr.fer.zemris.apr.lab2.function.IFunction;
import hr.fer.zemris.apr.lab2.interval.UnimodalInterval;
import hr.fer.zemris.apr.lab2.solution.IntervalSolution;
import hr.fer.zemris.apr.lab2.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ZlatniRez implements IAlgorithm {

    private IFunction function;
    private IntervalSolution interval;
    private double epsilon = 1e-6;
    private final double k = 0.5 * (Math.sqrt(5) - 1);

    public ZlatniRez(double x_0, double epsilon, IFunction function) {
        this(x_0, function);
        this.epsilon = epsilon;
    }

    public ZlatniRez(IntervalSolution interval, double epsilon, IFunction function) {
        this(interval, function);
        this.epsilon = epsilon;
    }

    public ZlatniRez(double x_0, IFunction function) {
        this.interval = UnimodalInterval.findInterval(1, x_0, function);
        this.function = function;
    }

    public ZlatniRez(IntervalSolution interval, IFunction function) {
        this.interval = interval;
        this.function = function;
    }

    public ZlatniRez(Path putDoDatoteke, IFunction function) {
        this.function = function;

        List<String> lines = null;
        try {
            lines = Files.readAllLines(putDoDatoteke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] pom = line.replace(" ", "").split(":");

            if (pom[0].equals("e")) {
                this.epsilon = Double.parseDouble(pom[1]);
            } else if (pom[0].equals("x_0")) {
                double x_0 = Double.parseDouble(pom[1]);
                this.interval = UnimodalInterval.findInterval(1, x_0, function);
            } else {
                String[] pom2 = pom[1].substring(1, pom[1].length() - 1).split(",");
                double l = Double.parseDouble(pom2[0]);
                double r = Double.parseDouble(pom2[1]);
                this.interval = new IntervalSolution(l, r);
            }
        }
    }


    @Override
    public VectorSolution run() {

        double a = interval.getLeft();
        double b = interval.getRight();

        double c = b - k * (b - a);
        double d = a + k * (b - a);

        double f_c = function.apply(new VectorSolution(c));
        double f_d = function.apply(new VectorSolution(d));

        while (b - a > epsilon) {
            if (f_c < f_d) {
                b = d;
                d = c;
                c = b - k * (b - a);
                f_d = f_c;
                f_c = function.apply(new VectorSolution(c));
            } else {
                a = c;
                c = d;
                d = a + k * (b - a);
                f_c = f_d;
                f_d = function.apply(new VectorSolution(d));
            }
        }


        return new VectorSolution((a + b) / 2);
    }
}
