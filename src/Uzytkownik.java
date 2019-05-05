import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.TreeMap;

/***
 * Klasa opisująca użytkownika systemu
 * @author Maria
 *
 */
public class Uzytkownik extends Osoba {
	protected LocalDate dataUrodzenia;
	private int wzrost;
	private byte[] zdjecieProfilowe;
	private static int minimalnyWiek = 16;
	
	private TreeMap<LocalDate, Waga> wagaKwalif = new TreeMap<LocalDate, Waga>();
	private TreeMap<LocalDate, PomiarSylwetki> pomiarySylwetkiKwalif = new TreeMap<LocalDate, PomiarSylwetki>();
	
	private Trener trener;
	
	protected Uzytkownik(String nick, LocalDate dataUrodzenia, int wzrost, byte[] zdjecieProfilowe) {
		super(nick, zdjecieProfilowe);
		this.dataUrodzenia = dataUrodzenia;
		this.wzrost = wzrost;
		wyliczWiek();
	}
	
	/***
	 * Metoda, która sprawdza czy można założyć konto użytkownika o podanym nick-u
	 * @param nick Nazwa użytkownika
	 * @return True - jeśli konto zostało założone, false - jeśli nie da się założyć konta
	 */
	public static boolean zalozKonto(String nick) {
		try {
			List<Uzytkownik> l = (List<Uzytkownik>) getEkstensje(Uzytkownik.class);
			for (Uzytkownik o : l) {
				if (o.getNick().equals(nick)) {
					System.out.println("Jest juz uzytkownik z nickiem: " + nick);
					return false;
				}
			}
		} catch (Exception e) { // jeśli brak ekstensji to nie ma jeszcze takiego uzytkowniak
			System.out.println("Nie ma uzytkownika z nickiem: " + nick);
			return true;
		}	
		return true;
	}

	/***
	 * Metoda, która loguje użytkownika do systemu
	 * @param nick Nazwa użytkownika
	 * @return True - jeśli użytkownik został pomyślnie zalogowany, false - jeśli użytkownika nie da się zalogować do systemu
	 */
	public static Uzytkownik zalogujSie(String nick) {
		try {
			List<Uzytkownik> l = (List<Uzytkownik>) getEkstensje(Uzytkownik.class);
			for (Uzytkownik u : l) {
				if (u.getNick().equals(nick))
					return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return null;
	}
	
	/***
	 * Metoda dodająca nową wagę użytkownika z atualną datą
	 * @param nowaWaga Nowa waga użytkownika
	 */
	public void dodajWageKwalif(Waga nowaWaga) {
		if(!wagaKwalif.containsKey(nowaWaga.date)) {
			wagaKwalif.put(nowaWaga.date, nowaWaga);
			nowaWaga.dodajUzytkownika(this);
		}
	}
	
	/***
	 * Metoda, która wyszukuje wagę użytkownika o podanej dacie
	 * @param date Data
	 * @return Waga użytkownika
	 * @throws Exception Wyjątek w przypadku braku wagi z podanym terminem
	 */
	public Waga znajdzWageKwalif(LocalDate date) throws Exception {
		if(!wagaKwalif.containsKey(date)) {
			throw new Exception("Nie znaleziono wagi dnia: " + date);
		}
		return wagaKwalif.get(date);
	}
	
	/***
	 * Metoda dodająca nowe pomiary sylwetki użytkownika z atualną datą
	 * @param nowePomiarSylwetki Pomiary sylwetki
	 */
	public void dodajPomiaryKwalif(PomiarSylwetki nowePomiarSylwetki) {
		if(!pomiarySylwetkiKwalif.containsKey(nowePomiarSylwetki.date)) {
			pomiarySylwetkiKwalif.put(nowePomiarSylwetki.date, nowePomiarSylwetki);
			nowePomiarSylwetki.dodajUzytkownika(this);
		}
	}
	
	/***
	 * Metoda, która wyszukuje pomiary sylwetki użytkownika o podanej dacie
	 * @param date Data
	 * @return Pomiary sylwetki
	 * @throws Exception Wyjątek w przypadku braku pomiarów sylwetki z podanym terminem
	 */
	public PomiarSylwetki znajdzPomiarySylwetkiKwalif(LocalDate date) throws Exception {
		if(!pomiarySylwetkiKwalif.containsKey(date)) {
			throw new Exception("Nie znaleziono pomiarow sylwetki dnia: " + date);
		}
		return pomiarySylwetkiKwalif.get(date);
	}
	
	/***
	 * Metoda tworząca nowego użytkownika w systemie
	 * @param nick Nick użytkownika
	 * @param dataUrodzenia Data urodzenia
	 * @param wzrost Wzrost
	 * @param zdjecieProfilowe Zdjęcie profilowe
	 * @return Użytkownik
	 */
	public static Object stworzNowegoUzytkownika(String nick, LocalDate dataUrodzenia, int wzrost, byte[] zdjecieProfilowe) {
		if (wyliczWiek(dataUrodzenia) < minimalnyWiek) {
			System.out.println("Wymagany wiek uzytkownika to 18 lat!");
			return null;
		}
		else {
			return new Uzytkownik(nick, dataUrodzenia, wzrost, zdjecieProfilowe);
		}
	}
	
	/***
	 * Metoda, która liczy średni wiek użytkowników systemu
	 * @return Średni wiek użytkowników systemu
	 */
	public static double wyliczSredniWiek() {
		double suma = 0;
		List<?> ekstensja = null;
		if (ekstensje.containsKey(Uzytkownik.class)) {
			ekstensja = ekstensje.get(Uzytkownik.class);
			for (Object o : ekstensja) {
				Uzytkownik u = (Uzytkownik)o;
				suma += Uzytkownik.wyliczWiek(u.dataUrodzenia);
			}
		}
		return ekstensja.size() != 0 ? suma/ekstensja.size() : 0;
	}
	
	/***
	 * Metoda dodająca wagę użytkownika
	 * @param masa Masa ciała użytkownika
	 */
	public void zwarzSie(double masa) {
		dodajWageKwalif(new Waga(masa, LocalDate.now()));
	}
	
	/***
	 * Metoda dodająca wagę użytkownika o podanym terminie
	 * @param masa Masa ciała użytkownika
	 * @param data Data
	 */
	public void wprowadzWage(double masa, LocalDate data) {
		dodajWageKwalif(new Waga(masa, LocalDate.now()));
	}
	
	/***
	 * Metoda zwracająca wiek użytkownika 
	 * @param dataUrodzenia Data urodzenia
	 * @return Wiek użytkownika
	 */
	public static int wyliczWiek(LocalDate dataUrodzenia) {
		return Period.between(dataUrodzenia, LocalDate.now()).getYears();
	}
	
	private int wyliczWiek() {
		return Period.between(this.dataUrodzenia, LocalDate.now()).getYears();
	}
	
	/***
	 * Metoda zwracająca wzrost użytkownika
	 * @return Wzrost
	 */
	public int getWzrost() {
		return this.wzrost;
	}
}