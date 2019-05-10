import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/***
 * Klasa Kontroler Modelu realizująca wzorzec projektowy MVC
 * @author Maria
 *
 */
public class Controller {

	private Model model;
	private WykonajCwiczenie wykonajCwiczenie;

	/***
	 * Konstruktor
	 */
	public Controller() {
		wykonajCwiczenie = new WykonajCwiczenie();
		wykonajCwiczenie.setController(this);
	}

	/***
	 * Metoda ustawiająca model
	 * @param model Model
	 */
	public void setModel(Model model){
		this.model = model;
		this.model.setWykonajCwiczenie(this.wykonajCwiczenie);
	}

	/***
	 * Metoda zwracająca listenera reagującego na kliknięcie w element listy treningów
	 * @return Listener
	 */
	public ListSelectionListener listaTreningowListener () {
		return new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					JList list = (JList) e.getSource();
					model.setNazwyCwiczenJList(list.getSelectedIndex());
				}
			}
		};
	}
	
	/***
	 * Metoda zwracająca listenera reagującego na kliknięcie w element listy ćwiczeń
	 * @return Listener
	 */
	public ListSelectionListener listaCwiczenListener() {
		return new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					Object selected = ((JList)e.getSource()).getSelectedValue();
					if(selected != null) {
						cwiczenieSelected(selected.toString());
					}
				}
			}
		};
	}
	
	/***
	 * Metoda zwracająca adapter otwierania okna
	 * @return Adapter
	 */
	public WindowAdapter getWindowListner() {
		return new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				super.windowOpened(e);
				model.setUzytkownik(Uzytkownik.zalogujSie("Mery"));
			}
		};
	}

	/***
	 * Metoda pokazująca widok wykonywania ćwiczeń
	 */
	public void wykonajCwiczenie() {
		wykonajCwiczenie.setVisible(true);
	}
	
	/***
	 * Metoda kontrulująca dodanie nowej serii do ćwiczenia
	 * @param nrSerii Numer serii
	 * @param iloscPowt Ilość powtórzeń
	 * @param masaCiezaru Masa ciężaru
	 */
	public void nastepnaSeriaClicked(int nrSerii, int iloscPowt, int masaCiezaru) {
		model.dodajSerie(nrSerii, iloscPowt, masaCiezaru);
	}

	/***
	 * Metoda zamykająca widok Wykonaj Ćwiczenie
	 */
	public void koniecClicked() {
		wykonajCwiczenie.setVisible(false);
		model.odswiezTabeleHistorii();
	}

	/***
	 * Metoda rozpoczynająca znalezienie nowego ćwiczenia z JListy
	 * @param nazwaCwiczenia Nazwa ćwiczenia
	 */
	public void cwiczenieSelected(String nazwaCwiczenia) {
		model.setCwiczenie(nazwaCwiczenia, true);
	}

}
