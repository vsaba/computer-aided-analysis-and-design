package hr.fer.zemris.apr.lab4.function;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public interface IFunction {

    int numOfVariables();

    int getNumOfFuncCalls();

    double applyPoint(VectorSolution x);

}
