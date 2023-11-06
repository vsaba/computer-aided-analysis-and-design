package hr.fer.zemris.apr.lab5.algorithm;

import hr.fer.zemris.apr.lab5.solution.VectorSolution;
import hr.fer.zemris.apr.lab5.system.ISystem;

public class PECE implements IAlgorithm {

    //pred = Euler, corr = trapezoid

    private IAlgorithm predictor;
    private IAlgorithm corrector;
    private double T;
    private double tMax;
    private double t0;
    private int maxIter;

    public PECE(ISystem system, double T, double tMax) {
        this.predictor = new Euler(system, T, tMax, PredCorrectEnum.PREDICTOR);
        this.corrector = new Trapezoid(system, T, tMax, PredCorrectEnum.CORRECTOR);
        this.T = T;
        this.tMax = tMax;
        this.maxIter = (int) ((tMax - t0) / T);
    }

    @Override
    public VectorSolution run(VectorSolution x0) {
        return new PredictorCorrector(this.predictor, this.corrector, 2, this.T, this.tMax).run(x0);
    }

    @Override
    public void setPredictedX(VectorSolution xk) {
        //not implemented
    }

    @Override
    public void setT0(double t0) {
        this.t0 = t0;
    }
}