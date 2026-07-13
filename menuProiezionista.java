package cinemax;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.time.LocalDateTime;

/** Gestisce le funzionalità riservate agli utenti con ruolo di proiezionista. 
 * La classe consente l'inserimento, la modifica e l'eliminazione delle proiezioni cinematografiche.
 * @author
 */
 public class menuProiezionista {
  
	 //CAMPI
 /** Proiezione gestita dal menu del proiezionista. */
 private Proiezione proiezione;
 

  //COSTRUTTORE
/** Costruisce un nuovo oggetto menuProiezionista.
 */
public menuProiezionista() {
	this.proiezione = proiezione;
}
   
   
   //METODI

 /** Crea una nuova proiezione utilizzando i dati inseriti
 * @return la proiezione creata
 */
	public static Proiezione creaProiezione(){
		Console cons = System.console();
		System.out.println("Inserire i dati ");
		String titolo = cons.readLine("Titolo: ");
		String genere = cons.readLine("Genere: ");
		String regista = cons.readLine("Regista: ");
		String Anno = cons.readLine("Anno: ");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("Durata in minuti: ");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("Età minima: ");
		int etaMinima = Integer.parseInt(EtaMinima);
				
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra);
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione p = new Proiezione(dataOra, titolo, genere, regista, anno, durataMinuti, etaMinima, prezzoBiglietto);
	}
	   
   
  /** Aggiunge una nuova proiezione al sistema acquisendo i dati necessari e verificandone la validità prima della memorizzazione.
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
 
	 //aggiunge una proiezione
   public static void aggiungiProiezione() throws IOException{	
		try{
	    FileWriter fwt = new FileWriter("../data/proiezioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		Proiezione p = creaProiezione();
		String proiezione;
		while((proiezione = brd.readLine()) != null){
			if (proiezione.contains(p.toString()))
				System.out.println("la proiezione inserita si accavalla con una già esistente");
		}
		bwt.newLine();
		bwt.write(p.toString());
		
		bwt.close();
		fwt.close();
		return p;
		}catch(Exception e){
			System.out.println("Un criterio inserito non è nel formato valido");
		}
	}

	 /** Modifica i dati di una proiezione già presente nel sistema individuata dalla data e ora specificate
 * @param <orario> data e ora della proiezione da modificare
 * @throws <IOException> se si verifica un errore durante la gestione del file
 */
	public static void modificaProiezione(LocalDateTime orario) throws IOException{	
		try{
		if(Prenotazione.cercaPrenotazione(orario) == null){
			File file = new File("../data");
			File temp = File.createTempFile("pro", ".csv", file);
			File vecchio = new File("../data/proiezioni.csv");
			try{
				FileWriter fwt = new FileWriter(temp, true);
				BufferedWriter bwt = new BufferedWriter(fwt);
				FileReader frd = new FileReader("../data/proiezioni.csv");
				BufferedReader brd = new BufferedReader(frd);
				
				Proiezione proVecchia = Proiezione.getProiezione(orario);
				Proiezione proNuova = creaProiezione();
				String linea;
				while((line = brd.readLine())!= null){
					if(!(proVecchia).equals(proNuova.)){
						bwt.write(linea);
					}else{
						bwt.write(proNuova.toString());
					}
					bwt.newLine();
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
			vecchio.delete();
			temp.renameTo(vecchio);
		}else
			System.out.println("ci sono prenotazioni per la proiezione da modificare, pertanto non è possibile modificarla");
		}catch(Exception e){
			e.getMessage();
		}	
	}

	 /** Elimina una proiezione dal sistema individuata dalla data e ora specificate
 * @param <orario> data e ora della proiezione da eliminare
 * @throws <IOException> se si verifica un errore durante la la gestione del file
 */
	 //elimina con gli stream
	public static void eliminaProiezione(LocalDateTime orario) throws IOException {
		try{
		if(Prenotazione.cercaPrenotazione(orario) == null){
			File file = new File("../data");
			File temp = File.createTempFile("pro", ".csv", file);
			File vecchio = new File("../data/proiezioni.csv");
			try{
				FileWriter fwt = new FileWriter(temp, true);
				BufferedWriter bwt = new BufferedWriter(fwt);
				FileReader frd = new FileReader("../data/proiezioni.csv");
				BufferedReader brd = new BufferedReader(frd);
				
				Proiezione proDelete = Proiezione.getProiezione(orario);
				String linea;
				while((linea = brd.readLine())!= null){
					if(!(proDelete.toString()).equals(linea)){
						bwt.write(linea);
					}
					bwt.newLine();
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
			vecchio.delete();
			temp.renameTo(vecchio);
		}else
			System.out.println("ci sono prenotazioni per la proiezione da cancellare, pertanto non è possibile eliminarla");
		}catch(Exception e){
			e.getMessage();
		}	
	}
}
