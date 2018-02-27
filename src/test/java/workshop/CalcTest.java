package workshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class CalcTest {

	Calc calc = new Calc();

	@Test
	public void testAdd(){
		int a = 1;
		int b = 2;
		int r = calc.add(a, b);
		assertEquals(3, r);
	}

	@Test
	public void testConcat(){

		String a = "a";
		String b = "b";
		assertEquals("ab", calc.concat(a, b));
	}

	@Ignore
	@Test
	public void testFail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSucced(){
		assertTrue(true);
	}
}
