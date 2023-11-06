package hr.fer.zemris.apr.lab4.algorithm;

import hr.fer.zemris.apr.lab4.function.IFunction;
import hr.fer.zemris.apr.lab4.limits.implLimits.IImplLimit;
import hr.fer.zemris.apr.lab4.solution.VectorSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProblemBezOgranicenja implements IAlgorithm {

    private List<IImplLimit> inequalImplLimits;
    private List<IImplLimit> equalImplLimits;
    private IFunction function;
    private VectorSolution x0;
    private double t = 1;
    private double epsilon = 1e-6;
    private IAlgorithm minAlgorithm = null;

    public ProblemBezOgranicenja(List<IImplLimit> inequalImplLimits, List<IImplLimit> equalImplLimits, IFunction function, VectorSolution x0) {
        this.inequalImplLimits = inequalImplLimits;
        this.equalImplLimits = equalImplLimits;
        this.function = function;
        this.x0 = x0;
    }

    public ProblemBezOgranicenja(Path putDoDatoteke, IFunction function) {
        this.function = function;
        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = Files.readAllLines(putDoDatoteke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileLines) {
            String[] pom = line.replace(" ", "").split(":");

            switch (pom[0]) {
                case "e" -> this.epsilon = Double.parseDouble(pom[1]);
                case "x_0" -> {
                    String[] pom2 = pom[1].substring(1, pom[1].length() - 1).split(",");
                    VectorSolution x_0 = new VectorSolution(new double[pom2.length]);
                    for (int i = 0; i < pom2.length; i++) {
                        x_0.setValue(i, Double.parseDouble(pom2[i]));
                    }
                }
                case "t" -> this.t = Double.parseDouble(pom[1]);
                default -> throw new IllegalArgumentException("Unknown character in file");
            }
        }
    }

    private class FindInnerPoint implements IAlgorithm {

        private VectorSolution x0Inner;

        private FindInnerPoint(VectorSolution x0Inner) {
            this.x0Inner = x0Inner;
        }

        @Override
        public VectorSolution run() {

            VectorSolution x = x0Inner.getCopy();
            VectorSolution x_s;
            IFunction GFunction = this.createG();

            do {
                x_s = x;

                minAlgorithm = new HookeJeeves(x, GFunction);

                x = minAlgorithm.run();

            } while (x_s.subtract(x).norm() > epsilon);


            return x;
        }


        private IFunction createG() {

            return new IFunction() {
                @Override
                public int numOfVariables() {
                    return function.numOfVariables();
                }

                @Override
                public int getNumOfFuncCalls() {
                    return -1;
                }

                @Override
                public double applyPoint(VectorSolution x) {

                    double pointApply = 0;

                    for (IImplLimit implLimit : inequalImplLimits) {
                        int t1 = implLimit.checkLimit(x) ? 0 : 1;
                        pointApply -= t1 * implLimit.getLimitValue(x);
                    }

                    for (IImplLimit implLimit : equalImplLimits) {
                        int t1 = implLimit.checkLimit(x) ? 0 : 1;
                        pointApply -= t1 * implLimit.getLimitValue(x);
                    }

                    return pointApply;
                }
            };
        }

    }


    @Override
    public VectorSolution run() {

        VectorSolution x = x0.getCopy();

        if (!checkIfSatisfyLimits(x0)) {
            x = new FindInnerPoint(x0).run();
        }

        VectorSolution x_s;
        IFunction uFunction = createUFunction();
        int iter = 0;

        do {
            x_s = x.getCopy();

            minAlgorithm = new HookeJeeves(x, uFunction);
            x = minAlgorithm.run();

            t *= 10;

            iter++;

            if (iter == 100000) {
                System.out.println("Algoritam divergirao. Prekinut nakon " + iter + " iteracija");
                break;
            }

        } while (x_s.subtract(x).norm() > epsilon);

        System.out.println("Iterations: " + iter);

        return x;
    }


    private IFunction createUFunction() {

        return new IFunction() {
            @Override
            public int numOfVariables() {
                return function.numOfVariables();
            }

            @Override
            public int getNumOfFuncCalls() {
                return -1;
            }

            @Override
            public double applyPoint(VectorSolution x) {
                double inequalImplSum = 0;
                for (IImplLimit implLimit : inequalImplLimits) {
                    double gValue = implLimit.checkLimit(x) ? implLimit.getLimitValue(x) : Double.MAX_VALUE;
                    inequalImplSum += Math.log(gValue);
                }
                inequalImplSum /= t;

                double equalImplSum = 0;
                for (IImplLimit implLimit : equalImplLimits) {
                    equalImplSum += Math.pow(implLimit.getLimitValue(x), 2);
                }
                equalImplSum *= t;


                return function.applyPoint(x) - inequalImplSum + equalImplSum;
            }
        };

    }

    private boolean checkIfSatisfyLimits(VectorSolution x) {

        for (IImplLimit limit : inequalImplLimits) {
            if (!limit.checkLimit(x)) {
                return false;
            }
        }

        for (IImplLimit limit : equalImplLimits) {
            if (!limit.checkLimit(x)) {
                return false;
            }
        }

        return true;
    }


}
