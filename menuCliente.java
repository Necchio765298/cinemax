package cinemax;
import java.lang.Integer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.UUID;

/** Gestisce le funzionalità riservate agli utenti con ruolo di cliente. 
 * La classe consente la creazione, la visualizzazione, la modifica e l'eliminazione delle prenotazioni.
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class menuCliente{
	
	//campi
	/** Utente che utilizza il menu cliente. */
	private Utente utente;
	/** Prenotazione gestita dal cliente. */
	private Prenotazione prenotazione;
	
	//costruttore
	/** Costruisce un nuovo oggetto menuCliente.
 * @param <utente> utente che utilizza il menu
 * @param <prenotazione> prenotazione associata al cliente
 */
	public menuCliente(Utente utente, Prenotazione prenotazione){
		this.utente = utente;
		this.prenotazione = prenotazione;
	}
	
	//metodi

	/** Visualizza le prenotazioni associate all'utente specificato.
 * @param <id> identificativo dell'utente
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static void visualizzaPrenotazione(long id) throws IOException{
		try{
			Prenotazione p = menuBigliettaio.cercaPrenotazione(id);
			String prenotazione = p.toString();
			
			System.out.println("Prenotazione trovata: "+ prenotazione);
		}catch(Exception e){
			e.getMessage();
		}
	}

	/** Crea una nuova prenotazione per la proiezione prevista nell'orario specificato verificando la disponibilità dei posti nella sala cinematografica
 * @param <orario> data e ora della proiezione
 * @return la prenotazione creata
 * @throws <IOExceptio> se si verifica un errore durante la gestione del file
 */
	public static Prenotazione creaPrenotazione(LocalDateTime orario) throws IOException{
		try{
			Console cons = System.console();
			String id = cons.readLine("inserire il proprio codice identificativo");
			long identificativo =(long) Integer.parseInt(id);
			Utente u = Utente.getUtente(identificativo);
			Proiezione p = Proiezione.getProiezione(orario);
			String biglietti = cons.readLine("Inserire il numero dei biglietti da acquistare: ");
			int numeroBiglietto = Integer.parseInt(biglietti);
			if(numeroBiglietto < 200-numeroBiglietto){ //se numero di posti richiesti è minore del numero di posti disponibili	
				Prenotazione prenotazione =  new Prenotazione(u, p, numeroBiglietto);
				Prenotazione.registraPrenotazione(prenotazione);
				System.out.println("Il codice della prenotazione è "+ prenotazione.getCodice());
				return prenotazione;
			}else{
				System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
			}
		}catch(Exception e){
			System.out.println("Un dato inserito non è valido");
		}
	}

	/** Modifica la data e l'ora di una prenotazione esistente
 * @param <dataVecchia> data e ora della proiezione originaria
 * @param <dataNuova> nuova data e ora della proiezione
 * @param <codice> codice identificativo della prenotazione
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	
	public static void modificaPrenotazione(LocalDateTime dataVecchia, LocalDateTime dataNuova, String codice) throws IOException{	
		try{
			if((dataVecchia.isAfter(LocalDateTime.now())) &&  (dataNuova.isAfter(LocalDateTime.now()))){
				File file = new File("../data");
				File temp = File.createTempFile("pre", ".csv", file);
				File vecchio = new File("../data/prenotazioni.csv");
				
					FileWriter fwt = new FileWriter(temp, true);
					BufferedWriter bwt = new BufferedWriter(fwt);
					FileReader frd = new FileReader("../data/prenotazioni.csv");
					BufferedReader brd = new BufferedReader(frd);
				try{	
					Prenotazione preVecchia = menuBigliettaio.cercaPrenotazione(codice);
					Prenotazione preNuova = menuCliente.creaPrenotazione(dataNuova);
					String linea;
					while((linea = brd.readLine())!= null){
						if(!(preVecchia.toString()).equals(linea)){
							bwt.write(linea);
						}else{
						bwt.write(preNuova.toString());
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
				System.out.println("La data vecchia o quella nuova non sono successive alla data odierna, pertanto non è possibile modificare la prenotazione");
		}catch(Exception e){
			e.getMessage();
		}	
	}
			

	/** Elimina una prenotazione dal sistema
 * @param <dataVecchia> data e ora della proiezione della prenotazione da eliminare
 * @param <codice> codice identificativo della prenotazione
 * @throws <IOException> se si verifica un errore durante la gestione del file
 */
	public static void eliminaPrenotazione(LocalDateTime dataVecchia, String codice) throws IOException{
		
			try{
		if(dataVecchia.compareTo(LocalDateTime.now())>0){
			File file = new File("../data");
			File temp = File.createTempFile("pre", ".csv", file);
			File vecchio = new File("../data/proiezioni.csv");
			
				FileWriter fwt = new FileWriter(temp, true);
				BufferedWriter bwt = new BufferedWriter(fwt);
				FileReader frd = new FileReader("../data/prenotazioni.csv");
				BufferedReader brd = new BufferedReader(frd);
			try{	
				Prenotazione preDelete = menuBigliettaio.cercaPrenotazione(codice);
				String linea;
				while((linea = brd.readLine())!= null){
					if(!(preDelete.toString()).equals(linea)){
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
			System.out.println("La data della prenotazione è antecedente la data odierna, pertanto non è stato possibile eliminarla");
		}catch(Exception e){
			e.getMessage();
		}	
	}
	
}
