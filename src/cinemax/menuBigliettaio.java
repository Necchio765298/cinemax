package cinemax;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	public static Prenotazione cercaPrenotazione(LocalDateTime data, long id, String nome, String cognome, String titolo, int biglietti, String codice) throws IOException{
		Prenotazione pre = null;
		try{
			FileReader frd = new FileReader("data/prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			String prenotazione = null;	
			while((prenotazione = brd.readLine()) != null){
				String[] dati = prenotazione.split(",");
				if(((LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).isEqual(data))
					|| (Long.parseLong(dati[1])==id)
					|| (dati[2].toLowerCase().equals(nome.toLowerCase()))
					|| (dati[3].toLowerCase().equals(cognome.toLowerCase()))
					|| (dati[4].toLowerCase().equals(titolo.toLowerCase()))
					|| (Integer.parseInt(dati[5])==biglietti)
					|| (dati[6].toUpperCase().equals(codice.toUpperCase()))){
					
					System.out.println(dati[0].toString()+" "+dati[1]+" "+dati[2]+" "+dati[3]+" "
					+" "+dati[4]+" "+dati[5]+" "+dati[6]);
					
					System.out.println("criterio trovato");
					Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					Utente u = Utente.getUtente(Long.parseLong(dati[1]));
					pre = new Prenotazione(dati[6], u, p, Integer.parseInt(dati[5]));
					System.out.println("Prenotazione trovata: "+ pre.toStringEsistente());
					break;
				}
			}
			brd.close();
			frd.close();
			
		}catch(Exception e){
			System.out.println("Dato inserito non valido" + e.getMessage());	
		}
		return pre;
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
