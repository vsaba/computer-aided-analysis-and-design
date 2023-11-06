package hr.fer.zemris.apr.lab5.algorithm;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;
import hr.fer.zemris.apr.lab5.system.ISystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RungeKutta4 implements IAlgorithm {

    private ISystem system;
    private double T;
    private double tMax;
    private double t0;
    private int maxIter;

    private Matrica A;
    private Matrica B;
    private int m;
    private int n;

    private PredCorrectEnum mode;

    public RungeKutta4(ISystem system, double T, double tMax, PredCorrectEnum mode) {
        this.system = system;
        this.T = T;
        this.tMax = tMax;
        this.t0 = 0;
        this.maxIter = (int) ((tMax - t0) / T);

        this.A = system.getA();
        this.B = system.getB();
        this.m = A.getM();
        this.n = A.getN();

        this.mode = mode;
        if (mode == PredCorrectEnum.PREDICTOR) {
            this.maxIter = 1;
        }
    }

    public RungeKutta4(Path path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        for (String line : lines) {
            String[] pom = line.replace(" ", "").split(":");

            switch (pom[0]) {
                case "T" -> this.T = Double.parseDouble(pom[1]);
                case "t0" -> this.t0 = Double.parseDouble(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }


    @Override
    public VectorSolution run(VectorSolution x0) {

        VectorSolution x = x0.getCopy();

        for (int i = 0; i < maxIter; i++) {
            double t_i = t0 + i * T;

            x = iteration(x, t_i);

            if (i % 50 == 0 && mode == PredCorrectEnum.NORMAL) {
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

    private VectorSolution iteration(VectorSolution x, double t_i) {

        Matrica r1 = system.getR(t_i).getAsMatrix();
        Matrica r23 = system.getR(t_i + T / 2).getAsMatrix();
        Matrica r4 = system.getR(t_i + T).getAsMatrix();

        VectorSolution m1 = A.mnozenje(x.getAsMatrix()).zbrajanje(B.mnozenje(r1)).getVector(0);
        VectorSolution m2 = A.mnozenje(x.add(m1.multiplyScalar(T / 2)).getAsMatrix()).zbrajanje(B.mnozenje(r23)).getVector(0);
        VectorSolution m3 = A.mnozenje(x.add(m2.multiplyScalar(T / 2)).getAsMatrix()).zbrajanje(B.mnozenje(r23)).getVector(0);
        VectorSolution m4 = A.mnozenje(x.add(m3.multiplyScalar(T / 2)).getAsMatrix()).zbrajanje(B.mnozenje(r4)).getVector(0);

        VectorSolution M = m1.add(m2.multiplyScalar(2)).add(m3.multiplyScalar(2)).add(m4);

        x = x.add(M.multiplyScalar(T / 6));

        return x;
    }
}
