//package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

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

	 //cerca prenotazione per intervallo di date
	public  static void cercaPrenotazione(LocalDateTime dataInizio, LocalDateTime dataFine) throws IOException{
		FileReader frd = new FileReader("prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String Date;
		while(brd.readLine() != null) {
			Date = brd.readLine().substring(0,10);
			LocalDateTime data = LocalDateTime.parse(Date);
			if(data.isAfter(dataInizio) && data.isBefore(dataFine))
				System.out.println(data.toString());
		}
		brd.close();
		frd.close();
	}
	
	//visualizza la prenotazione 
	public String visualizzaPrenotazione() throws IOException{
		return cercaPrenotazione().toString();
	}
}
