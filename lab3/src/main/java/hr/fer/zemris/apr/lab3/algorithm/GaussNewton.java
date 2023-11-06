package hr.fer.zemris.apr.lab3.algorithm;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GaussNewton implements IAlgorithm {

    private IFunction function;
    private IFunctionState state;
    private VectorSolution x_0;
    private double epsilon = 1e-6;
    private int maxIter = 100000;
    private boolean optimizeLambda;


    public GaussNewton(IFunction function, IFunctionState state, VectorSolution x_0, boolean optimizeLambda) {
        this.function = function;
        this.state = state;
        this.x_0 = x_0;
        this.optimizeLambda = optimizeLambda;

        this.function.setState(this.state);
    }

    public GaussNewton(Path path){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines){
            String[] pom = line.replace(" ", "").split(":");

            switch (pom[0]){
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

            Matrica jacobian = this.function.getJacobianMatrix(x);
            Matrica jacobian_T = jacobian.transponiraj();

            Matrica g = this.function.getSystem(x).getAsMatrix();

            Matrica A = jacobian_T.mnozenje(jacobian);
            Matrica b = jacobian_T.mnozenje(g.mnoziSkalarom(-1));

            Matrica temp = A.rijesiSustav(b, false);

            VectorSolution deltaX = new VectorSolution(temp.dohvatiStupac(0));

            double lambda = 1;
            if (optimizeLambda) {
                this.state.setStartingVector(x);
                this.state.setDeltaX(deltaX);
                IAlgorithm algorithm = new ZlatniRez(lambda, function);
                lambda = algorithm.run().getValue(0);
            }

            if (deltaX.multiplyScalar(lambda).norm() <= epsilon) {
                return x;
            }

            x.addEquals(deltaX.multiplyScalar(lambda));

//            System.out.println("Iteration: " + i + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        }

        System.out.println("Iteration: " + maxIter + "; x = " + x + "; F(x) = " + function.applyPoint(x));


        return x;
    }
}
