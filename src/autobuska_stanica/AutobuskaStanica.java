package autobuska_stanica;

public class AutobuskaStanica {
	
	private Polazak[] polasci = new Polazak[100];
	
	public void unesiPolazak(Polazak p) {
		if (p == null) {
			System.out.println("GRESKA");
			return;
		}
		
		for (int i=0; i<polasci.length;i++)
			if (polasci[i] == null) {
				polasci[i] = p;
				return;
			}
		
		System.out.println("GRESKA");
	}
	
	public boolean rezervisiKarte(String destinacija, int brojKarata) {
		
		for(int i=0; i<polasci.length; i++) {
			if (polasci[i]!= null &&
				(polasci[i].getDestinacija().equals(destinacija)) &&
				((polasci[i].getBrojSlobodnihMesta()-brojKarata)>=0)) {
				
				int brojSlobodnihMesta = polasci[i].getBrojSlobodnihMesta(); 
				polasci[i].setBrojSlobodnihMesta(brojSlobodnihMesta-brojKarata);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean proslediRezervaciju(AutobuskaStanica[] stanice,
			String destinacija, int brojKarata) {
		boolean statusRezervacije = false;
		
		for(int i=0; i<stanice.length; i++) {
			statusRezervacije = stanice[i].rezervisiKarte(destinacija, brojKarata);
			if (statusRezervacije == true) 
				break;
		}
		
		return statusRezervacije;

	}

}
