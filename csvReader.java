//package bin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Gestisce le operazioni di lettura e scrittura dei file csvcutilizzati dall'applicazione per la memorizzazione dei dati.
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
	/**
 * Costruisce un nuovo oggetto CSVReader per la gestione delle operazioni relative alle proiezioni.
 * @param <proiezione> proiezione da ricercare
 */
	public csvReader(String proiezione){
		this.proiezione = proiezione;
	}

	/**
 * Costruisce un nuovo oggetto CSVReader per la gestione delle operazioni relative agli utenti.
 * @param <utente> utente da registrare
 */
	public csvReader(Utente utente){
		this.utente = utente;
	}
	
	//metodi
	
	/**
 * Ricerca una o più proiezioni nel file csv in base ai criteri di ricerca specificati.
 * @param <args> criteri utilizzati per la ricerca
 * @return le proiezioni trovate sotto forma di stringa
 * @throws IOException se si verifica un errore durante la lettura del file
 */
	public static String cercaProiezione(String args) throws IOException{
		try{
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while((proiezione = brd.readLine()) != null){
			if(proiezione.contains(args))
				System.out.println(proiezione);
			
		}
		brd.close();
		frd.close();
		
		}catch(Exception e){
		System.out.println("Criterio inserito non valido");
		}
		return proiezione;
	}

	/**
 * Ricerca le proiezioni comprese in un determinato intervallo di date.
 * @param <dataInizio> data iniziale dell'intervallo
 * @param <dataFine> data finale dell'intervallo
 * @throws IOException se si verifica un errore durante la lettura del file
 */
	public  static void cercaProiezione(LocalDate dataInizio, LocalDate dataFine) throws IOException{
		try{
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String Date;
		while((Date = brd.readLine()) != null) {
			String Date1 = Date.substring(1,11);
			LocalDate data = LocalDate.parse(Date1);
			if(data.isAfter(dataInizio) && data.isBefore(dataFine))
				System.out.println(Date.toString());
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Formato della data inserita non corretto");
		}
	}
	
	
	/**
 * Registra un nuovo utente nel file csv.
 * @param <utente> utente da registrare
 * @throws IOException se si verifica un errore durante la scrittura del file
 */
	public static void registraCliente(Utente utente) throws IOException{
		try{
		FileWriter fwt = new FileWriter("../data/utenti.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		
		bwt.write(utente.toString());
		bwt.newLine();
		bwt.close();
		fwt.close();
		}catch(Exception e){
			System.out.println("Utente non opportunamente registrato");
		}
	}
}
