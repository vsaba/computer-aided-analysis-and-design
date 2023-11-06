package apr.lab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Matrica {

    private int m;  //dimenzija redaka
    private int n;  //dimenzija stupaca
    private double[][] elementi;
    private Matrica LU;
    private Matrica P;
    private int S;  //broj koliko puta smo permutirali jedinicnu matricu

    public Matrica(double[][] elementi, int m, int n) {
        this.elementi = elementi;
        this.m = m;
        this.n = n;
        this.LU = null;
        this.P = null;
        this.S = 0;

    }

    /**
     * Loads the matrix from the provided file
     *
     * @param datoteka
     * @return
     * @throws IOException
     */
    public static Matrica ucitajIzDatoteke(Path datoteka) throws IOException {

        List<String> fileLines = Files.readAllLines(datoteka);
        int m = fileLines.size();       //broj redaka
        String firstLine = fileLines.get(0).replace("\t", " ");
        String[] pom = firstLine.split(" ");
        int n = pom.length;
        double[][] elementi = new double[m][n];


        for (int i = 0; i < m; i++) {
            if (i != 0) {
                String line = fileLines.get(i).replace("\t", " ");
                pom = line.split(" ");
            }
            for (int j = 0; j < n; j++) {
                elementi[i][j] = Double.parseDouble(pom[j]);
            }
        }

        return new Matrica(elementi, m, n);
    }

    /**
     * Saves the matrix to the provided file. Creates one if the provided one doesn't exist
     *
     * @param datoteka
     * @throws IOException
     */
    public void ucitajUDatoteku(Path datoteka) throws IOException {

        if (!Files.exists(datoteka)) {
            Files.createFile(datoteka);
        }

        String s = this.toString();

        Files.writeString(datoteka, s);

        return;
    }

    /**
     * Prints the matrix to stdout
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Returns the element on the provided location
     *
     * @param i
     * @param j
     * @return
     */
    public double dohvatiElement(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException();
        }

        return elementi[i][j];
    }


    /**
     * Sets the element to the provided location
     *
     * @param element
     * @param i
     * @param j
     */
    public void postaviElement(double element, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException();
        }

        elementi[i][j] = element;

        return;
    }


    /**
     * Sums two matrices, returns a new matrix
     *
     * @param B
     * @return
     */
    public Matrica zbrajanje(Matrica B) {

        double[][] newMatrix = zbrojiMatrice(B, false);

        return new Matrica(newMatrix, this.m, this.n);
    }

    /**
     * Sum of two matrices, returns the matrix on which the method has been called
     *
     * @param B
     * @return
     */
    public Matrica zbrajanjeJednako(Matrica B) {

        this.elementi = zbrojiMatrice(B, false);
        return this;
    }


    /**
     * Subtracts the provided matrix from the current matrix
     *
     * @param B
     * @return Returns a new matrix
     */
    public Matrica oduzimanje(Matrica B) {
        double[][] newMatrix = zbrojiMatrice(B, true);

        return new Matrica(newMatrix, this.m, this.n);
    }

    /**
     * Subtracts the provided matrix from the current matrix
     *
     * @param B
     * @return Returns the current matrix
     */
    public Matrica oduzimanjeJednako(Matrica B) {

        this.elementi = zbrojiMatrice(B, true);

        return this;
    }

    /**
     * Helper method that sums, or subtracts the provided matrix from the current matrix, based on the provided argument
     *
     * @param B
     * @param oduzimaj
     * @return
     */
    private double[][] zbrojiMatrice(Matrica B, boolean oduzimaj) {
        if (!Arrays.equals(this.getDimensions(), B.getDimensions())) {
            throw new IllegalArgumentException("Matrices need to be the same dimension in order to complete addition");
        }

        double[][] newMatrix = new double[this.m][this.n];

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                if (!oduzimaj) {
                    newMatrix[i][j] = this.elementi[i][j] + B.dohvatiElement(i, j);
                } else {
                    newMatrix[i][j] = this.elementi[i][j] - B.dohvatiElement(i, j);
                }
            }
        }

        return newMatrix;
    }

    /**
     * Transposes the current matrix
     *
     * @return
     */
    public Matrica transponiraj() {
        double[][] newMatrix = new double[this.m][this.n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[j][i] = this.elementi[i][j];
            }
        }

        this.elementi = newMatrix;

        return this;
    }

    /**
     * Multiplies the provided matrix and the current matrix
     *
     * @param B
     * @return A new matrix
     */
    public Matrica mnozenje(Matrica B) {

        double[][] newMatrix = mnozi(B);

        return new Matrica(newMatrix, this.m, B.getDimensions()[1]);
    }

    /**
     * Multiplies the provided matrix and the current matrix
     *
     * @param B
     * @return Returns the current matrix
     */
    public Matrica mnozenjeJednako(Matrica B) {

        this.elementi = mnozi(B);

        return this;
    }


    /**
     * Helper method
     *
     * @param B
     * @return
     */
    private double[][] mnozi(Matrica B) {
        if (this.m != B.getDimensions()[0]) {
            throw new IllegalArgumentException("Matrice se ne mogu pomnoziti, dimenzije ne odgovaraju");
        }

        double[][] newMatrix = new double[this.m][B.getDimensions()[1]];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < B.getDimensions()[1]; j++) {
                for (int k = 0; k < this.n; k++) {
                    newMatrix[i][j] += this.dohvatiElement(i, k) * B.dohvatiElement(k, j);
                }
            }
        }
        return newMatrix;
    }


    /**
     * Multiplies the current matrix by a scalar value
     *
     * @param skalar
     * @return A new matrix
     */
    public Matrica mnoziSkalarom(double skalar) {

        double[][] newMatrix = pomnoziSkalarom(skalar);

        return new Matrica(newMatrix, this.m, this.n);
    }

    /**
     * Multiplies the current matrix by a scalar
     *
     * @param skalar
     * @return The current matrix
     */
    public Matrica mnoziSkalaromJednako(double skalar) {

        this.elementi = pomnoziSkalarom(skalar);

        return this;
    }

    /**
     * Helper method
     *
     * @param skalar
     * @return
     */
    private double[][] pomnoziSkalarom(double skalar) {

        double[][] newMatrix = new double[this.m][this.n];

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                newMatrix[i][j] = this.elementi[i][j] * skalar;
            }
        }

        return newMatrix;
    }

    /**
     * Returns the dimensions of the matrix
     *
     * @return
     */
    public int[] getDimensions() {

        return new int[]{m, n};
    }


    /**
     * Does the LUDekomposition of the matrix
     *
     * @return
     */
    public Matrica LUDekompozicija() {
        this.P = stvoriJedinicnu(m, n);

        double[][] newMatrix = Arrays.copyOf(elementi, m * n);
        for (int i = 0; i < m - 1; i++) {
            if (newMatrix[i][i] >= -1e-4 && newMatrix[i][i] <= 1e-4 || newMatrix[i][i] == 0) {
                throw new IllegalArgumentException("Pivot je mali ili 0, koristiti LUP");
            }
            for (int j = i + 1; j < n; j++) {
                newMatrix[j][i] /= newMatrix[i][i];
                for (int k = i + 1; k < n; k++) {
                    newMatrix[j][k] -= newMatrix[j][i] * newMatrix[i][k];
                }
            }
        }

        this.LU = new Matrica(newMatrix, m, n);

        return this;
    }

    /**
     * Does the LUP decomposition of the matrix
     *
     * @return
     */
    public Matrica LUPDekompozicija() {
        this.P = stvoriJedinicnu(m, n);

        double newMatrix[][] = Arrays.copyOf(elementi, m * n);
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Math.abs(newMatrix[j][i]) > Math.abs(newMatrix[i][i])) {
                    zamijeniRetke(newMatrix, i, j);
                    this.S++;
                }
            }

            if (newMatrix[i][i] >= -1e-4 && newMatrix[i][i] <= 1e-4 && newMatrix[i][i] != 0) {
                throw new IllegalArgumentException("Pivot je mali ili 0, koristiti LUP");
            }

            for (int j = i + 1; j < n; j++) {
                newMatrix[j][i] /= newMatrix[i][i];
                for (int k = i + 1; k < n; k++) {
                    newMatrix[j][k] -= newMatrix[j][i] * newMatrix[i][k];
                }
            }
        }
        this.LU = new Matrica(newMatrix, m, n);

        return this;
    }

    /**
     * Helper method that switches the rows provided rows of the provided matrix. Also switches the rows of the P matrix
     *
     * @param matrica
     * @param redak1
     * @param redak2
     */
    private void zamijeniRetke(double[][] matrica, int redak1, int redak2) {
        for (int j = 0; j < n; j++) {
            double tmp1 = P.dohvatiElement(redak1, j);
            P.postaviElement(P.dohvatiElement(redak2, j), redak1, j);
            P.postaviElement(tmp1, redak2, j);

            double tmp2 = matrica[redak1][j];
            matrica[redak1][j] = matrica[redak2][j];
            matrica[redak2][j] = tmp2;
        }
    }

    /**
     * Finds the inverse of the matrix using the LU matrix
     *
     * @return
     */
    public Matrica inverz() {

        if (LU == null) {
            LUPDekompozicija();
        }

        if(determinanta() >= -1e-9 && determinanta() <= 1e-9){
            throw new IllegalArgumentException("Determinanta je 0. Inverz ne postoji!");
        }

        //koristimo P matricu
        double[][] b = new double[m][1];
        Matrica inverse = new Matrica(new double[m][n], m, n);
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                b[j][0] = P.dohvatiElement(j, i);
            }

            Matrica temp = rijesiSustav(new Matrica(b, m, 1), true);

            for (int j = 0; j < m; j++) {
                inverse.postaviElement(temp.dohvatiElement(j, 0), j, i);
            }

        }

        return inverse;
    }

    /**
     * Calculates the determinant of the matrix using the LU matrix
     *
     * @return
     */
    public double determinanta() {

        if (LU == null) {
            LUPDekompozicija();
        }

        double detP = Math.pow(-1, S);
        double detL = 1;

        double detU = 1;
        for (int i = 0; i < m; i++) {
            detU *= this.LU.dohvatiElement(i, i);
        }

        return detP * detL * detU;
    }

    /**
     * Solves a system of equations using the LU matrix, forward and backwards substitution
     *
     * @param b
     * @param inverse
     * @return
     */
    public Matrica rijesiSustav(Matrica b, boolean inverse) {
        if (LU == null) {
            LUPDekompozicija();
        }

        //unaprijedna supstitucija
        Matrica L = getL();
        if (!inverse) {
            b = P.mnozenje(b);
        }
        for (int i = 1; i < m; i++) {
            double element = b.dohvatiElement(i, 0);
            for (int j = 0; j < i; j++) {
                double Lel = L.dohvatiElement(i, j);
                double bel = b.dohvatiElement(j, 0);
                element -= L.dohvatiElement(i, j) * b.dohvatiElement(j, 0);
            }
            b.postaviElement(element, i, 0);
        }

        //unazadna supstitucija
        Matrica U = getU();
        for (int i = m - 1; i >= 0; i--) {
            double element = b.dohvatiElement(i, 0);
            for (int j = m - 1; j > i; j--) {
                element -= U.dohvatiElement(i, j) * b.dohvatiElement(j, 0);
            }

            element /= U.dohvatiElement(i, i);
            b.postaviElement(element, i, 0);
        }

        return b;
    }

    /**
     * Gets the L matrix from the LU matrix
     *
     * @return
     */
    private Matrica getL() {
        double[][] newMatrix = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    newMatrix[i][j] = 1;
                } else if (j < i) {
                    newMatrix[i][j] = LU.dohvatiElement(i, j);
                } else {
                    newMatrix[i][j] = 0;
                }
            }
        }
        return new Matrica(newMatrix, m, n);
    }

    /**
     * gets the U matrix from the LU matrix
     *
     * @return
     */
    private Matrica getU() {
        double[][] newMatrix = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    newMatrix[i][j] = 0;
                } else {
                    newMatrix[i][j] = LU.dohvatiElement(i, j);
                }
            }
        }
        return new Matrica(newMatrix, m, n);
    }

    /**
     * creates a unit matrix
     *
     * @param m
     * @param n
     * @return
     */
    private Matrica stvoriJedinicnu(int m, int n) {
        double[][] newMatrix = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    newMatrix[i][j] = 1;
                } else {
                    newMatrix[i][j] = 0;
                }
            }
        }

        return new Matrica(newMatrix, m, n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrica matrica = (Matrica) o;

        if (m != matrica.m) return false;
        if (n != matrica.n) return false;
        return Arrays.deepEquals(elementi, matrica.elementi);
    }

    @Override
    public int hashCode() {
        int result = m;
        result = 31 * result + n;
        result = 31 * result + Arrays.deepHashCode(elementi);
        return result;
    }

    @Override
    public String toString() {
        String s = new String();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 1 == n) {
                    s += this.elementi[i][j];
                    break;
                }
                s += this.elementi[i][j] + "\t";
            }

            s += "\n";
        }
        return s;
    }

}
