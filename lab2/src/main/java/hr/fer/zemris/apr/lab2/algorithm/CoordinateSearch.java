package hr.fer.zemris.apr.lab2.algorithm;

import hr.fer.zemris.apr.lab2.function.IFunction;
import hr.fer.zemris.apr.lab2.function.OneDimFunction;
import hr.fer.zemris.apr.lab2.interval.UnimodalInterval;
import hr.fer.zemris.apr.lab2.solution.IntervalSolution;
import hr.fer.zemris.apr.lab2.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CoordinateSearch implements IAlgorithm {

    private VectorSolution x_0;
    private double epsilon;
    private int n;
    private IFunction function;

    public CoordinateSearch(VectorSolution x_0, double epsilon, IFunction function) {
        this.x_0 = x_0;
        this.epsilon = epsilon;
        this.function = function;
    }

    public CoordinateSearch(VectorSolution x_0, IFunction function) {
        this(x_0, 1e-6, function);
    }

    public CoordinateSearch(Path putDoDatoteke, IFunction function) {
        this.function = function;

        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = Files.readAllLines(putDoDatoteke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileLines) {
            String[] pom = line.replace(" ", "").split(":");

            if (pom[0].equals("e")) {
                this.epsilon = Double.parseDouble(pom[1]);
            } else if (pom[0].equals("x_0")) {
                String[] pom2 = pom[1].substring(1, pom[1].length() - 1).split(",");
                VectorSolution x_0 = new VectorSolution(new double[pom2.length]);
                for (int i = 0; i < pom2.length; i++) {
                    x_0.setValue(i, Double.parseDouble(pom2[i]));
                }
                this.x_0 = x_0;
            } else {
                throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }

    @Override
    public VectorSolution run() {
        VectorSolution x = x_0;
        VectorSolution xs;
        do {
            xs = x.getCopy();
            for (int i = 0; i < n; i++) {
                double lambda = 0;
                IFunction oneDimFunc = new OneDimFunction(this.function, i, x);
                IAlgorithm minAlg = new ZlatniRez(x.getValue(i), oneDimFunc);

                lambda = minAlg.run().getValue(0);
                x.setValue(i, x.getValue(i) + lambda);  //x = x + lambda * e_i
            }
        } while (x.subtract(xs).norm() > epsilon);

        return x;
    }
}
