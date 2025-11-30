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

public class AutobuskaStanicaTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	AutobuskaStanica instance;

	@Before
	public void setUp() throws Exception {
		instance = new AutobuskaStanica();
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
	public void atribut_polasci() {
		assertTrue("U klasi nije definisan atribut polasci", TestUtil.doesFieldExist(AutobuskaStanica.class, "polasci"));
	}
	
	@Test
	public void atribut_polasci_vidljivost() {
		assertTrue("Atribut polasci nije privatan", TestUtil.hasFieldModifier(AutobuskaStanica.class, "polasci", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_polasci_pocetnaVrednost() {
		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");
		
		assertEquals("Atribut polasci nije inicijalizovan na kapacitet 100", 100, polasci.length);
	}
	
	@Test (timeout = 2000)
	public void metoda_unesiPolazak_PrazanNiz() {
		Polazak p = new Polazak();
		
		instance.unesiPolazak(p);

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		assertEquals("Metoda nije unela polazak na prvo slobodno mesto", p, polasci[0]);
		
		for (int i=1;i<polasci.length;i++)
			assertEquals("Metoda je unela isti polazak vise puta u niz - na element sa indeksom "+i, null, polasci[i]);
	}
	
	@Test (timeout = 2000)
	public void metoda_unesiPolazak_NeprazanNiz() {
		Polazak p = new Polazak();
		Polazak p2 = new Polazak();
		
		instance.unesiPolazak(p);
		instance.unesiPolazak(p2);
		

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		assertEquals("Ako niz sadrzi vec jedan polazak, metoda nije unela polazak na drugo mesto", p2, polasci[1]);
		
		for (int i=2;i<polasci.length;i++)
			assertEquals("Metoda je unela isti polazak vise puta u niz - na element sa indeksom "+i, null, polasci[i]);
	}
	
	@Test (timeout = 2000)
	public void metoda_unesiPolazak_PrepunNiz() {
		for (int i=0;i<100;i++)
			instance.unesiPolazak(new Polazak() );
		
		Polazak p = new Polazak();
		
		instance.unesiPolazak(p);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa polaska u prepun niz", "GRESKA", outContent.toString().trim().toUpperCase());
		
		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		for (int i=0;i<polasci.length;i++)
			assertNotEquals("Metoda je ipak unela novi polazak u prepun niz - na element sa indeksom "+i, p, polasci[i]);
	}
	
	@Test (timeout = 2000)
	public void metoda_unesiPolazak_NULL() {
		instance.unesiPolazak(null);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa NULL polaska", "GRESKA", outContent.toString().trim().toUpperCase());
	}

	@Test (timeout = 2000)
	public void metoda_rezervisiKarte() {
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Nis");
		p4.setBrojSlobodnihMesta(10);
		p4.setVreme(vremeP);
		
		instance.unesiPolazak(p1);
		instance.unesiPolazak(p2);
		instance.unesiPolazak(p3);
		instance.unesiPolazak(p4);
		
		assertEquals("Kad uspe da rezervise destinaciju, metoda ne vraca true", true, instance.rezervisiKarte("Nis", 11));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		assertEquals("Metoda nije smanjila broj slobodnih mesta na polasku na kojem je rezervisala", 4, polasci[1].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[2].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[3].getBrojSlobodnihMesta());
	}
	
	@Test (timeout = 2000)
	public void metoda_rezervisiKarte_NeuspesnoNemaMesta() {
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Nis");
		p4.setBrojSlobodnihMesta(10);
		p4.setVreme(vremeP);
		
		instance.unesiPolazak(p1);
		instance.unesiPolazak(p2);
		instance.unesiPolazak(p3);
		instance.unesiPolazak(p4);
		
		assertEquals("Kad ne uspe da rezervise destinaciju, metoda ne vraca false", false, instance.rezervisiKarte("Nis", 150));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 15, polasci[1].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[2].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[3].getBrojSlobodnihMesta());
	}
	
	@Test (timeout = 2000)
	public void metoda_rezervisiKarte_NeuspesnoNemaDestinacije() {
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Nis");
		p4.setBrojSlobodnihMesta(10);
		p4.setVreme(vremeP);
		
		instance.unesiPolazak(p1);
		instance.unesiPolazak(p2);
		instance.unesiPolazak(p3);
		instance.unesiPolazak(p4);
		
		assertEquals("Kad ne uspe da rezervise destinaciju, metoda ne vraca false", false, instance.rezervisiKarte("Jagodina", 1));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(instance, "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 15, polasci[1].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[2].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[3].getBrojSlobodnihMesta());
	}

	@Test (timeout = 2000)
	public void metoda_proslediRezervaciju() {
		AutobuskaStanica[] nizStanica = new AutobuskaStanica[2];
		
		nizStanica[0] = new AutobuskaStanica();
		nizStanica[1] = new AutobuskaStanica();
		
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		nizStanica[0].unesiPolazak(p1);
		nizStanica[0].unesiPolazak(p2);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Novi Sad");
		p4.setBrojSlobodnihMesta(25);
		p4.setVreme(vremeP);
		
		nizStanica[1].unesiPolazak(p3);
		nizStanica[1].unesiPolazak(p4);
		
		assertEquals("Kad uspe da rezervise destinaciju, metoda ne vraca true", true, instance.proslediRezervaciju(nizStanica, "Novi Sad", 13));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[0], "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 15, polasci[1].getBrojSlobodnihMesta());

		polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[1], "polasci");

		assertEquals("Metoda nije smanjila broj slobodnih mesta na polasku na kojem je rezervisala", 12, polasci[1].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[0].getBrojSlobodnihMesta());
	}
	
	@Test (timeout = 2000)
	public void metoda_proslediRezervaciju_NeuspesnoNemaMesta() {
		AutobuskaStanica[] nizStanica = new AutobuskaStanica[2];
		
		nizStanica[0] = new AutobuskaStanica();
		nizStanica[1] = new AutobuskaStanica();
		
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		nizStanica[0].unesiPolazak(p1);
		nizStanica[0].unesiPolazak(p2);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Novi Sad");
		p4.setBrojSlobodnihMesta(25);
		p4.setVreme(vremeP);
		
		nizStanica[1].unesiPolazak(p3);
		nizStanica[1].unesiPolazak(p4);
		
		assertEquals("Kad ne uspe da rezervise destinaciju, metoda ne vraca false", false, instance.proslediRezervaciju(nizStanica, "Novi Sad", 26));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[0], "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 15, polasci[1].getBrojSlobodnihMesta());

		polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[1], "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 25, polasci[1].getBrojSlobodnihMesta());
	}

	@Test (timeout = 2000)
	public void metoda_proslediRezervaciju_NeuspesnoNemaDestinacije() {
		AutobuskaStanica[] nizStanica = new AutobuskaStanica[2];
		
		nizStanica[0] = new AutobuskaStanica();
		nizStanica[1] = new AutobuskaStanica();
		
		Polazak p1 = new Polazak();
		p1.setDestinacija("Novi Sad");
		p1.setBrojSlobodnihMesta(10);
		GregorianCalendar vremeP = new GregorianCalendar();
		vremeP.set(GregorianCalendar.YEAR, vremeP.get(GregorianCalendar.YEAR)+1);
		p1.setVreme(vremeP);
		
		Polazak p2 = new Polazak();
		p2.setDestinacija("Nis");
		p2.setBrojSlobodnihMesta(15);
		p2.setVreme(vremeP);
		
		nizStanica[0].unesiPolazak(p1);
		nizStanica[0].unesiPolazak(p2);
		
		Polazak p3 = new Polazak();
		p3.setDestinacija("Paracin");
		p3.setBrojSlobodnihMesta(20);
		p3.setVreme(vremeP);
		
		Polazak p4 = new Polazak();
		p4.setDestinacija("Novi Sad");
		p4.setBrojSlobodnihMesta(25);
		p4.setVreme(vremeP);
		
		nizStanica[1].unesiPolazak(p3);
		nizStanica[1].unesiPolazak(p4);
		
		assertEquals("Kad ne uspe da rezervise destinaciju, metoda ne vraca false", false, instance.proslediRezervaciju(nizStanica, "Jagodina", 1));

		Polazak[] polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[0], "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 10, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 15, polasci[1].getBrojSlobodnihMesta());

		polasci = (Polazak[]) TestUtil.getFieldValue(nizStanica[1], "polasci");

		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 20, polasci[0].getBrojSlobodnihMesta());
		assertEquals("Metoda je greskom smanjila broj slobodnih mesta na drugom polasku", 25, polasci[1].getBrojSlobodnihMesta());
	}
}
