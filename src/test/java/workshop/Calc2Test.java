package workshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class Calc2Test {

	Calc calc = new Calc();

	@Test
	public void testMultiply(){
		int a = 2;
		int b = 3;

		assertEquals(6, calc.multiply(a, b));
	}

	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
