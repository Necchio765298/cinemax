package cinemax;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

/** Gestisce le funzionalità riservate agli utenti con ruolo di bigliettaio 
 * La classe consente la ricerca e la visualizzazione delle prenotazioni effettuate dai clienti
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
	public class menuBigliettaio {
  
	//CAMPI
/** Prenotazione gestita dal menu del bigliettaio. */
	private Prenotazione prenotazione;
  
  //COSTRUTTORE
 /** Costruisce un nuovo oggetto menuBigliettaio.
 */
	public menuBigliettaio() {
	   this.prenotazione = prenotazione;
	   }
   
  //METODI	
/** Visualizza le informazioni relative a una prenotazione.
 * @throws <IOException> se si verifica un errore durante la gestione del file
 */
	public static void visualizzaPrenotazione(){
		}
	
/** Ricerca una prenotazione in base ai criteri specificati.
 * @param <arg> criteri utilizzati per la ricerca
 * @return la prenotazione trovata sotto forma di stringa
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	
	public static Prenotazione cercaPrenotazione(Object arg) throws IOException{
		FileReader frd = new FileReader("data/prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String prenotazione = null;
		try{
			Prenotazione pre = null;	
			while((prenotazione = brd.readLine()) != null){
				String[] dati = prenotazione.split(",");
				if(prenotazione.contains(arg.toString())){
					Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0]));
					Utente u = Utente.getUtente((long) Integer.parseInt(dati[1]));
					pre = new Prenotazione(u, p, Integer.parseInt(dati[5]));
					return pre;
				}
			}
			brd.close();
			frd.close();
			
		}catch(Exception e){
			System.out.println("Dato inserito non valido" + e.getMessage());	
		}
		return null;
	}

/** Ricerca le prenotazioni comprese in un determinato intervallo di date e orari.
 * @param <dataInizio> data e ora di inizio dell'intervallo
 * @param <dataFine> data e ora di fine dell'intervallo
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	
	public static Prenotazione cercaPrenotazione(LocalDateTime dataInizio, LocalDateTime dataFine) throws IOException{
		FileReader frd = new FileReader("data/prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String prenotazione = " ";
		try{
			Prenotazione pre = null;
			while((prenotazione = brd.readLine()) != null) {
				String[] dati= prenotazione.split(",");
				if(LocalDateTime.parse(dati[0]).isAfter(dataInizio) && LocalDateTime.parse(dati[0]).isBefore(dataFine)){
					Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0]));
					Utente u = Utente.getUtente((long) Integer.parseInt(dati[1]));
					pre = new Prenotazione(u, p, Integer.parseInt(dati[5]));
				}
			}
			brd.close();
			frd.close();
			return pre;
		}catch(Exception e){
			System.out.println("Data inserita nel formato non corretto");
			return null;
		}
	}

}
