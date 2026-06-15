//package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

 public class menuBigliettaio {
  
  //CAMPI
private Prenotazione prenotazione;
  
  //COSTRUTTORE
  public menuBigliettaio() {
   this.prenotazione = prenotazione;
   }
   
  //METODI
  
	//cerca prenotazione
	public  static String cercaPrenotazione(Object... args) throws IOException{
		FileReader frd = new FileReader("prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		while(!(brd.readLine().contains(args.toString()))){
			brd.readLine();
		}
		String prenotazione = brd.readLine();
		brd.close();
		frd.close();
		return prenotazione;
	}
	
	//visualizza la prenotazione
	public String visualizzaPrenotazione() throws IOException{
		return cercaPrenotazione(prenotazione).toString();
	}
}
