package hr.fer.zemris.apr.lab3.algorithm;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GradijentniSpust implements IAlgorithm {

    private int maxIter = 100000;
    private IFunction function;
    private VectorSolution x_0;
    private double epsilon = 1e-6;
    private boolean optimizeLambda;
    private IFunctionState state;

    public GradijentniSpust(IFunction function, IFunctionState state, VectorSolution x_0, boolean optimizeLambda) {
        this.function = function;
        this.x_0 = x_0;
        this.optimizeLambda = optimizeLambda;
        this.state = state;
        this.function.setState(this.state);
    }

    public GradijentniSpust(Path path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
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
                case "optimize" -> this.optimizeLambda = Boolean.parseBoolean(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }

    @Override
    public VectorSolution run() {

        VectorSolution x = x_0.getCopy();

        for (int i = 0; i < maxIter; i++) {

            VectorSolution gradient = function.getDerivative(x);

            if (gradient.norm() <= epsilon) {
                return x;
            }

            double lambda = 1;

            if (optimizeLambda) {
                this.state.setStartingVector(x);
                IAlgorithm algorithm = new ZlatniRez(lambda, function);
                lambda = algorithm.run().getValue(0);
            }

            x.subtractEquals(gradient.multiplyScalar(lambda));


        }

        System.out.println("Iteration: " + maxIter + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        return x;
    }
}
