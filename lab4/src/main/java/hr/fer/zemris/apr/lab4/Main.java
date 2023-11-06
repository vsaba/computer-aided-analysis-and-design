package hr.fer.zemris.apr.lab4;

import hr.fer.zemris.apr.lab4.algorithm.Box;
import hr.fer.zemris.apr.lab4.algorithm.IAlgorithm;
import hr.fer.zemris.apr.lab4.algorithm.ProblemBezOgranicenja;
import hr.fer.zemris.apr.lab4.function.*;
import hr.fer.zemris.apr.lab4.limits.explicitLimits.IExpLimit;
import hr.fer.zemris.apr.lab4.limits.explicitLimits.Zadatak_1_ogr;
import hr.fer.zemris.apr.lab4.limits.implLimits.*;
import hr.fer.zemris.apr.lab4.solution.VectorSolution;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        zadatak1();
//        zadatak2();
//        zadatak3();
    }

    private static void zadatak1() {
        IFunction function = new RosenbrockFunction();
        VectorSolution x0 = new VectorSolution(-1.9, 2);
        List<IImplLimit> implLimits = new ArrayList<>();
        implLimits.add(new Zadatak_1_a());
        implLimits.add(new Zadatak_1_b());
        IExpLimit expLimit = new Zadatak_1_ogr();

        IAlgorithm box = new Box(implLimits, expLimit, function, x0);

        VectorSolution x = box.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 1 is x : " + x + "; function at minimum: " + function.applyPoint(x));

        System.out.println();
        ////////////////////////////////////////////////////////////////////
        System.out.println();

        function = new F2();
        x0 = new VectorSolution(0.1, 0.3);

        box = new Box(implLimits, expLimit, function, x0);

        x = box.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 2 is x : " + x + "; function at minimum: " + function.applyPoint(x));
    }

    private static void zadatak2() {
        IFunction function = new RosenbrockFunction();
        VectorSolution x0 = new VectorSolution(-1.9, 2);
        List<IImplLimit> inequalImplLimit = new ArrayList<>();
        inequalImplLimit.add(new Zadatak_1_a());
        inequalImplLimit.add(new Zadatak_1_b());
        List<IImplLimit> equalImplLimit = new ArrayList<>();

        IAlgorithm algorithm = new ProblemBezOgranicenja(inequalImplLimit, equalImplLimit, function, x0);

        VectorSolution x = algorithm.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 1 is x : " + x + "; function at minimum: " + function.applyPoint(x));

        System.out.println();
        //////////////////////////////////////////////////////////////////////////////////////
        System.out.println();

        function = new F2();
        x0 = new VectorSolution(0.1, 0.3);
        inequalImplLimit = new ArrayList<>();
        inequalImplLimit.add(new Zadatak_1_a());
        inequalImplLimit.add(new Zadatak_1_b());
        equalImplLimit = new ArrayList<>();

        algorithm = new ProblemBezOgranicenja(inequalImplLimit, equalImplLimit, function, x0);

        x = algorithm.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 2 is x : " + x + "; function at minimum: " + function.applyPoint(x));

    }

    private static void zadatak3() {
        IFunction function = new F4();
        VectorSolution x0 = new VectorSolution(0, 1);
        List<IImplLimit> inequalImplLimit = new ArrayList<>();
        inequalImplLimit.add(new Zadatak_3_a());
        inequalImplLimit.add(new Zadatak_3_b());
        List<IImplLimit> equalImplLimit = new ArrayList<>();
        equalImplLimit.add(new Zadatak_3_c());

        IAlgorithm algorithm = new ProblemBezOgranicenja(inequalImplLimit, equalImplLimit, function, x0);

        VectorSolution x = algorithm.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 4 is x : " + x + "; function at minimum: " + function.applyPoint(x));

        System.out.println();
        //////////////////////////////////////////////////////////////////////////////////
        System.out.println();

        function = new F4();
        x0 = new VectorSolution(5, 5);

        algorithm = new ProblemBezOgranicenja(inequalImplLimit, equalImplLimit, function, x0);

        x = algorithm.run();

        System.out.println("Starting point x_0 : " + x0 + "; Minimum of function 4 is x : " + x + "; function at minimum: " + function.applyPoint(x));
    }

    private static void runTransform() {
        IFunction function = new F4();
        VectorSolution x0 = new VectorSolution(0.1, 1.5);
        List<IImplLimit> inequalImplLimit = new ArrayList<>();
        inequalImplLimit.add(new Zadatak_3_a());
        inequalImplLimit.add(new Zadatak_3_b());
        List<IImplLimit> equalImplLimit = new ArrayList<>();
        equalImplLimit.add(new Zadatak_3_c());

        IAlgorithm algorithm = new ProblemBezOgranicenja(inequalImplLimit, equalImplLimit, function, x0);

        VectorSolution x = algorithm.run();

        System.out.println("Minimum x : " + x + "; Function at minimum : " + function.applyPoint(x));

    }

    private static void runBox() {
        IFunction function = new RosenbrockFunction();
        VectorSolution x0 = new VectorSolution(-1.9, 2);
        List<IImplLimit> implLimits = new ArrayList<>();
        implLimits.add(new Zadatak_1_a());
        implLimits.add(new Zadatak_1_b());
        IExpLimit expLimit = new Zadatak_1_ogr();

        IAlgorithm box = new Box(implLimits, expLimit, function, x0);

        VectorSolution x = box.run();

        System.out.println("Minimum of function 1 is x : " + x + "; function at minimum: " + function.applyPoint(x));
    }

}
