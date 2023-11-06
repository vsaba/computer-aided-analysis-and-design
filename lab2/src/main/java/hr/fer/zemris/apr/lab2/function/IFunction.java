package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public interface IFunction {

    double apply(VectorSolution xVector);
    int getNumOfCalls();
}
