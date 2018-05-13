
public class Pietra {
	private Elementi[] arrayElementi;
	
	public Elementi[] getArrayElementi() {
		return arrayElementi;
	}

	public void setArrayElementi(Elementi[] arrayElementi) {
		this.arrayElementi = arrayElementi;
	}
	
	public enum Elementi {
		ACQUA("ACQUA", 10),
		ELETTRO("ELETTRO", 4),
		BUIOSPETTRO("BUOISPETTRO", 5),
		PSICO("PSICO", 3),
		FUOCO("FUOCO", 9),
		COLEOTTERO("COLEOTTERO", 10),
		VOLANTE("VOLANTE", 2),
		ERBA("ERBA",6),
		GHIACCIO("GHIACCIO", 9);	
		
		private String tipo;
		private int potenza;
		
		Elementi(String tipo, int potenza) {
			this.tipo=tipo;
			this.potenza=potenza;
		}
		
		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public int getPotenza() {
			return potenza;
		}

		public void setPotenza(int potenza) {
			this.potenza = potenza;
		}
		
	}
	
	public boolean presente(Elementi elemento){
		int presente=0;
		arrayElementi=(Elementi.values());
		for(int i=0;i<arrayElementi.length;i++) {
			System.out.println(arrayElementi[i]);
			if(arrayElementi[i].equals(elemento)) {
				presente++;
			}
			
		}
		if (presente!=0) return true;
		return false;
	}
	
	

	
	
	
}
