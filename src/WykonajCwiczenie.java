import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Toolkit;

/***
 * Klasa pomocniczego widoku 
 * @author Maria
 *
 */
public class WykonajCwiczenie extends JFrame {

	private JPanel contentPane;
	private JTextField textNrSerii;
	private JTextField textIloscPowt;
	private JTextField textCiezar;
	private Controller controller;
	private JLabel lblIloscPowt;
	private JLabel lblNrSerii;
	private JLabel lblCiezar;

	/***
	 * Metoda, która uruchamia widok WykonajĆwiczenie
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WykonajCwiczenie frame = new WykonajCwiczenie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/***
	 * Konstruktor
	 */
	public WykonajCwiczenie() {
		setResizable(false);	
		addComponentListener(new ComponentAdapter() {		
			@Override
			public void componentShown(ComponentEvent arg0) {
				setFrameOnCentre();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.borderHighlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNrSerii = new JLabel("Nr serii: ");
		lblNrSerii.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNrSerii.setBounds(47, 50, 110, 14);
		contentPane.add(lblNrSerii);
		
		lblIloscPowt = new JLabel("Ilość powtórzeń: ");
		lblIloscPowt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIloscPowt.setBounds(47, 109, 129, 14);
		contentPane.add(lblIloscPowt);
		
		lblCiezar = new JLabel("Ciężar: ");
		lblCiezar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCiezar.setBounds(47, 164, 110, 14);
		contentPane.add(lblCiezar);
		
		textNrSerii = new JTextField();
		textNrSerii.setEnabled(false);
		textNrSerii.setText("1");
		textNrSerii.setBounds(244, 49, 86, 20);
		contentPane.add(textNrSerii);
		textNrSerii.setColumns(10);
		
		textIloscPowt = new JTextField();
		textIloscPowt.setBounds(244, 108, 86, 20);
		contentPane.add(textIloscPowt);
		textIloscPowt.setColumns(10);
		
		textCiezar = new JTextField();
		textCiezar.setBounds(244, 163, 86, 20);
		contentPane.add(textCiezar);
		textCiezar.setColumns(10);
		
		JButton btnNastepnaSeria = new JButton("Następna seria");
		btnNastepnaSeria.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNastepnaSeria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					controller.nastepnaSeriaClicked(Integer.parseInt(textNrSerii.getText()), Integer.parseInt(textIloscPowt.getText()), Integer.parseInt(textCiezar.getText()));
					textNrSerii.setText(""+(Integer.parseInt(textNrSerii.getText())+1));
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Nie prawidłowy format danych", "Błąd wykonania ćwiczenia", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnNastepnaSeria.setBounds(47, 213, 143, 23);
		contentPane.add(btnNastepnaSeria);
		
		JButton btnKoniec = new JButton("Koniec");
		btnKoniec.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKoniec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.koniecClicked();
			}
		});
		btnKoniec.setBounds(236, 213, 150, 23);
		contentPane.add(btnKoniec);
	}
	
	/***
	 * Metoda ustawiająca kontroler
	 * @param controller Kontroler
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/***
	 * Metoda ustawiająca numer serii
	 * @param nrSerii Numer serii
	 */
	public void setNrSerii(int nrSerii) {
		textNrSerii.setText(""+nrSerii);
	}

	/***
	 * Metoda ustawiające nazwy etykiet 
	 * @param nazwyEtykiet Nazwy etykiet
	 * @param ciezarEnabled Czy zmienić nazwę etykietę na ciężar
	 */
	public void setEtykiety(String[] nazwyEtykiet, boolean ciezarEnabled) {
		lblNrSerii.setText(nazwyEtykiet[1] + ":");
		lblIloscPowt.setText(nazwyEtykiet[2] + ":");
		lblCiezar.setText(nazwyEtykiet[3] + ":");
		
		textIloscPowt.setText("");
		textCiezar.setEnabled(!ciezarEnabled);
		textCiezar.setText(ciezarEnabled ? "0" : "");
	}
	
	private void setFrameOnCentre() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}
