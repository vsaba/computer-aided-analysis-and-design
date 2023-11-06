package hr.fer.zemris.apr.lab3.algorithm;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.solution.IntervalSolution;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

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
        this.interval = findInterval(1, x_0, function);
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
                this.interval = findInterval(1, x_0, function);
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

        double f_c = function.applyPointToLambdaFunction(c);
        double f_d = function.applyPointToLambdaFunction(d);

        while (b - a > epsilon) {
            if (f_c < f_d) {
                b = d;
                d = c;
                c = b - k * (b - a);
                f_d = f_c;
                f_c = function.applyPointToLambdaFunction(c);
            } else {
                a = c;
                c = d;
                d = a + k * (b - a);
                f_c = f_d;
                f_d = function.applyPointToLambdaFunction(d);
            }
        }


        return new VectorSolution((a + b) / 2);
    }

    private IntervalSolution findInterval(double h, double x_0, IFunction function) {

        double l = x_0 - h;
        double r = x_0 + h;
        double m = x_0;

        int step = 1;

        double fm = function.applyPointToLambdaFunction(x_0);
        double fl = function.applyPointToLambdaFunction(l);
        double fr = function.applyPointToLambdaFunction(r);

        if(fm < fr && fm < fl){
            return new IntervalSolution(l, r);
        }
        else if(fm > fr){
            do{
                l = m;
                m = r;
                fm = fr;
                r = x_0 + h * (step *= 2);
                fr = function.applyPointToLambdaFunction(r);
            }while(fm > fr);
        }
        else{
            do {
                r = m;
                m = l;
                fm = fl;
                l = x_0 - h * (step *= 2);
                fl = function.applyPointToLambdaFunction(l);
            }while(fm > fl);
        }

        return new IntervalSolution(l, r);
    }
}
