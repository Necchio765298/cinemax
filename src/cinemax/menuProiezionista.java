//Necchio Arianna, matricola: 765298, sede: Como


package cinemax;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/** Gestisce le funzionalità riservate agli utenti con ruolo di "Proiezionista"
 * La classe consente la creazione, la modifica e l'eliminazione delle prenotazioni.
 * @author Arianna Necchio
 * @version 2.1
 */
 public class menuProiezionista {
  

 /** Proiezione gestita dal menu */
	private Proiezione proiezione;
 

/** Costruisce un nuovo oggetto di tipo <code>menuProiezionista</code>.*/
	public menuProiezionista() {
		this.proiezione = proiezione;
	}


 /** Crea una nuovo oggetto di tipo <code>Proiezione</code> chiedendo all'utente di compilare i dati richiesti, relativi alla proiezione
 * @return proiezione nuova proiezione 
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
				
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione p = new Proiezione(dataOra, titolo, genere, regista, anno, durataMinuti, etaMinima, prezzoBiglietto);
		System.out.println("Proiezione creata: "+ p.toString());
		return p;
	}
	   
   
  /** Inserisce una proiezione in coda al file se la data e l'ora per cui è stata creata
  non si accavallano con quelli di una già esistente, altrimenti l'operazione non viene portata a termine.
  @param p oggetto di tipo <code>Proiezione</code> da registrare su file
 * @throws IOException eccezione che si solleva se si verifica un errore durante la lettura o la scrittura del file
 * @throws Exception eccezione generica che non rientra nella classe <code>IOException</code>
 */
	public static void aggiungiProiezione(Proiezione p) throws IOException{	
		FileWriter fwt= null;
		BufferedWriter bwt = null;
		FileReader frd = null;
		BufferedReader brd = null;
		try{
			fwt = new FileWriter("data/proiezioni.csv", true);
			bwt = new BufferedWriter(fwt);
			frd = new FileReader("data/proiezioni.csv");
			brd = new BufferedReader(frd);
			
			String proiezione;
			while((proiezione = brd.readLine()) != null){
				String[] dati = proiezione.split(","); 
				if((LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).isEqual(p.getDataOra())){
					System.out.println("la proiezione inserita si accavalla con una già esistente");
					bwt.close();
					fwt.close();
					return;
				}
			}
			bwt.write(p.toString());
			bwt.newLine();
			System.out.println("La proiezione è stata aggiunta");
		}catch(Exception e){
			System.err.println("Un criterio inserito non è nel formato valido "+ e.getMessage());
		}finally{
			bwt.close();
			fwt.close();
		}
	}

	 /** Modifica una proiezione esistente se non ci sono ancora prenotazioni per questa,
	 ovvero se non sia stata effettuata nessuna prenotazione avente la medesima data e ora di quella da modificare.
 * @param orario data e ora della proiezione da modificare
 * @throws IOException eccezione che si solleva se si verifica un errore durante la fase di lettura/scrittura
 @throws Exception eccezione generica che non rientra nella classe <code>IOException</code>
 */
	public static void modificaProiezione(LocalDateTime orario) throws IOException{
		FileReader frd = null;
		BufferedReader brd = null;
		FileWriter fwt = null;
		BufferedWriter bwt = null;
		File vecchio = null;
		File temp = null;
		try{
			if(menuBigliettaio.cercaPrenotazione(orario, 0, "", "", "", 0, "").isEmpty()){
				File file = new File("data");
				temp = File.createTempFile("pro", ".csv", file);
				vecchio = new File("data/proiezioni.csv");
				
				fwt = new FileWriter(temp, true);
				bwt = new BufferedWriter(fwt);
				frd = new FileReader("data/proiezioni.csv");
				brd = new BufferedReader(frd);
				try{	
					Proiezione proOttenuta = Proiezione.getProiezione(orario);
					System.out.println("Proiezione da modificare trovata: "+proOttenuta.toString());
					System.out.println(" ");
					System.out.println("Inserire ora la nuova proiezione da salvare: ");
					Proiezione proNuova = creaProiezione();
					String linea;
					String[] dati = null;
					LocalDateTime loc = null;
					String t = null;
					String reg = null;
					String gen = null;
					Double prez = 0.0;
					while((linea = brd.readLine())!= null){
						
						dati = linea.split(",");
						loc=LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						t=dati[1].replaceAll("\"", "").trim();
						gen =dati[2].trim();
						reg = dati[3].replaceAll("\"", "").trim();
						prez=Double.parseDouble(dati[7].replace("\"", "").trim());
						Proiezione daLinea = new Proiezione(loc, t, gen, reg, Integer.parseInt(dati[4].trim()), Integer.parseInt(dati[5].trim()), Integer.parseInt(dati[6].trim()), prez);
						
						if(!(proOttenuta.getDataOra()).equals(daLinea.getDataOra())){
							bwt.write(daLinea.toString());
						}else{
							bwt.write(proNuova.toString());
						}
						bwt.newLine();
					}
				}catch(Exception e){
					System.err.println("Qualcosa non va "+e.getMessage());
				}
			}else{
				System.out.println("ci sono prenotazioni per la proiezione da modificare, pertanto non è possibile modificarla");
				return;
			}
		}catch(Exception e){
			System.err.println("Errore durante la modifica della proiezione"+ e.getMessage());
		}finally{
			try{
				brd.close();
				bwt.close();
				frd.close();
				fwt.close();
			}catch(IOException ecc){
				System.err.println("Errore nella chiusura dei flussi: " + ecc.getMessage());
			}catch(NullPointerException nullecc){
				System.err.println("un parametro è nullo: " + nullecc.getMessage());
			}	
		}
		try{
			if(vecchio.delete()){
				if(temp.renameTo(vecchio)){
					System.out.println("File aggiornato con successo!");
				}else{
					System.err.println("Errore: Impossibile rinominare il file temporaneo.");
					throw new Exception();
				}
			}else{
				System.err.println("Errore: Impossibile eliminare il file originale.");
			}
		}catch(Exception eccez){
			System.err.println("Eliminazione e rinomina falliti" + eccez.getMessage());
		}
	}

	 /** Elimina una proiezione esistente se non ci sono ancora prenotazioni per questa,
	 ovvero se non sia stata effettuata nessuna prenotazione avente la medesima data e ora di quella da eliminare.
 * @param orario data e ora della proiezione da eliminare
 * @throws IOException eccezione che si solleva se si verifica un errore durante la la gestione del file
  @throws Exception eccezione generica che non rientra nella classe <code>IOException</code>
 */
	 //elimina con gli stream
	public static void eliminaProiezione(LocalDateTime orario) throws IOException {
		FileReader frd = null;
		BufferedReader brd = null;
		FileWriter fwt = null;
		BufferedWriter bwt = null;
		File vecchio = null;
		File temp = null;
		try{
			if(menuBigliettaio.cercaPrenotazione(orario, 0, "", "", "", 0, "").isEmpty()){
				File file = new File("data");
				temp = File.createTempFile("pro", ".csv", file);
				vecchio = new File("data/proiezioni.csv");
				
				fwt = new FileWriter(temp, true);
				bwt = new BufferedWriter(fwt);
				frd = new FileReader("data/proiezioni.csv");
				brd = new BufferedReader(frd);
				try{	
					Proiezione proOttenuta = Proiezione.getProiezione(orario);
					System.out.println("Proiezione da eliminare trovata: "+proOttenuta.toString());
					String linea;
					String[] dati = null;
					LocalDateTime loc = null;
					String t = null;
					String reg = null;
					String gen = null;
					Double prez = 0.0;
					while((linea = brd.readLine())!= null){
						dati = linea.split(",");
						loc=LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						t=dati[1].replaceAll("\"", "").trim();
						gen =dati[2].trim();
						reg = dati[3].replaceAll("\"", "").trim();
						prez=Double.parseDouble(dati[7].replace("\"", "").trim());
						Proiezione daLinea = new Proiezione(loc, t, gen, reg, Integer.parseInt(dati[4].trim()), Integer.parseInt(dati[5].trim()), Integer.parseInt(dati[6].trim()), prez);
						
						if(!(proOttenuta.getDataOra()).equals(daLinea.getDataOra())){
							bwt.write(daLinea.toString());
							bwt.newLine();
						}else{
							continue;
						}
					}
				}catch(Exception e){
					System.err.println("Qualcosa non va "+e.getMessage());
				}
			}else{
				System.out.println("ci sono prenotazioni per la proiezione da modificare, pertanto non è possibile modificarla");
				return;
			}
		}catch(Exception e){
			System.err.println("Errore durante l'eliminazione della prenotazione"+ e.getMessage());
		}finally{
			try{
				brd.close();
				bwt.close();
				frd.close();
				fwt.close();
			}catch(IOException ecc){
 			System.err.println("Errore nella chiusura dei flussi: " + ecc.getMessage());
			}
		}
		try{
			if(vecchio.delete()){
				if(temp.renameTo(vecchio)){
					System.out.println("File aggiornato con successo!");
				}else{
					System.err.println("Errore: Impossibile rinominare il file temporaneo.");
					throw new Exception();
				}
			}else{
				System.err.println("Errore: Impossibile eliminare il file originale.");
			}
		}catch(Exception eccez){
			System.err.println("Eliminazione e rinomina falliti" + eccez.getMessage());
		}	
	}
}
