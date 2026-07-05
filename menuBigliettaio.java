//package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

/**
 * Gestisce le funzionalità riservate agli utenti con ruolo di bigliettaio. 
 * La classe consente la ricerca e la visualizzazione delle prenotazioni effettuate dai clienti.
 * @author
 */
 public class menuBigliettaio {
  
  //CAMPI
/** Prenotazione gestita dal menu del bigliettaio. */
private Prenotazione prenotazione;
  
  //COSTRUTTORE
 /**
 * Costruisce un nuovo oggetto menuBigliettaio.
 */
public menuBigliettaio() {
   this.prenotazione = prenotazione;
   }
   
  //METODI

	 /**
 * Ricerca una prenotazione in base ai criteri specificati.
 * @param <args> criteri utilizzati per la ricerca
 * @return la prenotazione trovata sotto forma di stringa
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
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

	 /**
 * Ricerca le prenotazioni comprese in un determinato intervallo di date e orari.
 * @param <dataInizio> data e ora di inizio dell'intervallo
 * @param <dataFine> data e ora di fine dell'intervallo
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
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
	
/**
 * Visualizza le informazioni della prenotazione individuata.
 * @return una stringa contenente i dati della prenotazione
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
}
