package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Kraj extends JFrame {
	private JPanel panel;
	private JLabel slika;
	private JButton igraj;
	private JButton exit;

	MojPanel prozor;

	/**
	 * Konstruktor
	 */
	public Kraj() {
		build();
	}

	/**
	 * Kreiraj prozor za kraj igre
	 */
	private void build() {
		this.setTitle("igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());

		panel = new JPanel();
		this.add(panel);

		slika = new JLabel();
		igraj = new JButton("Igraj opet");
		exit = new JButton("Izlaz");

		panel.setLayout(new GridLayout());
		slika.setLayout(new GridBagLayout());

		igraj.setPreferredSize(new Dimension(100, 50));
		exit.setPreferredSize(new Dimension(100, 50));

		slika.setIcon(new ImageIcon("Slike/kraj.jpg"));
		panel.add(slika);

		GridBagConstraints c = new GridBagConstraints();
		JLabel l = new JLabel();
		l.setText("Game over! ");
		l.setFont(new Font("Serif", Font.PLAIN, 100));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		slika.add(l, c);
		c.weightx = 0;
		c.weighty = 0;

		c.gridx = 0;
		c.gridy = 1;
		slika.add(igraj, c);

		c.gridx = 0;
		c.gridy = 2;
		slika.add(exit, c);

		pack();
		this.setLocationRelativeTo(null);

		igraj.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				prozor = new MojPanel();
				prozor.prikaziProzor();
				unistiProzor();
			}
		});
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				unistiProzor();
				System.exit(0);
			}
		});
	}
	/**
	 * Prikazi prozor za kraj igre
	 */
	public void prikaziKraj() {
		this.setVisible(true);
	}

	/**
	 * Unisti prozor
	 */
	private void unistiProzor() {
		this.dispose();
	}


}
