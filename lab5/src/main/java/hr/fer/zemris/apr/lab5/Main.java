package hr.fer.zemris.apr.lab5;

import hr.fer.zemris.apr.lab5.algorithm.*;
import hr.fer.zemris.apr.lab5.function.Zad1TrueFunc;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;
import hr.fer.zemris.apr.lab5.system.*;

public class Main {

    public static void main(String[] args) {
//        zadatak1();
//        zadatak2();
//        zadatak3();
        zadatak4();

    }

    private static void zadatak1() {
        ISystem system = Zad1System.getInstance();
        VectorSolution x0 = new VectorSolution(1, 1);
        VectorSolution x_true = Zad1TrueFunc.getInstance().applyT(x0, 10);

        ///////////////////////////////////////////////////////////
        System.out.println("Euler: ");

        IAlgorithm algorithm = new Euler(system, 0.01, 10, PredCorrectEnum.NORMAL);
        VectorSolution x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println("");
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println("Backwards Euler: ");

        algorithm = new BackwardsEuler(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println();
        ////////////////////////////////////////////////////////////////////////////
        System.out.println("Trapezoid:");

        algorithm = new Trapezoid(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("Runge Kutta 4. reda: ");

        algorithm = new RungeKutta4(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECE:");

        algorithm = new PECE(system, 0.01, 10);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECECE:");

        algorithm = new PECECE(system, 0.01, 10);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);
        System.out.println("True solution: " + x_true);
        System.out.println("Error: " + Math.abs(x.subtract(x_true).norm()));

        System.out.println();

    }

    private static void zadatak2() {
        ISystem system = Zad2System.getInstance();
        VectorSolution x0 = new VectorSolution(1, -2);

        ///////////////////////////////////////////////////////////
        System.out.println("Euler: ");

        IAlgorithm algorithm = new Euler(system, 0.1, 1, PredCorrectEnum.NORMAL);
        VectorSolution x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println("");
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println("Backwards Euler: ");

        algorithm = new BackwardsEuler(system, 0.1, 1, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////////////////
        System.out.println("Trapezoid:");

        algorithm = new Trapezoid(system, 0.1, 1, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("Runge Kutta 4. reda: ");

        algorithm = new RungeKutta4(system, 0.01, 1, PredCorrectEnum.NORMAL);            // za T=0.01 je stabilniji Runge Kutta postupak
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECE:");

        algorithm = new PECE(system, 0.01, 1);      //sa T = 0.01 je stabilniji
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECECE:");

        algorithm = new PECECE(system, 0.01, 1);        //sa T=0.01 je stabilniji
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
    }

    private static void zadatak3() {
        ISystem system = Zad3System.getInstance();
        VectorSolution x0 = new VectorSolution(1, 3);

        ///////////////////////////////////////////////////////////
        System.out.println("Euler: ");

        IAlgorithm algorithm = new Euler(system, 0.01, 10, PredCorrectEnum.NORMAL);
        VectorSolution x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println("");
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println("Backwards Euler: ");

        algorithm = new BackwardsEuler(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////////////////
        System.out.println("Trapezoid:");

        algorithm = new Trapezoid(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("Runge Kutta 4. reda: ");

        algorithm = new RungeKutta4(system, 0.01, 10, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECE:");

        algorithm = new PECE(system, 0.01, 10);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECECE:");

        algorithm = new PECECE(system, 0.01, 10);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();

    }

    private static void zadatak4() {

        ISystem system = Zad4System.getInstance();
        VectorSolution x0 = new VectorSolution(-1, 3);

        ///////////////////////////////////////////////////////////
        System.out.println("Euler: ");

        IAlgorithm algorithm = new Euler(system, 0.01, 1, PredCorrectEnum.NORMAL);
        VectorSolution x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println("");
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println("Backwards Euler: ");

        algorithm = new BackwardsEuler(system, 0.01, 1, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////////////////
        System.out.println("Trapezoid:");

        algorithm = new Trapezoid(system, 0.01, 1, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("Runge Kutta 4. reda: ");

        algorithm = new RungeKutta4(system, 0.01, 1, PredCorrectEnum.NORMAL);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECE:");

        algorithm = new PECE(system, 0.01, 1);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();
        ////////////////////////////////////////////////////////////////
        System.out.println("PECECE:");

        algorithm = new PECECE(system, 0.01, 1);
        x = algorithm.run(x0);

        System.out.println("Found solution: " + x);

        System.out.println();

    }
}
