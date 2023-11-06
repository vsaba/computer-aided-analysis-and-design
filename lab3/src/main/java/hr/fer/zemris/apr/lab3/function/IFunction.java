package hr.fer.zemris.apr.lab3.function;

import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.matrix.Matrica;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public interface IFunction {

    int numOfVariables();
    int getNumOfFuncCalls();
    int getNumOfDerivationCalls();
    int getNumOfHessianCalls();
    double applyPoint(VectorSolution x);
    VectorSolution getDerivative(VectorSolution x);
    double applyPointToLambdaFunction(double lambda);
    Matrica getHessian(VectorSolution x);
    Matrica getJacobianMatrix(VectorSolution x);
    VectorSolution getSystem(VectorSolution x);
    void setState(IFunctionState state);
}
