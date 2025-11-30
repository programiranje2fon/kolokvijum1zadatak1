package ispravka_koda;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringIspisivacTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void metoda_ispisiVertikalno() {	
		String[] niz =  {"PAS", "MACKA", "LOPTA", "DRVO"};
		
		StringIspisivac.ispisiVertikalno(niz);
		
		assertEquals("Za uneto {\"PAS\", \"MACKA\", \"LOPTA\", \"DRVO\"}, ne ispisuje se kako treba", 
				"PMLD" + System.lineSeparator() +
				"AAOR" + System.lineSeparator() + 
				"SCPV" + System.lineSeparator() + 
				" KTO" + System.lineSeparator() + 
				" AA " + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void metoda_ispisiVertikalno_test2() {	
		String[] niz =  {"PETAO", "MIS", "PANJ", "KUPUS"};
		
		StringIspisivac.ispisiVertikalno(niz);
		
		assertEquals("Za uneto {\"PETAO\", \"MIS\", \"PANJ\", \"KUPUS\"}, ne ispisuje se kako treba", 
				"PMPK" + System.lineSeparator() +
				"EIAU" + System.lineSeparator() + 
				"TSNP" + System.lineSeparator() + 
				"A JU" + System.lineSeparator() + 
				"O  S" + System.lineSeparator(), outContent.toString());
	}

}
