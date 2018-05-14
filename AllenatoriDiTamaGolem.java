package il.tama.golem;

import il.tama.golem.Pietra.Elementi;
import it.unibs.fp.mylib.InputDati;

public class AllenatoriDiTamaGolem {
	/**
	 * crea il giocatore
	 * @return Giocatore
	 */
	public static Giocatore creaGiocatore() {
		String nome=InputDati.leggiStringaNonVuota("Inserisci il tuo nome: ");
		return new Giocatore(nome);
	}
	/**
	 * crea il TamaGolem
	 * @return TamaGolem
	 */
	public static TamaGolem creaTamaGolem() {
		String nome=InputDati.leggiStringaNonVuota("Inserisci il nome del TamaGolem: ");
		return new TamaGolem(nome, TamaGolem.VITA_MASSIMA);
	}
	/**
	 * stampa gli elementi disponibili in base al numero di quelli con cui si è scelto di 
	 * giocare
	 * @param numeroElementi
	 */
	public static void stampaElementi(int numeroElementi) {
		Elementi element=null;
		for(int i=0;i<numeroElementi;i++)
			System.out.print(element.values()[i]+"\t");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stud
		String risposta="";
		int numeroElementi=0;
		int indicePietre=0;//numero necessario per scagliare le pietre in ciclo
		System.out.println("Benvenuti nel mondo dei TamaGolem(Non abbiamo copiato dai"
				+ " Pokémon...forse...)");
		while(!risposta.equalsIgnoreCase("no")){
			//creo i giocatori
			System.out.println("Hola, intrepidi allenatori, inserite i vostri nomi: ");
			System.out.println("Giocatore 1: ");
			Giocatore g1=creaGiocatore();
			System.out.println("Giocatore 2: ");
			Giocatore g2=creaGiocatore();
			//stabilisco con quanti elementi giocare
			numeroElementi=InputDati.leggiIntero("Con quanti elementi volete giocare? ");
			//creo l'oggetto battaglia: ogni battaglia avrà un nuovo equilibrio
			Battaglia scontro=new Battaglia(g1,g2,numeroElementi);
			//mostra le pietre disponibili in modo che il giocatore possa scegliere
			System.out.println("Pietre disponibili: ");
			stampaElementi(numeroElementi);  System.out.println();
			//il giocatore1 crea il suo TamaGolem
			System.out.println("Giocatore 1, crea i tuoi TamaGolem, puoi crearne "+scontro.getNumeroTamaGolem());
			for(int i=0;i<scontro.getNumeroTamaGolem();i++) {
				String nome=InputDati.leggiStringaNonVuota("Inserisci il nome del TamGolem: ");
				g1.aggiungiTamaGolem(nome);
				//assegna le pietre al TamaGolem
				for(int j=0;j<scontro.getNumeroPietre();j++) {
					String nomePietra=InputDati.leggiStringaNonVuota("Inserisci il nome della "
							+ "pietra che gli vuoi assegnare: ");
					g1.getTamaGolem(i).aggiungiPietra(nomePietra);
				}
			}
			//stessa cosa anche per il giocatore 2
			System.out.println("Pietre disponibili: ");
			stampaElementi(numeroElementi);
			System.out.println("Giocatore 2, crea i tuoi TamaGolem");
			for(int i=0;i<scontro.getNumeroTamaGolem();i++) {
				String nome=InputDati.leggiStringaNonVuota("Inserisci il nome del TamaGolem: ");
				g2.aggiungiTamaGolem(nome);
				for(int j=0;j<scontro.getNumeroPietre();j++) {
					String nomePietra=InputDati.leggiStringaNonVuota("Che pietra gli vuoi "
							+ "asseganre? ");
					g2.getTamaGolem(i).aggiungiPietra(nomePietra);
				}
			}
			System.out.println("Che il combattimento abbia inizio! Attenzione: se due "
					+ "TamaGolem sono eliminati insieme è perché hanno le stesse pietre");
			//inizia il combattimento: gli allenatori evocano i loro primi due TamaGolem
			TamaGolem tama1=g1.evocaTamaGolem();
			TamaGolem tama2=g2.evocaTamaGolem();
			while(!(scontro.sconfittaGicoatore1() || scontro.sconfittaGiocatore2())) {
				//controllo che due TamaGolem non abbiano le stesse pietre
				if(scontro.stessePietre(tama1.getPietre(), tama2.getPietre())) {
					g1.rimuoviTamaGolem();
					g2.rimuoviTamaGolem();
					if(!scontro.sconfittaGicoatore1())
						tama1=g1.evocaTamaGolem();
					else
						break;
					if(!scontro.sconfittaGiocatore2())
						tama2=g2.evocaTamaGolem();
					else
						break;
					continue; //se hanno le stesse pietre ma hanno ancora TamaGolem allora continua lo scontro
				}
				scontro.battlePhase(indicePietre,tama1,tama2);
				//il giocatore1 ha perso il TamaGolem? Se sì valuta che non sia stato sconfitto
				if(scontro.endPhaseGiocatore1() && !scontro.sconfittaGicoatore1()) {
					//altrimenti non può evocare il TamaGolem
					tama1=g1.evocaTamaGolem();
					System.out.println(g1.getNome()+" evoca "+g1.getTamaGolem(0).getNome());
					System.out.println("Gli restano ancora: "+g1.getNumeroTamaGolem());
				}
				//idem per il giocatore2
				else if(scontro.endPhaseGiocatore2() && !scontro.sconfittaGiocatore2()) {
					tama2=g2.evocaTamaGolem();
					System.out.println(g2.getNome()+" evoca "+g2.getTamaGolem(0).getNome());
					System.out.println("Gli restano ancora: "+g2.getNumeroTamaGolem());
				}
				else {
					/*
					 * se un giocatore è stato sconfitto andare a cercare il TamaGolem all'indice
					 * 0 causa una nullPtr exception, questo if si propone di evitarlo
					 */
					if(!scontro.sconfittaGicoatore1())
						System.out.println("Situazione attuale "+ g1.getTamaGolem(0).getNome()+
								": "+g1.getTamaGolem(0).getVita());
					else
						break;
					if(!scontro.sconfittaGiocatore2())
						System.out.println("Situazione attuale "+g2.getTamaGolem(0).getNome()
								+": "+g2.getTamaGolem(0).getVita());
					else
						break;
				}
				//incrementa indicePietre solo se non hanno raggiunto il numero di pietre scelte
				if(indicePietre!=scontro.getNumeroPietre()-1)
					indicePietre++;
				else
					indicePietre=0; //altrimenti va a 0 e riparte il ciclo delle pietre
			}
			/*
			 * si stabilisce il vincitore oppure se la sfida termina con un pareggio
			 * nel caso i due giocatori abbiano un solo TamaGolem con le stesse pietre
			 */
			if(g1.haiPerso() && !g2.haiPerso())
				System.out.println("Ha vinto la battaglia: "+g2.getNome());
			else if(g2.haiPerso() && !g1.haiPerso())
				System.out.println("Ha vinto la battaglia: "+g1.getNome());
			else
				System.out.println("Pareggio");
			System.out.println("Ecco l'equilibrio della partita: ");
			scontro.getTabellone().stampaEquilibrio();//mostro l'equilibrio
			risposta=InputDati.leggiStringaNonVuota("Volete giocare un'altra partita? ");
		}

	}

}
