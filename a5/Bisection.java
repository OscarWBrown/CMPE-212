package a5;

/**
 * Root finding via the bisection method.
 *
 */
public class Bisection implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION
	
	// fields
	/**
	 * The tolerance used by this root finder.
	 */
	public static final double TOL = 1.0e-6;
	
	// constructors
	/**
	 * 
	 */
	public Bisection() {
		
	}
	
	// methods
	/**
	 * Searches for a root on the interval {@code a} to {@code b} for the specified {@code Function1} object {@code f}.
	 * Returns {@code Root.NONE} if no root is found.
	 * 
	 * The bisection method requires that the values of {@code f.eval(a)} and {@code f.eval(b)} have 
	 * opposite signs. An exception is thrown if this requirement is not satisfied.
	 * 
	 * @implSpec {@code root} in the interface {@code RootFinder1}
	 * 
	 * @param {@code f} - a one-dimensional function
	 * @param {@code a} - the minimum value of the interval to search
	 * @param {@code b} - the maximum value of the interval to search
	 * 
	 * @return a {@code Root} object containing the root if a root is found, or {@code Root.NONE} if no root is found
	 * 
	 * @throws {@code IllegalArgumentException} - if {@code a} >= {@code b}
	 * @throws {@code IllegalArgumentException} - if the value of {@code f.eval(a)} has the same sign as 
	 * {@code f.eval(b)}
	 */
	public Root root(Function1 f, double a, double b) {
		if (a >= b) {
			throw new IllegalArgumentException("minimum is greater than the maximum");
		}
		if (Math.signum(f.eval(a)) == Math.signum(f.eval(b))) {
			throw new IllegalArgumentException("minimum and  maximum have the same sign");
		}
		
		double c = (a + b) / 2;
		while (Math.abs(f.eval(c)) > TOL) {
			if (Math.signum(f.eval(c)) == Math.signum(f.eval(a))) {
				a = c;
			}
			else {
				b = c;
			}
			c = (a + b) / 2;
		}
		
		return new Root(c);
	}

	/**
	 * Small example program that finds a root of the sine and cosine functions.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Bisection bisect = new Bisection();
		Root x0 = bisect.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 cos = new Cosine();
		x0 = bisect.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
		
	}

}
