package hr.fer.zemris.apr.lab5.algorithm;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;
import hr.fer.zemris.apr.lab5.system.ISystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Trapezoid implements IAlgorithm {

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
    private VectorSolution x_k1;


    public Trapezoid(ISystem system, double T, double tMax, PredCorrectEnum mode) {
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
        if (this.mode == PredCorrectEnum.CORRECTOR) {
            this.maxIter = 1;
        }
    }


    public Trapezoid(Path path) {
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

        Matrica P = Matrica.stvoriJedinicnu(m, n).oduzimanje(A.mnoziSkalarom(T / 2));
        P.LUPDekompozicija();

        VectorSolution x = x0.getCopy();

        for (int i = 0; i < maxIter; i++) {
            double t_i = t0 + i * T;

            if (mode == PredCorrectEnum.NORMAL) {
                x = normalIteration(x, t_i, P);
            } else if (mode == PredCorrectEnum.CORRECTOR) {
                x = correctorIteration(x, t_i, x_k1);
            }

            if (i % 50 == 0 && mode == PredCorrectEnum.NORMAL) {
                System.out.println("Iter: " + i + "; Current solution: " + x);
            }

        }

        return x;
    }

    @Override
    public void setPredictedX(VectorSolution xk) {
        this.x_k1 = xk;
    }

    @Override
    public void setT0(double t0) {
        this.t0 = t0;
    }


    private VectorSolution normalIteration(VectorSolution x, double t_i, Matrica P) {

        Matrica r_i = system.getR(t_i).getAsMatrix();
        Matrica r_i2 = system.getR(t_i + T).getAsMatrix();

        Matrica Q1 = Matrica.stvoriJedinicnu(m, n).zbrajanje(A.mnoziSkalarom(T / 2)).mnozenje(x.getAsMatrix());
        Matrica Q2 = B.mnoziSkalarom(T / 2).mnozenje(r_i.zbrajanje(r_i2));

        Matrica Q = Q1.zbrajanje(Q2);

        x = P.rijesiSustav(Q, false).getVector(0);

        return x;
    }

    private VectorSolution correctorIteration(VectorSolution x, double t_i, VectorSolution x_k1) {
        Matrica P = this.A.mnozenje(x.add(x_k1).getAsMatrix());

        Matrica r_i = system.getR(t_i).getAsMatrix();
        Matrica r_i1 = system.getR(t_i + T).getAsMatrix();
        Matrica Q = this.B.mnozenje(r_i.zbrajanje(r_i1));

        x = x.add(P.zbrajanje(Q).mnoziSkalarom(T / 2).getVector(0));

        return x;
    }
}
