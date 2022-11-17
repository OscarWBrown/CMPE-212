package a5;

/**
 * Root finding via Newton's method.
 *
 */
public class Newton implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION

	// fields 
	/**
	 * The tolerance used by this root finder.
	 */
	public static final double TOL = 1.0e-6;
	private Function1 df;
	
	// constructors
	/**
	 * Specifies the derivative function {@code df} for the function that this object finds the root
	 * of.
	 * 
	 * @param {@code df} - the derivative of the function to find the root of 
	 */
	public Newton(Function1 df) {
		this.df = df;
	}
	
	// methods
	/**
	 * Searches for a root on the interval {@code a} to {@code b} using Newton.s method. The initial 
	 * estimate of the root is the average of {@code a} and {@code b}.
	 * 
	 * If a root is found that is not in the interval [{@code a}, {@code b}] the this root finder returns
	 * Root.NONE.
	 * 
	 * @implSpec {@code root} in interface {@code RootFinder1}
	 * 
	 * @param {@code f} - a one-dimensional function
	 * @param {@code a} - the minimum value of the interval to search
	 * @param {@ code b} - the maximum value of the interval to search
	 * 
	 * @return a (@code Root} object containing the root if a root is found, or {@code Root.NONE} if not root is found
	 * 
	 * @throws {@code IllegalArgumentException} - if {@code a} >= {@code b}
	 */
	public Root root(Function1 f, double a, double b) {
		if (a >= b) {
			throw new IllegalArgumentException("minimum is greater than maximum");
		}
		
		double x0 = (a + b) / 2;
		while (Math.abs(f.eval(x0)) > TOL) {
			x0 = x0 - f.eval(x0) / (this.df).eval(x0);
		}
		
		return new Root(x0);
	}


	/**
	 * Small example program that finds a root of the sine and cosine function.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Function1 cos = new Cosine(); // derivative of sin(x)
		Newton n = new Newton(cos);
		Root x0 = n.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 negsin = new NegSine();
		Newton n2 = new Newton(negsin); // derivative of cos(x)
		x0 = n2.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
	}

}
