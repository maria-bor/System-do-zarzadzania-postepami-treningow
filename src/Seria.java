/***
 * Klasa reprezentująca serię
 * @author Maria
 *
 */
public class Seria extends ObiektPlusPlus {
	private int numer;
	protected int iloscPowtorzenOd;
	protected int iloscPowtorzenDo;
	private double czasTrwania;
		
	public Seria(int numer, Cwiczenie.Poziom poziom) {
		super();
		this.numer = numer;
		if(poziom == Cwiczenie.Poziom.Junior) {
			this.iloscPowtorzenOd = 6;
			this.iloscPowtorzenDo = 8;
		}
		else if(poziom == Cwiczenie.Poziom.Medium) {
			this.iloscPowtorzenOd = 8;
			this.iloscPowtorzenDo = 10;
		}
		else if(poziom == Cwiczenie.Poziom.Hardcore) {
			this.iloscPowtorzenOd = 10;
			this.iloscPowtorzenDo = 12;
		}
	}

	/***
	 * Metoda zwracająca numer serii
	 * @return Numer serii
	 */
	public int getNumer() {
		return this.numer;
	}
	
	/***
	 * Metoda zwracająca minimalną ilość powtórzeń w serii
	 * @return Ilość powtórzeń
	 */
	public int getIloscPowtorzenOd() {
		return iloscPowtorzenOd;
	}

	/***
	 * Metoda zwracająca maksymalną ilość powtórzeń w serii
	 * @return Ilość powtórzeń
	 */
	public int getIloscPowtorzenDo() {
		return iloscPowtorzenDo;
	}
	
	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Numer serii: " + numer + "\n");
		return sb.toString();				
	}
}