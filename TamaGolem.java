import java.util.*;

public class TamaGolem {
	
	private String nome;
	private Giocatore padrone;
	private final int VITA = 100;
	private ArrayList <Pietra> pietre = new ArrayList <Pietra>();
	
	
	Scanner tastiera = new Scanner(System.in);	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String setPadrone (Giocatore nome) {
		
		if (nome.toString() == nome.getNome()) {
			
			this.padrone = nome;
			return "Padrone assegnato correttamente";
			}
		
		else
			
			return "Nome padrone non valido";
		
		
	public String aggiungiPietra () {
		
		int i;
		int aggiungi = 1;
		
		while (aggiungi == 1)
			
			System.out.println("Inserisci l'elemento che desideri tra:");
			System.out.println("ACQUA\tELETTRO\tBUIOSPETTRO\tPSICO\tFUOCO\tCOLEOTTERO\tVOLANTE\tERBA\tGHIACCIO");
			
				String elemento = tastiera.nextLine();
						
				int index = pietre.indexOf(elemento);
				
				if (index != -1)
					
					
					return "Pietra inserita correttamente"
				
				else 
					return "Nome elemento non corretto!"
						
						
					}
				
		}
		
		
	
		
		
		
	}
	
	
	

}
