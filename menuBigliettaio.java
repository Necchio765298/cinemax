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
	public  static String cercaPrenotazione(String args) throws IOException{
		FileReader frd = new FileReader("../data/prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String prenotazione = " ";
		try{
		while((prenotazione =brd.readLine()) != null){
			if(prenotazione.contains(args))
				System.out.println(prenotazione);
		}
		
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Criterio inserito non valido");
		}
		return prenotazione;
	}

	 //cerca prenotazione per intervallo di date
	public  static void cercaPrenotazione(LocalDateTime dataInizio, LocalDateTime dataFine) throws IOException{
		try{
		FileReader frd = new FileReader("../data/prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String Date;
		while((Date = brd.readLine()) != null) {
			String Date1 = Date.substring(0,19);
			LocalDateTime data = LocalDateTime.parse(Date1);
			if(data.isAfter(dataInizio) && data.isBefore(dataFine))
				System.out.println(Date.toString());
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Data inserita nel formato non corretto");
		}
	}
	

}
