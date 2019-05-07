import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/***
 * Klasa reprezentująca ćwiczenia
 * @author Maria
 *
 */
public class Cwiczenie extends ObiektPlusPlus {
	private String nazwa;
	private String opis;
	private boolean czyWykonane = false;
	protected enum Poziom {Junior, Medium, Hardcore};
	public enum Rodzaj {GoraSila, DolSila, GoraHipertrofia, DolHipertrofia, Brzuch, CaleCialo};
	protected Poziom poziom;
	protected Rodzaj rodzaj;
	
	/***
	 * Konstruktor
	 * @param nazwa Nazwa ćwiczenia
	 * @param rodzaj Rodzaj ćwiczenia
	 * @param poziom Poziom ćwiczenia
	 */
	public Cwiczenie(String nazwa, Rodzaj rodzaj, Poziom poziom) { // konstruktor prywatny, żeby zablokować tworzenie części bez całości // Konstruktor klasy. Zwróćmy uwagę na to, że jest prywatny. Dzięki temu tylko metody z tej samej klasy mogą tworzyć jej instancje.
		this.nazwa = nazwa;
		this.rodzaj = rodzaj;
		this.poziom = poziom;
	}	
	
	/***
	 * Metoda zwraca nazwę ćwiczenia
	 * @return Nazwa ćwiczenia
	 */
	public String getNazwa() {
		return nazwa;
	}
	
	/***
	 * Metoda zwracająca informację czy ćwiczenie zostało wykonane
	 * @return True - jeśli ćwiczenie zostało wykonane, false - w przeciwnym wypadku
	 */
	public boolean getCzyWykonane() {
		return this.czyWykonane;
	}
	
	/***
	 * 	Metoda zwracająca rodzaj treningu
	 * @return Rodzaj treningu
	 */
	public Rodzaj getRodzaj() {
		return rodzaj;
	}
	
	/***
	 * Metoda zwracająca poziom treningu
	 * @return Poziom treningu
	 */
	public Poziom getPoziom() {
		return poziom;
	}
	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString(){
		return nazwa;
	}
}
