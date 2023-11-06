package apr.lab1;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        zadatak2();
//        zadatak3();
//        zadatak4();
//        zadatak5();
//        zadatak6();
//        zadatak7();
//        zadatak8();
//        zadatak9();
//        zadatak10();


    }

    public static void zadatak2() throws IOException {

        Matrica prvaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_2/PrvaMatrica.txt"));
        Matrica drugaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_2/DrugaMatrica.txt"));

//        prvaMatrica.LUDekompozicija();

        prvaMatrica.LUPDekompozicija();

        prvaMatrica.rijesiSustav(drugaMatrica, false).ucitajUDatoteku(Paths.get("Zadaci/Zadatak_2/Rjesenje.txt"));

    }

    public static void zadatak3() throws IOException{

        Matrica matrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_3/Matrica.txt"));

//        matrica.LUDekompozicija();

        matrica.LUPDekompozicija();

        matrica.ucitajUDatoteku(Paths.get("Zadaci/Zadatak_3/Rjesenje.txt"));

    }

    public static void zadatak4()throws IOException{
        Matrica prvaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_4/PrvaMatrica.txt"));
        Matrica drugaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_4/DrugaMatrica.txt"));

//        prvaMatrica.LUDekompozicija();

        prvaMatrica.LUPDekompozicija();

        prvaMatrica.rijesiSustav(drugaMatrica, false).ucitajUDatoteku(Paths.get("Zadaci/Zadatak_4/Rjesenje.txt"));
    }

    public static void zadatak5() throws IOException{

        Matrica prvaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_5/PrvaMatrica.txt"));
        Matrica drugaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_5/DrugaMatrica.txt"));

//        prvaMatrica.LUDekompozicija();

        prvaMatrica.LUPDekompozicija();

        prvaMatrica.rijesiSustav(drugaMatrica, false).ucitajUDatoteku(Paths.get("Zadaci/Zadatak_5/Rjesenje.txt"));


    }

    public static void zadatak6() throws IOException{

        Matrica prvaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_6/PrvaMatrica.txt"));
        Matrica drugaMatrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_6/DrugaMatrica.txt"));

//        prvaMatrica.LUDekompozicija();

        prvaMatrica.LUPDekompozicija();

        prvaMatrica.rijesiSustav(drugaMatrica, false).ucitajUDatoteku(Paths.get("Zadaci/Zadatak_6/Rjesenje.txt"));


    }

    public static void zadatak7() throws IOException{
        Matrica matrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_7/Matrica.txt"));

        Matrica inverz = matrica.inverz();

        inverz.ucitajUDatoteku(Paths.get("Zadaci/Zadatak_7/Rjesenje.txt"));
    }

    public static void zadatak8() throws IOException{
        Matrica matrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_8/Matrica.txt"));

        Matrica inverz = matrica.inverz();

        inverz.ucitajUDatoteku(Paths.get("Zadaci/Zadatak_8/Rjesenje.txt"));


    }

    public static void zadatak9() throws IOException{
        Matrica matrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_9/Matrica.txt"));

        System.out.println(matrica.determinanta());
    }

    public static void zadatak10() throws IOException{
        Matrica matrica = Matrica.ucitajIzDatoteke(Paths.get("Zadaci/Zadatak_10/Matrica.txt"));

        System.out.println(matrica.determinanta());
    }

}