package a5;

/**
 * The ideal projectile angle function.
 *
 */
public class ProjectileAngle implements Function1 {
	
	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION
	
	// fields 
	/** 
	 * Magnitude of Earth standard gravity constant
	 */
	public static final double G = 9.80665;
	private double distance;
	private double speed;
	
	// constructors
	/**
	 * Ideal projectile angle function for a target at the specified distance and a projectile
	 * having the specified initial speed.
	 * 
	 * @param {@code distance} - horizontal distance to the target
	 * @param {@code speed} - initial speed of the projectile
	 * 
	 * @throws {@code IllegalArgumentException} - if {@code distance} < 0
	 * @throws {@code IllegalArgumentException} - if {@code speed} < 0
	 */
	public ProjectileAngle(double distance, double speed) {
		if (distance < 0) {
			throw new IllegalArgumentException("distance is less than zero");
		}
		if (speed < 0) {
			throw new IllegalArgumentException("speed is less than zero");
		}
		
		this.distance = distance;
		this.speed = speed;
	}
	
	// methods
	/**
	 * Returns the horizontal distance to the target.
	 * 
	 * @returns the horizontal distance to the target
	 */
	public double getDistance() {
		return this.distance;
	}
	
	/**
	 * Returns the initial speed of the projectile.
	 * 
	 * @returns the initial speed of the projectile
	 */
	public double getSpeed() {
		return this.speed;
	}
	
	/**
	 * Sets the horizontal distance to the target returning the previously set distance.
	 * 
	 * @param {@code distance} - the new horizontal distance to the target
	 * 
	 * @returns the previous horizontal distance to the target
	 */
	public double setDistance(double distance) {
		double oldDistance = this.distance;
		this.distance = distance;
		return oldDistance;
	}
	
	/**
	 * Sets the initial speed of the projectile returning the previously set speed.
	 * 
	 * @param {@code speed} - the new initial speed of the projectile
	 * 
	 * @returns the previous speed of the projectile
	 */
	public double setSpeed(double speed) {
		double oldSpeed = this.speed;
		this.speed = speed;
		return oldSpeed;
	}
	
	/**
	 * Evaluate the function at the specified argument returning y = f(X) if the function 
	 * exists at {@code x}. Returns {@code Double.NaN} if the function does not exist at {@code x}.
	 * 
	 * @implSpec {@code eval} in interface {@code Function1}
	 * 
	 * @param {@code x} - the argument equal to the function
	 * 
	 * @returns the function evaluated at {@code x}
	 */
	public double eval(double x) {
		return this.distance * Math.tan(x) - 0.5 * G * Math.pow((this.distance / (this.speed * Math.cos(x))), 2);
	}
	
	/**
	 * Small example program that finds the two possible aiming angles
	 * for a projectile launched at 32.0 m/s at a target located 100 m
	 * horizontally from the launch point.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// target located 100 m horizontally from the launch point
		// launched at 50 m/s
		ProjectileAngle f = new ProjectileAngle(100.0, 50.0);
		
		// root finding using the bisection method
		Bisection n = new Bisection();
		
		// first angle is between 0 and 45 degrees 
		Root x0 = n.root(f, 0.0, Math.PI / 4.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}
		
		// second angle is between 45 and 90 degrees
		x0 = n.root(f, Math.PI / 4.0, Math.PI / 2.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}
	}

}
