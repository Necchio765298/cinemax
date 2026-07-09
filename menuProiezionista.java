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

	 /** Modifica i dati di una proiezione già presente nel sistema.
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	 //modifica con gli stream
	public static void modificaProiezione(LocalDateTime orario) throws IOException{	
		
		
		try{
		if(/*se non ci sono ancora prenotazioni per la proiezione da modificare */){
			File file = new File("../data");
			File temp = File.createTempFile("pro", ".csv", file);
			File vecchio = new File("../data/proiezioni.csv");
			try{
				FileWriter fwt = new FileWriter(temp, true);
				BufferedWriter bwt = new BufferedWriter(fwt);
				FileReader frd = new FileReader("../data/prenotazioni.csv");
				BufferedReader brd = new BufferedReader(frd);
				
				Proiezione proVecchia = Proiezione.getProiezione(orario);
				Proiezione proNuova = creaProiezione();
			
				if(!(proVecchia).equals(proNuova.)){
					bwt.write(proVecchia.toString());
				}else{
					bwt.write(proNuova.toString());
				}
				bwt.newLine();
			
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
			System.out.println("ci sono prenotazioni per la proiezione da modificare");
		}catch(Exception e){
			e.getMessage();
		}	
	}

	 /** Elimina una proiezione dal sistema aggiornando il file delle proiezioni.
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	 //elimina con gli stream
	public static void eliminaProiezione() throws IOException {
		FileWriter fwt = new FileWriter("../data/proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		Console cons = System.console();
		System.out.println("Quale proiezione eliminare? Inserire i dati della proiezione per ricercarla");
		String titolo = cons.readLine("Titolo: ");
		String genere = cons.readLine("Genere: ");
		String regista = cons.readLine("Regista: ");
		String Anno = cons.readLine("Anno: ");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("Durata in minuti: ");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("Età minima: ");
		int etaMinima = Integer.parseInt(EtaMinima);
		Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra);
		
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione proiezioneDaEliminare = new Proiezione(film, dataOra, prezzoBiglietto);
		String proiezionedaeliminare = csvReader.cercaProiezione(proiezioneDaEliminare.toString());
		do{
			if(proiezionedaeliminare == brd.readLine()){
				String proiez = brd.readLine();
				proiez= " ";
				bwt.write(proiez);
			}
		}while(brd.readLine() != null);
		System.out.println(" ");
		
		bwt.close();
		fwt.close(); 
		brd.close();
		frd.close();
	}
}
