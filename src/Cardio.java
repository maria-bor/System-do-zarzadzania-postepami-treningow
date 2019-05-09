import java.util.Arrays;
import java.util.List;

/***
 * Klasa reprezentująca trening typu Cardio 
 * @author Maria
 *
 */
public class Cardio extends Trening {
	protected enum Maszyna {Bieznia, Rower, Aeroby};
	private static List<Cwiczenie.Rodzaj> rodzajeTreningu = Arrays.asList(Cwiczenie.Rodzaj.CaleCialo);

	/***
	 * Konstruktor
	 */
	public Cardio() {
		super();
		naglowkiTablicy = new String[] {"Data", "Nr serii", "Czas[min]", "Ciężar"};
	}

	/***
	 * Metoda zwraca rodzaje treningu typu Cardio
	 * @return Listę rodzajów treningu
	 */
	public static List<Cwiczenie.Rodzaj> getRodzajeTreningu() {
		return rodzajeTreningu;
	}

	@Override
	public void wybierzCwiczenie(Cwiczenie cwiczenie) {	
//		if (cwiczenia.get(Trening.Rodzaj.CaleCialo).contains(cwiczenie)) {
//			cwiczenieWybrane = cwiczenie;
//		}
//		else {
//			cwiczenieWybrane = null;
//		}
	}

	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString() {
		return "Cardio";
	}
}
