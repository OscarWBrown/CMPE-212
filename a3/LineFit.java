package a3;

import java.awt.Color;


import java.util.ArrayList;
import java.util.List;

import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

import princeton.introcs.StdDraw;

public class LineFit {

	/**
	 * Finds the line of best fit to a sequence of points using ordinary least
	 * squares. The returned array consists of the y-intercept followed by
	 * the slope of the best fit line. If {@code a} is the returned array,
	 * then the equation of the line is given by:
	 * 
	 * <pre>
	 * y = a[0] + a[1] * x;
	 * </pre>
	 * 
	 * @param p a list of points to fit a line to
	 * @return an array containing the y-intercept and slope of the best fit line
	 * @throws IllegalArgumentException if {@code p} has fewer than two points
	 * @throws ArithmeticException if the line of best fit is exactly vertical
	 */
	public static double[] lsfit(List<Vector2> p) {
		double n = p.size();
		if (n < 2) {
			throw new IllegalArgumentException("list of points has fewer than two points");
		}
		
		Vector2 sumxy = new Vector2();
		Vector2 temp = new Vector2();
		double productxy = 0.0;
		double sumProductxy = 0.0;
		double xSquared = 0.0;
		double sumxSquared = 0.0;
		
		
		for (int i = 0; i < n; i++) {
			temp = p.get(i);
			sumxy.add(temp);
			productxy = temp.x() * temp.y();
			xSquared = temp.x() * temp.x();
			sumxSquared += xSquared;
			sumProductxy += productxy;
		}
		
		double sumx = sumxy.x();
		double sumy = sumxy.y();
		
		Matrix2 A = new Matrix2(n, sumx, sumx, sumxSquared);
		Vector2 B = new Vector2(sumy, sumProductxy);
		
		Matrix2 invA = A.inv();
		Vector2 V = invA.mult(B);
		
		if (V.y() == Double.POSITIVE_INFINITY || V.y() == Double.NEGATIVE_INFINITY) {
			throw new ArithmeticException("line of best fit is exactly vertical");
		}
		
		double[] lineParameters = {V.x(), V.y()};
		return lineParameters;
	}

	/**
	 * Plots the specified sequence of points and line.
	 * 
	 * @param p a list of points to plot
	 * @param line the y-intercept and slope of the line to plot
	 */
	public static void plot(List<Vector2> p, double[] line) {
		StdDraw.clear();
		for (Vector2 v : p) {
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.filledCircle(v.x(), v.y(), 0.25);
		}
		double x1 = -10.0;
		double y1 = line[1] * x1 + line[0];
		double x2 = 10.0;
		double y2 = line[1] * x2 + line[0];
		StdDraw.setPenColor(Color.RED);
		StdDraw.line(x1, y1, x2, y2);
	}

	/**
	 * Plots 10 points randomly displaced vertically from the line
	 * {@code y = 0.0 + x} and the line of the best fit. Prints the
	 * slope and intercept of the best fit line to standard output. 
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		StdDraw.setScale(-11, 11);
		List<Vector2> p = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			double x = i * 2 - 10;
			double y = x + (Math.random() - 0.5) * 4;
			p.add(new Vector2(x, y));
		}
		double[] params = lsfit(p);
		System.out.println("slope : " + params[1] + ", intercept : " + params[0]);
		plot(p, params);
	}
}
