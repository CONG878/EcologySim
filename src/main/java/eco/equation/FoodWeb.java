package eco.equation;

public class FoodWeb {

	// Governing equations based on
	// the Lotka-Volterra equation and logistic equation.

	// Population dynamics model for a food web comprising
	// bears, wolves, and rabbits.

	double[] dxdt(double[] x, double a00, double a10, double a20, double a21, double a22, double b, double r0,
			double r1, double r2, double A, double B) {

		double dx0dt = (-a00 * x[0] - A * (a20 * x[0] + a21 * x[1]) / (x[2] + b)) * x[0] + r0 * x[0];
		double dx1dt = (-a10 * x[0] - B * (a20 * x[0] + a21 * x[1]) / (x[2] + b)) * x[1] + r1 * x[1];
		double dx2dt = (-a20 * x[0] - a21 * x[1] - a22 * x[2]) * x[2] + r2 * x[2];
		double[] dxs = { dx0dt, dx1dt, dx2dt };
		return dxs;
	}

	// RK4 method is used in temporal discretization for
	// the approximate solutions of ordinary differential equations.

	public double[] RK4(double[] tx_i, double h, double a00, double a10, double a20, double a21, double a22, double b,
			double r0, double r1, double r2, double A, double B) {
		FoodWeb d1 = new FoodWeb();
		// Count number of iterations using step size or step height h

		double[] k1 = { 0, 0, 0 }, k2 = { 0, 0, 0 }, k3 = { 0, 0, 0 }, k4 = { 0, 0, 0 }, xpk1 = { 0, 0, 0 },
				xpk2 = { 0, 0, 0 }, xpk3 = { 0, 0, 0 };

		// Iterate for number of iterations
		double[] x = { tx_i[1], tx_i[2], tx_i[3] };
		double t = tx_i[0];

		// Apply Runge-Kutta Formulas to find next value of x
		for (int j1 = 0; j1 < 3; j1++) {
			k1[j1] = h * (d1.dxdt(x, a00, a10, a20, a21, a22, b, r0, r1, r2, A, B)[j1]);
			xpk1[j1] = x[j1] + 0.5 * k1[j1];
			k2[j1] = h * (d1.dxdt(xpk1, a00, a10, a20, a21, a22, b, r0, r1, r2, A, B)[j1]);
			xpk2[j1] = x[j1] + 0.5 * k2[j1];
			k3[j1] = h * (d1.dxdt(xpk2, a00, a10, a20, a21, a22, b, r0, r1, r2, A, B)[j1]);
			xpk3[j1] = x[j1] + k3[j1];
			k4[j1] = h * (d1.dxdt(xpk3, a00, a10, a20, a21, a22, b, r0, r1, r2, A, B)[j1]);
		}

		// Update next value of x
		for (int j2 = 0; j2 < 3; j2++) {
			x[j2] = x[j2] + (1.0 / 6.0) * (k1[j2] + 2 * k2[j2] + 2 * k3[j2] + k4[j2]);
		}

		// Update next value of t
		t = t + h;
		double[] tx = { t, x[0], x[1], x[2] };
		return tx;
	}

	public static void main(String[] args) {
	}

}
