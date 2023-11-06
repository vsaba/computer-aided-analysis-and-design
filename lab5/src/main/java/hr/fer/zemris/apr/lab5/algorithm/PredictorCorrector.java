package hr.fer.zemris.apr.lab5.algorithm;

import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public class PredictorCorrector implements IAlgorithm {

    private int s;
    private IAlgorithm predictor;
    private IAlgorithm corrector;
    private double T;
    private double tMax;
    private double t0;
    private int maxIter;

    public PredictorCorrector(IAlgorithm predictor, IAlgorithm corrector, int s, double T, double tMax) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.s = s;
        this.T = T;
        this.tMax = tMax;
        this.t0 = 0;
        this.maxIter = (int) ((tMax - t0) / T);
    }


    @Override
    public VectorSolution run(VectorSolution x0) {
        VectorSolution x = x0.getCopy();

        for (int i = 0; i < maxIter; i++) {
            double t_i = t0 + i * T;

            predictor.setT0(t_i);
            VectorSolution x_k = predictor.run(x);

            corrector.setT0(t_i);
            for (int j = 0; j < s; j++) {
                corrector.setPredictedX(x_k);
                x_k = corrector.run(x);
            }

            x = x_k;

            if (i % 50 == 0) {
                System.out.println("Iter: " + i + "; Current solution: " + x);
            }
        }

        return x;
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
