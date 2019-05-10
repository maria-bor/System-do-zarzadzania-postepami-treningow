import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/***
 * Klasa Modelu realizująca wzorzec projektowy MVC
 * @author Maria
 *
 */
public class Model {
	private Uzytkownik uzytkownik;
	private View view;
	private WykonajCwiczenie wykonajCwiczenie;
	private ObiektPlusPlus[] treningi;
	private ObiektPlusPlus[] cwiczenia;
	private Trening wybranyTrening;
	private ObiektPlusPlus wybraneCwiczenie;
	private int nrSeriiNastepnej = 0; 

	/***
	 * Konstruktor
	 */
	public Model() {

	}

	/***
	 * Metoda ustawia główny widok
	 * @param view Widok
	 */
	public void setWidok(View view){
		this.view = view;
	}

	/***
	 * Metoda zwraca aktualnego użytkownika
	 * @return Obiekt użytkownika
	 */
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	/***
	 * Metoda ustawia użytkownika
	 * @param uzytkownik Użytkownik
	 */
	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
		try {
			treningi = uzytkownik.dajPowiazania("trening");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] nazwyTreningow = new String[treningi.length];
		for (int i = 0; i < treningi.length; i++) {
			nazwyTreningow[i] = treningi[i].toString();
		}
		view.setTreningi(nazwyTreningow);
	}

	/***
	 * Metoda tworzy listę nazw wszystkich ćwiczeń powiązanych z wybranym treningiem 
	 * @param indeks Indeks wybranego treningu
	 */
	public void setNazwyCwiczenJList(int indeks) {
		List<String> nazwyCwiczen = new ArrayList<String>();
		wybranyTrening = (Trening) treningi[indeks];
		try {
			ObiektPlusPlus[] cwiczenia = wybranyTrening.dajPowiazania("cwiczenia");
			for (ObiektPlusPlus c : cwiczenia){
				nazwyCwiczen.add(c.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] nazwyCwiczenTablica = new String[nazwyCwiczen.size()];
		nazwyCwiczenTablica = nazwyCwiczen.toArray(nazwyCwiczenTablica);

		view.wykonajCwiczenieBtnEnable(false);
		view.setCwiczenia(nazwyCwiczenTablica);
		wykonajCwiczenie.setEtykiety(wybranyTrening.getNaglowkiTablicy(), wybranyTrening instanceof Cardio);
	}

	/***
	 * Metoda wyszukuje i ustawia aktualnie wybrane ćwiczenie w JList wśród ćwiczeń powiązanych z wybranym treningiem
	 * @param nazwaCwiczenia Nazwa wyszukiwanego ćwiczenia
	 * @param czyOdswiezycTabeleHistorii Informacja czy odsiężyć tabelę historii (odświeżamy tylko przy nacięnięciu przycisku "Koniec")
	 */
	public void setCwiczenie(String nazwaCwiczenia, boolean czyOdswiezycTabeleHistorii) {
		try {
			ObiektPlusPlus[] cwiczenia = wybranyTrening.dajPowiazania("cwiczenia");
			for (ObiektPlusPlus c : cwiczenia) {
				if(c.toString().equals(nazwaCwiczenia)){
					wybraneCwiczenie = c;
					// szukamy następnego numeru serii:
					nrSeriiNastepnej = 1;
					ObiektPlusPlus[] serie = wybraneCwiczenie.dajPowiazania("serie");
					if(serie.length!=0) {
						ObiektPlusPlus[] seriaCiezar =  serie[serie.length-1].dajPowiazania("seriaCiezar");
						if(seriaCiezar.length != 0) {
							if(((SeriaCiezar)seriaCiezar[seriaCiezar.length-1]).data.isEqual(LocalDate.now())) {
								nrSeriiNastepnej = serie.length+1;
							}
						}
					}
					wykonajCwiczenie.setNrSerii(nrSeriiNastepnej);
				}
			}
		} catch (Exception e) {
			wykonajCwiczenie.setNrSerii(nrSeriiNastepnej);
			e.printStackTrace();
		}

		if(czyOdswiezycTabeleHistorii){
			odswiezTabeleHistorii();
		}
		view.wykonajCwiczenieBtnEnable(true);
	}

	/***
	 * Metoda wypełniająca tabelę informacji nt. wybranego ćwiczenia w JList
	 */
	public void odswiezTabeleHistorii() {
		String[] naglowkiTablicy = wybranyTrening.getNaglowkiTablicy();
		Object[][] dane = null;
		try {		
			int nrSerii = 0;
			ObiektPlusPlus[] serie = wybraneCwiczenie.dajPowiazania("serie");
			for (ObiektPlusPlus s : serie) {
				ObiektPlusPlus[] sc = s.dajPowiazania("seriaCiezar");
				nrSerii += sc.length;
			}
			dane = new Object[nrSerii][naglowkiTablicy.length];
			nrSerii = 0;
		
			for(ObiektPlusPlus sOPP : serie) {
				ObiektPlusPlus[] scArr = sOPP.dajPowiazania("seriaCiezar");
				for(ObiektPlusPlus scOPP : scArr) {
					Seria s = (Seria)sOPP;
					SeriaCiezar sc = (SeriaCiezar)scOPP;
					dane[nrSerii][0] = sc.data.toString();
					dane[nrSerii][1] = s.getNumer();
					dane[nrSerii][2] = sc.iloscWykonanychPowtorzen;
					dane[nrSerii][3] = sc.dajPowiazania("ciezar")[0].toString();
					nrSerii++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.odswiezTabeleHistorii(naglowkiTablicy, dane);
	}

	/***
	 * Metoda dodająca nową serię do aktualnie wybranego ćwiczenia (zostanie stworzone nowe powiązania)
	 * @param nrSerii Numer serii
	 * @param iloscPowt Ilość powtórzeń
	 * @param masaCiezaru Masa ciężaru
	 */
	public void dodajSerie(int nrSerii, int iloscPowt, int masaCiezaru) {
		try {
			Seria seria = new Seria(nrSerii, Cwiczenie.Poziom.Junior);
			Ciezar ciezar = new Ciezar(masaCiezaru);
			SeriaCiezar seriaCiezar = new SeriaCiezar(LocalDate.now(), iloscPowt);
			wybraneCwiczenie.dodajCzesc("serie", "cwiczenie", seria);
			seria.dodajPowiazanie("seriaCiezar", "serie", seriaCiezar);
			seriaCiezar.dodajPowiazanie("ciezar", "seriaCiezar", ciezar);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/***
	 * Metoda zapamiętuje aktualnie wybrane ćwiczenie przez użytkownika w JList
	 * @param wykonajCwiczenie Aktualnie wybrane ćwiczenie
	 */
	public void setWykonajCwiczenie(WykonajCwiczenie wykonajCwiczenie) {
		this.wykonajCwiczenie = wykonajCwiczenie;
	}
}
