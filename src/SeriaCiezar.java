import java.time.LocalDate;
import java.util.ArrayList;

/***
 * Klasa pośrednicząca tworząca powiązanie pomiędzy klasami Seria i Ciężar
 * @author Maria
 *
 */
public class SeriaCiezar extends ObiektPlusPlus  {
	protected LocalDate data;
	protected int iloscWykonanychPowtorzen;
	private Seria seria;
	private Ciezar ciezar;
	
	public SeriaCiezar(LocalDate data, int iloscWykonanychPowtorzen) {
		super();
		this.data = data;
		this.iloscWykonanychPowtorzen = iloscWykonanychPowtorzen;
	}	
	
	/***
	 * Dostarcza napis reprezentujący obiekt
	 * @return Napis
	 */
	public String toString() {
		StringBuffer sb  =  new StringBuffer();
		sb.append("Data wykonania serii: " + data + "\n");
		sb.append(seria);
		sb.append(ciezar);
		return sb.toString();
	}
}