import java.io.Serializable;
import java.time.LocalDate;

public class PomiarSylwetki extends ObiektPlus implements Serializable{
	private double obwodKlatki;
	private double obwodWPasie;
	private double obwodBioder;
	private double obwodBicepsa;
	
	public LocalDate date;
	private Uzytkownik uzytkownik;
	
	public PomiarSylwetki(double obwodKlatki, double obwodWPasie, double obwodBioder, double obwodBicepsa, LocalDate data) {
		super();
		this.obwodWPasie = obwodWPasie;
		this.obwodKlatki = obwodKlatki;
		this.obwodBioder = obwodBioder;
		this.obwodBicepsa = obwodBicepsa;
		this.date = data;
	}

	public void dodajUzytkownika(Uzytkownik uzytkownik) {
		if (this.uzytkownik != uzytkownik) {
			this.uzytkownik = uzytkownik;
			this.uzytkownik.dodajPomiaryKwalif(this);
		}
	}
	
	public void wyswietlWykres() {
		StringBuffer sb = new StringBuffer();
		sb.append("Trendy pomiarów sylwetki: \n");
		sb.append("* data: " + this.date + "\n");
		sb.append("* obwod bicepsa: " + this.obwodBicepsa 
				+ "\n* obwod klatki: " + this.obwodKlatki
				+ "\n* obwod bioder: " + this.obwodBioder
				+ "\n* obwod w pasie: " + this.obwodWPasie);
		System.out.println(sb.toString());
	}
}
