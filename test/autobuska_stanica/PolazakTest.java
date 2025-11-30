package autobuska_stanica;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;

public class PolazakTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	Polazak instance;

	@Before
	public void setUp() throws Exception {
		instance = new Polazak();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void atribut_destinacija() {
		assertTrue("U klasi nije definisan atribut destinacija", TestUtil.doesFieldExist(Polazak.class, "destinacija"));
	}
	
	@Test
	public void atribut_destinacija_vidljivost() {
		assertTrue("Atribut destinacija nije privatan", TestUtil.hasFieldModifier(Polazak.class, "destinacija", Modifier.PRIVATE));
	}

	
	@Test
	public void atribut_vreme() {
		assertTrue("U klasi nije definisan atribut vreme", TestUtil.doesFieldExist(Polazak.class, "vreme"));
	}
	
	@Test
	public void atribut_vreme_vidljivost() {
		assertTrue("Atribut vreme nije privatan", TestUtil.hasFieldModifier(Polazak.class, "vreme", Modifier.PRIVATE));
	}

	@Test
	public void atribut_brojSlobodnihMesta() {
		assertTrue("U klasi nije definisan atribut brojSlobodnihMesta", TestUtil.doesFieldExist(Polazak.class, "brojSlobodnihMesta"));
	}
	
	@Test
	public void atribut_brojSlobodnihMesta_vidljivost() {
		assertTrue("Atribut brojSlobodnihMesta nije privatan", TestUtil.hasFieldModifier(Polazak.class, "brojSlobodnihMesta", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setDestinacija() {
		instance.setDestinacija("Baku");
		
		assertEquals("Kada se unese 'Baku', atribut ne dobija tu vrednost", "Baku", instance.getDestinacija());
	}
	
	@Test
	public void metoda_setDestinacija_NULL() {
		instance.setDestinacija(null);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa NULL destinacije", "GRESKA", outContent.toString().trim().toUpperCase());
	}

	@Test
	public void metoda_setDestinacija_PrazanString() {
		instance.setDestinacija("");
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa praznog Stringa kao destinacije", "GRESKA", outContent.toString().trim().toUpperCase());
		assertEquals("Uneta je nedozvoljena vrednost (prazan String) u atribut destinacija", null, instance.getDestinacija());
	}
	
	@Test
	public void metoda_getDestinacija() {
		instance.setDestinacija("Baku");
		
		assertEquals("Kada se unese 'Baku' u destinaciju, metoda getDestinacija ne vraca tu vrednost", "Baku", instance.getDestinacija());
	}

	@Test
	public void metoda_setVreme() {
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		instance.setVreme(vremeP);
		
		assertEquals("Kada se unese vreme koje je u buducnosti, metoda ga ne unosi u atribut", vremeP, instance.getVreme());
	}
	
	@Test
	public void metoda_setVreme_NULL() {
		instance.setVreme(null);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa null vremena", "GRESKA", outContent.toString().trim().toUpperCase());
	}

	@Test
	public void metoda_setVreme_Proslost() {
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)-1);
		instance.setVreme(vremeP);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa vremena iz proslosti", "GRESKA", outContent.toString().trim().toUpperCase());
		assertEquals("Kada se unese vreme koje je u proslosti, metoda ga ipak unosi u atribut", null, instance.getVreme());
	}
	
	@Test
	public void metoda_setBrojSlobodnihMesta() {
		instance.setBrojSlobodnihMesta(12);
		
		assertEquals("Uneto je 12 slobodnih mesta, ali atribut nije dobio tu vrednost", 12, instance.getBrojSlobodnihMesta());
	}
	
	@Test
	public void metoda_setBrojSlobodnihMesta_Negativno() {
		instance.setBrojSlobodnihMesta(-1);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa negativnog broja mesta", "GRESKA", outContent.toString().trim().toUpperCase());
		assertEquals("Uneto je -1 slobodno mesto, i atribut je dobio tu vrednost a nije trebalo", 0, instance.getBrojSlobodnihMesta());
	}
	
	

	@Test
	public void metoda_toString() {
		instance.setDestinacija("Nis");
		instance.setBrojSlobodnihMesta(13);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		instance.setVreme(vremeP);
		
		assertEquals ("Povratni String ne pocinje sa izrazom 'DESTINACIJA:'", "DESTINACIJA:", instance.toString().toUpperCase().substring(0,12));
		assertTrue ("Povratni String ne sadrzi izraz 'VREME:'", instance.toString().toUpperCase().contains("VREME:"));
		assertTrue ("Povratni String ne sadrzi izraz 'BROJ MESTA:'", instance.toString().toUpperCase().contains("BROJ MESTA:"));
		assertTrue ("Za unetu destinaciju 'Nis', naziv destinacije se ne nalazi u povratnom String-u", instance.toString().contains("Nis"));
		assertTrue ("Za uneto 13 mesta, taj broj se ne nalazi u povratnom String-u", instance.toString().contains("13"));
	}

}
