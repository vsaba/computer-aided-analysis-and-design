package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class Zadatak_5_func implements IFunction {

    private int numOfCalls = 0;
    private IFunctionState state;


    @Override
    public int numOfVariables() {
        return 2;
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
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfCalls++;
        return Math.pow((Math.pow(x1, 2) + Math.pow(x2, 2) - 1), 2) + Math.pow((x2 - Math.pow(x1, 2)), 2);
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
                {2 * x1, 2 * x2},
                {-2 * x1, 1}
        }, 2, 2);
    }

    @Override
    public VectorSolution getSystem(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);
        return new VectorSolution((Math.pow(x1, 2) + Math.pow(x2, 2) - 1), (x2 - Math.pow(x1, 2)));
    }

    @Override
    public void setState(IFunctionState state) {
        this.state = state;
    }
}
