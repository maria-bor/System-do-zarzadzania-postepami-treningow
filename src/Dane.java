import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Klasa przygotowująca dane programu 
 * @author Maria
 *
 */
public class Dane {	
	/***
	 * Metoda inicjująca wczytanie danych
	 */
	public static void zaladujDane() {
		try {
			ObiektPlus.odczytajEkstensje();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Trener.zalozKonto("Krzysztof")) {
			new Trener("Krzysztof", null);
		}
		Uzytkownik uzytkownik1 = null;
		if (Uzytkownik.zalozKonto("Mery")) {
			Object o = Uzytkownik.stworzNowegoUzytkownika("Mery", LocalDate.of(1994, 06, 24), 160, null);
			if (o != null) {
				uzytkownik1 = (Uzytkownik) o;
				uzytkownik1.zwarzSie(51);
			}
		}	
//		Tworzone tylko raz !
//		tworzDane(uzytkownik1);
		System.out.println("Ekstensje po wczytaniu i ewentualnym utworzeniu trenera lub uzytkownika:");
		try {
			ObiektPlus.pokazEkstensje(Trener.class);
			ObiektPlus.pokazEkstensje(Uzytkownik.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void tworzDane(Uzytkownik uzytkownik) {
		List<String> nazwyCwiczen = null;		
		nazwyCwiczen = tworzTreningSilowy(uzytkownik, nazwyCwiczen);		
		nazwyCwiczen = tworzCardio(uzytkownik, nazwyCwiczen);		
		nazwyCwiczen = tworzTabate(uzytkownik, nazwyCwiczen);
	}

	private static List<String> tworzTabate(Uzytkownik uzytkownik, List<String> nazwyCwiczen) {
		Tabata tabata = new Tabata();
		uzytkownik.dodajPowiazanie("trening", "uzytkownik", tabata);
		for (Cwiczenie.Rodzaj r : Tabata.getRodzajeTreningu()) {
			switch(r) {
			case Brzuch:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("podnoszenie nóg", "deska"));
				break;
			case GoraHipertrofia:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("wyciskanie hantli", "wiosłowanie sztangą podchwytem"));
				break;
			case GoraSila:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("ohp sztanga", "biceps hantlami"));
				break;
			case DolHipertrofia:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("hip trust", "RDL", "wykroki chodzące ze sztangą"));
				break;
			case DolSila:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("przysiad ze sztangą", "zakroki chodzącez hantlami"));
				break;
			case CaleCialo:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("trucht", "podskoki", "pajace"));
				break;
			default:
				continue;
			}
			for (String nazwaCwiczenia : nazwyCwiczen) {
				try {
					Cwiczenie cw = new Cwiczenie(nazwaCwiczenia, r, Cwiczenie.Poziom.Junior);
					tabata.dodajCzesc("cwiczenia", "trening", cw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return nazwyCwiczen;
	}

	private static List<String> tworzCardio(Uzytkownik uzytkownik, List<String> nazwyCwiczen) {
		Cardio cardio = new Cardio();
		uzytkownik.dodajPowiazanie("trening", "uzytkownik", cardio);
		for (Cwiczenie.Rodzaj r : Cardio.getRodzajeTreningu()) {
			switch(r) {
			case CaleCialo:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("bieg", "jazda na rowerze", "jazda na rolkach"));
				break;
			default:
				continue;
			}
			for (String nazwaCwiczenia : nazwyCwiczen) {
				try {
					Cwiczenie cw = new Cwiczenie(nazwaCwiczenia, r, Cwiczenie.Poziom.Junior);
					cardio.dodajCzesc("cwiczenia", "trening", cw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return nazwyCwiczen;
	}

	private static List<String> tworzTreningSilowy(Uzytkownik uzytkownik, List<String> nazwyCwiczen) {
		TreningSilowy treningSilowy = new TreningSilowy();
		uzytkownik.dodajPowiazanie("trening", "uzytkownik", treningSilowy);
		for (Cwiczenie.Rodzaj r : TreningSilowy.getRodzajeTreningu()) {
			switch(r) {
			case Brzuch:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("brzuszki", "plank"));
				break;
			case GoraHipertrofia:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("wyciskanie sztangi poziom", "wiosłowanie sztangą nachwytem", 
						"ściaganie do klatki chwyt neutralny", "wyciskanie hantli nad głowe", 
						"unoszenie hantli w opadziu tułowia", "pompki na poręczach"));
				break;
			case GoraSila:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("ohp sztanga", "ściąganie do klatki nachwytem", 
						"wiosłowanie na maszynie siedząc", "wyciskanie hantli skos dodatni", 
						"facepull", "biceps hantlami z supinacją"));
				break;
			case DolHipertrofia:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("barbell hip trust", "wypychanie na suwnicy", 
						"RDL", "wykroki chodzące z hantlami", "uginanie nóg na maszynie"));
				break;
			case DolSila:
				nazwyCwiczen = new ArrayList<>(Arrays.asList("odwodziciel", "przysiad klasyczny", 
						"zakroki z hantlami", "uginanie nóg na maszynie", "kickback wyciąg dolny"));
				break;
			default:
				continue;
			}
			for (String nazwaCwiczenia : nazwyCwiczen) {
				try {
					Cwiczenie cw = new Cwiczenie(nazwaCwiczenia, r, Cwiczenie.Poziom.Junior);
					treningSilowy.dodajCzesc("cwiczenia", "trening", cw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return nazwyCwiczen;
	}
}