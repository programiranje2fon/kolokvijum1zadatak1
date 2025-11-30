package autobuska_stanica.proba;

import java.util.GregorianCalendar;

import autobuska_stanica.AutobuskaStanica;
import autobuska_stanica.Polazak;

public class ProbaAutobuskaStanica {

	public static void main(String[] args) {
		AutobuskaStanica as = new AutobuskaStanica();
		
		Polazak p = new Polazak();
		
		p.setDestinacija("Novi Sad");
		p.setBrojSlobodnihMesta(55);
		
		GregorianCalendar vreme = new GregorianCalendar();
		
		vreme.set(2018, 11, 31, 23, 59, 59);
		
		p.setVreme(vreme);
		
		as.unesiPolazak(p);
	}

}
