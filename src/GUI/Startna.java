/**
 * 
 */
package GUI;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagConstraints;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Startna extends JFrame {
			MojPanel prozor;
			private JPanel panel;
			private JLabel slika;
			private JButton igraj;

			/**
			 * Konstruktor Startna
			 */
			public Startna() {
				kreirajProzor();
			}
			
			/**
			 * Kreiraj prozor
			 */
			private void kreirajProzor() {
				prozor = new MojPanel();
				this.setTitle("igra");
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				this.setLayout(new GridLayout());
				panel = new JPanel();
				this.add(panel);
				slika = new JLabel();
				igraj = new JButton("Igraj");
				panel.setLayout(new GridLayout());
				slika.setLayout(new GridBagLayout());
				igraj.setPreferredSize(new Dimension(100, 50));
				slika.setIcon(new ImageIcon("slike/Startna.jpg"));
				panel.add(slika);
				GridBagConstraints c = new GridBagConstraints();
				JLabel l = new JLabel();
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
				pack();
				this.setLocationRelativeTo(null);

				igraj.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						prozor.prikaziProzor();
						unistiProzor();
					}
				});
				
			}

			/**
			 * Prikazi startni prozor
			 */
			public void prikaziStartni() {
				this.setVisible(true);
			}

			/**
			 * Unisti prozor
			 */
			private void unistiProzor() {
				this.dispose();
			}
			
			
			
		}
