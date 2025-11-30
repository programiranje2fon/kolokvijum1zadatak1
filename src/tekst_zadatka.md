# Zadatak 1

** NAPOMENA: PO ZAVRŠETKU ZADATKA OBAVEZNO TESTIRATI REŠENJE POZIVANJEM AUTOMATIZOVANIH TESTOVA (desnim tasterom na naziv projekta, Run as - Java Application - PokreniTestove)**

Napraviti javnu klasu **Polazak** u paketu **autobuska_stanica** koja ima:
 
* Privatni atribut **destinacija** (npr. “Novi Sad”).

* Privatni atribut **vreme** koji predstavlja datum i vreme polaska autobusa (koristiti klasu GregorianCalendar).

* Privatni atribut **brojSlobodnihMesta** koji predstavlja broj slobodnih mesta za taj polazak.

* Odgovarajuće javne get i set metode za ove atribute. Nedozvoljene vrednosti za atribut destinacija su null i prazan String. Vreme mora biti različito od null i mora da se odnosi na neki vremenski trenutak posle sadašnjeg, a broj slobodnih mesta mora da bude nula ili više. U slučaju unosa ovih nedozvoljenih vrednosti potrebno je ispisati reč "GRESKA" na ekranu.

* Redefinisanu metodu **toString** klase Object koja vraća String u kome se nalaze svi podaci o polasku uz odgovarajući tekst u formatu “DESTINACIJA:#### VREME:#### BROJ MESTA:####”. 

Napraviti javnu klasu **AutobuskaStanica** u paketu **autobuska_stanica** koja ima:

* Privatni atribut **polasci** koji predstavlja niz objekata klase Polazak. Ovaj niz je potrebno odmah inicijalizovati na kapacitet 100 elemenata.

* Javnu metodu **unesiPolazak** koja kao parametar prima objekat klase Polazak i unosi ga na prvo slobodno mesto u nizu. Mesto u nizu je slobodno ako je element na tom mestu NULL. Unošenje se vrši samo ako uneti objekat nije null i ako u nizu ima mesta. U suprotnom, ispisati reč "GRESKA" na ekranu. 

* Javnu metodu **rezervisiKarte** koja kao parametre prima: naziv destinacije i broj karata. Metoda na osnovu unete destinacije pronalazi polazak za tu destinaciju (u bilo koje vreme) i proverava da li na tom polasku ima dovoljno mesta da može da se rezerviše uneti broj karata. Ako ima, metoda smanjuje broj slobodnih mesta za broj karata i vraća TRUE. Ako nema, traži se neki drugi polazak za tu destinaciju i cela procedura se ponavlja. Ako ni na jednom polasku za tu destinaciju nema dovoljno mesta, metoda vraća FALSE.

* Javnu metodu **proslediRezervaciju** prima tri ulazna parametra: niz objekata klase AutobuskaStanica, naziv destinacije i broj karata. Metoda na osnovu unetih parametara pokušava da napravi rezervaciju karata ali na nekoj drugoj autobuskoj stanici. Rezervacija se vrši pozivanjem odgovarajuće metode jedne od autobuskih stanica iz unetog niza. Ako rezervacija uspe na nekoj od stanica, metoda vraća TRUE. Ako rezervacija ne uspe ni na jednoj stanici, vraća FALSE.

Napraviti javnu klasu **ProbaAutobuskaStanica** u paketu **autobuska_stanica.proba** koja u main metodi pravi jedan objekat klase AutobuskaStanica i jedan objekat klase Polazak ("Novi Sad", 55 slobodnih mesta, 31.12.2018. 23:59:59) i unosi ovaj polazak u autobusku stanicu.

# Zadatak 2 (ispravka koda)

** NAPOMENA: PO ZAVRŠETKU ZADATKA OBAVEZNO TESTIRATI REŠENJE POZIVANJEM AUTOMATIZOVANIH TESTOVA (desnim tasterom na naziv projekta, Run as - Java Application - PokreniTestove)**

U produžetku teksta je dat kod klase sa metodom koja kao parametar dobija niz String vrednosti i na ekranu ispisuje sve String-ove ali vertikalno (u kolonama), jedan pored drugog: u prvom redu su samo prva slova svih stringova, u drugom redu samo druga ova itd. Smatrati da nijedan element niza nije null niti duži od 5 znakova.

Na primer, ako metoda kao ulaz dobije niz sa četiri String vrednosti {“PAS“, “MACKA“, “LOPTA“, “DRVO“}, konačan izlaz na ekranu treba da izgleda ovako:

	PMLD
	AAOR
	SCPV
	 KTO
	 AA 

Dati kod se kompajlira, ali ne radi to šta treba. Napraviti klasu **StringIspisivac** u paketu **ispravka_koda**, prekucati u nju kod koji je dat  i, uz minimalne izmene ga ispraviti tako da funkcioniše kako treba. Napraviti neku test klasu i, koristeći njenu main metodu, pozvati metodu **ispisiVertikalno()** i proveriti njen rad.

	package ispravka_koda;
	
	public class StringIspisivac {
		public static void ispisiVertikalno(String[] niz) {
			int i=0;
			while(i < 5){
				int j=0;
				while (j < niz.length){
					if (i < niz[j].length())
						System.out.print(niz[i].charAt(j));
					else	System.out.print("");
					j++; i++;
				}
			System.out.println();
			}
		}
	}
