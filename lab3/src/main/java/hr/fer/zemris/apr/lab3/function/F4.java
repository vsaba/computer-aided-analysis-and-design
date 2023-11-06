package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class F4 implements IFunction {

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
        return 1 / 4.f * Math.pow(x1, 4) - Math.pow(x1, 2) + 2 * x1 + Math.pow(x2 - 1, 2);
    }

    @Override
    public VectorSolution getDerivative(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfDerivationCalls++;
        return new VectorSolution((Math.pow(x1, 3) - 2 * x1 + 2), (2 * (x2 - 1)));
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        return this.state.applyPointToLambdaFunction(lambda);
    }

    @Override
    public Matrica getHessian(VectorSolution x) {
        double x1 = x.getValue(0);

        numOfHessianCalls++;
        return new Matrica(new double[][]{
                {3 * Math.pow(x1, 2) - 2, 0},
                {0, 2}
        }, 2, 2);
    }

    @Override
    public Matrica getJacobianMatrix(VectorSolution x) {
        //todo kad skuzis kako dobiti sustav onda napravi i jakobijanu ove funkcije
        return null;
    }

    @Override
    public VectorSolution getSystem(VectorSolution x) {
        //todo skuzi kako napraviti sustav iz ove funkcije
        return null;
    }

    @Override
    public void setState(IFunctionState state) {
        this.state = state;
    }
}
