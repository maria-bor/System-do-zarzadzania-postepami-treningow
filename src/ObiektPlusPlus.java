import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;

/***
 * Klasa zarządzająca asocjacjami
 * @author Maria
 *
 */
public class ObiektPlusPlus extends ObiektPlus implements Serializable{
	/***
	 * Przechowuje informacje o wszystkich powiązaniach tego obiektu
	 */
	private Hashtable<String, LinkedHashMap<Object, ObiektPlusPlus>> powiazania = new Hashtable<String, LinkedHashMap<Object, ObiektPlusPlus>>();
	
	/***
	 * Przechowuje informacje o wszystkich częściach powiązanych z którymkolwiek z obiektów
	 */
	private static HashSet<ObiektPlusPlus> wszystkieCzesci = new HashSet<ObiektPlusPlus>();
	
	/***
	 * Konstruktor
	 */
	public ObiektPlusPlus() {
		super();
	}
	
	private void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObiektPlusPlus obiektDocelowy, Object kwalifikator, int licznik) {
		LinkedHashMap<Object, ObiektPlusPlus> powiazaniaObiektu;
		
		if (licznik < 1) {
			return;
		}

		if(powiazania.containsKey(nazwaRoli)) {
			powiazaniaObiektu = powiazania.get(nazwaRoli);
		}
		else {
			powiazaniaObiektu = new LinkedHashMap<Object, ObiektPlusPlus>();
			powiazania.put(nazwaRoli, powiazaniaObiektu);
		}
		
		if(!powiazaniaObiektu.containsKey(kwalifikator)) {
			powiazaniaObiektu.put(kwalifikator, obiektDocelowy);
			obiektDocelowy.dodajPowiazanie(odwrotnaNazwaRoli, nazwaRoli, this, this, licznik -1);
		}
	}
	
	/***
	 * Metoda tworząca asocjację kwalifikowaną
	 * @param nazwaRoli Nazwa roli
	 * @param odwrotnaNazwaRoli Odwrotna nazwa roli
	 * @param obiektDocelowy Obiekt z którym jest tworzona asocjacja
	 * @param kwalifikator Kwalifikator
	 */
	public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObiektPlusPlus obiektDocelowy, Object kwalifikator) {
		dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, kwalifikator, 2);
	}
	
	/***
	 * Metoda tworząca asocjację binarną
	 * @param nazwaRoli Nazwa roli
	 * @param odwrotnaNazwaRoli Odwrotna nazwa roli
	 * @param obiektDocelowy Obiekt z którym jest tworzona asocjacja
	 */
	public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObiektPlusPlus obiektDocelowy) {
		dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, obiektDocelowy);
	}

	/***
	 * Metoda tworząca kompozycję
	 * @param nazwaRoli Nazwa roli
	 * @param odwrotnaNazwaRoli Odwrotna nazwa rolki
	 * @param obiektCzesc Część
	 * @throws Exception Wyjątek w przypadku istnienia powiązania
	 */
	public void dodajCzesc(String nazwaRoli, String odwrotnaNazwaRoli, ObiektPlusPlus obiektCzesc) throws Exception {
		if(wszystkieCzesci.contains(obiektCzesc)) {
			throw new Exception("Ta część jest już powiązana z całością");
		}
		dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektCzesc);
		wszystkieCzesci.add(obiektCzesc);
	}
	
	/***
	 * Metoda zwracająca kontener powiązań dla podanej roli
	 * @param nazwaRoli Nazwa roli
	 * @return Kontener powiązań 
	 * @throws Exception Wyjątek w przypadku braku powiązań dla roli
	 */
	public ObiektPlusPlus[] dajPowiazania(String nazwaRoli) throws Exception {
		LinkedHashMap<Object, ObiektPlusPlus> powiazaniaObiektu;
		if(!powiazania.containsKey(nazwaRoli)) {
			throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
		}
		powiazaniaObiektu = powiazania.get(nazwaRoli);
		return (ObiektPlusPlus[])powiazaniaObiektu.values().toArray(new ObiektPlusPlus[0]);
	}
	
	/***
	 * Metoda wyświwetlająca wszystkie powiązania dla podanej roli
	 * @param nazwaRoli Nazwa roli
	 * @param stream Strumień do zapisu danych 
	 * @throws Exception Wyjątek w przypadku braku powiązań dla roli
	 */
	public void wyswietlPowiazania(String nazwaRoli, PrintStream stream) throws Exception {
		LinkedHashMap<Object, ObiektPlusPlus> powiazaniaObiektu;
		if(!powiazania.containsKey(nazwaRoli)) {
			throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
		}
		powiazaniaObiektu = powiazania.get(nazwaRoli);
		Collection col = powiazaniaObiektu.values();
		stream.println(this.getClass().getSimpleName() + " powiazania w roli " + nazwaRoli + ": ");
		for (Object o : col) {
			stream.println(" " + o);
		}
	}
	
	/***
	 * Metoda zwracająca kontener powiązań dla podanej roli i kwalifikatora
	 * @param nazwaRoli Nazwa roli
	 * @param kwalifikator Kwalifikator
	 * @return Kontener powiązań
	 * @throws Exception Wyjątek w przypadku braku roli lub kwalifikatora
	 */
	public ObiektPlusPlus dajPowiazanyObiekt(String nazwaRoli, Object kwalifikator) throws Exception {
		LinkedHashMap<Object, ObiektPlusPlus> powiazaniaObiektu;
		if(!powiazania.containsKey(nazwaRoli)) {
			throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
		}
		powiazaniaObiektu = powiazania.get(nazwaRoli);
		if(!powiazaniaObiektu.containsKey(kwalifikator)) {
			throw new Exception("Brak powiazn dla kwalifikatora: " + kwalifikator);
		}
		return powiazaniaObiektu.get(kwalifikator);
	}
}
