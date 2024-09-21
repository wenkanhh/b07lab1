public class Polynomial {
	private double[] coef;

	public Polynomial() {
		this.coef = new double[]{0};
	}

	public Polynomial(double[] coef) {
        	this.coef = coef.clone();
	}

public Polynomial add(Polynomial poly) {
	int maxLength = Math.max(this.coef.length, poly.coef.length);
	double[] resultCoef = new double[maxLength];

	for (int i = 0; i < maxLength; i++) {
        double thisCoef = 0;
		double otherCoef = 0;
		if (i < this.coef.length) {
		thisCoef = this.coef[i];
		}

		if (i < poly.coef.length) {
		otherCoef = poly.coef[i];
		}
	resultCoef[i] = thisCoef + otherCoef;
	}

	return new Polynomial(resultCoef);
}

public double evaluate(double x) {
	double result = 0;
	for (int i = 0; i < coef.length; i++) {
		result += coef[i] * Math.pow(x, i);
	}

	return result;
}

public boolean hasRoot(double x) {
	return evaluate(x) == 0;
}
}
