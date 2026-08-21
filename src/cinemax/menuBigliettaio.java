//Necchio Arianna, matricola: 765298, sede: Como

package cinemax;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/** Gestisce le funzionalità riservate agli utenti con ruolo di "Bigliettaio"
 * La classe consente la visualizzazione e la ricerca delle prenotazioni.
 * @author Arianna Necchio
 * @version 2.1
 */
	public class menuBigliettaio {
  
	//CAMPI
/** Prenotazione gestita dal menu*/
	private Prenotazione prenotazione;
  
  //COSTRUTTORE
 /** Costruisce un nuovo oggetto di tipo <code>menuBigliettaio</code>. */
	public menuBigliettaio() {
	   this.prenotazione = prenotazione;
	   }
   
  //METODI	
	/** Visualizza una lista di prenotazioni.
 * @param memo lista di oggetti di tipo <code>Prenotazione</code> da visualizzare
 * @throws IOException eccezione che si solleva se si verifica un errore durante la gestione degli stream
 */
	public static void visualizzaPrenotazione(ArrayList<Prenotazione> memo) throws IOException{
		try{
		System.out.println("Prenotazioni trovate: ");
		for(Prenotazione pre : memo)
			System.out.println(pre.toString());
		}catch(Exception e){
			e.getMessage();
		}
	}
	
/** Ricerca una o più prenotazioni nel file Prenotazioni.csv in base ai criteri di ricerca specificati.
* Il metodo riceve in argomento dati di tipo <code>String</code>, <code>LocalDateTime</code> e <code>int</code>, in particolare si può ricercare una prenotazione per:
* - data e ora;
* - codice ID del cliente;
* - nome;
* - cognome;
* - titolo del film;
* - numero dei biglietti prenotati;
* - codice della prenotazione.
* @param data data e ora della prenotazione in formato yyyy-MM-dd HH-mm-ss
* @param id codice identificativo cliente
* @param nome nome
* @param cognome conome
* @param titolo titolo del film
* @param biglietti numero di biglietti prenotati
* @param codice codice prenotazione
* @return memo lista di prenotazioni trovate, sotto un oggetto di tipo <code>ArrayList</code>
* @throws IOException eccezione che si solleva se si verifica un errore durante la lettura del file
* @throws Exception eccezione che non rientra nella classe <code>IOException</code>
*/
	public static ArrayList<Prenotazione> cercaPrenotazione(LocalDateTime data, long id, String nome, String cognome, String titolo, int biglietti, String codice) throws IOException{
		Prenotazione pre = null;
		ArrayList<Prenotazione> memo = null;
		FileReader frd = null;
		BufferedReader brd= null;
		try{
			frd = new FileReader("data/prenotazioni.csv");
			brd = new BufferedReader(frd);
			String prenotazione = null;	
			memo = new ArrayList<Prenotazione>();
			while((prenotazione = brd.readLine()) != null){
				String[] dati = prenotazione.split(",");
				if(((LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).isEqual(data))
					|| (Long.parseLong(dati[1])==id)
					|| (dati[2].toLowerCase().equals(nome.toLowerCase()))
					|| (dati[3].toLowerCase().equals(cognome.toLowerCase()))
					|| (dati[4].toLowerCase().equals(titolo.toLowerCase()))
					|| (Integer.parseInt(dati[5])==biglietti)
					|| (dati[6].toUpperCase().equals(codice.toUpperCase()))){
					
					Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					Utente u = Utente.getUtente(Long.parseLong(dati[1]));
					pre = new Prenotazione(dati[6], u, p, Integer.parseInt(dati[5]));
					memo.add(pre);
				}
			}
		}catch(Exception e){
			System.out.println("Dato inserito non valido" + e.getMessage());	
		}finally{
			brd.close();
			frd.close();
		}
		return memo;
	}

/** Ricerca una o più prenotazioni nel file Prenotazioni.csv per intervallo di date.
* Il metodo riceve in argomento dati di tipo <code>LocalDateTime</code>
* @param dataInizio data e ora di inizio ricerca della prenotazione in formato yyyy-MM-dd HH-mm-ss
* @param dataFine data e ora di fine ricerca della prenotazione in formato yyyy-MM-dd HH-mm-ss
* @return memo lista di prenotazioni trovate, sotto un oggetto di tipo <code>ArrayList</code>
* @throws IOException eccezione che si solleva se si verifica un errore durante la lettura del file
* @throws Exception eccezione che non rientra nella classe <code>IOException</code>
*/
	public static ArrayList<Prenotazione> cercaPrenotazione(LocalDateTime dataInizio, LocalDateTime dataFine) throws IOException{
		FileReader frd = null;
		BufferedReader brd = null;
		String prenotazione = " ";
		ArrayList<Prenotazione> memo = new ArrayList<Prenotazione>();
		try{
			frd = new FileReader("data/prenotazioni.csv");
			brd = new BufferedReader(frd);
			Prenotazione pre = null;
			while((prenotazione = brd.readLine()) != null) {
				String[] dati= prenotazione.split(",");
				if(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isAfter(dataInizio) && LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isBefore(dataFine)){
					Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					Utente u = Utente.getUtente(Long.parseLong(dati[1]));
					pre = new Prenotazione(dati[6], u, p, Integer.parseInt(dati[5]));
					memo.add(pre);
				}
			}
		}catch(Exception e){
			System.out.println("Data inserita nel formato non corretto" + e.getMessage());
		}finally{
			brd.close();
			frd.close();
		}
		return memo;
	}

}
