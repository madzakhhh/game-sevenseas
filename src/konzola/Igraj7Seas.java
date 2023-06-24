
package konzola;

import java.util.Scanner;
import logika.SevenSeas;

/**
 * @author Hata
 *
 */
public class Igraj7Seas {
	public static void main(String[] args) {
		int x,y;
		SevenSeas sevenSeas = new SevenSeas();	
		Scanner ulaz = new Scanner(System.in);
		
		while (sevenSeas.dajBrojBrodova() > 0 &&  !sevenSeas.jeLiKraj()) {
			System.out.println("Pomjeri igraca na koordinate: ");
			System.out.print("x: ");
			x = ulaz.nextInt();
			System.out.print("y: ");
			y = ulaz.nextInt();
			sevenSeas.igraj(x,y);			
			if (sevenSeas.jeLiKraj()) {
				if (sevenSeas.jeLiPobjeda()) {
					System.out.println("Pobjeda");
					break;
				}
				else 
					System.out.println("Poraz");
			}
		}
		ulaz.close();


	}

}
