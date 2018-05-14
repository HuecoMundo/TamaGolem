package il.tama.golem;

import java.util.*;

import il.tama.golem.Pietra.Elementi;
/**
 * Questa classe crea l'equilibrio del mondo
 * @author Axel Mastroianni Matteo Terzi Moussa
 * @version 1.0
 *
 */
public class Equilibrio {
	public static final int MASSIMI_ELEMENTI=10;
	public static final int MASSIMA_POTENZA_ELEMENTO=10;
	private String nome="";
	private int numeroElementi=0;
	private Random r=new Random();
	private int[][] matriceEquilibrio;
	/**
	 * il costruttore da un nome all'Equilibrio inoltre prepara una matrice
	 * triangolare inferiore(zeri sotto la diagonale principale) con numeri pari
	 * necessari perché successivamente si dividerà per due
	 * @param nome
	 * @param numeroElementi
	 */
	public Equilibrio(String nome, int numeroElementi) {
		if(!nome.equals(""))
			this.nome=nome;
		if(numeroElementi>3) {
			if(numeroElementi<=MASSIMI_ELEMENTI) {
				this.numeroElementi=numeroElementi;
				matriceEquilibrio=new int[numeroElementi][numeroElementi];
				for(int i=0;i<numeroElementi;i++) {
					for(int j=i;j<numeroElementi;j++) {
						if(j==i)//interazione tra stessi elementi nulla
							matriceEquilibrio[i][j]=0;
						else {
							int numeroPari=Math.abs(r.nextInt()%MASSIMA_POTENZA_ELEMENTO)+1;
							if(numeroPari%2==0)
								matriceEquilibrio[i][j]=numeroPari;
							else
								j--;
						}
					}
				}
			}
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(!nome.equals(""))
			this.nome = nome;
	}
	public int getNumeroElementi() {
		return numeroElementi;
	}
	public void setNumeroElementi(int numeroElementi) {
		if(numeroElementi>=3)
			this.numeroElementi = numeroElementi;
	}
	
	/**
	 * stampa l'equilibrio
	 */
	public void stampaEquilibrio() {
		System.out.println("ACQUA\t ELETTRO\t ERBA\t FUOCO\t PSICO\t BUIO\t VOLANTE\t SPETTRO\t COLEOTTERO"
				+ " \tGHIACCIO");
		for(int i=0;i<numeroElementi;i++) {
			System.out.print(Elementi.values()[i]+"\t");
			for(int j=0;j<numeroElementi;j++)
				System.out.print(matriceEquilibrio[i][j]+"\t");
			System.out.println();
		}
	}
	/**
	 * 
	 * @param riga
	 * @return somma la somma degli elementi di una riga
	 */
	public int sommaRiga(int[] riga) {
		int somma=0;
		for(int i=0;i<riga.length;i++)
			somma+=riga[i];
		return somma;
	}
	/**
	 * per creare l'equilibrio mi servo delle somme parziali sia sulle righe che sulle colonne
	 * sommando volta per volta i vari elementi sulla riga e sulla colonna
	 * la condizione j>i è necessaria per riempire gli zeri sotto la diagonale principale
	 * inoltre se j non fosse diverso dal numeroElementi-1(colonne) in quella posizione avrei
	 * uno zero
	 * Difetto: nell'ultima riga sono presenti alcuni numeri negativi se cresce il numero
	 * di elementi con cui sto giocando, inoltre crescono anche le potenze nella parte
	 * sotto la diagonale principale, tuttavia non siamo riusciti a trovare un modo alternativo
	 * di creare l'equilibrio e questo è il migliore che ci è venuto in mente
	 * Nota creativa: l'ultima riga potrebbe essere interpretata come l'elemento che subisce
	 * quasi sempre ma che quando attacca fa grandi danni(ad esempio la mossa Ghigliottina dei
	 * Pokemon oppure la classe dei maghi nei giochi MMORPG)
	 * Ultima nota creativa: sebbene ci siano valori molto grandi nella matrice sotto la 
	 * diagonale principale questi sono "bilanciati" dal fatto che non coprono l'intera riga
	 * ma solo metà quindi non sono forti contro tutti ma solo contro la meta!
	 */
	public void creaEquilibrio() {
		for(int i=0;i<numeroElementi;i++) {
			int sommaRiga=sommaRiga(matriceEquilibrio[i]);
			int sommaParzialeRiga=0;
			int sommaParzialeColonna=0;
			for(int j=0;j<numeroElementi;j++) {
				if(j!=numeroElementi-1) {
					sommaParzialeRiga+=matriceEquilibrio[i][j];
					if(j>i && j!=i) 
						matriceEquilibrio[j][i]=(sommaRiga-sommaParzialeRiga)/2;
					sommaParzialeColonna+=matriceEquilibrio[j][i];
				}
				else
					matriceEquilibrio[j][i]=sommaRiga-sommaParzialeColonna;
			}
		}
	}
	/**
	 * ritorna la vittoria o meno di un elemento andando a prendere le coordinate nella matrice
	 * @param elemento1
	 * @param elemento2
	 * @return true or false
	 */
	
	public boolean chiVince(int elemento1, int elemento2) {
		if(matriceEquilibrio[elemento1][elemento2]>matriceEquilibrio[elemento2][elemento1])
			return true; //vince il primo
		return false; //vince il secondo
	}
	/**
	 * la coordinata (riga,colonna) mi indica la potenza di un elemento
	 * @param riga
	 * @param colonna
	 * @return l'elemento alla posizione (riga,colonna) della matrice
	 */
	public int setPotenzaPietra(int riga, int colonna) {
		return matriceEquilibrio[riga][colonna];
	}
	
	

}
