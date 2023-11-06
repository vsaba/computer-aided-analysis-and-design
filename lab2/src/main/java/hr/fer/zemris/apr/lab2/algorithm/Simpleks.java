package hr.fer.zemris.apr.lab2.algorithm;

import hr.fer.zemris.apr.lab2.function.IFunction;
import hr.fer.zemris.apr.lab2.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simpleks implements IAlgorithm {

    private VectorSolution X_0;
    private double alfa = 1;
    private double beta = 0.5;
    private double gamma = 2;
    private double sigma = 0.5;
    private double epsilon = 1e-6;
    private double delta = 1;
    private IFunction function;
    private List<VectorSolution> simplexSolutions;

    public Simpleks(VectorSolution X_0, IFunction function) {
        this.X_0 = X_0;
        this.function = function;
    }

    public Simpleks(VectorSolution X_0, IFunction function, double delta){
        this.X_0 = X_0;
        this.function = function;
        this.delta = delta;
    }

    public Simpleks(VectorSolution X_0, double alfa, double beta, double gamma, double sigma, double epsilon, double delta, IFunction function) {
        this.X_0 = X_0;
        this.alfa = alfa;
        this.beta = beta;
        this.gamma = gamma;
        this.sigma = sigma;
        this.epsilon = epsilon;
        this.delta = delta;
        this.function = function;
    }

    public Simpleks(Path putDoDatoteke, IFunction function) {
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
                case "delta" -> this.delta = Double.parseDouble(pom[1]);
                case "alfa" -> this.alfa = Double.parseDouble(pom[1]);
                case "beta" -> this.beta = Double.parseDouble(pom[1]);
                case "gamma" -> this.gamma = Double.parseDouble(pom[1]);
                case "sigma" -> this.sigma = Double.parseDouble(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }

    @Override
    public VectorSolution run() {

        simplexSolutions = generateSolutions();
        VectorSolution x_c;
        do {
            Map<String, Integer> lhs = findBestWorstSecond();

            int l = lhs.get("l");
            int h = lhs.get("h");
            int s = lhs.get("s");

            VectorSolution x_h = simplexSolutions.get(h);
            VectorSolution x_l = simplexSolutions.get(l);
            VectorSolution x_s = simplexSolutions.get(s);

            x_c = new VectorSolution(new double[X_0.getDimension()]);

            for (int i = 0; i < simplexSolutions.size(); i++) {
                if (i == h) {
                    continue;
                }
                x_c.addEquals(simplexSolutions.get(i));
            }

            x_c.multiplyScalar(1 / (double) simplexSolutions.size());

            printValues(x_h, x_l, x_c);

            VectorSolution x_r = x_c.multiplyScalar(1 + alfa).subtract(x_h.multiplyScalar(alfa));   //x_r = (1 + alfa) * x_c - alfa*x_h

            if (function.apply(x_r) < function.apply(x_l)) {
                //x_e = (1 - gamma)x_c + gamma*x_r
                VectorSolution x_e = x_c.multiplyScalar(1 - gamma).add(x_r.multiplyScalar(gamma));

                if (function.apply(x_e) < function.apply(x_l)) {
                    simplexSolutions.set(h, x_e);
                } else {
                    simplexSolutions.set(h, x_r);
                }
            } else {

                boolean test = true;
                for (VectorSolution x : simplexSolutions) {
                    if (function.apply(x_r) >= function.apply(x)) {
                        test = false;
                        break;
                    }
                }

                if (test) {
                    simplexSolutions.set(h, x_r);
                } else {
                    VectorSolution x_k;
                    if (function.apply(x_r) < function.apply(simplexSolutions.get(h))) {
                        x_k = x_c.multiplyScalar(1 - beta).add(x_r.multiplyScalar(beta));
                    } else {
                        x_k = x_c.multiplyScalar(1 - beta).add(x_h.multiplyScalar(beta));
                    }

                    if (function.apply(x_k) < function.apply(simplexSolutions.get(h))) {
                        simplexSolutions.set(h, x_k);
                    } else {
                        for (int i = 0; i < simplexSolutions.size(); i++) {
                            if (i == l) {
                                continue;
                            }

                            VectorSolution x_i = simplexSolutions.get(i);
                            x_i.addEquals(simplexSolutions.get(l)).multiplyScalarEquals(1.f / 2.f);
                        }
                    }
                }

            }
        } while (checkEnd(x_c));

        Map<String, Integer> lhs = findBestWorstSecond();

        int n = simplexSolutions.size();
        VectorSolution finalSolution = new VectorSolution(new double[X_0.getDimension()]);
        for (int i = 0; i < n; i++) {
            finalSolution.addEquals(simplexSolutions.get(i));
        }

        finalSolution.multiplyScalarEquals(1.f / n);

        return finalSolution;
    }

    private void printValues(VectorSolution x_h, VectorSolution x_l, VectorSolution x_c) {
        System.out.print("X[h]: " + x_h + ", f(X[h]) = " + function.apply(x_h) + "; ");
        System.out.println("X[l]: " + x_l + ", f(X[l]) = " + function.apply(x_l));
        System.out.println("Centroid: " + x_c);
        System.out.println();
    }

    private boolean checkEnd(VectorSolution x_c) {

        double sum = 0;

        for (int i = 0; i < simplexSolutions.size(); i++) {
            sum += Math.pow(function.apply(simplexSolutions.get(i)) - function.apply(x_c), 2);
        }

        sum /= 2;
        sum = Math.sqrt(sum);

        return sum > epsilon;
    }

    private List<VectorSolution> generateSolutions() {
        List<VectorSolution> solutionsList = new ArrayList<>();
        solutionsList.add(X_0);
        for (int i = 0; i < X_0.getDimension(); i++) {
            VectorSolution x_i = X_0.getCopy();
            x_i.setValue(i, x_i.getValue(i) + delta);

            solutionsList.add(x_i);
        }

        return solutionsList;
    }


    private Map<String, Integer> findBestWorstSecond() {

        double lowest = function.apply(simplexSolutions.get(0));
        double highest = function.apply(simplexSolutions.get(0));
        double secondHighest = function.apply(simplexSolutions.get(0));

        int lIndex = 0;
        int hIndex = 0;
        int sIndex = 0;


        for (int i = 0; i < simplexSolutions.size(); i++) {
            VectorSolution x = simplexSolutions.get(i);

            if (function.apply(x) >= highest) {
                hIndex = i;
            }
        }

        for (int i = 0; i < simplexSolutions.size(); i++) {
            VectorSolution x = simplexSolutions.get(i);

            if (function.apply(x) <= lowest) {
                lIndex = i;
            }
        }

        for (int i = 0; i < simplexSolutions.size(); i++) {
            if (i == hIndex) {
                continue;
            }

            VectorSolution x = simplexSolutions.get(i);

            if (function.apply(x) >= secondHighest) {
                sIndex = i;
            }
        }

        Map<String, Integer> lhs = new HashMap<>();

        lhs.put("l", lIndex);
        lhs.put("h", hIndex);
        lhs.put("s", sIndex);

        return lhs;
    }
}
