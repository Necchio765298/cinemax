package cinemax;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

/** Gestisce le operazioni di lettura e scrittura dei file csvcutilizzati dall'applicazione per la memorizzazione dei dati.
 * La classe permette di ricercare le proiezioni e di registrare nuovi utenti, interagendo con i file csv del progetto.
 * @author
 */

public class csvReader{
	//campi
	/** Proiezione utilizzata per le operazioni di ricerca. */
	private static String proiezione;
	/** Utente da registrare nel file csv. */
	private Utente utente;
	
	//costruttori
	/** Costruisce un nuovo oggetto CSVReader per la gestione delle operazioni relative alle proiezioni.
 * @param <proiezione> proiezione da ricercare
 */
	public csvReader(String proiezione){
		this.proiezione = proiezione;
	}

	/** Costruisce un nuovo oggetto CSVReader per la gestione delle operazioni relative agli utenti.
 * @param <utente> utente da registrare
 */
	public csvReader(Utente utente){
		this.utente = utente;
	}
	
	//metodi
	

	
	
	
}
