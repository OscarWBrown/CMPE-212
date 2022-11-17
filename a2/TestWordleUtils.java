package a2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestWordleUtils {

	@Test
	public void test01a_replace() {
		// method args
		String s = "A";
		int index = 0;
		char c = 'B';
		
		// expected answer
		String exp = "B";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01b_replace() {
		// method args
		String s = "GUMBO";
		int index = 0;
		char c = 'J';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	@Test
	public void test01c_replace() {
		// method args
		String s = "JIMBO";
		int index = 1;
		char c = 'U';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	@Test
	public void test_isGreen() {
		//method args
		String guess = "NOTRIGHT";
		String answer = "RIGHT";
		
		//expected answer
		
		try {
			WordleUtils.isGreen(guess, answer);
			fail("IllegalArgumentException Guess length is not equal to answer length");
		}
		catch(IllegalArgumentException x) {
			// expected exception is thrown
		}	
	}
	
	public void test_isYellow() {
		//method args
		String guess = "KAYAK";
		String answer = "WHACK";
		
		//expected answer
		ArrayList<Boolean> exp = new ArrayList<Boolean>();
		exp.add(true);
		exp.add(true);
		exp.add(false);
		exp.add(false);
		exp.add(false);
		
		//run test
		assertEquals(exp, WordleUtils.isYellow(guess, answer));
		
	}
	
	public void test_getColors() {
		//method args
		String answer = "TESTS";
		String guess = "TENTS";
		
		//expected answer
		ArrayList<WordleColor> exp = new ArrayList<>();
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GRAY);
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GREEN);
		
		//run test
		
		assertEquals(exp, WordleUtils.getColors(guess, answer));
	
		
				
	}
	
}
