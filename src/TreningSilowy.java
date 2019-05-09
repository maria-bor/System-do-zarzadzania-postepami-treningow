import java.util.Arrays;
import java.util.List;


/***
 * Klasa reprezentująca trening typu Trening Siłowy
 * @author Maria
 *
 */
public class TreningSilowy extends Trening{
	private static List<Cwiczenie.Rodzaj> rodzajeTreningu = Arrays.asList(Cwiczenie.Rodzaj.GoraSila, Cwiczenie.Rodzaj.GoraHipertrofia, 
			Cwiczenie.Rodzaj.DolSila, Cwiczenie.Rodzaj.DolHipertrofia, Cwiczenie.Rodzaj.Brzuch);
	
	/***
	 * Konstruktor
	 */
	public TreningSilowy(){
		super();
	}

	/***
	 * Metoda zwraca rodzaje treningu typu Trening Siłowy
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
//				|| cwiczenia.get(Trening.Rodzaj.DolSila).contains(cwiczenie)) {
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
		return "Trening siłowy";
	}
}
