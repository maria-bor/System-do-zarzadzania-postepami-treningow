import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/***
 * Klasa zarządzająca uniwersalną ekstensją, która korzysta z serializacji, żeby utrwalić ekstensję.
 * Uniwersalnośc ekstensji polega na użyciu kontenera przechowującego klucze i wartości, 
 * gdzie kluczem jest nazwa konkretnej klasy biznesowej, a wartością kontener zawierający referencję do jej wystąpień (właściwa ekstensja)
 * @author Maria
 *
 */
public class ObiektPlus implements Serializable{
	protected static HashMap<Class<?>, List<ObiektPlus>> ekstensje = new HashMap<>();
	
	protected ObiektPlus() {
		if(!ekstensje.containsKey(this.getClass())) {
			ekstensje.put(this.getClass(), new LinkedList<ObiektPlus>());
		}
		ekstensje.get(this.getClass()).add(this);
	}
	
	/***
	 * Metoda klasowa zapisująca wszystkie ekstensje do pliku
	 * @throws FileNotFoundException Wyjątek w przypadku braku pliku
	 * @throws IOException Wyjątek serializacji
	 */
	public static void zapiszEkstensje() throws FileNotFoundException, IOException {	
		new PrintWriter("ekstensje.bin").close();
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("ekstensje.bin"))) {
			output.writeObject(ekstensje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Metoda klasowa odczytująca wszystkie ekstensje z pliku (wcześniej tam zapisane)
	 * @throws ClassNotFoundException Wyjątek w przypadku podania nazwy klasy, do której nie ma definicji
	 * @throws IOException Wyjątek serializacji
	 */
	public static void odczytajEkstensje() throws ClassNotFoundException, IOException {	
		if(new File("ekstensje.bin").exists()) {
			try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("ekstensje.bin"))) {
				ekstensje = (HashMap<Class<?>, List<ObiektPlus>>) input.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	/***
	 * Metoda zwracająca ekstensję wybranej klasy
	 * @param klasa Nazwa klasy
	 * @return Ekstensja
	 * @throws Exception Wyjątek w przypadku braku ekstensji wybranej klasy
	 */
	public static List<?> getEkstensje(Class<?> klasa) throws Exception {
		List<?> ekstensja = null;
		if (ekstensje.containsKey(klasa)) {
			ekstensja = ekstensje.get(klasa);
			return ekstensja;
		}
		else {
			throw new Exception("Brak ekstensji klasy: " + klasa);
		}
	}
	
	/***
	 * Metoda klasowa wyświetlająca wszystkie ekstensje wybranej klasy
	 * @param klasa Nazwa klasy
	 * @throws Exception Wyjątek w przypadku braku ekstensji klasy
	 */
	public static void pokazEkstensje(Class<?> klasa) throws Exception {
		List<?> ekstensja = null;
		if (ekstensje.containsKey(klasa)) {
			ekstensja = ekstensje.get(klasa);
		}
		else {
			throw new Exception("Brak ekstensji klasy: " + klasa);
		}
		System.out.println("Ekstensja klasy: " + klasa.getSimpleName());
		for(Object obiekt : ekstensja) {
			System.out.println(obiekt);
		}
	}
	
	/***
	 * Metoda usuwająca obiekt z ekstensji
	 * @param klasa Nazwa klasy
	 * @param o Obiekt do usunięcia
	 * @throws Exception Wyjątek w przypadku próby usunięcia obiektu z ekstensji, który nie został do niej dodany
	 */
	public static void usunZEkstensji(Class<?> klasa, ObiektPlus o) throws Exception{
		if (ekstensje.containsKey(klasa)) {
			List<?> ekstensja = ekstensje.get(klasa);
			if(ekstensja.contains(o)) {
				ekstensja.remove(o);
			}
			else {
				throw new Exception("Proba usuniecia z ekstensji obiektu, ktory nie zostal dodany do ekstensji (klasa:" + klasa + ").");
			}
		}
	}
}
