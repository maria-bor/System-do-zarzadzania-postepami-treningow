import java.util.ArrayList;

/***
 * Klasa reprezentująca Ciężar
 * @author Maria
 *
 */
public class Ciezar extends ObiektPlusPlus {
	private double masa;
	
	/***
	 * Konstruktor
	 * @param masa Masa ciężaru
	 */
	public Ciezar(double masa) {
		super();
		this.masa = masa;
	}
	
	/***
	 * Metoda pokazująca historię progresii/regresii masy ciężaru 
	 * @param ciezarSeria Obiekt klasy pośredniczącej między klasami Seria i Ciężar
	 */
	public void utworzWykres(SeriaCiezar ciezarSeria) {
		StringBuffer sb = new StringBuffer();
		sb.append("Waga ciezaru: " + masa + ", data: " + ciezarSeria.data + "\n");
		System.out.println(sb.toString());		
	}
	
	/***
	 * Metoda zwracająca masę ciężaru
	 * @return Masa ciężaru
	 */
	public double getMasa() {
		return this.masa;
	}
	
	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString() {
		return "" + this.masa;
	}
}