package hr.fer.zemris.apr.lab4.algorithm;


import hr.fer.zemris.apr.lab4.function.IFunction;
import hr.fer.zemris.apr.lab4.limits.explicitLimits.IExpLimit;
import hr.fer.zemris.apr.lab4.limits.implLimits.IImplLimit;
import hr.fer.zemris.apr.lab4.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box implements IAlgorithm {

    private List<IImplLimit> implicitLimits;
    private IExpLimit explicitLimit;
    private VectorSolution x_0;
    private double epsilon = 1e-6;
    private double alfa = 1.3;
    private int n;
    private List<VectorSolution> points = new ArrayList<>();    // = null;
    private IFunction function;

    public Box(List<IImplLimit> implicitLimits, IExpLimit explicitLimits, IFunction function, VectorSolution x_0) {
        this.implicitLimits = implicitLimits;
        this.explicitLimit = explicitLimits;
        this.function = function;
        this.x_0 = x_0;
        this.n = x_0.getDimension();
    }

    public Box(Path putDoDatoteke, IFunction function) {
        this.function = function;
        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = Files.readAllLines(putDoDatoteke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileLines) {
            String[] pom = line.replace(" ", "").split(":");

            switch (pom[0]) {
                case "e" -> this.epsilon = Double.parseDouble(pom[1]);
                case "x_0" -> {
                    String[] pom2 = pom[1].substring(1, pom[1].length() - 1).split(",");
                    VectorSolution x_0 = new VectorSolution(new double[pom2.length]);
                    for (int i = 0; i < pom2.length; i++) {
                        x_0.setValue(i, Double.parseDouble(pom2[i]));
                    }
                }
                case "alfa" -> this.alfa = Double.parseDouble(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }


    @Override
    public VectorSolution run() {

        if (!checkExplicit(x_0) && !checkImplicit(x_0)) {
            throw new IllegalArgumentException("The starting point does not satisfy all limits");
        }

        VectorSolution x_c = x_0.getCopy();

        points = generateStarters(x_c);

        int iter = 0;

        do {
            int h = findIndexOfMax();
            int h2 = findIndexOfSecondMax(h);
            x_c = calculateX_C(h);

            VectorSolution x_h = points.get(h);
            VectorSolution x_r = x_c.multiplyScalar(1 + alfa).subtract(x_h.multiplyScalar(alfa));

            for (int i = 0; i < n; i++) {
                double x_d = explicitLimit.getLowerLimit();
                double x_g = explicitLimit.getUpperLimit();
                if (x_r.getValue(i) < x_d) {
                    x_r.setValue(i, x_d);
                } else if (x_r.getValue(i) > x_g) {
                    x_r.setValue(i, x_g);
                }
            }

            while (!checkImplicit(x_r)) {
                x_r.addEquals(x_c).multiplyScalarEquals(1.0 / 2.0);
            }

            VectorSolution x_h2 = points.get(h2);
            if (function.applyPoint(x_r) > function.applyPoint(x_h2)) {
                x_r.addEquals(x_c).multiplyScalarEquals(1.0 / 2.0);
            }

            points.set(h, x_r);

            iter++;

            if (iter == 100000) {
                System.out.println("Postupak divergirao. Algoritam prekinut nakon 100000 koraka.");
                break;
            }

        } while (msqe(x_c) > epsilon);


        System.out.println("Msqe: " + msqe(x_c) + "; iterations: " + iter);

        return x_c;
    }

    private VectorSolution calculateStarterX_C(List<VectorSolution> tempPoints) {

        VectorSolution x = new VectorSolution(new double[n]);

        for (VectorSolution point : tempPoints) {
            x.addEquals(point);
        }

        return x.multiplyScalar(1.0 / (tempPoints.size()));
    }

    private VectorSolution calculateX_C(int h) {

        VectorSolution x = new VectorSolution(new double[n]);

        for (int i = 0; i < this.points.size(); i++) {
            if (i == h) {
                continue;
            }
            VectorSolution point = this.points.get(i);
            x.addEquals(point);
        }

        return x.multiplyScalar(1.0 / (this.points.size() - 1));
    }

    private int findIndexOfSecondMax(int max) {
        int h = 0;

        if (h == max) {
            h++;
        }

        double secondMax = function.applyPoint(points.get(h));

        for (int i = 0; i < points.size(); i++) {
            if (i == max) {
                continue;
            }

            VectorSolution point = points.get(i);
            double fPoint = function.applyPoint(point);
            if (fPoint > secondMax) {
                secondMax = fPoint;
                h = i;
            }
        }


        return h;
    }

    private int findIndexOfMax() {
        int h = 0;
        double max = function.applyPoint(points.get(h));

        for (int i = 1; i < points.size(); i++) {
            VectorSolution point = points.get(i);
            double fPoint = function.applyPoint(point);
            if (function.applyPoint(point) > max) {
                max = function.applyPoint(point);
                h = i;
            }
        }

        return h;
    }

    private List<VectorSolution> generateStarters(VectorSolution x) {
        Random rand = new Random();
        VectorSolution x_c = x.getCopy();
        List<VectorSolution> tempPoints = new ArrayList<>();
        tempPoints.add(x_c);

        for (int i = 0; i < 2 * n; i++) {
            double[] values = new double[n];

            for (int j = 0; j < n; j++) {
                double x_d = explicitLimit.getLowerLimit();
                double x_g = explicitLimit.getUpperLimit();

                double value = x_d + rand.nextDouble() * (x_g - x_d);

                values[j] = value;
            }

            VectorSolution newX = new VectorSolution(values);

            while (!checkImplicit(newX)) {
                newX.addEquals(x_c).multiplyScalarEquals(0.5);
            }

            tempPoints.add(newX);

            x_c = calculateStarterX_C(tempPoints);

        }

        return tempPoints;
    }

    private boolean checkImplicit(VectorSolution x) {

        for (IImplLimit limit : implicitLimits) {

            if (!limit.checkLimit(x)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkExplicit(VectorSolution x) {
        double x_d = explicitLimit.getLowerLimit();
        double x_g = explicitLimit.getUpperLimit();

        for (int i = 0; i < n; i++) {
            if (x.getValue(i) < x_d || x.getValue(i) > x_g) {
                return false;
            }
        }

        return true;
    }

    private double msqe(VectorSolution x_c) {

        double fxc = function.applyPoint(x_c);
        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Math.pow(function.applyPoint(points.get(i)) - fxc, 2);
        }

        return Math.sqrt((1.0 / 2.0) * sum);
    }
}
