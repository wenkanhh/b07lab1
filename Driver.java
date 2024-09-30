import java.io.*;

public class Driver {
    public static void main(String[] args) {
        // Case 1: Default Polynomial
        System.out.println("Case 1: Default Polynomial");
        Polynomial p = new Polynomial();
        System.out.println("p(x) = " + p);
        System.out.println("p(3) = " + p.evaluate(3));
        System.out.println("p has root 0: " + p.hasRoot(0));
        System.out.println();

        // Case 2: Adding Polynomials
        System.out.println("Case 2: Adding Polynomials");
        double[] c1 = {6, 5};  // Polynomial 6 + 5x^3
        int[] e1 = {0, 3};     // Exponents 0, 3
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println("p1(x) = " + p1);

        double[] c2 = {-2, -9};  // Polynomial -2x + -9x^4
        int[] e2 = {1, 4};       // Exponents 1, 4
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("p2(x) = " + p2);

        Polynomial sum = p1.add(p2);
        System.out.println("Sum (p1 + p2)(x) = " + sum);
        System.out.println("Sum evaluated at x = 0.1: " + sum.evaluate(0.1));
        System.out.println("Sum has root 1: " + sum.hasRoot(1));
        System.out.println();

        // Case 3: Multiplying Polynomials
        System.out.println("Case 3: Multiplying Polynomials");
        Polynomial product = p1.multiply(p2);
        System.out.println("Product (p1 * p2)(x) = " + product);
        System.out.println("Product evaluated at x = 2: " + product.evaluate(2));
        System.out.println();

        // Case 4: Evaluating at negative and zero values
        System.out.println("Case 4: Evaluating at negative and zero values");
        System.out.println("p1(-2) = " + p1.evaluate(-2));
        System.out.println("p2(0) = " + p2.evaluate(0));
        System.out.println();

        // Case 5: Root checking
        System.out.println("Case 5: Root Checking");
        System.out.println("p1 has root -2: " + p1.hasRoot(-2));
        System.out.println("p2 has root 0: " + p2.hasRoot(0));
        System.out.println();

        // Case 6: Polynomial from file
        System.out.println("Case 6: Polynomial from file");
        try {
            // Assuming file "polynomial.txt" contains "5-3x2+7x8"
            Polynomial filePoly = new Polynomial(new File("polynomial.txt"));
            System.out.println("Polynomial from file: " + filePoly);
            System.out.println("File Polynomial evaluated at x = 2: " + filePoly.evaluate(2));
        } catch (IOException e) {
            System.out.println("Error reading polynomial from file: " + e.getMessage());
        }
        System.out.println();

        // Case 7: Saving polynomial to file
        System.out.println("Case 7: Saving Polynomial to file");
        try {
            sum.saveToFile("sum_polynomial.txt");
            System.out.println("Sum polynomial saved to 'sum_polynomial.txt'");
        } catch (IOException e) {
            System.out.println("Error saving polynomial to file: " + e.getMessage());
        }
    }
}
