package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class RosenbrockFunction implements IFunction {

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
        return 100 * Math.pow((x2 - Math.pow(x1, 2)), 2) + Math.pow((1 - x1), 2);
    }

    @Override
    public VectorSolution getDerivative(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);


        double gradX1 = 200 * (x2 - Math.pow(x1, 2)) * (-2 * x1) - 2 * (1 - x1);
        double gradX2 = 200 * (x2 - Math.pow(x1, 2));

        numOfDerivationCalls++;
        return new VectorSolution(gradX1, gradX2);
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        return this.state.applyPointToLambdaFunction(lambda);
    }

    @Override
    public Matrica getHessian(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);


        double[][] elementi = new double[][]{
                {1200 * Math.pow(x1, 2) - 400 * x2 + 2, -400 * x1},
                {-400 * x1, 200}
        };

        numOfHessianCalls++;
        return new Matrica(elementi, 2, 2);
    }

    @Override
    public Matrica getJacobianMatrix(VectorSolution x) {

        double x1 = x.getValue(0);

        return new Matrica(new double[][]{
                {-20 * x1, 10},
                {-1, 0}
        }, 2, 2);
    }

    @Override
    public VectorSolution getSystem(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        return new VectorSolution((10 * (x2 - Math.pow(x1, 2))), (1 - x1));
    }

    @Override
    public void setState(IFunctionState state) {
        this.state = state;
    }
}
