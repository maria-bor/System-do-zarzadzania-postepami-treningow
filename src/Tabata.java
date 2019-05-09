import java.util.Arrays;
import java.util.List;

/***
 * Klasa reprezentująca trening typu Tabata
 * @author Maria
 *
 */
public class Tabata extends Trening {
	private static int czasRundy = 20;
	private static int czasPrzerwy = 10;
	private int liczbaRund = 1;
	private static List<Cwiczenie.Rodzaj>  rodzajeTreningu = Arrays.asList(Cwiczenie.Rodzaj.GoraSila, Cwiczenie.Rodzaj.GoraHipertrofia, 
			Cwiczenie.Rodzaj.DolSila, Cwiczenie.Rodzaj.DolHipertrofia, Cwiczenie.Rodzaj.Brzuch, Cwiczenie.Rodzaj.CaleCialo);

	/***
	 * Konstruktor
	 */
	public Tabata() {
		super();
	}
	
	/***
	 * Metoda zwraca rodzaje treningu typu Tabata
	 * @return Listę rodzajów treningu
	 */
	public static List<Cwiczenie.Rodzaj> getRodzajeTreningu() {
		return rodzajeTreningu;
	}

	@Override
	public void wybierzCwiczenie(Cwiczenie cwiczenie) {
//		if (cwiczenia.get(Trening.Rodzaj.Brzuch).contains(cwiczenie) 
//				|| cwiczenia.get(Trening.Rodzaj.GoraHipertrofia).contains(cwiczenie)
//				|| cwiczenia.get(Trening.Rodzaj.GoraSila).contains(cwiczenie)
//				|| cwiczenia.get(Trening.Rodzaj.DolHipertrofia).contains(cwiczenie)
//				|| cwiczenia.get(Trening.Rodzaj.DolSila).contains(cwiczenie)
//				|| cwiczenia.get(Trening.Rodzaj.CaleCialo).contains(cwiczenie)) {
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
		return "Tabata";
	}
}
