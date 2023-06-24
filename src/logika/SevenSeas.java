package logika;

import java.util.Random;

/**
 * @author Hata
 *
 */
public class SevenSeas {
	private int n;
	private int broj_brodova=0;
	private int[][] matrica;
	private int[][] prijePoteza;
	private int []  random_niz;
	private int trenutni_X,trenutni_Y;
	Boolean pogresan_potez = true;
	Boolean kraj = false;
	Boolean pobjeda = false;
	
	/**
	 * Konstruktor
	 */
	public SevenSeas() {
		n=11;
		matrica = new int[n][n];
		prijePoteza = new int[n][n];
		broj_brodova=0;
		random_niz=new int [] {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3};
		trenutni_X=5;
		trenutni_Y=5;
		generisiRandomMatricu();
	}

	/**
	 * Funkcija za matricu na pocetaku igre
	 */
	private void generisiRandomMatricu() {
		int rand = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i==5 && j==5) {
					matrica[i][j]=2;
				}
				else {
					rand = getRandom(random_niz);
					if (rand==1) broj_brodova++;
					matrica[i][j] = rand;
				}
				
			}
		}		
		ispisiMatricu();
	}
	/**
	 * random broj iz niza
	 * @param array
	 * @return
	 */
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	/**
	 * Get metoda, metoda koja vraca trenutno stanje 
	 * @return
	 */

	public int[][] dajMatricu() {
		return matrica;
	}

	/**
	 * Get metoda, metoda koja vraca matricu prije poteza
	 * @return
	 */
	
	public int[][] dajKopiju() {
		return prijePoteza;
	}
	
	/**
	 * Get metoda, metoda koja vraca broj bodova
	 * @return
	 */
	public int dajBrojBrodova() {
		return broj_brodova;
	}
	/**
	 * Get metoda, metoda provjerava da li je kraj
	 * @return
	 */
	public Boolean jeLiKraj() {
		return kraj;
	}

	/**
	 * Get metoda, provjerava li je pobjeda
	 * @return
	 */
	public Boolean jeLiPobjeda() {
		return pobjeda;
	}
	

	/**
	 * Ispis trenutnog stanja
	 */

	public void ispisiMatricu() {
		System.out.print("    ");
		for(int i=0;i<n;i++) 
			System.out.format("%4d", i);
		System.out.println();
		
		for (int i = 0; i < n; i++) {
			System.out.format("%4d",i );
			for (int j = 0; j < n; j++) {
				System.out.format("%4d", matrica[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * ispisi predhodnu
	 */
	public void ispisiKopiju() {
		System.out.print("   ");
		for(int i=0;i<n;i++) 
			System.out.print(i+" ");
		System.out.println();
		System.out.print("   ");
		for(int i=0;i<n;i++) 
			System.out.print(". ");
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i+ "- ");
			for (int j = 0; j < n; j++) {
				System.out.print(prijePoteza[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * Kopija matrice prije odigranog poteza
	 */

	private void napraviKopiju() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				prijePoteza[i][j] = matrica[i][j];
			}
		}
	}
	/**
	 * Provjeri da li je korak ispravan, moguce se kretati samo za jedno mjesto
	 * @param x
	 * @param y
	 */
	private boolean ispravanKorak(int x,int y) {
		if(Math.abs(trenutni_X-x)>1 || Math.abs(trenutni_Y-y)>1  ) {
			return false;
		}
		return true;
	}
	/**
	 * Pomicanje igraca
	 */
	private void zamijeni(int x,int y) {
		matrica[trenutni_X][trenutni_Y]=0;
		trenutni_X=x;
		trenutni_Y=y;	
		matrica[trenutni_X][trenutni_Y]=2;
		
	}
	/**
	 * Igrac puca
	 * @param duzina
	 * @return
	 */
	private boolean pucaj(int duzina) {
		if ((trenutni_X+duzina<11) && matrica[(trenutni_X+duzina)%11][trenutni_Y]==1 )
		{ 
			if ((trenutni_X-duzina>=0) && matrica[(trenutni_X-duzina+11)%11][trenutni_Y]==1 ) {
				matrica[(trenutni_X-duzina)%11][trenutni_Y]=0;
				broj_brodova-=2;
			}
			else 
				broj_brodova--;
			matrica[(trenutni_X+duzina)%11][trenutni_Y]=0;
			return true;
			
		}
		else if ((trenutni_X-duzina>=0) && matrica[(trenutni_X-duzina)%11][trenutni_Y]==1 ) {
			broj_brodova--;			
			matrica[(trenutni_X-duzina)%11][trenutni_Y]=0;
			return true;
			
		}
		else if((trenutni_Y-duzina>=0) && (trenutni_X+duzina<11) && matrica[(trenutni_X+duzina)%11][(trenutni_Y-duzina)%11]==1  ) {
			if ( (trenutni_X-duzina>=0) && (trenutni_Y+duzina<11) && matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]==1){
				broj_brodova-=2;
				matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]=0;
				}
			else 
				broj_brodova--;
			matrica[(trenutni_X+duzina)%11][(trenutni_Y-duzina)%11]=0;
			return true;
			
		}
		else if (trenutni_X-duzina>=0 && trenutni_Y+duzina<11 && matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]==1 ) {
			broj_brodova--;
			matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]=0;
			return true;
		}	
		else if( (trenutni_Y-duzina>=0) && matrica[trenutni_X][(trenutni_Y-duzina)%11]==1) {
			if ((trenutni_Y+duzina<11) && matrica[trenutni_X][(trenutni_Y+duzina)%11]==1) {
				broj_brodova-=2;
				matrica[trenutni_X][(trenutni_Y+duzina)%11]=0;
			}
			else 
				broj_brodova--;
			matrica[trenutni_X][(trenutni_Y-duzina)%11]=0;
			return true;
				
		}
		else if ((trenutni_Y+duzina<11) && matrica[trenutni_X][(trenutni_Y+duzina)%11]==1) {
			broj_brodova--;
			matrica[trenutni_X][(trenutni_Y+duzina)%11]=0;
			return true;
		}
		else if ( (trenutni_X-duzina>=0) && (trenutni_Y-duzina>=0)  && matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]==1  ) {
			if ((trenutni_X-duzina>=0)&& (trenutni_Y+duzina<11) && matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]==1)
				{
				broj_brodova-=2;
				matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]=0;
				}
			else 
				broj_brodova--;
			matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]=0;
			return true;
			
		}
		else if ((trenutni_X-duzina>=0) && (trenutni_Y+duzina<11) && matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]==1  ) {
			broj_brodova--;			
			matrica[(trenutni_X-duzina)%11][(trenutni_Y+duzina)%11]=0;
			return true;
		}
		else if ((trenutni_X+duzina<11) && (trenutni_Y+duzina<11) && matrica[(trenutni_X+duzina)%11][(trenutni_Y+duzina)%11]==1) {
			if ((trenutni_X-duzina>=0) && (trenutni_Y-duzina>=0) && matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]==1 ) {
				broj_brodova-=2;
				matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]=0;
			}
			else
				broj_brodova--;
			matrica[(trenutni_X+duzina)%11][(trenutni_Y+duzina)%11]=0;
			return true;
		}
		else if ((trenutni_X-duzina>=0) && (trenutni_Y-duzina>=0) && matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]==1 ) {
			broj_brodova--;
			matrica[(trenutni_X-duzina)%11][(trenutni_Y-duzina)%11]=0;
			return true;
		}
		return false;
	}
	
	/**
	 * Kretanje brodova po najblizem putu do igraca
	 * @param x
	 * @param y
	 */
	
	private void pomjeri_brodove(int x,int y) {
		napraviKopiju();
		zamijeni(x,y);
		for (int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				if (prijePoteza[i][j]==1) {
					if (i<trenutni_X && j<trenutni_Y) {
						matrica[i][j]=0;
						if (i+1==trenutni_X && trenutni_Y==j+1) kraj=true;
						if (matrica[i+1][j+1]!=0) broj_brodova--; //ako ono na sta se pomjera brod je prepreka,pojede brod
						else matrica[i+1][j+1]=1;	
					}
					else if (i<trenutni_X && j>trenutni_Y) {
						matrica[i][j]=0;
						if (i+1==trenutni_X && j-1==trenutni_Y) kraj=true;
						if (matrica[i+1][j-1]!=0) broj_brodova--;
						else matrica[i+1][j-1]=1;
					}
					else if (i>trenutni_X && j<trenutni_Y) {
						matrica[i][j]=0;
						if (i-1==trenutni_X && j+1==trenutni_Y) kraj=true;
						if (matrica[i-1][j+1]!=0) broj_brodova--;
						else matrica[i-1][j+1]=1;
					}
					else if (i>trenutni_X && j>trenutni_Y) {
						matrica[i][j]=0;
						if (i-1==trenutni_X && j-1==trenutni_Y) kraj=true;
						if (matrica[i-1][j-1]!=0) broj_brodova--;
						else matrica[i-1][j-1]=1;
					}
					else if (i==trenutni_X) {
						matrica[i][j]=0;
						if (j<trenutni_Y) {
							if (j+1==trenutni_Y) kraj=true;
							if (matrica[i][j+1]!=0 ) broj_brodova--;
							else matrica[i][j+1]=1;
						}
						else if (j>trenutni_X) {
							if (j-1==trenutni_Y) kraj=true;
							if (matrica[i][j-1]!=0) broj_brodova--;
							else matrica[i][j-1]=1;
						}
						else {
							kraj=true;
						}
					}
					else if (j==trenutni_Y) {
						matrica[i][j]=0;
						if (i<trenutni_X) {
							if (i+1==trenutni_X) kraj=true;
							if (matrica[i+1][j]!=0) broj_brodova--;
							else matrica[i+1][j]=1;
						}
						else if (i>trenutni_X) {
							if (i-1==trenutni_X) kraj=true;
							if (matrica[i-1][j]!=0) broj_brodova--;
							else matrica[i-1][j]=1;
						}
						else {
							kraj=true;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Algoritam igrice
	 * @param x
	 * @param y
	 */
	public void igraj(int x, int y) {
		napraviKopiju();
		if (broj_brodova>0 && !kraj) {
			if(ispravanKorak (x,y)) {
				if (x==trenutni_X && y==trenutni_Y) {
					for (int duzina=1;duzina<4;duzina++)
						if (pucaj(duzina)) break;
				}
				else {					
					pomjeri_brodove(x,y);}											
			}
			System.out.println();
			ispisiMatricu();		
		}
		if (broj_brodova == 0) {
			pobjeda = true;
			kraj = true;
		}
		else {
			if (kraj)
				pobjeda=false;
		}
		
	}
	
}
