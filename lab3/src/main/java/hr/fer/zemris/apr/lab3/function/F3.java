package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class F3 implements IFunction {

    private int numOfCalls = 0;
    private int numOfDerivationCalls = 0;
    private int numOfHessianCalls = 0;
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
        return numOfDerivationCalls;
    }

    @Override
    public int getNumOfHessianCalls() {
        return numOfHessianCalls;
    }

    @Override
    public double applyPoint(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfCalls++;
        return Math.pow((x1 - 2), 2) + Math.pow((x2 + 3), 2);
    }

    @Override
    public VectorSolution getDerivative(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfDerivationCalls++;
        return new VectorSolution(2 * (x1 - 2), 2 * (x2 + 3));
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        return this.state.applyPointToLambdaFunction(lambda);
    }

    @Override
    public Matrica getHessian(VectorSolution x) {
        numOfHessianCalls++;
        return new Matrica(new double[][]{
                {2, 0},
                {0, 2}}, 2, 2);
    }

    @Override
    public Matrica getJacobianMatrix(VectorSolution x) {
        return new Matrica(new double[][]{
                {1, 0},
                {0, 1}
        }, 2, 2);
    }

    @Override
    public VectorSolution getSystem(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        return new VectorSolution((x1 - 2), (x2 + 3));
    }

    @Override
    public void setState(IFunctionState state) {
        this.state = state;
    }
}
