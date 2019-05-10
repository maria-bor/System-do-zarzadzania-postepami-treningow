import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/***
 * Klasa Widoku realizująca wzorzec projektowy MVC
 * @author Maria
 *
 */
public class View {

	private JFrame frame;
	private Controller controller;
	private JList listTrening;
	private JList listCwiczenia;
	private JTable tableHistoria;
	private JButton btnWykonajCwiczenie;

	/***
	 * Metoda uruchamiająca główny widok
	 * @param args
	 */
	public static void main(String[] args) {
		Dane.zaladujDane();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Model model = new Model();				
					View view = new View();		
					Controller controller = new Controller();

					model.setWidok(view);	
					controller.setModel(model);
					view.setController(controller);
					view.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/***
	 * Konstruktor
	 */
	public View() {
		initialize();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					ObiektPlus.zapiszEkstensje();
				} catch (FileNotFoundException exception) {
					exception.printStackTrace();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				System.out.println("Ekstensje zapisane");
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("InternalFrame.borderHighlight"));
		frame.setBounds(100, 100, 472, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		setFrameOnCentre();

		JLabel lblWybierzTrening = new JLabel("Wybierz trening:");
		lblWybierzTrening.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWybierzTrening.setBounds(10, 11, 147, 29);
		frame.getContentPane().add(lblWybierzTrening);

		JLabel lblWybierzCwiczenie = new JLabel("Wybierz ćwiczenie:");
		lblWybierzCwiczenie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWybierzCwiczenie.setBounds(167, 11, 256, 29);
		frame.getContentPane().add(lblWybierzCwiczenie);

		listTrening = new JList();
		listTrening.setBorder(new LineBorder(new Color(0, 0, 0)));
		listTrening.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTrening.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listTrening.setBounds(10, 51, 147, 99);
		frame.getContentPane().add(listTrening);

		JScrollPane scrollPaneCwiczenia = new JScrollPane();
		scrollPaneCwiczenia.setBounds(167, 51, 289, 99);
		frame.getContentPane().add(scrollPaneCwiczenia);

		listCwiczenia = new JList();
		scrollPaneCwiczenia.setViewportView(listCwiczenia);

		JScrollPane scrollPaneTablica = new JScrollPane();
		scrollPaneTablica.setBounds(10, 227, 446, 242);
		frame.getContentPane().add(scrollPaneTablica);

		tableHistoria = new JTable();
		tableHistoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableHistoria.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableHistoria.setEnabled(false);
		tableHistoria.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Data", "Nr serii", "Ilość powtórzeń", "Ciężar"
				}
				));
		tableHistoria.getColumnModel().getColumn(2).setPreferredWidth(88);
		scrollPaneTablica.setViewportView(tableHistoria);

		btnWykonajCwiczenie = new JButton("Wykonaj ćwiczenie");
		btnWykonajCwiczenie.setEnabled(false);
		btnWykonajCwiczenie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.wykonajCwiczenie();
			}
		});
		btnWykonajCwiczenie.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnWykonajCwiczenie.setBounds(208, 161, 183, 25);
		frame.getContentPane().add(btnWykonajCwiczenie);
	}

	private void setController(Controller controller){
		this.controller = controller;
		listTrening.addListSelectionListener(controller.listaTreningowListener());
		listCwiczenia.addListSelectionListener(controller.listaCwiczenListener());
		frame.addWindowListener(controller.getWindowListner());
	}

	/***
	 * Metoda wyświetlająca listę nazw treningu
	 * @param listaTreningow Lista nazw treningu
	 */
	public void setTreningi(String[] listaTreningow) {
		listTrening.setModel(new AbstractListModel() {
			String[] values = listaTreningow;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	/***
	 * Metoda wyświetlająca listę nazw ćwiczeń
	 * @param listaCwiczen Lista nazw ćwiczeń
	 */
	public void setCwiczenia(String[] listaCwiczen) {
		listCwiczenia.setModel(new AbstractListModel() {
			String[] values = listaCwiczen;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});		
		odswiezTabeleHistorii(new String[] {
				"Data", 
				"Nr serii", 
				listTrening.getSelectedValue().toString().equals("Cardio") ? "Czas[min]" : "Ilość powtórzeń", "Ciężar"}, new Object[][]{});
	}

	/***
	 * Metoda ustawiająca model tabeli
	 * @param naglowkiTablicy Nagłówki tablicy
	 * @param dane Dane
	 */
	public void odswiezTabeleHistorii(String[] naglowkiTablicy, Object[][] dane) {
		tableHistoria.setModel(new DefaultTableModel(dane, naglowkiTablicy));
	}

	/***
	 * Metoda aktywuje przycisk
	 * @param b Informacja czy aktywować przycisk
	 */
	public void wykonajCwiczenieBtnEnable(boolean b) {
		btnWykonajCwiczenie.setEnabled(b);
	}
	
	private void setFrameOnCentre() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}
