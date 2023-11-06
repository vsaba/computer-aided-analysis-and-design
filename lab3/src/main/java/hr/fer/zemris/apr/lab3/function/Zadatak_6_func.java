package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class Zadatak_6_func implements IFunction {

    private int numOfCalls = 0;
    private IFunctionState state;


    @Override
    public int numOfVariables() {
        return 3;
    }

    @Override
    public int getNumOfFuncCalls() {
        return numOfCalls;
    }

    @Override
    public int getNumOfDerivationCalls() {
        return -1;
    }

    @Override
    public int getNumOfHessianCalls() {
        return -1;
    }

    @Override
    public double applyPoint(VectorSolution x) {
        VectorSolution g = this.getSystem(x);
        double g1 = g.getValue(0);
        double g2 = g.getValue(1);
        double g3 = g.getValue(2);
        double g4 = g.getValue(3);
        double g5 = g.getValue(4);
        double g6 = g.getValue(5);

        numOfCalls++;
        return Math.pow(g1, 2) + Math.pow(g2, 2) + Math.pow(g3, 2) + Math.pow(g4, 2) + Math.pow(g5, 2) + Math.pow(g6, 2);
    }

    @Override
    public VectorSolution getDerivative(VectorSolution x) {
        throw new IllegalArgumentException("This method is not implemented");
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        return this.state.applyPointToLambdaFunction(lambda);
    }

    @Override
    public Matrica getHessian(VectorSolution x) {
        throw new IllegalArgumentException("This method is not implemented");
    }

    @Override
    public Matrica getJacobianMatrix(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);


        return new Matrica(new double[][]{
                {Math.exp(x2), x1 * Math.exp(x2), 1},
                {Math.exp(2 * x2), 2 * x1 * Math.exp(2 * x2), 1},
                {Math.exp(3 * x2), 3 * x1 * Math.exp(3 * x2), 1},
                {Math.exp(5 * x2), 5 * x1 * Math.exp(5 * x2), 1},
                {Math.exp(6 * x2), 6 * x1 * Math.exp(6 * x2), 1},
                {Math.exp(7 * x2), 7 * x1 * Math.exp(7 * x2), 1}
        }, 6, 3);
    }

    @Override
    public VectorSolution getSystem(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);
        double x3 = x.getValue(2);

        double g1 = getFunctionG(x1, x2, x3, 1, 3);
        double g2 = getFunctionG(x1, x2, x3, 2, 4);
        double g3 = getFunctionG(x1, x2, x3, 3, 4);
        double g4 = getFunctionG(x1, x2, x3, 5, 5);
        double g5 = getFunctionG(x1, x2, x3, 6, 6);
        double g6 = getFunctionG(x1, x2, x3, 7, 8);

        return new VectorSolution(g1, g2, g3, g4, g5, g6);
    }

    @Override
    public void setState(IFunctionState state) {
        this.state = state;
    }

    private double getFunctionG(double x1, double x2, double x3, double t, double y) {
        return x1 * Math.exp(t * x2) + x3 - y;
    }
}
