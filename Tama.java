import java.io.*;
import java.util.*;


public class Tama {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
Scanner scrivi = new Scanner(System.in);
		
do {	


	System.out.println("Inserisci il nome del giocatore 1:");
	
	Giocatore g1 = new Giocatore (scrivi.nextLine());
	
	System.out.println("Inserisci il nome del giocatore 2:");
	
	Giocatore g2 = new Giocatore (scrivi.nextLine());
	
	
	
	
	
	
	
	
	
	int continua = scrivi.nextInt(); 
	
	}while (continua == 1);

}
