import java.util.*;

public class Giocatore {
	
	private String nome ;
	private ArrayList <TamaGolem> tamaGolem = new ArrayList <TamaGolem>();
	
	
	public Giocatore (String nome) {
		
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public boolean haiPerso (TamaGolem tamagolem) {
		
		if (tamaGolem.size() == 0)
			return true;
		
		else 
			return false;
		
	}
	
	
	public void aggiungiTama (String nome , String padrone) {
		
		
	}
	
	
	
	
	
	
}	
	
