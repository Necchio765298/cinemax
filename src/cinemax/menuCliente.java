//Necchio Arianna, matricola: 765298, sede: Como

package cinemax;
import java.lang.Integer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.UUID;
import java.util.ArrayList;

/** Gestisce le funzionalità riservate agli utenti con ruolo di "Cliente"
 * La classe consente la creazione, la visualizzazione, la modifica e l'eliminazione delle prenotazioni
 * @author Arianna Necchio
 * @version 2.1
 */
public class menuCliente{
	
	/** Utente che utilizza il menu-cliente. */
	private Utente utente;
	/** Prenotazione gestita dal cliente. */
	private Prenotazione prenotazione;
	
	/** Costruisce un nuovo oggetto di tipo <code>menuCliente</code>.
 * @param utente utente che utilizza il menu
 * @param prenotazione prenotazione prenotata dal cliente
 */
	public menuCliente(Utente utente, Prenotazione prenotazione){
		this.utente = utente;
		this.prenotazione = prenotazione;
	}


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

	/** Crea una nuovo oggetto di tipo <code>Prenotazione</code> per la proiezione prevista nell'orario specificato se il 
 * numero di posti richiesti è minore del numero di posti disponibili.
 * @param orario data e ora della proiezione da visionare
 * @param u utente al quale va associata la prenotazione
 * @return prenotazione prenotazione creata se l'operazione ha avuto successo. Questo valore è fornito come elemento da aggiungere alla lista di prenotazioni da visualizzare.
 * @see Prenotazione
 * @see menuBigliettaio
 * @throws IOException eccezione che si può sollevare durante la lettura/scrittura del file "Prenotazioni.csv"
 */
	public static Prenotazione creaPrenotazione(LocalDateTime orario, Utente u) throws IOException{
		try{
			Console cons = System.console();
			System.out.println("Utente: "+ u.toString());
			Proiezione p = Proiezione.getProiezione(orario);
			System.out.println("Proiezione: " + p.toString());
			System.out.println("");
			String biglietti = cons.readLine("Inserire il numero dei biglietti da acquistare: ");
			int numeroBiglietto = Integer.parseInt(biglietti);
			Prenotazione prenotazione =  new Prenotazione(u, p, numeroBiglietto);
			System.out.println("Prenotazione: "+prenotazione.toString());
			System.out.println("");
			if(numeroBiglietto < 200-Prenotazione.getTotaleBiglietti(orario)){
				System.out.println("Il codice della prenotazione è "+ prenotazione.getCodice());
				return prenotazione;
			}else{
				System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
			}
			System.out.println("");
		}catch(Exception e){
			System.out.println("Un dato inserito non è valido" + e.getMessage());
		}
		return null;
	}

	/** Modifica una prenotazione esistente nel file "Prenotazioni.csv" consentendo di modificare la proiezione da visionare
	e il numero dei biglietti da acquistare solo se sia la data della vecchia prenotazione che quella per cui si vuole prenotare
	siano successive alla data odierna.
 * @param dataVecchia data e ora della proiezione da modificare
 * @param dataNuova nuova data e ora della proiezione da salvare
 * @param u utente che ha prenotato
 * @throws IOException eccezione che si solleva se si verifica un errore durante la lettura/scrittura del file "Prenotazioni.csv"
 */
	
	public static void modificaPrenotazione(LocalDateTime dataVecchia, LocalDateTime dataNuova, Utente u) throws IOException{	
		Console cons = System.console();
		FileReader frd = null;
		BufferedReader brd = null;
		FileWriter fwt = null;
		BufferedWriter bwt = null;
		File vecchio = null;
		File temp = null;
		try{
			if((dataVecchia.isAfter(LocalDateTime.now())) &&  (dataNuova.isAfter(LocalDateTime.now()))){
				File file = new File("data");
				temp = File.createTempFile("pre", ".csv", file);
				vecchio = new File("data/prenotazioni.csv");
				
					fwt = new FileWriter(temp);
					bwt = new BufferedWriter(fwt);
					frd = new FileReader("data/prenotazioni.csv");
					brd = new BufferedReader(frd);
				try{	
					ArrayList<Prenotazione> preVecchia = menuBigliettaio.cercaPrenotazione(dataVecchia, 0, "", "", "", 0, "");
					Prenotazione preOttenuta= preVecchia.get(0);
					System.out.println(" ");
					Prenotazione preNuova = menuCliente.creaPrenotazione(dataNuova, u);
					
					String linea;
					String[] dati = null;
					while((linea = brd.readLine())!= null){
						
						dati = linea.split(",");
						String codice = dati[6];
						int numBiglietti = Integer.parseInt(dati[5]);
						Utente utente = Utente.getUtente(Long.parseLong(dati[1]));
						Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
						Prenotazione daLinea = new Prenotazione(codice, utente, p, numBiglietti); //tutte le prenotazioni del file
						
						if(!(preOttenuta.getCodice()).equals(daLinea.getCodice())){
							bwt.write(daLinea.toString());
						}else{
							bwt.write(preNuova.toString());
						}
						bwt.newLine();
					}
				}catch(Exception e){
					System.err.println("Qualcosa non va "+e.getMessage());
				}
			}else{
				System.out.println("La data vecchia o quella nuova non sono successive alla data odierna, pertanto non è possibile modificare la prenotazione");
				return;
			}
		}catch(Exception e){
			System.err.println("Errore durante la modifica della prenotazione"+ e.getMessage());
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
	
			

	/** Elimina una prenotazione esistente dal file "Prenotazioni.csv" solo se la data della vecchia prenotazione sia successiva alla data odierna.
 * @param dataVecchia data e ora della proiezione da eliminare
 * @param codice codice della prenotazione da eliminare
 * @throws IOException eccezione che si solleva se si verifica un errore durante la lettura/scrittura del file "Prenotazioni.csv"
 */
	public static void eliminaPrenotazione(LocalDateTime dataVecchia, String codice) throws IOException{
		Console cons = System.console();
		FileReader frd = null;
		BufferedReader brd = null;
		FileWriter fwt = null;
		BufferedWriter bwt = null;
		File vecchio = null;
		File temp = null;
		try{
			if(dataVecchia.compareTo(LocalDateTime.now())>0){
				File file = new File("data");
				temp = File.createTempFile("pre", ".csv", file);
				vecchio = new File("data/prenotazioni.csv");
				
				fwt = new FileWriter(temp, true);
				bwt = new BufferedWriter(fwt);
				frd = new FileReader("data/prenotazioni.csv");
				brd = new BufferedReader(frd);
				try{
					LocalDateTime dataFinta = LocalDateTime.parse("2000-01-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					ArrayList<Prenotazione> preDelete = menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", "", 0, codice);
					Prenotazione preOttenuta= preDelete.get(0);
					System.out.println("Prenotazione da cancellare: "+preOttenuta.toString());
					String linea;
					String[] dati = null;
					while((linea = brd.readLine())!= null){
						dati = linea.split(",");
						Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
						Utente u = Utente.getUtente(Long.parseLong(dati[1]));
						Prenotazione preAltra = new Prenotazione(dati[6], u, p, Integer.parseInt(dati[5]));
						
						
						if(!(preOttenuta.getCodice().equals(preAltra.getCodice()))){
							bwt.write(preAltra.toString());
							bwt.newLine();
						}else{
							continue;
						}
					}
				}catch(Exception e){
					System.err.println("Qualcosa non va "+e.getMessage());
				}
			}else{
				System.out.println("La data della prenotazione è antecedente la data odierna, pertanto non è stato possibile eliminarla");
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
	
}
