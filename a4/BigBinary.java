package a4;

import java.util.Arrays;
import java.util.List;
import java.lang.Math;



/**
 * A class that represents a signed immutable binary number having no specified
 * limits on the number of bits. The binary number is represented using a ones
 * complement representation.
 *
 */
public class BigBinary implements Comparable<BigBinary> {
	
	// fields
	
	private Bit[] biN;
	
	// constructors
	
	/**
	 * Initializes this binary number to have the specified number of bits and a
	 * decimal value of (positive) zero.
	 * 
	 * @param nbits the number of bits
	 * @throws IllegalArgumentException if {@code nbits} is less than 1
	 */
	public BigBinary(int nbits) {
		if (nbits < 1) {
			throw new IllegalArgumentException("number of bits is less than 1");
		}
		this.biN = new Bit [nbits];
			for (int i = 0; i < nbits; i++) {
				this.biN[i] = Bit.ZERO;
			}
		
	}

	/**
	 * Translates the decimal string representation of a {@code BigBinary} into a
	 * {@code BigBinary}. The string representation consists of a sequence of one or
	 * more binary digits (0s and 1s). The string may not contain any extraneous
	 * characters (whitespace, for example) or characters not equal to {@code '0'}
	 * or {@code '1'}.
	 * 
	 * <p>
	 * If {@code bits} is empty then this binary number is initialized to the 8-bit
	 * binary number equal to zero.
	 * 
	 * @param bits the bits of the binary number
	 * @throws IllegalArgumentException if bits is not a valid representation of a
	 *                                  {@code BigBinary}
	 */
	public BigBinary(String bits) {
		 int l = bits.length();
		 this.biN = new Bit [l];
		 if (l == 0) {
			 for(int i=0;i<8;i++);
		 }
		 else { 
			 char[] arr = bits.toCharArray();
			 for (int i = 0; i < l; i++) {
				 if (arr[i] == '0') {
					 this.biN[i] = Bit.ZERO;
				 }
				 else if (arr[i] == '1') {
					 this.biN[i] = Bit.ONE;
				 }
				 else {
					 throw new IllegalArgumentException("this input contains invalid bit values");
				 }
			 }
		 }
		
	}
	
	// methods

	/**
	 * Returns the number of bits in this binary number.
	 * 
	 * @return the number of bits in this binary number
	 */
	public int numberOfBits() {
		return this.biN.length;
		
	}

	/**
	 * Returns {@code true} if this binary number is equal to positive or negative
	 * zero, {@code false} otherwise. In ones complement representation, a binary
	 * number is equal to zero if all of the bits are equal to zero or all of the
	 * bits are equal to one.
	 * 
	 * @return {@code true} if this binary number is equal to positive or negative
	 *         zero, {@code false} otherwise
	 */
	public boolean isZero() {
		int n = this.numberOfBits();
		int sumDigits = 0;
		for (int i = 0; i < n; i++) {
			if (this.biN[i] == Bit.ONE) {
				sumDigits += 1;
			}
		}
		if (sumDigits != 0 && sumDigits != n) {
			return false;
		}
		else {
			return true;
		}
		
	}

	/**
	 * Returns {@code true} if this binary number is negative, and {@code false}
	 * otherwise. This method considers {@code -0} to be negative.
	 * 
	 * @return {@code true} if this binary number is negative, and {@code false}
	 *         otherwise
	 */
	public boolean isNegative() {
		if (this.biN[0] == Bit.ONE) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns a new binary number equal to the bitwise NOT of this binary number.
	 * The returned number has a decimal value equal to the negative decimal value
	 * of this binary number (it is the ones complement of this number).
	 * 
	 * @return a new binary number equal to the bitwise NOT of this binary number
	 */
	public BigBinary not() {
		int n = this.numberOfBits();
		BigBinary notBiN = new BigBinary(n);
		
		for (int i = 0; i < n; i++) {
			if (this.biN[i] == Bit.ONE) {
				notBiN.biN[i] = Bit.ONE.not();
			}
			else {
				notBiN.biN[i] = Bit.ZERO.not();
			}
		}
		
		return notBiN;
	}

	/**
	 * Returns the bits of this number as a list. The returned list cannot be used
	 * to modify this binary number.
	 * 
	 * <p>
	 * If you use a list to store the bits of the number then return a new copy of
	 * the list.
	 * 
	 * <p>
	 * If you use an array to store the bits of the number then use
	 * {@code Arrays.toList} to get a list representation of your array, and then
	 * return a new copy of that list.
	 * 
	 * @return a the bits of this number as a list
	 */
	public List<Bit> getBits() {
		List<Bit> bitList = Arrays.asList(this.biN);
		return bitList;
		
	}

	/**
	 * Compares this binary number to an object for equality. Returns true if and
	 * only if {@code obj} is a {@code BigBinary} number having the same number of
	 * bits and the same decimal value as this binary number.
	 * 
	 * <p>
	 * Note that this method considers positive zero to be equal to negative zero if
	 * both numbers have the same number of bits. For all other binary numbers, two
	 * binary numbers are equal if and only if they have exact same bit pattern.
	 * 
	 * <p>
	 * Two binary numbers having the same decimal value but different number of bits
	 * are considered to be not equal by this method.
	 * 
	 * @param obj the object to compare
	 * @return true if this binary number is equal to the other binary number, false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		// have to test if classes are equivalent ??
		// if it obj cannot be cast to BigBinary does that mean it is not equal by default ??
	
		// an instance is equal to itself 
		if (this == obj) {
			return true;
		}
		
		// an instance is never equal to null
		
		if (obj == null) {
			return false;
		}
		
		// only instances of the exact same type can be equal
		
		if (obj.getClass() != BigBinary.class) {
			return false;
		}
		
		// instances of the same state are equal
		
		int mismatch = 0;
		int match = 0;
		if (this.numberOfBits() == ((BigBinary) obj).numberOfBits()) {
			for (int i = 0; i < this.numberOfBits(); i++) {
				if (this.biN[i] == ((BigBinary) obj).biN[i]) {
					match += 1;
				}
				else {
					mismatch += 1;
				}
			}
			// both are zero
			if (mismatch == this.numberOfBits() && this.toDecimal() == 0 && ((BigBinary) obj).toDecimal() == 0) {
				return true;
			}
			// not zero but still equal
			else if (match == this.numberOfBits()) {
				return true;
			}
			// remaining cases
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}

	/**
	 * Returns a hash code for this binary number.
	 * 
	 * @return a hash code for this binary number
	 */
	@Override
	public int hashCode() {
		int negCount = 0;
		int posCount = 0;
		if (this.biN[0] == Bit.ONE) {
			for (int i = 0; i < this.numberOfBits(); i++) {
				negCount -= 1;
			}
			return negCount;
		}
		else {
			posCount += 1;
			return posCount;
		}
		
	}

	/**
	 * Compares this binary number to another binary number for order. The result is
	 * {@code -1} if this number is less than the other number, {@code 0} is this
	 * number is equal to the other number, and {@code 1} if this number is greater
	 * than the other number.
	 * 
	 * <p>
	 * Unlike {@code equals}, this method will correctly compare binary numbers
	 * having different numbers of bits. Also, this method considers {@code -0} to
	 * be less than {@code 0}.
	 * 
	 * <p>
	 * Conceptually, the decimal value of the numbers are used in the comparison,
	 * but it is not practical to compute the decimal value of an arbitrary length
	 * binary number. Instead a bit-wise comparison is performed.
	 * 
	 * 
	 * @param other the binary number to compare
	 * @return -1, 0 or 1 as this number is numerically less than, equal to, or
	 *         greater than the other number
	 */
	public int compareTo(BigBinary other) {
		int length1 = this.biN.length;
		int length2 = other.biN.length;
		int lengthDiff = Math.abs(length1 - length2);
		int sum1 = 0;
		int sum2 = 0;
		BigBinary thisNot = this.not();
		BigBinary otherNot = other.not();
		
		for (int i = 0; i < length1; i++) {
			if (this.biN[i] == Bit.ONE) {
				sum1 += 1;
			}
		}
		
		for (int i = 0; i < length2; i++) {
			if (other.biN[i] == Bit.ZERO) {
				sum2 += 1;
			}
		}
		
		// both equal to +zero
		if (this.isZero() && other.isZero() && this.isNegative() == false && other.isNegative() == false) {
			return 0;
		}
		
		// both equal to -zero
		if (this.isZero() == true && other.isZero() == true && this.isNegative() == true && other.isNegative() == true) {
			return 0;
		}
		
		// this -zero, other +zero
		
		// this positive, other negative
		if (this.biN[0] == Bit.ZERO && other.biN[0] == Bit.ONE) {
			return 1;
		}
		
		// other positive, this negative
		if (this.biN[0] == Bit.ONE && other.biN[0] == Bit.ZERO) {
			return -1;
		}
		
		// both positive
		if (this.biN[0] == Bit.ZERO && other.biN[0] == Bit.ZERO) {
			// this longer than other
			if (length1 > length2) {
				for (int i = 1; i <= lengthDiff; i++) {
					if (this.biN[i] == Bit.ONE) {
						// this has more significant 1
						return 1;
					}
				}
			
				// loop length of other
				for (int i = 1; i < length2; i++) {
				
					if (this.biN[i + lengthDiff] == Bit.ONE && other.biN[i] == Bit.ZERO) {
						// this has more significant 1
						return 1;
					}
					else if (this.biN[i + lengthDiff] == Bit.ZERO && other.biN[i] == Bit.ONE) {
						// other has more significant 1
						return -1;
					}
					else {
						// either both or neither have a more significant 1 at this position 
						continue;
					}
					
				}
			
				// both numbers have identical decimal value
				return 0;
			
			}
			
			// other longer than this 
			if (length1 < length2) {
				for (int i = 1; i <= lengthDiff; i++) {
					if (other.biN[i] == Bit.ONE) {
						// other has more significant 1
						return -1;
					}
				}
			
				// loop length of this
				for (int i = 1; i < length1; i++) {
					
						if (this.biN[i] == Bit.ONE && other.biN[i + lengthDiff] == Bit.ZERO) {
							// this has more significant 1
							return 1;
						}
						else if (this.biN[i] == Bit.ZERO && other.biN[i + lengthDiff] == Bit.ONE) {
							// other has more significant 1
							return -1;
						}
						else {
							// either both or neither have a more significant 1 at this position 
							continue;
						}
					
				}
				
				// both numbers have identical decimal value
				return 0;
				
			}
			
			// same length 
			if (length1 == length2) {
				for (int i = 0; i < length1; i++) {
					if (this.biN[i] == Bit.ONE && other.biN[i] == Bit.ZERO) {
						// this has more significant 1
						return 1;
					}
					else if (this.biN[i] == Bit.ZERO && other.biN[i] == Bit.ONE) {
						// other has more significant 1
						return -1;
					}
					else {
						// either both or neither have a more significant 1 at this position 
						continue;
					}
				}
			}
		}
			
		// both negative
		if (this.biN[0] == Bit.ONE && other.biN[0] == Bit.ONE) {
			// this longer than other
			if (length1 > length2) {
				for (int i = 1; i <= lengthDiff; i++) {
					if (thisNot.biN[i] == Bit.ONE) {
						// this has more significant 1
						return -1;
					}
				}
			
				// loop length of other
				for (int i = 1; i < length2; i++) {
					
						if (thisNot.biN[i + lengthDiff] == Bit.ONE && otherNot.biN[i] == Bit.ZERO) {
							// this has more significant 1
							return -1;
						}
						else if (thisNot.biN[i + lengthDiff] == Bit.ZERO && otherNot.biN[i] == Bit.ONE) {
							// other has more significant 1
							return 1;
						}
						else {
							// either both or neither have a more significant 1 at this position 
							continue;
						}
					
				}
			
				// both numbers have identical decimal value
				return 0;
			
			}
			
			// other longer than this 
			if (length1 < length2) {
				for (int i = 1; i <= lengthDiff; i++) {
					if (other.biN[i] == Bit.ONE) {
						// other has more significant 1
						return 1;
					}
				}
			
				// loop length of this
				for (int i = 1; i < length1; i++) {
					
						if (thisNot.biN[i] == Bit.ONE && otherNot.biN[i + lengthDiff] == Bit.ZERO) {
							// this has more significant 1
							return -1;
						}
						else if (thisNot.biN[i] == Bit.ZERO && otherNot.biN[i + lengthDiff] == Bit.ONE) {
							// other has more significant 1
							return 1;
						}
						else {
							// either both or neither have a more significant 1 at this position 
							continue;
						}
					
				}
					
				// both numbers have identical decimal value
				return 0;
				
			}
				
			// same length 
			if (length1 == length2) {
				for (int i = 0; i < length1; i++) {
					if (thisNot.biN[i] == Bit.ONE && otherNot.biN[i] == Bit.ZERO) {
						// this has more significant 1
						return -1;
					}
					else if (thisNot.biN[i] == Bit.ZERO && otherNot.biN[i] == Bit.ONE) {
						// other has more significant 1
						return 1;
					}
					else {
						// either both or neither have a more significant 1 at this position 
						continue;
					}
				}
				return 0;
			}
		}
		
		// placeholder return value, will never get returned
		return 1;
		
//		for (int i = 0; i < length1; i++) {
//			if (this.biN[i] == Bit.ONE) {
//				sum1 += 1;
//			}
//		}
//		
//		for (int i = 0; i < length2; i++) {
//			if (other.biN[i] == Bit.ZERO) {
//				sum2 += 1;
//			}
//		}
//		
//		// this +zero, other -zero
//		if (sum1 == 0 && sum2 == length2) {
//			return 1;
//		}
//		
//		// this -zero, other +zero
//		if (sum1 == length1 && sum2 == 0) {
//			return -1;
//		}
//		
//		// both equal to +zero or -zero
//		else {
//			return 0;
//		}
	}

	/**
	 * Returns a string representation of this binary number. The returned string is
	 * made up of the bit values (0 or 1) of the number from the left-most bit to
	 * the right-most bit.
	 * 
	 * @return a string representation of this binary number
	 */
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (Bit bit : this.getBits()) {
			b.append(bit.toString());
		}
		return b.toString();
	}

	/**
	 * Returns the decimal (base 10) value of this binary number as an {@code int}
	 * value throwing an exception if the result overflows an {@code int}.
	 * 
	 * @return the decimal (base 10) value of this binary number
	 * @throws ArithmeticException if the result overflows an {@code int}
	 */
	public int toDecimal() {
		long val = 0;
		List<Bit> bits = this.getBits();
		for (int i = 0; i < bits.size(); i++) {
			int y = bits.size() - 1 - i;
			int bitVal = bits.get(i).value();
			if (this.isNegative()) {
				bitVal = -bits.get(i).not().value();
			}
			val += bitVal * Math.pow(2, y);
			if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
				throw new ArithmeticException();
			}
		}
		return (int) val;
	}

	/**
	 * Prints the output of of calling {@code toDecimal}, {@code isZero},
	 * {@code isNegative}, and {@code not} on a binary number.
	 * 
	 * @param b      a binary number
	 * @param header a message to print at the start of the test
	 */
	static void test(BigBinary b, String header) {
		String s = b.toString();
		String str;
		try {
			str = "" + b.toDecimal();
		} catch (ArithmeticException ex) {
			str = "overflows int";
		}
		System.out.println(header);
		System.out.println(s + ".toDecimal()  : " + str);
		System.out.println(s + ".isZero()     : " + b.isZero());
		System.out.println(s + ".isNegative() : " + b.isNegative());
		System.out.println(s + ".not()        : " + b.not());
		System.out.println();
	}

	/**
	 * Prints the output of of calling {@code equals} and {@code compareTo} on a
	 * pair of binary numbers.
	 * 
	 * @param x      a binary number
	 * @param y      a binary number
	 * @param header a message to print at the start of the test
	 */
	static void testEqualsAndCompareTo(BigBinary x, BigBinary y, String header) {
		String sx = x.toString();
		String sy = y.toString();
		System.out.println(header);
		System.out.println(sx + ".equals(" + sy + ")    : " + x.equals(y));
		System.out.println(sx + ".compareTo(" + sy + ") : " + x.compareTo(y));
		System.out.println(sy + ".compareTo(" + sx + ") : " + y.compareTo(x));
		System.out.println();
	}

	/**
	 * A simple test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// 8-bit binary number equal to +0
		BigBinary b = new BigBinary(8);
		test(b, "8-bit binary number equal to +0");

		// 8-bit binary number equal to -0
		b = new BigBinary("11111111");
		test(b, "8-bit binary number equal to -0");

		// 8-bit binary number equal to 127
		b = new BigBinary("01111111");
		test(b, "8-bit binary number equal to 127");

		// 8-bit binary number equal to -127
		b = new BigBinary("10000000");
		test(b, "8-bit binary number equal to -127");

		// 32-bit binary number equal to Integer.MAX_VALUE
		b = new BigBinary("01111111111111111111111111111111");
		test(b, "32-bit binary number equal to Integer.MAX_VALUE");

		// 33-bit binary number equal to Integer.MAX_VALUE + 1
		b = new BigBinary("010000000000000000000000000000000");
		test(b, "33-bit binary number equal to Integer.MAX_VALUE + 1");

		// 33-bit binary number equal to Integer.MIN_VALUE
		b = new BigBinary("101111111111111111111111111111111");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE");

		// 33-bit binary number equal to Integer.MIN_VALUE - 1
		b = new BigBinary("101111111111111111111111111111110");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE - 1");

		// compare 4-bit +0 to 4-bit +0
		BigBinary x = new BigBinary(4);
		BigBinary y = new BigBinary(4);
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit +0");

		// compare 4-bit +0 to 4-bit -0
		x = new BigBinary(4);
		y = new BigBinary("1111");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit -0");

		// compare 4-bit +5 to 4-bit +1
		x = new BigBinary("0101");
		y = new BigBinary("0001");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 4-bit +1");

		// compare 4-bit -5 to 4-bit -1
		x = new BigBinary("1010");
		y = new BigBinary("1110");
		testEqualsAndCompareTo(x, y, "compare 4-bit -5 to 4-bit -1");

		// compare 4-bit +0 to 2-bit +0
		x = new BigBinary(4);
		y = new BigBinary("00");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 2-bit +0");

		// compare 4-bit +5 to 6-bit +5
		x = new BigBinary("0101");
		y = new BigBinary("000101");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 6-bit +5");

		// compare large number to a larger number
		x = new BigBinary("01000011000000101000101010100001111101");
		y = new BigBinary("010000110000001010001010101000011111011");
		testEqualsAndCompareTo(x, y, "compare large number to a larger number");

		// compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)
		x = new BigBinary("101111111111111111111111111111111");
		y = new BigBinary("101111111111111111111111111111110");
		testEqualsAndCompareTo(x, y, "compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)");
	}
}
