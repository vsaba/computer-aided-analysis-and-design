package hr.fer.zemris.apr.lab4.algorithm;

import hr.fer.zemris.apr.lab4.function.IFunction;
import hr.fer.zemris.apr.lab4.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HookeJeeves implements IAlgorithm {

    private VectorSolution x_0;
    private double epsilon = 1e-6;
    private IFunction function;
    private double delta_x = 0.5;

    public HookeJeeves(VectorSolution x_0, double epsilon, IFunction function, double delta_x) {
        this.x_0 = x_0;
        this.epsilon = epsilon;
        this.function = function;
        this.delta_x = delta_x;
    }

    public HookeJeeves(VectorSolution x_0, IFunction function) {
        this.x_0 = x_0;
        this.function = function;
    }

    public HookeJeeves(Path putDoDatoteke, IFunction function) {
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
                case "delta" -> this.delta_x = Double.parseDouble(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }


    @Override
    public VectorSolution run() {

        VectorSolution x_p = x_0.getCopy();
        VectorSolution x_b = x_0.getCopy();
        VectorSolution x_n;

        do {
            x_n = localSearch(x_p);
            if (function.applyPoint(x_n) < function.applyPoint(x_b)) {
                x_p = x_n.multiplyScalar(2).subtract(x_b);
                x_b = x_n.getCopy();
            } else {
                delta_x /= 2;
                x_p = x_b.getCopy();
            }

        } while (delta_x >= epsilon);


        return x_b;
    }


    private VectorSolution localSearch(VectorSolution x_p) {

        VectorSolution x = x_p.getCopy();
        for (int i = 0; i < x.getDimension(); i++) {
            double startingValue = function.applyPoint(x);

            x.setValue(i, x.getValue(i) + delta_x);

            double newValue = function.applyPoint(x);

            if (newValue > startingValue) {
                x.setValue(i, x.getValue(i) - 2 * delta_x);
                newValue = function.applyPoint(x);
                if (newValue > startingValue) {
                    x.setValue(i, x.getValue(i) + delta_x);
                }
            }
        }

        return x;
    }
}
