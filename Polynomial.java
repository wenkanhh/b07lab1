import java.io.*;
import java.util.ArrayList;


public class Polynomial {
	private double[] coef;
	private int[] expo;

	public Polynomial() {
		this.coef = new double[]{0};
		this.expo = new int[]{0};
	}

	public Polynomial(double[] coef, int[] expo) {
        	this.coef = coef.clone();
			this.expo = expo.clone();
	}
	
	public Polynomial multiply(Polynomial poly) {
        ArrayList<Double> newCoefficients = new ArrayList<>();
        ArrayList<Integer> newExponents = new ArrayList<>();

        for (int i = 0; i < this.coef.length; i++) {
            for (int j = 0; j < poly.coef.length; j++) {
                double newCoef = this.coef[i] * poly.coef[j];
                int newExp = this.expo[i] + poly.expo[j];

                int index = newExponents.indexOf(newExp);
                if (index != -1) {
                    newCoefficients.set(index, newCoefficients.get(index) + newCoef);
                } else {
                    newCoefficients.add(newCoef);
                    newExponents.add(newExp);
                }
            }
        }

        return new Polynomial(doubleArray(newCoefficients), intArray(newExponents));
    }

    public Polynomial(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String polyString = br.readLine();
        br.close();
        parsePoly(polyString);
    }
	
	private void parsePoly(String polyStr) {
    	ArrayList<Double> coefList = new ArrayList<>();
        ArrayList<Integer> expList = new ArrayList<>();

        polyStr = polyStr.replaceAll("\\s+", "");
        String[] terms = polyStr.split("(?=[+-])");

        for (String term : terms) {
            double coef = 1;
            int exp = 0;

            if (term.contains("x")) {
                String[] parts = term.split("x");
                
                coef = (parts[0].equals("") || parts[0].equals("+")) ? 1 : (parts[0].equals("-") ? -1 : Double.parseDouble(parts[0]));
                
                exp = (parts.length == 1 || parts[1].isEmpty()) ? 1 : Integer.parseInt(parts[1].replace("^", ""));
            } else {
                coef = Double.parseDouble(term);
            }

            coefList.add(coef);
            expList.add(exp);
        }

        this.coef = doubleArray(coefList);
        this.expo = intArray(expList);
    }

    public Polynomial add(Polynomial poly) {
        ArrayList<Double> newCoef = new ArrayList<>();
        ArrayList<Integer> newExpo = new ArrayList<>();

        int i = 0, j = 0;
        while (i < this.coef.length || j < poly.coef.length) {
            if (i < this.coef.length && (j >= poly.coef.length || this.expo[i] > poly.expo[j])) {
                newCoef.add(this.coef[i]);
                newExpo.add(this.expo[i]);
                i++;
            } else if (j < poly.coef.length && (i >= this.coef.length || poly.expo[j] > this.expo[i])) {
                newCoef.add(poly.coef[j]);
                newExpo.add(poly.expo[j]);
                j++;
            } else {
                double sum = this.coef[i] + poly.coef[j];
                if (sum != 0) {
                    newCoef.add(sum);
                    newExpo.add(this.expo[i]);
                }
                i++;
                j++;
            }
        }

        return new Polynomial(doubleArray(newCoef), intArray(newExpo));
    }

    private double[] doubleArray(ArrayList<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private int[] intArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coef.length; i++) {
            result += coef[i] * Math.pow(x, expo[i]);
        }
        return result;
    }

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
}

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(this.toString());
        bw.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coef.length; i++) {
            if (coef[i] != 0) {
                if (sb.length() > 0 && coef[i] > 0) sb.append("+");
                sb.append(coef[i]);
                if (expo[i] > 0) sb.append("x");
                if (expo[i] > 1) sb.append("^").append(expo[i]);
            }
        }
        return sb.toString();
    }
}
