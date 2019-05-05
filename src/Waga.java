import java.io.Serializable;
import java.time.LocalDate;

/***
 * Klasa reprezentująca wagę użytkownika
 * @author Maria
 *
 */
public class Waga extends ObiektPlus implements Serializable {
	public double masaCiala;
	public LocalDate date;
	private Uzytkownik uzytkownik;
	
	/***
	 * Konstruktor 
	 * @param masaCiala Masa ciała
	 * @param data Data
	 */
	public Waga(double masaCiala, LocalDate data) {
		super();
		this.masaCiala = masaCiala;
		this.date = data;
	}

	/***
	 * Metoda dodająca wagę do użytkownika
	 * @param uzytkownik Użytkownik
	 */
	public void dodajUzytkownika(Uzytkownik uzytkownik) {
		if (this.uzytkownik != uzytkownik) {
			this.uzytkownik = uzytkownik;
			this.uzytkownik.dodajWageKwalif(this);
		}
	}
	
	/***
	 * Metoda wyświetlająca historię wprowadzenie wag przez użytkownika
	 */
	public void wyswietlWykres() {
		StringBuffer sb = new StringBuffer();
		sb.append("Trendy wag: \n");
		sb.append("* waga: " + this.masaCiala + ", data: " + this.date + "\n");
		System.out.println(sb.toString());
	}
}