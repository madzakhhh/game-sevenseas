package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logika.SevenSeas;

/**
 * @author Hata
 *
 */

public class MojPanel extends JFrame {
	int n;
	private JButton[][] dugmad;
	private JButton glavno;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel broj_brodova;
	private JLabel broj_brodova_txt;
	private JLabel prazno_polje;
	private int xPrviKlik, yPrviKlik; // koordinate prvi klik
	private Boolean pobjeda;
	
	SevenSeas sevenSeas;
	Kraj kraj;
	SljedeciLevel next_level;
	/**
	 * Konstruktor bez parametara
	 */
	public MojPanel() {
		n = 11;
		build();
	}
	
	/**
	 * Kreiraj prozor
	 */
	private void build() {
		kraj = new Kraj();
		next_level = new SljedeciLevel();
		sevenSeas = new SevenSeas();
		dugmad = new JButton[n][n];

		this.setTitle("Seven Seas");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		panel2 = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		this.add(panel2, c);

		panel1 = new JPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(panel1, c);

		panel1.setLayout(new GridLayout(n, n));
		panel2.setLayout(new GridLayout(5, 1));

	
		broj_brodova_txt= new JLabel ("Broj brodova: ");
		broj_brodova=new JLabel(String.valueOf(sevenSeas.dajBrojBrodova()));
		prazno_polje = new JLabel();
		broj_brodova.setFont(new Font("Serif", Font.PLAIN, 30));
		broj_brodova_txt.setFont(new Font("Serif", Font.PLAIN, 30));
		panel2.add(broj_brodova_txt);
		panel2.add(prazno_polje);
		panel2.add(broj_brodova);
		generisiPocetak();
		pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Generisi pocetak 
	 */
	private void generisiPocetak() {

		Dimension dim = new Dimension(40,40);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i==5 && j==5) {
					dugmad[i][j] = new JButton(postaviPolje(i, j)); //postaviBrod
					dugmad[i][j].setPreferredSize(dim);
					panel1.add(dugmad[i][j]);
				}
				else {
					dugmad[i][j] = new JButton(postaviPolje(i, j)); //postaviBrod
					dugmad[i][j].setPreferredSize(dim);
					panel1.add(dugmad[i][j]);}
			}
		}

		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (dugmad[i][j] == b) {
								int X = i; 
								int Y = j;
								sevenSeas.igraj(X, Y);
								broj_brodova.setText(String.valueOf(sevenSeas.dajBrojBrodova()));
								update();
								if (sevenSeas.jeLiKraj()) {
									if (sevenSeas.jeLiPobjeda())
										pobjeda = true;
									else
										pobjeda = false;

									if (pobjeda == false)
										kraj.prikaziKraj();
									else
										next_level.prikaziNextLevel();
									unistiProzor();
								}
							}
						}
					}
				}
			
		};

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				dugmad[i][j].addActionListener(buttonListener);
		} 
	}
	/**
	 * Postavlja ikonu koja odgovara polju iz matrice u SevenSeas iz logike
	 * @param x
	 * @param y
	 * @return
	 */
	private Icon postaviPolje(int x, int y) {
		Icon ic = new ImageIcon("slike/brodic.png");
		if (sevenSeas.dajMatricu()[x][y] == 0)
			ic = new ImageIcon("slike/prazno.jpg");
		else if (sevenSeas.dajMatricu()[x][y] == 2)
			ic = new ImageIcon("slike/glavni.png");
		else if (sevenSeas.dajMatricu()[x][y]==3)
			ic= new ImageIcon("slike/prepreka.png");
		return ic;
	}
	
	/**
	 * Updateovati polja koja nisu ostala ista u predhodnoj matrici i matrici nakon poteza
	 */
	private void update() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (sevenSeas.dajMatricu()[i][j] != sevenSeas.dajKopiju()[i][j] ) {
					dugmad[i][j].setIcon(postaviPolje(i, j));
				}
			}
		}
	}
	
	/**
	 * Prikazi prozor
	 */
	public void prikaziProzor() {
		this.setVisible(true);
	}

	/**
	 * Unisti prozor
	 */
	private void unistiProzor() {
		this.dispose();
	}

	/**
	 * get metoda,vraca je li pobjeda
	 * @return
	 */
	public Boolean jeLiPobjeda() {
		return pobjeda;
	}
	
	
	
	


}