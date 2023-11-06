package hr.fer.zemris.apr.lab2;

import hr.fer.zemris.apr.lab2.algorithm.*;
import hr.fer.zemris.apr.lab2.function.*;
import hr.fer.zemris.apr.lab2.solution.VectorSolution;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        zadatak1();
//        zadatak2();
//        zadatak3();
        zadatak4();
//        zadatak5();

    }


    private static void zadatak1() {

        IFunction function = new Func_3_onedim();
        VectorSolution x_0 = new VectorSolution(10);

        IAlgorithm zlatni = new ZlatniRez(x_0.getValue(0), function);
        VectorSolution solution = zlatni.run();

        System.out.println("Zlatni rez:");
        System.out.println("Solution is: " + solution);
        System.out.println("Function value: " + function.apply(solution));
        System.out.println();

        IAlgorithm coordinate = new CoordinateSearch(x_0, function);
        solution = coordinate.run();

        System.out.println("Koordinatne osi:");
        System.out.println("Solution is: " + solution);
        System.out.println("Function value: " + function.apply(solution));
        System.out.println();

        IAlgorithm simpleks = new Simpleks(x_0, function);
        solution = simpleks.run();

        System.out.println("Simpleks:");
        System.out.println("Solution is: " + solution);
        System.out.println("Function value: " + function.apply(solution));
        System.out.println();

        IAlgorithm hookeJeeves = new HookeJeeves(x_0, function);
        solution = hookeJeeves.run();

        System.out.println("HookeJeeves:");
        System.out.println("Solution is: " + solution);
        System.out.println("Function value: " + function.apply(solution));
    }

    private static void zadatak2() {
        IFunction function1 = new RosenbrockFunction();
        IFunction function2 = new Func_2();
        IFunction function3 = new Func_3_fiveDim();
        IFunction function4 = new JakobovicFunction();

        VectorSolution solution1 = new Simpleks(new VectorSolution(-1.9, 2), function1).run();
        System.out.println("Rosenbrock function;Simpleks:");
        System.out.println("Number of function calls: " + function1.getNumOfCalls());
        System.out.println("Solution: " + solution1 + "; function value: " + function1.apply(solution1));
        VectorSolution solution2 = new Simpleks(new VectorSolution(0.1, 0.3), function2).run();
        System.out.println("Second function;Simpleks:");
        System.out.println("Number of function calls: " + function2.getNumOfCalls());
        System.out.println("Solution: " + solution2 + "; function value: " + function2.apply(solution2));
        VectorSolution solution3 = new Simpleks(new VectorSolution(0, 0, 0, 0, 0), function3).run();
        System.out.println("Third function;Simpleks:");
        System.out.println("Number of function calls: " + function3.getNumOfCalls());
        System.out.println("Solution: " + solution3 + "; function value: " + function3.apply(solution3));
        VectorSolution solution4 = new Simpleks(new VectorSolution(5.1, 1.1), function4).run();
        System.out.println("Jakobovic function;Simpleks:");
        System.out.println("Number of function calls: " + function4.getNumOfCalls());
        System.out.println("Solution: " + solution4 + "; function value: " + function4.apply(solution4));

        function1 = new RosenbrockFunction();
        function2 = new Func_2();
        function3 = new Func_3_fiveDim();
        function4 = new JakobovicFunction();

        solution1 = new HookeJeeves(new VectorSolution(-1.9, 2), function1).run();
        System.out.println("Rosenbrock function;HookeJeeves:");
        System.out.println("Number of function calls: " + function1.getNumOfCalls());
        System.out.println("Solution: " + solution1 + "; function value: " + function1.apply(solution1));
        solution2 = new HookeJeeves(new VectorSolution(0.1, 0.3), function2).run();
        System.out.println("Second function;HookeJeeves:");
        System.out.println("Number of function calls: " + function2.getNumOfCalls());
        System.out.println("Solution: " + solution2 + "; function value: " + function2.apply(solution2));
        solution3 = new HookeJeeves(new VectorSolution(0, 0, 0, 0, 0), function3).run();
        System.out.println("Third function;HookeJeeves:");
        System.out.println("Number of function calls: " + function3.getNumOfCalls());
        System.out.println("Solution: " + solution3 + "; function value: " + function3.apply(solution3));
        solution4 = new HookeJeeves(new VectorSolution(5.1, 1.1), function4).run();
        System.out.println("Jakobovic function;HookeJeeves:");
        System.out.println("Number of function calls: " + function4.getNumOfCalls());
        System.out.println("Solution: " + solution4 + "; function value: " + function4.apply(solution4));

//        function1 = new RosenbrockFunction();
//        function2 = new Func_2();
//        function3 = new Func_3_fiveDim();
//        function4 = new JakobovicFunction();
//
//        solution1 = new CoordinateSearch(new VectorSolution(-1.9, 2), function1).run();
//        System.out.println("Rosenbrock function;CoordinateSearch:");
//        System.out.println("Number of function calls: " + function1.getNumOfCalls());
//        System.out.println("Solution: " + solution1 + "; function value: " + function1.apply(solution1));
//        solution2 = new CoordinateSearch(new VectorSolution(0.1, 0.3), function2).run();
//        System.out.println("Second function;CoordinateSearch:");
//        System.out.println("Number of function calls: " + function2.getNumOfCalls());
//        System.out.println("Solution: " + solution2 + "; function value: " + function2.apply(solution2));
//        solution3 = new CoordinateSearch(new VectorSolution(0, 0, 0, 0, 0), function3).run();
//        System.out.println("Third function;CoordinateSearch:");
//        System.out.println("Number of function calls: " + function3.getNumOfCalls());
//        System.out.println("Solution: " + solution3 + "; function value: " + function3.apply(solution3));
//        solution4 = new CoordinateSearch(new VectorSolution(5.1, 1.1), function4).run();
//        System.out.println("Jakobovic function;CoordinateSearch:");
//        System.out.println("Number of function calls: " + function4.getNumOfCalls());
//        System.out.println("Solution: " + solution4 + "; function value: " + function4.apply(solution4));

    }

    private static void zadatak3() {

        IFunction function4 = new JakobovicFunction();

        VectorSolution solution = new HookeJeeves(new VectorSolution(5, 5), function4).run();

        System.out.println("Jakobovic function;HookeJeeves:");
        System.out.println("Solution: " + solution + "; function value: " + function4.apply(solution));

        solution = new Simpleks(new VectorSolution(5, 5), function4).run();

        System.out.println("Jakobovic function;Simpleks:");
        System.out.println("Solution: " + solution + "; function value: " + function4.apply(solution));

    }

    private static void zadatak4() {
        System.out.println("Eksperiment za pocetnu tocku: (0.5, 0.5)");
        for (int i = 1; i <= 5; i++) {
            int interval = new Random().nextInt(19) + 1;
            IFunction function = new RosenbrockFunction();
            IAlgorithm simpleks = new Simpleks(new VectorSolution(0.5, 0.5), function, interval);
            VectorSolution solution = simpleks.run();
            System.out.println("Za interval: " + interval + " pronadena je tocka: " + solution + ", za koju je vrijednost funckije: "
                    + "za koju je vrijednost funkcije: " + function.apply(solution) + ". Potreban broj poziva funkcije: " + function.getNumOfCalls());
        }

        System.out.println();
        System.out.println();

        System.out.println("Eksperiment za pocetnu tocku: (20, 20)");

        for (int i = 1; i <= 5; i++) {
            int interval = new Random().nextInt(20) + 1;
            IFunction function = new RosenbrockFunction();
            IAlgorithm simpleks = new Simpleks(new VectorSolution(20, 20), function);
            VectorSolution solution = simpleks.run();
            System.out.println("Za interval: " + interval + " pronadena je tocka: " + solution + ", za koju je vrijednost funckije: "
                    + "za koju je vrijednost funkcije: " + function.apply(solution) + ". Potreban broj poziva funkcije: " + function.getNumOfCalls());
        }
    }

    private static void zadatak5() {
        int n = 100;
        int globalOptimum = 0;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            IFunction function = new SchafferFunction();
            IAlgorithm algorithm = new HookeJeeves(new VectorSolution(-50 + (50 + 50) * r.nextDouble(), -50 + (50 + 50) * r.nextDouble()), function);


            VectorSolution solution = algorithm.run();
            System.out.println("Iteration: " + i);
            System.out.println("Found solution: " + solution);
            System.out.println("Function value: " + function.apply(solution));
            if (function.apply(solution) <= 1e-4) {
                globalOptimum++;
            }
            System.out.println();
        }

        System.out.println("Probability of global optimum: " + (double) globalOptimum / n);
    }
}
