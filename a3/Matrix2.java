package a3;

import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * A 2x2 matrix. This class provides basic mathematical operations such
 * as matrix-vector and matrix-matrix products, determinant, and inverse.
 *
 */
public class Matrix2 {
	
	private double a;
	private double b;
	private double c;
	private double d;

	// constructors
	
	/**
	 * Initializes this matrix so that its entries are equal to the specified
	 * values. The matrix entries are defined to be:
	 * 
	 * <pre>
	 * a b
	 * c d
	 * </pre>
	 * 
	 * @param a the value of the upper left element
	 * @param b the value of the upper right element
	 * @param c the value of the lower left element
	 * @param d the value of the lower right element
	 */
	public Matrix2(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		
	}
	/**
	 * Initializes this matrix to the identity matrix.
	 */
	public Matrix2() {
		this(1.0, 0.0, 0.0, 1.0);
	
//		this.a = 1;
//		this.b = 0;
//		this.c = 0;
//		this.d = 1;
	}
	
	/**
	 * Initializes this matrix so that it is equal to the specified matrix.
	 * 
	 * @param other - a matrix to copy.
	 */
	public Matrix2(Matrix2 other) {
//		this(a, b, c, d);
		
		this.a = other.a;
		this.b = other.b;
		this.c = other.c;
		this.d = other.d;
		
	}
	
	// methods 
	
	/**
	 * Returns the entry of this matrix located at the given one-based 
	 * row and column indexes.
	 * 
	 * @param row - the row index of the element 
	 * @param col - the column index of the element 
	 * 
	 * @return the element at the specified row and column indexes 
	 * 
	 * @throws IndexOutOfBoundsException - if row is not 1 and not 2
	 * @throws IndexOutOfBoundsException - if col is not 1 and not 2 
	 */
	public double get(int row, int col) {
		if (col != 1 || col != 2) {
			throw new IndexOutOfBoundsException("column index is not 1 and not 2");
		}
		if (row != 1 || row != 2) {
			throw new IndexOutOfBoundsException("row index is not 1 and not 2");
		}
		
		double entry = 0;
		if (row == 1 && col == 1) {
			this.a = entry;
		}
		else if (row == 1 && col == 2) {
			this.b = entry;
		}
		else if (row == 2 && col == 2) {
			this.c = entry;
		}
		else {
			this.d = entry;
			
		}
		
		return entry;
	}
	
	/**
	 * Sets the entry of this matrix located at the given zero-based row and column 
	 * indexes to the specified value.
	 * 
	 * @param row - the row index of the element 
	 * @param col - the column index of the element 
	 * @param val - the element to store in this matrix
	 * 
	 * @return a reference to this matrix
	 * 
	 * @throws IndexOutOfBoundsException - if row is not 1 and not 2
	 * @throws IndexOutOfBoundsException - if col is not 1 and not 2
	 */
	public Matrix2 set(int row, int col, double val) {
		if (col != 1 || col != 2) {
			throw new IndexOutOfBoundsException("column index is not 1 and not 2");
		}
		if (row != 1 || row != 2) {
			throw new IndexOutOfBoundsException("row index is not 1 and not 2");
		}
		
		Matrix2 setMatrix = new Matrix2(this);
		
		if (row == 1 && col == 1) {
			setMatrix.a = val;
		}
		else if (row == 1 && col == 2) {
			setMatrix.b = val;
		}
		else if (row == 2 && col == 1) {
			setMatrix.c = val;
		}
		else {
			setMatrix.d = val;
		}
		
		return setMatrix;
	}
	
	/**
	 * Postmultiply this matrix with the specified column vector returning a new vector.
	 * 
	 * <p>
	 * w = A.mult(v) is equivalent to the mathematical equation w = Av where v and w 
	 * are 2x1 column vectors
	 * 
	 * @param v - a 2x1 vector
	 * 
	 * @return a 2x1 vector equal to this matrix times v
	 */
	public Vector2 mult(Vector2 v) {
		double vx = v.x();
		double vy = v.y();
		
		double m1 = vx * this.a + vy * this.b;
		double m2 = vx * this.c + vy * this.d;
			
		Vector2 product = new Vector2();
		product.set(m1, m2);
		return product;
	}
	
	/**
	 * Postmultiply this matrix with the specified matrix returning a new matrix.
	 * 
	 * <p>
	 * C = a.mult(B) is equivalent to the mathematical equation C = AB.
	 * 
	 * @param m - a 2x2 matrix
	 * 
	 * @return a 2x2 matrix equal to this matrix times m
	 */
	public Matrix2 mult(Matrix2 m) {
		 double newA = this.a * m.a + this.b * m.c;
		 double newB = this.a * m.b + this.b * m.d;
		 double newC = this.c * m.a + this.d * m.c;
		 double newD = this.c * m.b + this.d * m.d;
		 
		 Matrix2 multMatrix = new Matrix2(newA, newB, newC, newD);
		 return multMatrix;
	}
	
	/**
	 * Returns the determinant of this matrix.
	 * 
	 * @return the determinant of this matrix
	 */
	public double det() {
		double determinant = this.a * this.d - this.b * this.c;
		return determinant;
		
	}
	
	/**
	 * Returns the inverse of this matrix if the determinant of this matrix is 
	 * not exactly zero.
	 * 
	 * @return the inverse of this matrix
	 * 
	 * @throws ArithmeticException - if the determinant of this matrix is exactly zero
	 */
	public Matrix2 inv() {
		double determinant = this.det();
		if (determinant == 0) {
			throw new ArithmeticException("determinant of this matrix is exactly zero");
		}
		
		Matrix2 invMatrix = new Matrix2(this);
		
		invMatrix.a = d * (1 / determinant);
		invMatrix.b = -b * (1 / determinant);
		invMatrix.c = -c * (1 / determinant);
		invMatrix.d = a * (1 / determinant);
		
		return invMatrix;
		
	}
	
	/**
	 * Returns a string representation of this matrix. The returned string
	 * has the following form:
	 * 
	 * <pre>
	 * [a, b]
	 * [c, d]
	 * </pre>
	 * 
	 * <p>
	 * where {@code a}, {@code b}, {@code c}, and {@code d} are the {@code double}
	 * values of the entries of this matrix.
	 * 
	 * @return a string representation of this matrix
	 */
	@Override
	public String toString() {
		String matrixString = "[" + this.a + ", " + this.b + "]\n[" + this.c + ", " + this.d + "]";
		return matrixString;
	}
	
} 
