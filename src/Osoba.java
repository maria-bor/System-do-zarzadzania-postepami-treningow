import java.io.Serializable;

/***
 * Abstrakcyjna klasa Osoba
 * @author Maria
 *
 */
public abstract class Osoba extends ObiektPlusPlus implements Serializable{
	protected String nick;
	protected byte[] zdjecieProfilowe; // atrybut opcjonalny
	
	/***
	 * Konstruktor klasy Osoba
	 * @param nick Nazwa użytkownika
	 * @param zdjecieProfilowe Zdjęcie profilowe użytkownika
	 */
	public Osoba(String nick, byte[] zdjecieProfilowe) {
		super();
		this.nick = nick;
		this.zdjecieProfilowe = zdjecieProfilowe;
	}
	
	/***
	 * Metoda zwracająca nick użytkownika
	 * @return Zwraca nazwę użytkownika
	 */
	public String getNick() {
		return this.nick;
	}
	
	/***
	 * Metoda, która zakłada konto użytkownika
	 * @param nick Nazwa użytkownika
	 * @return True - jeśli konto zostało założone, false - jeśli nie da się założyć konta
	 */
	public static boolean zalozKonto(String nick) {
		throw new IllegalStateException("Nie mozna zalozyc konta klasy Osoba.");
	}
	
	/***
	 * Metoda, która loguje użytkownika do systemu
	 * @param nick Nazwa użytkownika
	 * @return True - jeśli użytkownik został pomyślnie zalogowany, false - jeśli użytkownika nie da się zalogować do systemu
	 */
	public static Osoba zalogujSie(String nick) {
		throw new IllegalStateException("Nie mozna zalogowac konta klasy Osoba.");
	}
	
	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString() {
		return getClass().getName() + ": " + nick;
	}
}
