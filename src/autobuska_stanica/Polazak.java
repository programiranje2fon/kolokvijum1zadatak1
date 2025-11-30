package autobuska_stanica;

import java.util.GregorianCalendar;

public class Polazak {
	
	private String destinacija;
	private GregorianCalendar vreme;
	private int brojSlobodnihMesta;
	
	public String getDestinacija() {
		return destinacija;
	}
	
	public void setDestinacija(String destinacija) {
		if (destinacija == null || destinacija.equals(""))
			System.out.println("GRESKA");
		else
			this.destinacija = destinacija;
	}
	
	public GregorianCalendar getVreme() {
		return vreme;
	}
	
	public void setVreme(GregorianCalendar vreme) {
		if (vreme == null || vreme.before(new GregorianCalendar()))
			System.out.println("GRESKA");
		else					
			this.vreme = vreme;
	}
	
	public int getBrojSlobodnihMesta() {
		return brojSlobodnihMesta;
	}
	
	public void setBrojSlobodnihMesta(int brojSlobodnihMesta) {
		if (brojSlobodnihMesta < 0)
			System.out.println("GRESKA");
		else					
			this.brojSlobodnihMesta = brojSlobodnihMesta;
	}

	@Override
	public String toString() {
		return "DESTINACIJA: " + destinacija + " VREME: "+ vreme.getTime() + " BROJ MESTA: " + brojSlobodnihMesta;
	}
	
	
	
	

}
