import java.util.ArrayList;
import java.util.List;

/***
 * Klasa opisująca użytkownika systemu typu trener
 * @author Maria
 *
 */
public class Trener extends Osoba {
	private ArrayList<Uzytkownik> podopieczni = new ArrayList<Uzytkownik>();
	
	/***
	 * Konstruktot
	 * @param nick Nazwa konta
	 * @param zdjecieProfilowe Zdjęcie profilowe
	 */
	public Trener(String nick, byte[] zdjecieProfilowe) {
		super(nick, zdjecieProfilowe);
	}
	
	/***
	 * Metoda, która sprawdza czy można założyć konto trenera o podanym nick-u
	 * @param nick Nazwa trenera
	 * @return True - jeśli konto zostało założone, false - jeśli nie da się założyć konta
	 */
	public static boolean zalozKonto(String nick) {
		try {
			List<Trener> l = (List<Trener>) getEkstensje(Trener.class);
			for (Trener o : l) {
				if (o.getNick().equals(nick)) {
					System.out.println("Jest juz trener z nickiem: " + nick);
					return false;
				}
			}
		} catch (Exception e) { // jeśli brak ekstensji to nie ma jeszcze takiego trenera
//			e.printStackTrace();
			System.out.println("Nie ma trenera z nickiem: " + nick);
			return true;
		}	
		return true;
	}

	/***
	 * Metoda, która loguje trenera do systemu
	 * @param nick Nazwa trenera
	 * @return True - jeśli trener został pomyślnie zalogowany, false - jeśli trenera nie da się zalogować do systemu
	 */
	public static Trener zalogujSie(String nick) {
		try {
			List<Trener> l = (List<Trener>) getEkstensje(Trener.class);
			for (Trener t : l) {
				if (t.getNick().equals(nick))
					return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return null;
	}

	/***
	 * Metoda ustawiającego podopiecznego
	 * @param u Podopieczny
	 */
	public void dodajPodopiecznego(Uzytkownik u) {
		if (!podopieczni.contains(u)) {
			this.podopieczni.add(u);
		}
	}
	
	/**
	 * Metoda usuwająca podopiecznego
	 * @param u Podopieczny
	 */
	public void usunPodopiecznego(Uzytkownik u) {
		if (podopieczni.contains(u)) {
			this.podopieczni.remove(u);
		}
	}
	
	/***
	 * Metoda wyświetlająca listę podopiecznych
	 */
	public void wyswietlListePodopiecznych() {
		for (Uzytkownik uzytkownik : podopieczni) {
			uzytkownik.toString();
		}
	}
}
