/***
 * Abstrakcyjna klasa Trening
 * @author Maria
 *
 */
public abstract class Trening extends ObiektPlusPlus {
	protected Cwiczenie cwiczenieWybrane = null;
	protected String[] naglowkiTablicy;

	/***
	 * Konstruktor
	 */
	public Trening() {
		super();
		naglowkiTablicy = new String[] {"Data", "Nr serii", "Ilość powtórzeń", "Ciężar"};
	}
	
	/***
	 * Metoda zwracająca nagłówki tablicy historii
	 * @return Tablica nagłówków
	 */
	public String[] getNaglowkiTablicy() {
		return naglowkiTablicy;
	}
	
	/***
	 * Metoda abstrakcyjna. Ustawia i sprawdza poprawność wybranego ćwiczenia w treningu
	 * @param cwiczenie Ćwiczenie
	 */
	public abstract void wybierzCwiczenie(Cwiczenie cwiczenie);
}